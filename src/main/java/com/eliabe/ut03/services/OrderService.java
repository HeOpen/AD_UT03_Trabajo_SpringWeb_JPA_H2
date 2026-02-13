package com.eliabe.ut03.services;

import com.eliabe.ut03.entities.*;
import com.eliabe.ut03.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Orders createOrderFromCart(Integer customerId) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("No existe el cliente con el código " + customerId));

        List<CartItems> cartItems = cartItemRepository.findByCustomer_CustomerIdOrderByProduct_NameAsc(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderTotal(0.0);

        final Orders savedOrder = orderRepository.save(order);
        Double total = 0.0;

        for (CartItems cartItem : cartItems) {
            Products product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }

            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            orderItemRepository.save(orderItem);
            total += (product.getPrice() * cartItem.getQuantity());
        }

        savedOrder.setOrderTotal(total);

        cartItemRepository.deleteByCustomer_CustomerId(customerId);

        return orderRepository.save(savedOrder);
    }
    @Autowired
    private ShipmentRepository shipmentRepository; // Necesitarás crear este repo también

    @Transactional
    public Shipments sendOrder(Integer orderId, ShipmentDTO shipmentDTO) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No existe el pedido " + orderId));

        if (shipmentRepository.existsByOrder_OrderId(orderId)) {
            throw new RuntimeException("El pedido " + orderId + " ya había sido enviado");
        }

        Shipments shipment = new Shipments();
        shipment.setOrder(order);
        shipment.setShipmentDate(LocalDateTime.now()); // Requisito 121
        shipment.setAddress(shipmentDTO.getAddress());
        shipment.setCity(shipmentDTO.getCity());
        shipment.setZipCode(shipmentDTO.getZipCode());
        shipment.setCountry(shipmentDTO.getCountry());
        shipment.setState(shipmentDTO.getState());

        return shipmentRepository.save(shipment); // Requisito 277
    }
}
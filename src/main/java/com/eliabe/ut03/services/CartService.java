package com.eliabe.ut03.services;

import com.eliabe.ut03.dto.CartDTO;
import com.eliabe.ut03.dto.CartItemDTO;
import com.eliabe.ut03.entities.CartItems;
import com.eliabe.ut03.entities.Customers;
import com.eliabe.ut03.entities.Products;
import com.eliabe.ut03.repositories.CartItemRepository;
import com.eliabe.ut03.repositories.CustomerRepository;
import com.eliabe.ut03.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProductToCart(Integer customerId, Integer productId, Integer quantity) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado.  " + customerId));

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No existe el producto"));

        Optional<CartItems> existingItem = cartItemRepository.findByCustomer_CustomerIdAndProduct_ProductId(customerId, productId);

        if (existingItem.isPresent()) {
            CartItems item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItems newItem = new CartItems();
            newItem.setCustomer(customer);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }

    public List<CartItems> getCartByCustomer(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("No existe el cliente con el código " + customerId);
        }
        return cartItemRepository.findByCustomer_CustomerIdOrderByProduct_NameAsc(customerId);
    }

    public CartDTO getCartDTO(Integer customerId) {
        List<CartItems> entities = cartItemRepository.findByCustomer_CustomerIdOrderByProduct_NameAsc(customerId);

        CartDTO cartDTO = new CartDTO();
        Double total = 0.0;

        List<CartItemDTO> itemsDTO = entities.stream().map(entity -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setProductName(entity.getProduct().getName());
            itemDTO.setQuantity(entity.getQuantity());
            itemDTO.setUnitPrice(entity.getProduct().getPrice());

            Double subtotal = entity.getQuantity() * entity.getProduct().getPrice();
            itemDTO.setSubtotal(subtotal);

            return itemDTO;
        }).toList();

        total = itemsDTO.stream().mapToDouble(CartItemDTO::getSubtotal).sum();

        cartDTO.setItems(itemsDTO);
        cartDTO.setTotalAmount(total);

        return cartDTO;
    }

    @Transactional
    public void clearCart(Integer customerId) {
        cartItemRepository.deleteByCustomer_CustomerId(customerId);
    }
}
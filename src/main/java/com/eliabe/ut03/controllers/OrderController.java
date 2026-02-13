package com.eliabe.ut03.controllers;

import com.eliabe.ut03.dto.ShipmentDTO;
import com.eliabe.ut03.olaheliabe.entities.Orders;
import com.eliabe.ut03.olaheliabe.entities.Shipments;
import com.eliabe.ut03.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<Orders> createOrder(@PathVariable Integer customerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrderFromCart(customerId));
    }

    //fixme No entiendo pq el shipmentDTO me da problemas. No tiene sentido
    @PostMapping("/send/{orderId}")
    public ResponseEntity<Shipments> sendOrder(@PathVariable Integer orderId, @RequestBody ShipmentDTO shipmentDTO) {
        return ResponseEntity.ok(orderService.sendOrder(orderId, shipmentDTO));
    }
}
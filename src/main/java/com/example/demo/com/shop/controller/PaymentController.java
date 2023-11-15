package com.example.demo.com.shop.controller;

import com.example.demo.com.shop.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    ServiceImpl service;

    @GetMapping("/payment/{location}/{amount}")
    public ResponseEntity pay(@PathVariable String location, @PathVariable BigDecimal amount){
        service.makePay(location, amount , 1l);
        return ResponseEntity.ok("successfull");
    }
}

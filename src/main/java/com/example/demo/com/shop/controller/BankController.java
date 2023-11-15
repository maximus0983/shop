package com.example.demo.com.shop.controller;

import com.example.demo.com.shop.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class BankController {
    @Autowired
    ServiceImpl service;

    @GetMapping("/bankAccountOfEMoney")
    public BigDecimal getBonus(){
        return service.getBonus(1l);
    }

    @GetMapping("/money")
    public BigDecimal getMoney(){
        return service.getMoney(1l);
    }
}

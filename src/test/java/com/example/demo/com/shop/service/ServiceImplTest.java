package com.example.demo.com.shop.service;

import com.example.demo.com.shop.entity.BankAccount;
import com.example.demo.com.shop.repo.BankAccountRepo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceImplTest {
    @Autowired
    private Service service;
    @Autowired
    private BankAccountRepo repo;

    @BeforeEach
    void setUp() {
        BankAccount account = new BankAccount(1,BigDecimal.ZERO, BigDecimal.valueOf(5000));
        repo.save(account);
    }

    @AfterEach
    void tearDown() {
        BankAccount bankAccount = repo.findById(1l).get();
        repo.delete(bankAccount);
    }

    @Test
    void makePayCheckIntemediateAmountForShop() {
        service.makePay("shop", BigDecimal.valueOf(100), 1l);
        BankAccount bankAccount = repo.findById(1l).get();
        assertEquals(BigDecimal.valueOf(4900), bankAccount.getEMoney());
        assertEquals(BigDecimal.valueOf(10), bankAccount.getBonus());
    }
    @Test
    void makePayCheckLowAmountForShop() {
        service.makePay("shop", BigDecimal.valueOf(10), 1l);
        BankAccount bankAccount = repo.findById(1l).get();
        assertEquals(BigDecimal.valueOf(4989), bankAccount.getEMoney());
        assertEquals(BigDecimal.valueOf(0), bankAccount.getBonus());
    }

    @Test
    void makePayCheckLowAmountForOnline() {
        service.makePay("online", BigDecimal.valueOf(10), 1l);
        BankAccount bankAccount = repo.findById(1l).get();
        assertEquals(BigDecimal.valueOf(4989), bankAccount.getEMoney());
        assertEquals(BigDecimal.valueOf(0), bankAccount.getBonus());
    }

    @Test
    void makePayCheckIntermediateAmountForOnline() {
        service.makePay("online", BigDecimal.valueOf(100), 1l);
        BankAccount bankAccount = repo.findById(1l).get();
        assertEquals(BigDecimal.valueOf(4900), bankAccount.getEMoney());
        assertEquals(BigDecimal.valueOf(17), bankAccount.getBonus());
    }

    @Test
    void makePayCheckHighAmount() {
        service.makePay("online", BigDecimal.valueOf(1000), 1l);
        BankAccount bankAccount = repo.findById(1l).get();
        assertEquals(BigDecimal.valueOf(4000), bankAccount.getEMoney());
        assertEquals(BigDecimal.valueOf(300), bankAccount.getBonus());
    }
}
package com.example.demo.com.shop.service;

import java.math.BigDecimal;

public interface Service {
    void makePay(String location, BigDecimal amount, long bankAccountId);

    BigDecimal getBonus(long accountId);

    BigDecimal getMoney(long accountId);
}

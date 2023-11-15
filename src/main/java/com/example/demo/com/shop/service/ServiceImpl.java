package com.example.demo.com.shop.service;

import com.example.demo.com.shop.entity.BankAccount;
import com.example.demo.com.shop.repo.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private static final int SHOP_PERCENT_BONUS = 10;
    private static final int SPECIAL_PERCENT_BONUS = 30;
    private static final int ONLINE_PERCENT_BONUS = 17;
    private static final int BANK_PERCENT_COMMISSION = 10;
    private static final String BANK_ACCOUNT_NOT_FOUND = "Bank account not found";
    @Autowired
    BankAccountRepo repo;

    @Override
    public void makePay(String location, BigDecimal amount, long bankAccountId) {
        BankAccount bankAccount = repo.findById(bankAccountId).orElseThrow(() -> new RuntimeException(BANK_ACCOUNT_NOT_FOUND));
        BigDecimal dbEMoney = bankAccount.getEMoney();
        BigDecimal dbBonus = bankAccount.getBonus();
        BigDecimal newAmount = dbEMoney.subtract(amount);
        BigDecimal bonus;

        if (amount.compareTo(BigDecimal.valueOf(20)) < 0) {
            BigDecimal commission = calcPercentage(amount, BANK_PERCENT_COMMISSION);
            bankAccount.setEMoney(newAmount.subtract(commission));
        } else if (amount.compareTo(BigDecimal.valueOf(300)) >= 0) {
            bonus = calcPercentage(amount, SPECIAL_PERCENT_BONUS);
            bankAccount.setEMoney(newAmount);
            bankAccount.setBonus(dbBonus.add(bonus));
        } else {
            if (location.equals("shop")) {
                bonus = calcPercentage(amount, SHOP_PERCENT_BONUS);
            } else {
                bonus = calcPercentage(amount, ONLINE_PERCENT_BONUS);
            }
            bankAccount.setEMoney(newAmount);
            bankAccount.setBonus(dbBonus.add(bonus));
        }
        repo.save(bankAccount);
    }

    private BigDecimal calcPercentage(BigDecimal amount, int percent) {
        return amount.divide(BigDecimal.valueOf(100),4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(percent));
    }

    @Override
    public BigDecimal getBonus(long accountId) {
        BankAccount bankAccount = repo.findById(accountId).orElseThrow(() -> new RuntimeException(BANK_ACCOUNT_NOT_FOUND));
        return bankAccount.getBonus();
    }

    @Override
    public BigDecimal getMoney(long accountId) {
        BankAccount bankAccount = repo.findById(accountId).orElseThrow(() -> new RuntimeException(BANK_ACCOUNT_NOT_FOUND));
        return bankAccount.getEMoney();
    }
}

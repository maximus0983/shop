package com.example.demo.com.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table
@NoArgsConstructor
public class BankAccount {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private BigDecimal bonus;
    private BigDecimal eMoney;

    public BankAccount(BigDecimal bonus, BigDecimal eMoney) {
        this.bonus = bonus;
        this.eMoney = eMoney;
    }

    public BankAccount(long id, BigDecimal bonus, BigDecimal eMoney) {
        this.id = id;
        this.bonus = bonus;
        this.eMoney = eMoney;
    }
}

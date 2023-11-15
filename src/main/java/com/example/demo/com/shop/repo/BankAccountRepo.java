package com.example.demo.com.shop.repo;

import com.example.demo.com.shop.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}

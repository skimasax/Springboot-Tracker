package com.springboot_crud.Repository;

import com.springboot_crud.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

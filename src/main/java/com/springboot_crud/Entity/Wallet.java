package com.springboot_crud.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "wallet_number")
    private Integer walletNumber;

    @Column(name="wallet_credit")
    private BigDecimal walletCredit;

    @Column(name="wallet_debit")
    private BigDecimal walletDebit;

    @Column(name="total")
    private BigDecimal total;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Wallet(Long id, User user, Integer walletNumber, BigDecimal walletCredit, BigDecimal walletDebit, BigDecimal total) {
        this.id = id;
        this.user = user;
        this.walletNumber = walletNumber;
        this.walletCredit = walletCredit;
        this.walletDebit = walletDebit;
        this.total = total;
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(Integer walletNumber) {
        this.walletNumber = walletNumber;
    }

    public BigDecimal getWalletCredit() {
        return walletCredit;
    }

    public void setWalletCredit(BigDecimal walletCredit) {
        this.walletCredit = walletCredit;
    }

    public BigDecimal getWalletDebit() {
        return walletDebit;
    }

    public void setWalletDebit(BigDecimal walletDebit) {
        this.walletDebit = walletDebit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

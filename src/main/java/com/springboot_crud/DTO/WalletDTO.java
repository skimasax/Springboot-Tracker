package com.springboot_crud.DTO;

import java.math.BigDecimal;

public class WalletDTO {
    private Long id;
    private BigDecimal walletDebit;
    private BigDecimal walletCredit;
    private BigDecimal total;
    private Integer walletNumber;

    public WalletDTO(Long id, BigDecimal walletDebit, BigDecimal walletCredit, BigDecimal total, Integer walletNumber) {
        this.id = id;
        this.walletDebit = walletDebit;
        this.walletCredit = walletCredit;
        this.total = total;
        this.walletNumber = walletNumber;
    }

    public WalletDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWalletDebit() {
        return walletDebit;
    }

    public void setWalletDebit(BigDecimal walletDebit) {
        this.walletDebit = walletDebit;
    }

    public BigDecimal getWalletCredit() {
        return walletCredit;
    }

    public void setWalletCredit(BigDecimal walletCredit) {
        this.walletCredit = walletCredit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(Integer walletNumber) {
        this.walletNumber = walletNumber;
    }
}

package com.springboot_crud.Service.impl;

import com.springboot_crud.Model.Wallet;
import com.springboot_crud.Repository.WalletRepository;
import com.springboot_crud.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;


    @Override
    public Wallet createWallet(Wallet wallet)
    {
       return walletRepository.save(wallet);
    }
}

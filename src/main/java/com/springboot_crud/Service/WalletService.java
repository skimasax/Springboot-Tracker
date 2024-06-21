package com.springboot_crud.Service;

import com.springboot_crud.Model.Wallet;
import com.springboot_crud.Repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
            public WalletService(WalletRepository walletRepository)
            {
                this.walletRepository=walletRepository;
            }

            public Wallet createWallet(Wallet wallet)
            {
                return this.walletRepository.save(wallet);
            }




}

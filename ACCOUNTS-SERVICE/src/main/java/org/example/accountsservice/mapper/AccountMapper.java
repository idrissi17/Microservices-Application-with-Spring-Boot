package org.example.accountsservice.mapper;

import org.example.accountsservice.dao.entities.Accounts;
import org.example.accountsservice.dto.AccountsDto;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public AccountsDto fromAccountsToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }


    public Accounts fromAccountsDtoToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}

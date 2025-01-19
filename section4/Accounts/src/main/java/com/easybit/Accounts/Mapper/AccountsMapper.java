package com.easybit.Accounts.Mapper;

import com.easybit.Accounts.Dto.AccountsDto;
import com.easybit.Accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto marToAccountsDto(Accounts accounts, AccountsDto accountsDto) {

        accountsDto.setAccountNumber(accounts.getAccountNumber());

        accountsDto.setAccountType(accounts.getAccountType());

        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;

    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());

        accounts.setBranchAddress(accountsDto.getBranchAddress());

        return accounts;

    }
}

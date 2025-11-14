package org.sumeet.account.mapper;

import org.sumeet.account.dto.AccountDto;
import org.sumeet.account.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account accounts, AccountDto accountDto) {
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountsDto, Account account) {
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());
        return account;
    }

}

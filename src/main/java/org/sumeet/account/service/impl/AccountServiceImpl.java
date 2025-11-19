package org.sumeet.account.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.sumeet.account.constants.AccountConstants;
import org.sumeet.account.dto.AccountDto;
import org.sumeet.account.dto.CustomerDto;
import org.sumeet.account.entity.Account;
import org.sumeet.account.entity.Customer;
import org.sumeet.account.exception.CustomerAlreadyExistsException;
import org.sumeet.account.exception.ResourceNotFoundException;
import org.sumeet.account.mapper.AccountMapper;
import org.sumeet.account.mapper.CustomerMapper;
import org.sumeet.account.repository.AccountRepository;
import org.sumeet.account.repository.CustomerRepository;
import org.sumeet.account.service.IAccountService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerByMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(customerByMobileNumber.isPresent())
            throw new CustomerAlreadyExistsException("Customer with the given mobile number already exists " + customerDto.getMobileNumber());

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
                );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    private Account createNewAccount(Customer savedCustomer) {
        Account account = new Account();
        account.setCustomerId(savedCustomer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        return account;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto){
        boolean isUpdated = false;

        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto!=null){
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
                    );
            AccountMapper.mapToAccount(accountDto,account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
                    );

            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}

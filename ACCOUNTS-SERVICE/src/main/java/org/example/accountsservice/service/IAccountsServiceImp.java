package org.example.accountsservice.service;

import jakarta.transaction.Transactional;
import org.example.accountsservice.service.IAccountsService;
import org.example.accountsservice.dao.entities.Accounts;
import org.example.accountsservice.dao.entities.Customer;
import org.example.accountsservice.dao.repository.AccountsRepository;
import org.example.accountsservice.dao.repository.CustomerRepository;
import org.example.accountsservice.dto.AccountsDto;
import org.example.accountsservice.dto.CustomerDto;
import org.example.accountsservice.exception.CustomerAlreadyExistsException;
import org.example.accountsservice.exception.ResourceNotFoundException;
import org.example.accountsservice.mapper.AccountMapper;
import org.example.accountsservice.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@Transactional
public class IAccountsServiceImp implements IAccountsService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;

    public IAccountsServiceImp(AccountsRepository accountsRepository, CustomerRepository customerRepository, CustomerMapper customerMapper, org.example.accountsservice.mapper.AccountMapper accountMapper) {
        this.customerMapper = customerMapper;
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = customerMapper.fromCustomerDtoToCustomer(customerDto, new Customer());
        if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with phone number : " + customerDto.getPhoneNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }


    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        int accountNumber = 1000000000 + new Random().nextInt(900000000);
        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType("Savings");
        accounts.setBranchAddress("123 Main Street, San Francisco, USA");
        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number", phoneNumber)
                );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString()));

        CustomerDto customerDto = customerMapper.fromCustomerToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(accountMapper.fromAccountsToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public Boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", accountsDto.getAccountNumber().toString()));
            accountMapper.fromAccountsDtoToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Integer customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            customerMapper.fromCustomerDtoToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;


    }

    @Override
    public Boolean deleteAccount(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customerId);
        return true;
    }

}

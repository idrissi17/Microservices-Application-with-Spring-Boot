package org.example.accountsservice.service;

import org.example.accountsservice.dto.CustomerDto;
import org.springframework.stereotype.Service;



public interface IAccountsService {

    public void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccount(String phoneNumber);

    public Boolean updateAccount(CustomerDto customerDto);

    public Boolean deleteAccount(Integer customerId);
}

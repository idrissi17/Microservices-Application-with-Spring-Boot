package org.example.accountsservice.mapper;


import org.example.accountsservice.dao.entities.Customer;
import org.example.accountsservice.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto fromCustomerToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setFullName(customer.getFullName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    public Customer fromCustomerDtoToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setFullName(customerDto.getFullName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customer;
    }


}

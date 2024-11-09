package org.example.accountsservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "full name should not be empty")
    @Size(min = 5, max = 30, message = "full name should be between 5 and 30 characters")
    private String fullName;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @NotEmpty(message = "address should not be empty")
    @Size(min = 5, max = 100, message = "address should be between 5 and 100 characters")
    private String address;

    @Pattern(regexp = "(^[0-9]{10}$)", message = "phone number should be 10 digits")
    private String phoneNumber;
    private AccountsDto accountsDto;



}

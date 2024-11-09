package org.example.accountsservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class AccountsDto {


    @NotEmpty(message = "account number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "account number should be 10 digits")
    private Integer accountNumber;

    @NotEmpty(message = "account type should not be empty")
    private String accountType;

    @NotEmpty(message = "branch address should not be empty")
    private String branchAddress;
}

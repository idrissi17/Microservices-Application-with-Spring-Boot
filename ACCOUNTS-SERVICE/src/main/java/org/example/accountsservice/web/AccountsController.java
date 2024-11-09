package org.example.accountsservice.web;


import jakarta.validation.Valid;
import org.example.accountsservice.dto.CustomerDto;
import org.example.accountsservice.dto.ResponseDto;
import org.example.accountsservice.service.IAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final IAccountsService accountsService;

    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("Account created successfully", HttpStatus.CREATED.toString()));
    }

    @GetMapping("/fetch/{phoneNumber}")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@PathVariable String phoneNumber) {
        CustomerDto customerDto = accountsService.fetchAccount(phoneNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Account updated successfully", HttpStatus.OK.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("Failed to update account", HttpStatus.INTERNAL_SERVER_ERROR.toString()));
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable int customerId) {
        boolean isDeleted = accountsService.deleteAccount(customerId);
        System.out.println(isDeleted);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Account deleted successfully", HttpStatus.OK.toString()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("Failed to delete account", HttpStatus.INTERNAL_SERVER_ERROR.toString()));
        }
    }


}

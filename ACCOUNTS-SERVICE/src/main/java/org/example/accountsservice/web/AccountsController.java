package org.example.accountsservice.web;


import jakarta.validation.Valid;
import org.example.accountsservice.dto.AccountsContactInfoDto;
import org.example.accountsservice.dto.CustomerDto;
import org.example.accountsservice.dto.ResponseDto;
import org.example.accountsservice.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final IAccountsService accountsService;
    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;


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
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
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

//    @GetMapping("/build-info")
//    public ResponseEntity<String> getBuildVersion() {
//        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
//    }

//    @GetMapping("/java-version")
//    public ResponseEntity<String> getJavaVersion() {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(environment.getProperty("MAVEN_HOME"));
//    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto>getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsContactInfoDto);

    }



}

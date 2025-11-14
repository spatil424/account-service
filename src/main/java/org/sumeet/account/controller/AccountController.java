package org.sumeet.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sumeet.account.constants.AccountConstants;
import org.sumeet.account.dto.CustomerDto;
import org.sumeet.account.dto.ResponseDto;
import org.sumeet.account.entity.Account;
import org.sumeet.account.service.IAccountService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private IAccountService iAccountService;

    @GetMapping("sayHello")
    public String greet(){
        return "Hello World Guys!!";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

}

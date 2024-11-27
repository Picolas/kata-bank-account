package com.nicolaspiplard.katabank.infrastructure.adapter.input.rest;

import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.request.AccountWithdrawRequestDto;
import com.nicolaspiplard.katabank.application.port.input.AccountService;
import com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.response.AccountWithdrawResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountWithdrawResponse> withdraw(
            @PathVariable Long id,
            @Valid @RequestBody AccountWithdrawRequestDto request) {
        Account account = accountService.withdraw(id, request.getAmount());
        AccountWithdrawResponse response = new AccountWithdrawResponse(account.getId(), account.getBalance());
        return ResponseEntity.ok(response);
    }
}

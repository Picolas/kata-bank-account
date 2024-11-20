package com.nicolaspiplard.katabank.infrastructure.adapter.input.rest;

import com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.request.AccountWithdrawRequestDto;
import com.nicolaspiplard.katabank.application.port.input.AccountService;
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
    public ResponseEntity<String> withdraw(
            @PathVariable Long id,
            @Valid @RequestBody AccountWithdrawRequestDto request) {
        accountService.withdraw(id, request.getAmount());
        return ResponseEntity.ok("Le compte a été débité avec succès.");
    }
}

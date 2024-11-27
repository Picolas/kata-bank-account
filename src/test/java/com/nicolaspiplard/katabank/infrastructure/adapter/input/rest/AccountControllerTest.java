package com.nicolaspiplard.katabank.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolaspiplard.katabank.application.port.input.AccountService;
import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.request.AccountWithdrawRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void withdraw_ShouldReturnUpdatedAccount() throws Exception {
        Account account = new Account(1L, BigDecimal.valueOf(50));
        AccountWithdrawRequestDto request = new AccountWithdrawRequestDto(BigDecimal.valueOf(50));

        when(accountService.withdraw(1L, BigDecimal.valueOf(50))).thenReturn(account);

        mockMvc.perform(post("/accounts/1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(50));

        verify(accountService).withdraw(1L, BigDecimal.valueOf(50));
    }

    @Test
    void withdraw_WithInvalidAmount_ShouldReturnBadRequest() throws Exception {
        AccountWithdrawRequestDto request = new AccountWithdrawRequestDto(BigDecimal.valueOf(-10));

        mockMvc.perform(post("/accounts/1/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
package br.com.payment.application.inout.input;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record PaymentInput(

        @NotNull(message = "order must be provided")
        Long order,

        @NotNull(message = "amount must be provided")
        BigDecimal amount,

        @NotNull(message = "client must be provided")
        String client
){}

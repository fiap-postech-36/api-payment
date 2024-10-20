package br.com.payment.application.inout.input;

import jakarta.validation.constraints.NotNull;


public record PaymentInput(

        @NotNull(message = "order must be provided")

        Long order
){}

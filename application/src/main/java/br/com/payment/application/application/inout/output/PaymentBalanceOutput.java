package br.com.payment.application.inout.output;

import br.com.payment.domain.core.domain.entities.StatusPayment;

import java.math.BigDecimal;

public record PaymentBalanceOutput(

        Long id,

        BigDecimal amount,

        String client,

        StatusPayment status){}

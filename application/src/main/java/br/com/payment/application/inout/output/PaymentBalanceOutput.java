package br.com.payment.application.inout.output;

import br.com.payment.domain.core.domain.entities.internal.StatusPayment;

import java.math.BigDecimal;

public record PaymentBalanceOutput(

        String id,

        BigDecimal amount,

        String client,

        StatusPayment status){}

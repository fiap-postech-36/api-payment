package br.com.payment.application.inout.output;

import br.com.payment.domain.core.domain.entities.StatusPayment;

import java.math.BigDecimal;

public record PaymentOutput(

        Long id,

        BigDecimal amount,

        String client,

        String qrCode,

        StatusPayment status){}

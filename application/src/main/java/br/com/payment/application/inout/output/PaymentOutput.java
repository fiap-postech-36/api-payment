package br.com.payment.application.inout.output;

import br.com.payment.domain.core.domain.entities.internal.StatusPayment;

import java.math.BigDecimal;

public record PaymentOutput(

        String id,

        BigDecimal amount,

        String cpf,

        String qrCode,

        StatusPayment status){}

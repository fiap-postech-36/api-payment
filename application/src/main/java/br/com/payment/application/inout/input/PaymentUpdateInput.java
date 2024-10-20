package br.com.payment.application.inout.input;


public record PaymentUpdateInput(

       //TODO verificar id que passamos para o mercadopago para recuperar aqui e alterar o status da order
        Long order,
        Long idPayment

){}

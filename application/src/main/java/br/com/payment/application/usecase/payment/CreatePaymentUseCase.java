package br.com.payment.application.usecase.payment;

import br.com.payment.application.gateway.IntegrationLinkPaymentGateway;
import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.inout.mapper.PaymentInputOutputMapper;
import br.com.payment.application.usecase.UseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CreatePaymentUseCase implements UseCase<PaymentInput, Payment> {

    private final IntegrationLinkPaymentGateway integrationLinkPaymentGateway;

    private final PaymentGateway paymentGateway;

    @Override
    public Optional<Payment> execute(final PaymentInput paymentInput) {

        Optional<Payment> paymentProcess = Optional.of(PaymentInputOutputMapper.INSTANCE.paymentRequestToPayment(paymentInput));

        paymentProcess.ifPresent(pay -> {
            QrCode qrCode = integrationLinkPaymentGateway.generatedQrCode(
                PaymentRequest.builder()
                    .description("order payment")
                    .paymentMethodId("pix")
                    .transactionAmount(paymentProcess.get().getAmount()).build());
            paymentProcess.get().setQrCode(qrCode.getQrCode());
        });

        return paymentGateway.save(paymentProcess.get());
    }
}

package br.com.payment.application.facade;

import br.com.payment.application.exception.NoResourceFoundException;
import br.com.payment.application.infra.RabbitMQConfig;
import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.application.inout.mapper.PaymentInputOutputMapper;
import br.com.payment.application.inout.output.PaymentBalanceOutput;
import br.com.payment.application.inout.output.PaymentOutput;
import br.com.payment.application.usecase.payment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final FilterPaymentUseCase filterPaymentUseCase;
    private final GetByIdPaymentUseCase getByIdPaymentUseCase;
    private final UpdateProcessPaymentCase updateProcessPaymentCase;

    public PaymentOutput create(final PaymentInput paymentInput) {
        final var customerOutPut = createPaymentUseCase.execute(paymentInput);
        return PaymentInputOutputMapper.INSTANCE.paymentToPaymentResponse(customerOutPut.orElse(null));
    }

    public PaymentOutput updateProcessPayment(final PaymentUpdateInput paymentUpdateInput) {
        final var paymentOut = this.updateProcessPaymentCase.execute(paymentUpdateInput);
        return PaymentInputOutputMapper.INSTANCE.paymentToPaymentResponse(paymentOut.orElse(null));
    }

    public Page<PaymentBalanceOutput> filter(final FilterInput filterInput) {
        final var page = filterPaymentUseCase.execute(filterInput).orElse(Page.empty());
        final var content = page.getContent().stream()
            .map(PaymentInputOutputMapper.INSTANCE::paymentToPaymentBalanceOutput)
            .toList();

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public PaymentBalanceOutput getPayment(final String id) {
        return PaymentInputOutputMapper.INSTANCE.paymentToPaymentBalanceOutput(getByIdPaymentUseCase.execute(id)
            .orElseThrow(NoResourceFoundException::new));
    }
}

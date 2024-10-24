package br.com.payment.infra.gateways;

import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.infra.mapper.PaymentMapper;
import br.com.payment.infra.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper mapper = PaymentMapper.INSTANCE;

    @Override
    public Optional<Payment> save(final Payment payment) {
        final var paymentEntity = mapper.paymentToPaymentEntity(payment);
        return Optional.ofNullable(mapper.paymentEntityToPayment(paymentRepository.save(paymentEntity)));
    }

    @Override
    public Optional<Payment> update(final Payment newPayment) {
        return save(newPayment);
    }

    @Override
    public Optional<Payment> findById(final String id) {
        return Optional.ofNullable(mapper.paymentEntityToPayment(paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"))));
    }

    @Override
    public Collection<Payment> findAll() {
        return paymentRepository.findAll().stream()
            .map(mapper::paymentEntityToPayment)
            .toList();
    }

}

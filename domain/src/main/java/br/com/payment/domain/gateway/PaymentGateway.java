package br.com.payment.domain.gateway;


import br.com.payment.domain.core.domain.entities.Payment;

import java.util.Collection;
import java.util.Optional;

public interface PaymentGateway {

    Optional<Payment> save(final Payment payment);
    Optional<Payment> update(final Payment payment);
    Optional<Payment> findById(final Long id);
    Collection<Payment> findAll();

}

package br.com.payment.infra.mapper;

import br.com.payment.infra.entity.PaymentEntity;
import br.com.payment.domain.core.domain.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment paymentEntityToPayment(final PaymentEntity paymentEntity);

    PaymentEntity paymentToPaymentEntity(final Payment payment);

}

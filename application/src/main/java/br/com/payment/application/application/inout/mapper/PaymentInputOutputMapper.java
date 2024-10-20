package br.com.payment.application.inout.mapper;

import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.inout.output.PaymentBalanceOutput;
import br.com.payment.application.inout.output.PaymentOutput;
import br.com.payment.domain.core.domain.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentInputOutputMapper {
    PaymentInputOutputMapper INSTANCE = Mappers.getMapper(PaymentInputOutputMapper.class);

    @Mapping(source = "order", target = "order")
    Payment paymentRequestToPayment(final PaymentInput paymentInput);

    PaymentOutput paymentToPaymentResponse(final Payment payment);

    PaymentBalanceOutput paymentToPaymentBalanceOutput(final Payment payment);

}

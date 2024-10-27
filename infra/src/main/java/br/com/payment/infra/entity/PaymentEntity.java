package br.com.payment.infra.entity;

import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private String id;

    private BigDecimal amount;

    private String cpf;

    private String qrCode;

    private StatusPayment status;

    @Indexed(unique = true)
    private String order;

    private LocalDateTime paymentAt;

}

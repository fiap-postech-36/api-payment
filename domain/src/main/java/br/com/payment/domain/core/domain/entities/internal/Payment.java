package br.com.payment.domain.core.domain.entities.internal;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Payment  implements Serializable {

    private Long id;
    private BigDecimal amount;
    private String client;
    private StatusPayment status;
    private String qrCode;
    private String order;
    private LocalDateTime date;

    public void setStatusPending() {
        this.date = LocalDateTime.now();
        this.status = StatusPayment.PENDING;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatusPaid() {
        this.status = StatusPayment.PAID;
    }

    public void setStatusReject() {
        this.status = StatusPayment.REJECT;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}

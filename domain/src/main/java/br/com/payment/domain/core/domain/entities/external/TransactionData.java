package br.com.payment.domain.core.domain.entities.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {

    @JsonProperty("qr_code")
    private String qrCode;

}

package br.com.payment.domain.core.domain.entities.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointOfInteraction {

    @JsonProperty("transaction_data")
    private TransactionData transactionData;

}

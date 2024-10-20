package br.com.payment.domain.core.domain.entities.internal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Product implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String urlImage;
    private BigDecimal price;

}

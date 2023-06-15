package com.example.classicmodels.model.keys;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class OrderDetailPK implements Serializable {
    @Getter
    @Setter
    protected Long orderNumber;
    @Getter
    @Setter
    protected String productCode;

    public OrderDetailPK(Long orderNumber, String productCode) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
    }

    // equals, hashCode

}

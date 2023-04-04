package com.example.demo.model.keys;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PaymentPK implements Serializable {
    @Getter
    @Setter
    protected Long customerNumber;
    @Getter
    @Setter
    protected String checkNumber;

    public PaymentPK(Long customerNumber, String checkNumber) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
    }
    // equals, hashCode
}

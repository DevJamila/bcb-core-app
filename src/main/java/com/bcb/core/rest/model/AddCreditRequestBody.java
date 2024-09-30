package com.bcb.core.rest.model;

import java.math.BigDecimal;

public class AddCreditRequestBody {
    private BigDecimal amount;

    public AddCreditRequestBody() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

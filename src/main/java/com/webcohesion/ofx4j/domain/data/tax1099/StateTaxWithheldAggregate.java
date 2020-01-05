package com.webcohesion.ofx4j.domain.data.tax1099;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.Element;

@Aggregate("STTAXWHAGG")
public class StateTaxWithheldAggregate {
    private String amount;
    private String payerState;
    private String payerStateId;
    private String stateDistribution;

    @Element( name = "AMOUNT", required = true , order = 0 )
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Element( name = "PAYERSTATE", required = true , order = 1 )
    public String getPayerState() {
        return payerState;
    }

    public void setPayerState(String payerState) {
        this.payerState = payerState;
    }

    @Element( name = "PAYERSTID", required = false , order = 2 )
    public String getPayerStateId() {
        return payerStateId;
    }

    public void setPayerStateId(String payerStateId) {
        this.payerStateId = payerStateId;
    }

    @Element( name = "STDIST", required = false , order = 3 )
    public String getStateDistribution() {
        return stateDistribution;
    }

    public void setStateDistribution(String stateDistribution) {
        this.stateDistribution = stateDistribution;
    }
}

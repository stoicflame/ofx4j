package com.webcohesion.ofx4j.domain.data.signup;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;

@Aggregate("HOLDERINFO")
public class AccountHolderInfo {

    private AccountHolder primaryAccountHolder;
    private AccountHolder secondaryAccountHolder;

    @ChildAggregate(order = 10, required = true, name = "PRIMARYHOLDER")
    public AccountHolder getPrimaryAccountHolder() {
        return primaryAccountHolder;
    }

    public void setPrimaryAccountHolder(AccountHolder primaryAccountHolder) {
        this.primaryAccountHolder = primaryAccountHolder;
    }

    @ChildAggregate(order = 20, name = "SECONDARYHOLDER")
    public AccountHolder getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(AccountHolder secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
    }
}

package com.webcohesion.ofx4j.domain.data.investment.statements;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;
import com.webcohesion.ofx4j.meta.Element;

import java.math.BigDecimal;

/**
 * Aggregate for the 401(k) account balance.
 *
 * @see "Section 13.9.2.9, OFX Spec"
 */
@Aggregate("INV401KBAL")
public class FourOhOneKBalance {
    private BigDecimal cashBalance;
    private BigDecimal preTaxValue;
    private BigDecimal postTaxValue;
    private BigDecimal matchValue;
    private BigDecimal profitSharingValue;
    private BigDecimal rolloverValue;
    private BigDecimal otherVestValue;
    private BigDecimal otherNonVestValue;
    private BigDecimal totalValue;
    private BalanceList balanceList;


    /**
     * Gets the cash balance in the 401(k) account. This is not a required field according to the OFX spec.
     *
     * @return the cash balance
     */
    @Element(name = "CASHBAL", order = 10)
    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    /**
     * Sets the cash balance in the 401(k) account. This is not a required field according to the OFX spec.
     *
     * @param cashBalance the current cash balance in the 401(k) account.
     */
    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    /**
     * Current value of all securities purchased with Before Tax Employee contributions. This is not a required field
     * according to the OFX spec.
     *
     * @return the pre-tax value of all the securities in the account
     */
    @Element(name = "PRETAX", order = 20)
    public BigDecimal getPreTaxValue() {
        return preTaxValue;
    }

    /**
     * Sets the current value of all securities purchased with Before Tax Employee contributions. This is not a required
     * field according to the OFX spec.
     *
     * @param preTaxValue the current value of all securities purchased with Before Tax Employee contributions.
     */
    public void setPreTaxValue(BigDecimal preTaxValue) {
        this.preTaxValue = preTaxValue;
    }

    /**
     * Current value of all securities purchased with After Tax Employee contributions. This is not a required field
     * according to the OFX spec.
     *
     * @return the after-tax value of all the securities in the account
     */
    @Element(name = "AFTERTAX", order = 30)
    public BigDecimal getPostTaxValue() {
        return postTaxValue;
    }

    /**
     * Sets the current value of all securities purchased with After Tax Employee contributions. This is not a required
     * field according to the OFX spec.
     *
     * @param postTaxValue the current value of all securities purchased with Post Tax Employee contributions.
     */
    public void setPostTaxValue(BigDecimal postTaxValue) {
        this.postTaxValue = postTaxValue;
    }

    /**
     * Current value of all securities purchased with Employer Match contributions. This is not a required field
     * according to the OFX spec.
     *
     * @return the value of all the securities in the account purchased with employer match contributions
     */
    @Element(name = "MATCH", order = 40)
    public BigDecimal getMatchValue() {
        return matchValue;
    }

    /**
     * Sets the current value of all securities purchased with Employer Match contributions. This is not a required
     * field according to the OFX spec.
     *
     * @param matchValue the current value of all securities purchased with Employer Match contributions.
     */
    public void setMatchValue(BigDecimal matchValue) {
        this.matchValue = matchValue;
    }

    /**
     * Current value of all securities purchased with Employer Profit Sharing contributions. This is not a required
     * field according to the OFX spec.
     *
     * @return the value of all the securities in the account purchased with employer profit sharing contributions
     */
    @Element(name = "PROFITSHARING", order = 50)
    public BigDecimal getProfitSharingValue() {
        return profitSharingValue;
    }

    /**
     * Sets the current value of all securities purchased with Employer Profit Sharing contributions. This is not a
     * required field according to the OFX spec.
     *
     * @param profitSharingValue the current value of all securities purchased with Employer Profit Sharing
     *                           contributions.
     */
    public void setProfitSharingValue(BigDecimal profitSharingValue) {
        this.profitSharingValue = profitSharingValue;
    }

    /**
     * Current value of all securities purchased with Rollover contributions. This is not a required field according to
     * the OFX spec.
     *
     * @return the value of all the securities in the account purchased with Rollover contributions
     */
    @Element(name = "ROLLOVER", order = 60)
    public BigDecimal getRolloverValue() {
        return rolloverValue;
    }

    /**
     * Sets the current value of all securities purchased with Rollover contributions. This is not a required field
     * according to the OFX spec.
     *
     * @param rolloverValue the current value of all securities purchased with Rollover contributions.
     */
    public void setRolloverValue(BigDecimal rolloverValue) {
        this.rolloverValue = rolloverValue;
    }

    /**
     * Current value of all securities purchased with Other (vesting) contributions. This is not a required field
     * according to the OFX spec.
     *
     * @return the value of all the securities in the account purchased with Other (vesting) contributions
     */
    @Element(name = "OTHERVEST", order = 70)
    public BigDecimal getOtherVestValue() {
        return otherVestValue;
    }

    /**
     * Sets the current value of all securities purchased with Other (vesting) contributions. This is not a required
     * field according to the OFX spec.
     *
     * @param otherVestValue the current value of all securities purchased with Other (vesting) contributions.
     */
    public void setOtherVestValue(BigDecimal otherVestValue) {
        this.otherVestValue = otherVestValue;
    }

    /**
     * Current value of all securities purchased with Other (non-vesting) contributions. This is not a required field
     * according to the OFX spec.
     *
     * @return the value of all the securities in the account purchased with Other (non-vesting) contributions
     */
    @Element(name = "OTHERNONVEST", order = 80)
    public BigDecimal getOtherNonVestValue() {
        return otherNonVestValue;
    }

    /**
     * Sets the current value of all securities purchased with Other (non-vesting) contributions. This is not a required
     * field according to the OFX spec.
     *
     * @param otherNonVestValue the current value of all securities purchased with Other (non-vesting) contributions.
     */
    public void setOtherNonVestValue(BigDecimal otherNonVestValue) {
        this.otherNonVestValue = otherNonVestValue;
    }

    /**
     * Current value of all securities purchased with all contributions. This is a required field according to the OFX
     * spec.
     *
     * @return the value of all the securities in the account purchased with all contributions
     */
    @Element(name = "TOTAL", required = true, order = 90)
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * Sets the current value of all securities purchased with all contributions. This is a required field according to
     * the OFX spec.
     *
     * @param totalValue the current value of all securities purchased with all contributions.
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * The 401(k) balance list (at most one). This is not a required field according to the OFX spec.
     *
     * @return the 401(k) balance list
     */
    @ChildAggregate( name = "BALLIST", required = true, order = 100 )
    public BalanceList getBalanceList() {
        return balanceList;
    }

    /**
     * Sets the 401(k) balance list (at most one). This is not a required field according to the OFX spec.
     *
     * @param balanceList the 401(k) balance list
     */
    public void setBalanceList(BalanceList balanceList) {
        this.balanceList = balanceList;
    }
}

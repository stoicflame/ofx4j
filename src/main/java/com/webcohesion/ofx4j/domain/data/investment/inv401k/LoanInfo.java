package com.webcohesion.ofx4j.domain.data.investment.inv401k;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.Element;

import java.util.Date;

/**
 * 401(k) loan information.
 *
 * @see "Section 13.9.3, OFX Spec"
 */
@Aggregate("LOANINFO")
public class LoanInfo {

    private String id;
    private String description;
    private Double initialLoanBalance;
    private Date loanStartDate;
    private Double currentPrincipalBalance;
    private Date asOfDate;
    private Double annualInterestRate;
    private Double paymentAmount;
    private String paymentFrequency;
    private Integer initialPayments;
    private Integer remainingPayments;
    private Date loanEndDate;
    private Double projectedInterest;
    private Double interestPayed;
    private Date nextPaymentDueDate;

    /**
     * Identifier of this loan. This is a required field according to the OFX spec.
     *
     * @return the identifier of this loan.
     */
    @Element(name = "LOANID", required = true, order = 10)
    public String getId() {
        return id;
    }

    /**
     * Sets the identifier of this loan. This is a required field according to the OFX spec.
     *
     * @param id the identifier of this loan.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Loan description. This is not a required field according to the OFX spec.
     *
     * @return the loan description.
     */
    @Element(name = "LOANDESC", order = 20)
    public String getDescription() {
        return description;
    }

    /**
     * Sets the loan description. This is not a required field according to the OFX spec.
     *
     * @param description the loan description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Initial loan balance. This is not a required field according to the OFX spec.
     *
     * @return the initial loan balance.
     */
    @Element(name = "INITIALLOANBAL", order = 30)
    public Double getInitialLoanBalance() {
        return initialLoanBalance;
    }

    /**
     * Sets the initial loan balance. This is not a required field according to the OFX spec.
     *
     * @param initialLoanBalance the initial loan balance.
     */
    public void setInitialLoanBalance(Double initialLoanBalance) {
        this.initialLoanBalance = initialLoanBalance;
    }

    /**
     * Start date of loan. This is not a required field according to the OFX spec.
     *
     * @return the start date of loan.
     */
    @Element(name = "LOANSTARTDATE", order = 40)
    public Date getLoanStartDate() {
        return loanStartDate;
    }

    /**
     * Sets the start date of loan. This is not a required field according to the OFX spec.
     *
     * @param loanStartDate the start date of loan.
     */
    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    /**
     * Current loan principal balance. This is a required field according to the OFX spec.
     *
     * @return the current loan principal balance.
     */
    @Element(name = "CURRENTLOANBAL", required = true, order = 50)
    public Double getCurrentPrincipalBalance() {
        return currentPrincipalBalance;
    }

    /**
     * Sets the current loan principal balance. This is a required field according to the OFX spec.
     *
     * @param currentPrincipalBalance the current loan principal balance.
     */
    public void setCurrentPrincipalBalance(Double currentPrincipalBalance) {
        this.currentPrincipalBalance = currentPrincipalBalance;
    }

    /**
     * Date and time of the current loan balance. This is a required field according to the OFX spec.
     *
     * @return the date and time of the current loan balance.
     */
    @Element(name = "DTASOF", required = true, order = 60)
    public Date getAsOfDate() {
        return asOfDate;
    }

    /**
     * Sets the date and time of the current loan balance. This is a required field according to the OFX spec.
     *
     * @param asOfDate the date and time of the current loan balance.
     */
    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }

    /**
     * Loan annual interest rate. This is not a required field according to the OFX spec.
     *
     * @return the loan annual interest rate.
     */
    @Element(name = "LOANRATE", order = 70)
    public Double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * Sets the loan annual interest rate. This is not a required field according to the OFX spec.
     *
     * @param annualInterestRate the loan annual interest rate.
     */
    public void setAnnualInterestRate(Double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Loan payment amount. This is not a required field according to the OFX spec.
     *
     * @return the loan payment amount.
     */
    @Element(name = "LOANPMTAMT", order = 80)
    public Double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the loan payment amount. This is not a required field according to the OFX spec.
     *
     * @param paymentAmount the loan payment amount.
     */
    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Frequency of loan repayments. This is not a required field according to the OFX spec.
     *
     * @return the frequency of loan repayments.
     */
    @Element(name = "LOANPMTFREQ", order = 90)
    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the frequency of loan repayments. This is not a required field according to the OFX spec.
     *
     * @param paymentFrequency the frequency of loan repayments.
     */
    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    /**
     * Gets the payment frequency as one of the well-known types.
     *
     * @return the payment frequency or null if it's not one of the well-known types
     */
    public LoanPaymentFrequency getPaymentFrequencyEnum() {
        return LoanPaymentFrequency.fromOfx(getPaymentFrequency());
    }

    /**
     * Initial number of loan payment. This is not a required field according to the OFX spec.
     *
     * @return the initial number of loan payments.
     */
    @Element(name = "LOANPMTSINITIAL", order = 100)
    public Integer getInitialPayments() {
        return initialPayments;
    }

    /**
     * Sets the initial number of loan payments. This is not a required field according to the OFX spec.
     *
     * @param initialPayments the initial number of loan payments.
     */
    public void setInitialPayments(Integer initialPayments) {
        this.initialPayments = initialPayments;
    }

    /**
     * Remaining number of loan payments.  This is not a required field according to the OFX spec.
     *
     * @return the remaining number of loan payments.
     */
    @Element(name = "LOANPMTSREMAINING", order = 110)
    public Integer getRemainingPayments() {
        return remainingPayments;
    }

    /**
     * Sets the remaining number of loan payments. This is not a required field according to the OFX spec.
     *
     * @param remainingPayments the remaining number of loan payments.
     */
    public void setRemainingPayments(Integer remainingPayments) {
        this.remainingPayments = remainingPayments;
    }

    /**
     * Expected loan end date. This is not a required field according to the OFX spec.
     *
     * @return the expected loan end date.
     */
    @Element(name = "LOANMATURITYDATE", order = 120)
    public Date getLoanEndDate() {
        return loanEndDate;
    }

    /**
     * Sets the expected loan end date. This is not a required field according to the OFX spec.
     *
     * @param loanEndDate the expected loan end date.
     */
    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    /**
     * Total projected interest to be paid on this loan. This is not a required field according to the OFX spec.
     *
     * @return the total projected interest to be paid on this loan.
     */
    @Element(name = "LOANTOTALPROJINTEREST", order = 130)
    public Double getProjectedInterest() {
        return projectedInterest;
    }

    /**
     * Sets the total projected interest to be paid on this loan. This is not a required field according to the OFX spec.
     *
     * @param projectedInterest the total projected interest to be paid on this loan.
     */
    public void setProjectedInterest(Double projectedInterest) {
        this.projectedInterest = projectedInterest;
    }

    /**
     * Total interested paid to date on this loan. This is not a required field according to the OFX spec.
     *
     * @return the total interested paid to date on this loan.
     */
    @Element(name = "LOANINTERESTTODATE", order = 140)
    public Double getInterestPayed() {
        return interestPayed;
    }

    /**
     * Sets the total interested paid to date on this loan. This is not a required field according to the OFX spec.
     *
     * @param interestPayed the total interested paid to date on this loan.
     */
    public void setInterestPayed(Double interestPayed) {
        this.interestPayed = interestPayed;
    }

    /**
     * Next payment due date. This is not a required field according to the OFX spec.
     *
     * @return the next payment due date.
     */
    @Element(name = "LOANNEXTPMTDATE", order = 150)
    public Date getNextPaymentDueDate() {
        return nextPaymentDueDate;
    }

    /**
     * Sets the next payment due date. This is not a required field according to the OFX spec.
     *
     * @param nextPaymentDueDate the next payment due date.
     */
    public void setNextPaymentDueDate(Date nextPaymentDueDate) {
        this.nextPaymentDueDate = nextPaymentDueDate;
    }
}
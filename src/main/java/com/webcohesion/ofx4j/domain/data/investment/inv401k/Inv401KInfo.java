package com.webcohesion.ofx4j.domain.data.investment.inv401k;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;
import com.webcohesion.ofx4j.meta.Element;

import java.util.Date;
import java.util.List;

/**
 * Aggregate for the 401(k) account info.
 *
 * @see "Section 13.9.3, OFX Spec"
 */
@Aggregate("INV401K")
public class Inv401KInfo {

    private String employerName;
    private String planId;
    private Date planJoinDate;
    private String employerContactInfo;
    private String brokerContactInfo;
    private Double preTaxDeferredPercent;
    private Double afterTaxDeferredPercent;
    //private MatchInfo matchInfo;
    //private ContributionsInfo contributionsInfo;
    private Double estimatedVestedEmployerContributionsPercentage;
    //private List<VestInfo> vestInfoRecords;
    private List<LoanInfo> loanInfoRecords;
    //private Inv401KSummary inv401KSummary;

    /**
     * Name of the employer. This is a required field according to the OFX spec.
     *
     * @return the employer name
     */
    @Element(name = "EMPLOYERNAME", required = true, order = 10)
    public String getEmployerName() {
        return employerName;
    }

    /**
     * Sets the employer name. This is a required field according to the OFX spec.
     *
     * @param employerName the name of the employer.
     */
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    /**
     * Plan number. This is not a required field according to the OFX spec.
     *
     * @return the plan number
     */
    @Element(name = "PLANID", order = 20)
    public String getPlanId() {
        return planId;
    }

    /**
     * Sets the plan number. This is not a required field according to the OFX spec.
     *
     * @param planId the plan number.
     */
    public void setPlanId(String planId) {
        this.planId = employerName;
    }

    /**
     * Date the employee joined the plan. This is not a required field according to the OFX spec.
     *
     * @return the plan number
     */
    @Element(name = "PLANJOINDATE", order = 30)
    public Date getPlanJoinDate() {
        return planJoinDate;
    }

    /**
     * Sets the date the employee joined the plan. This is not a required field according to the OFX spec.
     *
     * @param planJoinDate the date the employee joined the plan.
     */
    public void setPlanJoinDate(Date planJoinDate) {
        this.planJoinDate = planJoinDate;
    }

    /**
     * Name of contact person at employer, plus any available contact information, such as phone number. This is not a
     * required field according to the OFX spec.
     *
     * @return the name of contact person at employer, plus any available contact information, such as phone number.
     */
    @Element(name = "EMPLOYERCONTACTINFO", order = 40)
    public String getEmployerContactInfo() {
        return employerContactInfo;
    }

    /**
     * Sets the employer contact info. This is not a required field according to the OFX spec.
     *
     * @param employerContactInfo the name of contact person at employer, plus any available contact information, such
     *                            as phone number.
     */
    public void setEmployerContactInfo(String employerContactInfo) {
        this.employerContactInfo = employerContactInfo;
    }

    /**
     * Name of contact person at broker, plus any available contact information, such as phone number. This is not a
     * required field according to the OFX spec.
     *
     * @return the name of contact person at broker, plus any available contact information, such as phone number.
     */
    @Element(name = "BROKERCONTACTINFO", order = 50)
    public String getBrokerContactInfo() {
        return brokerContactInfo;
    }

    /**
     * Sets the broker contact info. This is not a required field according to the OFX spec.
     *
     * @param brokerContactInfo the name of contact person at broker, plus any available contact information, such as
     *                          phone number.
     */
    public void setBrokerContactInfo(String brokerContactInfo) {
        this.brokerContactInfo = brokerContactInfo;
    }

    /**
     * Percent of employee salary deferred before tax. This is not a required field according to the OFX spec.
     *
     * @return the Percent of employee salary deferred before tax.
     */
    @Element(name = "DEFERPCTPRETAX", order = 60)
    public Double getPreTaxDeferredPercent() {
        return preTaxDeferredPercent;
    }

    /**
     * Sets the percent of employee salary deferred before tax. This is not a required field according to the OFX spec.
     *
     * @param preTaxDeferredPercent the percent of employee salary deferred before tax.
     */
    public void setPreTaxDeferredPercent(Double preTaxDeferredPercent) {
        this.preTaxDeferredPercent = preTaxDeferredPercent;
    }

    /**
     * Percent of employee salary deferred after tax. This is not a required field according to the OFX spec.
     *
     * @return the Percent of employee salary deferred after tax.
     */
    @Element(name = "DEFERPCTAFTERTAX", order = 70)
    public Double getAfterTaxDeferredPercent() {
        return afterTaxDeferredPercent;
    }

    /**
     * Sets the percent of employee salary deferred before tax. This is not a required field according to the OFX spec.
     *
     * @param afterTaxDeferredPercent the percent of employee salary deferred after tax.
     */
    public void setAfterTaxDeferredPercent(Double afterTaxDeferredPercent) {
        this.afterTaxDeferredPercent = afterTaxDeferredPercent;
    }

    ///**
    // * Employer match information. This is not a required field according to the OFX spec.
    // *
    // * @return the employer match information.
    // */
    //@ChildAggregate(name = "MATCHINFO", order = 80)
    //public MatchInfo getMatchInfo() {
    //    return matchInfo;
    //}

    ///**
    // * Sets the employer match information. This is not a required field according to the OFX spec.
    // *
    // * @param matchInfo the employer match information.
    // */
    //public void setMatchInfo(MatchInfo matchInfo) {
    //    this.matchInfo = matchInfo;
    //}

    ///**
    // * Contributions information. This is not a required field according to the OFX spec.
    // *
    // * @return the contributions information.
    // */
    //@ChildAggregate(name = "CONTRIBINFO", order = 90)
    //public ContributionsInfo getContributionsInfo() {
    //    return contributionsInfo;
    //}

    ///**
    // * Sets the contributions information. This is not a required field according to the OFX spec.
    // *
    // * @param matchInfo the contributions information.
    // */
    //public void setContributionsInfo(ContributionsInfo contributionsInfo) {
    //    this.contributionsInfo = contributionsInfo;
    //}

    /**
     * The estimated percentage of employer contributions vested as of the current date. This is not a required field
     * according to the OFX spec.
     *
     * @return the estimated percentage of employer contributions vested as of the current date.
     */
    @Element(name = "CURRENTVESTPCT", order = 100)
    public Double getEstimatedVestedEmployerContributionsPercentage() {
        return estimatedVestedEmployerContributionsPercentage;
    }

    /**
     * Sets the estimated percentage of employer contributions vested as of the current date. This is not a required
     * field according to the OFX spec.
     *
     * @param estimatedVestedEmployerContributionsPercentage the the estimated percentage of employer contributions
     *                                                       vested as of the current date.
     */
    public void setEstimatedVestedEmployerContributionsPercentage(Double estimatedVestedEmployerContributionsPercentage) {
        this.estimatedVestedEmployerContributionsPercentage = estimatedVestedEmployerContributionsPercentage;
    }

    ///**
    // * Vest change dates. Provides the vesting percentage as of any particular past, current, or future date. This is
    // * not a required field according to the OFX spec.
    // *
    // * @return the vest change dates.
    // */
    //@ChildAggregate(name = "VESTINFO", order = 110)
    //public List<VestInfo> getVestInfoRecords() {
    //    return vestInfoRecords;
    //}

    ///**
    // * Sets the vest change dates. This is not a required field according to the OFX spec.
    // *
    // * @param vestInfoRecords the vest change dates.
    // */
    //public void setVestInfoRecords(List<VestInfo> vestInfoRecords) {
    //    this.vestInfoRecords = vestInfoRecords;
    //}

    /**
     * Loans outstanding against this account (0 or more). This is not a required field according to the OFX spec.
     *
     * @return the loans outstanding against this account.
     */
    @ChildAggregate(name = "LOANINFO", order = 120)
    public List<LoanInfo> getLoanInfoRecords() {
        return loanInfoRecords;
    }

    /**
     * Sets the loans outstanding against this account. This is not a required field according to the OFX spec.
     *
     * @param loanInfoRecords the loans outstanding against this account.
     */
    public void setLoanInfoRecords(List<LoanInfo> loanInfoRecords) {
        this.loanInfoRecords = loanInfoRecords;
    }

    ///**
    // * List of contributions to 401(k) account. This is not a required field according to the OFX spec.
    // *
    // * @return the list of contributions to 401(k) account.
    // */
    //@ChildAggregate(name = "INV401KSUMMARY", order = 130)
    //public Inv401KSummary getInv401KSummary() {
    //    return inv401KSummary;
    //}

    ///**
    // * Sets the list of contributions to 401(k) account. This is not a required field according to the OFX spec.
    // *
    // * @param inv401KSummary the list of contributions to 401(k) account.
    // */
    //public void setInv401KSummary(Inv401KSummary inv401KSummary) {
    //    this.inv401KSummary = inv401KSummary;
    //}
}
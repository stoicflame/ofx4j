package com.webcohesion.ofx4j.domain.data.investment.inv401k;

public enum LoanPaymentFrequency {
    WEEKLY,
    BIWEEKLY,
    TWICEMONTHLY,
    MONTHLY,
    FOURWEEKS,
    BIMONTHLY,
    QUARTERLY,
    SEMIANNUALLY,
    ANNUALLY,
    OTHER;

    public static LoanPaymentFrequency fromOfx(String ofxVal) {
        if ("WEEKLY".equals(ofxVal)) {
            return WEEKLY;
        } else if ("BIWEEKLY".equals(ofxVal)) {
            return BIWEEKLY;
        } else if ("TWICEMONTHLY".equals(ofxVal)) {
            return TWICEMONTHLY;
        } else  if ("MONTHLY".equals(ofxVal)) {
            return MONTHLY;
        } else if ("FOURWEEKS".equals(ofxVal)) {
            return FOURWEEKS;
        } else if ("BIMONTHLY".equals(ofxVal)) {
            return BIMONTHLY;
        } else if ("QUARTERLY".equals(ofxVal)) {
            return QUARTERLY;
        } else if ("SEMIANNUALLY".equals(ofxVal)) {
            return SEMIANNUALLY;
        } else if ("ANNUALLY".equals(ofxVal)) {
            return ANNUALLY;
        } else if ("OTHER".equals(ofxVal)) {
            return OTHER;
        } else {
            return null;
        }
    }
}
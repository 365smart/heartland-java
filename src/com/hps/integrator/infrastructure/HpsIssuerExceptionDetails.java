package com.hps.integrator.infrastructure;

public class HpsIssuerExceptionDetails {
    private String issuerResponseCode;
    private String issuerResponseText;
    private HpsDebitMac debitMac;

    public String getIssuerResponseCode() {
        return issuerResponseCode;
    }

    public void setIssuerResponseCode(String issuerResponseCode) {
        this.issuerResponseCode = issuerResponseCode;
    }

    public String getIssuerResponseText() {
        return issuerResponseText;
    }

    public void setIssuerResponseText(String issuerResponseText) {
        this.issuerResponseText = issuerResponseText;
    }

    public HpsDebitMac getDebitMac() {
        return debitMac;
    }

    public void setDebitMac(HpsDebitMac debitMac) {
        this.debitMac = debitMac;
    }
}

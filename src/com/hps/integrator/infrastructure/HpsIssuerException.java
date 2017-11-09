package com.hps.integrator.infrastructure;

public class HpsIssuerException extends HpsException {
	
	private int transactionId;
    private HpsIssuerExceptionCodes code;
    private HpsIssuerExceptionDetails details;

	public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message)
	{		
		super(message);
		this.setTransactionId(transactionId);
        this.setCode(code);
	}

    public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message, Exception e)
    {
        super(message, e);
        this.setTransactionId(transactionId);
        this.setCode(code);
    }

    public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message, String issuerCode, String issuerMessage) {
        this(transactionId, code, message);
    }

    public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message, String issuerCode, String issuerMessage, Exception e) {
	    this(transactionId, code, message, issuerCode, issuerMessage, null, e);
    }

    public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message, String issuerCode, String issuerMessage, HpsDebitMac debitMac)
    {
        super(message);
        this.setTransactionId(transactionId);
        this.setCode(code);

        HpsIssuerExceptionDetails details = new HpsIssuerExceptionDetails();
        details.setIssuerResponseCode(issuerCode);
        details.setIssuerResponseText(issuerMessage);
        details.setDebitMac(debitMac);

        this.setDetails(details);
    }

    public HpsIssuerException(int transactionId, HpsIssuerExceptionCodes code, String message, String issuerCode, String issuerMessage, HpsDebitMac debitMac, Exception e)
    {
        super(message, e);
        this.setTransactionId(transactionId);
        this.setCode(code);

        HpsIssuerExceptionDetails details = new HpsIssuerExceptionDetails();
        details.setIssuerResponseCode(issuerCode);
        details.setIssuerResponseText(issuerMessage);
        details.setDebitMac(debitMac);

        this.setDetails(details);
    }

    public HpsIssuerExceptionCodes getCode() {
        return code;
    }

    public void setCode(HpsIssuerExceptionCodes code) {
        this.code = code;
    }

    public HpsIssuerExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(HpsIssuerExceptionDetails details) {
        this.details = details;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
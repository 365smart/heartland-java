package com.hps.integrator.infrastructure;

public class HpsDebitMac
{
  String transactionCode;
  String transmissionNumber;
  String bankResponseCode;
  String traceNumber;

  public String getTransactionCode() {
    return transactionCode;
  }

  public void setTransactionCode(String transactionCode) {
    this.transactionCode = transactionCode;
  }

  public String getTransmissionNumber() {
    return transmissionNumber;
  }

  public void setTransmissionNumber(String transmissionNumber) {
    this.transmissionNumber = transmissionNumber;
  }

  public String getBankResponseCode() {
    return bankResponseCode;
  }

  public void setBankResponseCode(String bankResponseCode) {
    this.bankResponseCode = bankResponseCode;
  }

  public String getTraceNumber() {
    return traceNumber;
  }

  public void setTraceNumber(String traceNumber) {
    this.traceNumber = traceNumber;
  }

  public HpsDebitMac fromElementTree(Element debitMacElement) {
    if(debitMacElement == null) {
      return null;
    }
    if(debitMacElement.has("TransactionCode")) {
      this.setTransactionCode(debitMacElement.getString("TransactionCode"));
    }
    if(debitMacElement.has("TransmissionNumber")) {
      this.setTransmissionNumber(debitMacElement.getString("TransmissionNumber"));
    }
    if(debitMacElement.has("BankResponseCode")) {
      this.setBankResponseCode(debitMacElement.getString("BankResponseCode"));
    }
    if(debitMacElement.has("TraceNumber")) {
      this.setTraceNumber(debitMacElement.getString("TraceNumber"));
    }
    return this;
  }
}

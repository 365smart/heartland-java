package com.hps.integrator.infrastructure;

public enum HpsGatewayResponseCode
{
  UndocumentedError(-99999),
  Unauthorized(-21),
  AuthenticationError(-2),
  PorticoError(-1),
  Success(0),
  GatewaySystemError(1),
  DuplicateTransaction(2),
  InvalidOriginalTransaction(3),
  TransactionAlreadyAssociatedWithBatch(4),
  NoCurrentBatch(5),
  InvalidReturnAmount(6),
  InvalidReportParameters(7),
  BadTrackData(8),
  NoTransactionAssociatedWithBatch(9),
  EmptyReport(10),
  OriginalTransactionNotCPC(11),
  InvalidCPCData(12),
  InvalidEditData(13),
  InvalidCardNumber(14),
  BatchCloseInProgress(15),
  InvalidShipDate(16),
  InvalidEncryptionVersion(17),
  E3MSRFailture(18),
  InvalidReversalAmount(19),
  DatabaseOperationTimeOut(20),
  ArchiveDatabaseUnavailableTryAgainLater(21),
  ArchiveDatabaseUnavailableRealTimeDatabaseAttempted(22),
  TokenLookupError(23),
  TokenNotSupportedForServiceType(24),
  TokenPresentedAndRequested(25),
  SetTokenAttributeError(26),
  TokenNotFound(27),
  BackendNoResponse(30),
  PorticoAttemptedReversalFailed(31),
  MissingKTBError(32),
  MissingKSNError(33),
  InvalidDataReceived(34),
  DeviceSettingError(35),
  InvalidOriginalTxnForRepeat(36),
  MissingElement(37),
  InvalidAuthAmount(38),
  InvalidTLVData(40),
  FraudDetectedError(41),
  ProcessorSystemError(50),
  ProcessorConfigurationError(51);

  private final int iValue;
  HpsGatewayResponseCode(int value)
  {
    iValue = value;
  }

  public int value()
  {
    return iValue;
  }

  public static HpsGatewayResponseCode fromInt(int value)
  {
    for (HpsGatewayResponseCode enumVal : values())
    {
      if (value == enumVal.iValue)
      {
        return enumVal;
      }
    }
    return UndocumentedError;
  }
}


package com.hps.integrator.infrastructure;

public class ConsoleHpsRequestLogger implements IHpsRequestLogger
{
  private String emptyIfNull(String value)
  {
    return value == null ? "" : value;
  }

  public void onBeforeRequest(HttpRequestInfo request)
  {
    System.out.println("Request: " + emptyIfNull(request.getBody()));
  }

  public void onResponseReceived(HttpRequestInfo request, HttpResponseInfo response)
  {
    System.out.println("Response: " + emptyIfNull(response.getBody()));
  }
}

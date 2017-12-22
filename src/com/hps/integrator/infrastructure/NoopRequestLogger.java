package com.hps.integrator.infrastructure;

public class NoopRequestLogger implements IHpsRequestLogger
{
  public void onBeforeRequest(HttpRequestInfo request)
  {

  }

  public void onResponseReceived(HttpRequestInfo request, HttpResponseInfo response)
  {

  }
}

package com.hps.integrator.infrastructure;

public interface IHpsRequestLogger
{
  /**
   * Called before a request is sent.
   * @param request The request to be sent.
   */
  void onBeforeRequest(HttpRequestInfo request);

  /**
   * Called after a response is received.
   * @param request The request
   * @param response The response
   */
  void onResponseReceived(HttpRequestInfo request, HttpResponseInfo response);
}

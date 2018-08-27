package com.hps.integrator.infrastructure;

import java.util.List;
import java.util.Map;

public class HttpResponseInfo
{
  private final int statusCode;
  private final Map<String, List<String>> headers;
  private final String responseBody;

  public HttpResponseInfo(int statusCode, Map<String, List<String>> headers, String responseBody)
  {
    this.responseBody = responseBody;
    this.statusCode = statusCode;
    this.headers = headers;
  }

  public int getStatusCode()
  {
    return statusCode;
  }

  public Map<String, List<String>> getHeaders()
  {
    return headers;
  }

  public String getBody()
  {
    return responseBody;
  }
}

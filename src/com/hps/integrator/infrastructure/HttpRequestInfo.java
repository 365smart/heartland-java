package com.hps.integrator.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestInfo
{
  private String url;
  private String method;
  private Map<String, List<String>> headers;
  private String body;
  private byte[] bodyBytes;

  public HttpRequestInfo()
  {
    headers = new HashMap<String, List<String>>();
  }

  public String getMethod()
  {
    return method;
  }

  public Map<String, List<String>> getHeaders()
  {
    return headers;
  }

  public String getBody()
  {
    return body;
  }

  public void setBody(String body)
  {
    this.body = body;
    if (body != null)
    {
      bodyBytes = body.getBytes();
    }
    else
    {
      bodyBytes = new byte[0];
    }
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setMethod(String method)
  {
    this.method = method;
  }

  public void setHeader(String header, String value)
  {
    List<String> values = this.headers.get(header);
    if (values == null)
    {
      values = new ArrayList<String>();
      this.headers.put(header, values);
    }
    values.add(value);
  }

  public byte[] getBodyBytes()
  {
    return this.bodyBytes;
  }
}

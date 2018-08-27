package com.hps.integrator.services;

import com.google.gson.Gson;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class HpsRestGatewayService {

    int limit = -1;
    int offset = -1;

    IHpsServicesConfig servicesConfig;
    IHpsRequestLogger requestLogger;
    Gson gson;

    protected HpsRestGatewayService(IHpsServicesConfig config) {
        this(config, null);
    }

    protected HpsRestGatewayService(IHpsServicesConfig config, IHpsRequestLogger requestLogger) {
        if(config != null) {
            this.servicesConfig = config;
        }
        this.requestLogger = requestLogger == null ? new ConsoleHpsRequestLogger() : requestLogger;
        gson = new Gson();
    }

    protected String doRequest(String verb, String endpoint) throws HpsException {
        return this.doRequest(verb, endpoint, null, null, null);
    }
    protected String doRequest(String verb, String endpoint, Object data) throws HpsException {
        return this.doRequest(verb, endpoint, data, null, null);

    }
    private String buildUrl(String url, String endpoint, Map<String, String> queryStringParameters) throws UnsupportedEncodingException
    {
        String queryString = null;
        //Query string
        if(queryStringParameters != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("?");
            for (Map.Entry<String, String> entry : queryStringParameters.entrySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(String.format("%s=%s",
                        URLEncoder.encode(entry.getKey(), "UTF-8"),
                        URLEncoder.encode(entry.getValue(), "UTF-8")
                ));
            }
            queryString = sb.toString();
            return url + endpoint + queryString;
        }else{
            return url + endpoint;
        }
    }
    protected String doRequest(String verb, String endpoint, Object data, HashMap<String, String> additionalHeaders, HashMap<String, String> queryStringParameters) throws HpsException {
        HttpsURLConnection conn;
        HttpRequestInfo requestInfo = getRequestInfo(verb, endpoint, data, additionalHeaders, queryStringParameters);
        requestLogger.onBeforeRequest(requestInfo);
        try
        {
            conn = (HttpsURLConnection)new URL(requestInfo.getUrl()).openConnection();
        }
        catch (IOException e)
        {
            throw new HpsException(e.getMessage(), e);
        }

        String result;

        try {

            conn.setDoInput(true);
            conn.setRequestMethod(requestInfo.getMethod());

            //Headers
            for (Map.Entry<String, List<String>> headerValues : requestInfo.getHeaders().entrySet())
            {
                conn.addRequestProperty(headerValues.getKey(), HpsStringUtils.join(headerValues.getValue(), ','));
            }

            //Payload
            if (requestInfo.getBodyBytes() != null && requestInfo.getBodyBytes().length > 0)
            {
                conn.setDoOutput(true);
                DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
                requestStream.write(requestInfo.getBodyBytes());
                requestStream.flush();
                requestStream.close();
            }

            //Get the response
            InputStream responseStream = conn.getInputStream();
            result = readFully(responseStream);
            requestLogger.onResponseReceived(requestInfo, new HttpResponseInfo(conn.getResponseCode(), conn.getHeaderFields(), result));
            responseStream.close();

            return result;
        }
        catch (IOException e) {
            try {
                if (conn.getResponseCode() == 400) {
                    InputStream errorStream = conn.getErrorStream();
                    String errorMessage = readFully(errorStream);
                    errorStream.close();

                    throw new HpsException(errorMessage, e);
                } else {
                    throw new HpsException(e.getMessage(), e);
                }
            } catch (IOException ie) { throw new HpsException(e.getMessage(), ie); }
        }
    }

    private HttpRequestInfo getRequestInfo(String verb, String endpoint, Object data, HashMap<String, String> additionalHeaders, HashMap<String, String> queryStringParameters) throws HpsException
    {
        String mUrl = servicesConfig.getServiceUri();
        HttpRequestInfo requestInfo = new HttpRequestInfo();
        requestInfo.setMethod(verb);

        try
        {
            String url = buildUrl(mUrl, endpoint, queryStringParameters);
            requestInfo.setUrl(url);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new HpsException(e.getMessage(), e);
        }

        //Add the headers
        requestInfo.setHeader("Content-Type", "application/json");
        if (additionalHeaders != null) {
            for (Map.Entry<String, String> entry : additionalHeaders.entrySet()) {
                requestInfo.setHeader(entry.getKey(), entry.getValue());
            }
        }

        //Handle the payload
        String payload;
        if (!verb.equals("GET"))
        {
            if (data != null) {
                payload = gson.toJson(data);
                requestInfo.setBody(payload);
                requestInfo.setHeader("Content-Length", String.format("%s", requestInfo.getBodyBytes().length));
            }
        }
        return requestInfo;
    }

    private String readFully(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        int c;
        while((c = reader.read()) != -1)
            sb.append((char)c);
        return sb.toString();
    }

    protected <T> T hydrateObject(String data, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(data, clazz);
    }
}

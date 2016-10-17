package com.rexxar.common.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicResponseHandlerEx extends BasicResponseHandler{
  
  private static final Logger LOGGER = LoggerFactory.getLogger(BasicResponseHandlerEx.class);
  
  /**
   * Returns the response body as a String if the response was successful (a
   * 2xx status code). If no response body exists, this returns null. If the
   * response was unsuccessful (>= 300 status code), throws an
   * {@link HttpResponseException}.
   */
  public String handleResponse(final HttpResponse response)
          throws HttpResponseException, IOException {
      StatusLine statusLine = response.getStatusLine();
      HttpEntity entity = response.getEntity();
      if (statusLine.getStatusCode() >= 300) {
          LOGGER.error("status code greater than 300 and statusCode is "+statusLine.getStatusCode()+EntityUtils.toString(entity));
          throw new HttpResponseException(statusLine.getStatusCode(),
                  statusLine.getReasonPhrase());
      }
      return entity == null ? null : EntityUtils.toString(entity);
  }


}
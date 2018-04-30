package dg.pegasus;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Allows to configure parts of the interceptors for all incoming web requests.
 * @author gianninasd
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
  
  /**
   * Sets the request GUID used during event logging.
   */
  @Override
  public boolean preHandle( HttpServletRequest req, HttpServletResponse res, Object handler ) {
    MDC.put("guid", UUID.randomUUID().toString());
    return true;
  }
}
package dg.pegasus.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Response class used to return proper error messages to the client.
 * @author gianninasd
 */
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Error error;

  public ErrorResponse( String code, String message ) {
    this.error = new Error(code, message);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  /**
   * Inner class containing the details of the error response.
   */
  public class Error {

    private String code;
    private String message;
    private List<ErrorField> details = new ArrayList<ErrorField>();

    public Error( String code, String message ) {
      this.code = code;
      this.message = message;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void addDetails( String field, String message ) {
      details.add( new ErrorField(field, message) );
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public List<ErrorField> getDetails() {
      return details;
    }

    public void setDetails(List<ErrorField> details) {
      this.details = details;
    }
  }

  /**
   * Inner class containing the field level error.
   */
  public class ErrorField {
    
    private String field;
    private String message;

    public ErrorField( String field, String message ) {
      this.field = field;
      this.message = message;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
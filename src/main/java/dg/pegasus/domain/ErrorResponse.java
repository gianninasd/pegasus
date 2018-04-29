package dg.pegasus.domain;

import java.io.Serializable;

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

    public Error( String code, String message ) {
      this.code = code;
      this.message = message;
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
  }
}
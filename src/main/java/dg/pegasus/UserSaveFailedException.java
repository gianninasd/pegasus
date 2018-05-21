package dg.pegasus;

/**
 * Thrown when an unknown error occured during a user save operation.
 * @author gianninasd
 */
public class UserSaveFailedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserSaveFailedException() {
    super();
  }

  public UserSaveFailedException( String message ) {
    super( message );
  }

  public UserSaveFailedException( Exception ex ) {
    super( ex );
  }
}
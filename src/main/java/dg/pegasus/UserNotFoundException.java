package dg.pegasus;

/**
 * Thrown when the user cannot be found in the database.
 * @author gianninasd
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * Default contructor
	 */
	public UserNotFoundException() {

	}

	/**
	 * Constructor specifying the user id that was not found
	 */
	public UserNotFoundException( String id ) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
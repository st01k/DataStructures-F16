package fixes;

/**
 * Exception for illegal states in *fix classes.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class IllegalStateException extends RuntimeException {

	private static final long serialVersionUID = 9060313339439762149L;

	public IllegalStateException(String message) {
		
       super(message);
    }
}

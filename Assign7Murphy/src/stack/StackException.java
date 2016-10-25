package stack;

/**
 * Stack Exception for stack abuse.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class StackException extends java.lang.RuntimeException {
	
	private static final long serialVersionUID = 5622582104748809284L;

	public StackException(String message) {
		
		super(message);
	}
}

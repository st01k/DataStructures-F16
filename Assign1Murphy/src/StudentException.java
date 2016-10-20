/**
 * Custom student exception class to handle student specific errors.
 * @author Casey Murphy
 *
 */
public class StudentException extends Exception
{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Student exception default constructor.
	 */
	public StudentException() {}
	
	/**
	 * Student exception constructor with message.
	 * @param message
	 */
    public StudentException(String message)
    {
       super(message);
    }
 }
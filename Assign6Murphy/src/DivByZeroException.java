/**
 * Custom division by zero exception class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class DivByZeroException extends ArithmeticException
{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Default division by zero exception.
	 */
	public DivByZeroException() {}
	
	/**
	 * Division by zero exception with custom message.
	 * @param message
	 */
    public DivByZeroException(String message)
    {
       super(message);
    }
 }
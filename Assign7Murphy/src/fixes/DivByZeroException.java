package fixes;

/**
 * Custom division by zero exception class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class DivByZeroException extends ArithmeticException {
	
	private static final long serialVersionUID = 7903925125899149422L;
	
    public DivByZeroException(String message) {
    	
       super(message);
    }
 }
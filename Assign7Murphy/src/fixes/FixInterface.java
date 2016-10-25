package fixes;

/**
 * Interface for infix, prefix, and postfix notations.
 * Provides regex for each.
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface FixInterface {

	final String identifierRegex = "[a-z]";
	final String digitRegex = "^-?[0-9]+";
	final String parenRegex = "[()]";
	final String operatorRegex = "[-+*/%^]";
	final String identifiers = "abcdefghijklmnopqrstuvwxyz";
}

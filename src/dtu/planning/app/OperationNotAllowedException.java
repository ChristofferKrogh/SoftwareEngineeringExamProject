package dtu.planning.app;

public class OperationNotAllowedException extends Exception {

	/**
	 * Generated a default serial version unique ID
	 */
	private static final long serialVersionUID = -6797101480481250870L;

	/**
	 * A new exception is constructed with error message errorMessage.
	 * @param errorMessage the error message of the exception
	 */
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}


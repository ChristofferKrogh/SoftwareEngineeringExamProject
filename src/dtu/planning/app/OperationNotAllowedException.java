package dtu.planning.app;

public class OperationNotAllowedException extends Exception {

	/**
	 * A new exception is constructed with error message errorMessage.
	 * @param errorMessage the error message of the exception
	 */
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}


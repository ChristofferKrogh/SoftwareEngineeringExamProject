package dtu.planning.app;

public class ActivityNotFoundException extends Exception {

	/**
	 * Generated a default serial version unique ID
	 */
	private static final long serialVersionUID = -5120818988866399660L;

	public ActivityNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}

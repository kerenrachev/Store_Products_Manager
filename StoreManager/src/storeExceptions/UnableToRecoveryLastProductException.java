package storeExceptions;

public class UnableToRecoveryLastProductException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToRecoveryLastProductException() {
		super("Unable to restore, no save was made or the last save was restored");
	}
}

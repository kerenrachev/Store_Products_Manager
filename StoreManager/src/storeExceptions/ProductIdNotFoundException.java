package storeExceptions;

public class ProductIdNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductIdNotFoundException() {
		super("Product not found on file ! ");
	}

}

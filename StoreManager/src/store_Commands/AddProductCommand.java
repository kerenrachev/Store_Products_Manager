package store_Commands;

import Model.Product;
import Model.Store;
import interfaces.Command;

public class AddProductCommand implements Command{
	
	private Store store;
	private String key;
	private Product product;
	
	public AddProductCommand(Store store,String key , Product product)
	{
		this.store = store;
		this.key = key;
		this.product = product;
	}

	@Override
	public void execute() {
		store.addProduct(key, product);
	}
}

package store_Commands;

import Model.Product;
import Model.Store;
import interfaces.Command;

public class AddProductCommandToMap implements Command<String>{
	
	private Store store;
	private String key;
	private Product product;
	
	public AddProductCommandToMap(Store store,String key , Product product)
	{
		this.store = store;
		this.key = key;
		this.product = product;
	}

	@Override
	public String execute() {
		store.addProduct(key , product);
		return key;
	}
}

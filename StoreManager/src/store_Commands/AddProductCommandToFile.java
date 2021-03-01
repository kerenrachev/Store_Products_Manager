package store_Commands;

import Model.Product;
import Model.Store;
import interfaces.Command;

public class AddProductCommandToFile implements Command<String>{
	
	private Store store;

	public AddProductCommandToFile(Store store)
	{
		this.store = store;

	}

	@Override
	public String execute() {
		store.addProductToFile();
		return null;
	}
}

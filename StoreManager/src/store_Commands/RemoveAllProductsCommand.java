package store_Commands;

import Model.Store;
import interfaces.Command;

public class RemoveAllProductsCommand implements Command<Integer>{
	private Store store;

	public RemoveAllProductsCommand(Store store) {
		this.store = store;
	}



	@Override
	public Integer execute() {
		return store.removeAllProducts();
	}


}

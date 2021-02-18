package store_Commands;

import java.util.Map;

import Model.Product;
import Model.Store;
import interfaces.Command;

public class GetMapCommand implements Command<Map<String,Product>> {
	private Store store;

	public GetMapCommand(Store store) {
		this.store = store;
	}



	@Override
	public Map<String, Product> execute() {
		return store.getMap();
	}


}

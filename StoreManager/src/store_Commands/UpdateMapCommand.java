package store_Commands;

import Model.Store;
import interfaces.Command;

public class UpdateMapCommand implements Command<Object> {

	private Store store;
	private int mapType;

	public UpdateMapCommand(Store store, int mapType) {
		this.store = store;
		this.mapType = mapType;
	}



	@Override
	public Object execute() {
		store.updateMapType(mapType);
		return null;
	}

}

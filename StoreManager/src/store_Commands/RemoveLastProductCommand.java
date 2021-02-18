package store_Commands;

import Model.Store;
import interfaces.Command;
import storeExceptions.UnableToRecoveryLastProductException;

public class RemoveLastProductCommand implements Command<Integer>{
	private Store store;

	public RemoveLastProductCommand(Store store) {
		this.store = store;
	}



	@Override
	public Integer execute() {
		try {
			return store.removeLastProduct();
		} catch (UnableToRecoveryLastProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}


}

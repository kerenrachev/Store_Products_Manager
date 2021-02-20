package store_Commands;

import java.util.ArrayList;

import Controller.StoreController;
import Model.Store;
import interfaces.Command;

public class SendMassagesCommand implements Command<Integer>{
	private Store store;
	private String massage;
	private StoreController s;

	public SendMassagesCommand(Store store, String massage, StoreController s) {
		this.store = store;
		this.s=s;
	}
	@Override
	public Integer execute() {
		
		String[] customersNames = store.getStoreUpdates().notifyAllObservers();
		if(customersNames.length==0)
			return 0; 
		else
			s.startThreadByRequest(customersNames);
			return 1; 
	}


}

package store_Commands;

import Model.Store;
import interfaces.Command;

public class SendMassagesCommand implements Command<Integer>{
	private Store store;
	private String massage;

	public SendMassagesCommand(Store store, String massage) {
		this.store = store;
	}



	@Override
	public Integer execute() {
		return store.sendMassages(massage);
	}


}

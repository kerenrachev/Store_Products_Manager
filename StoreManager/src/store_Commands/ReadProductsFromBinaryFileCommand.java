package store_Commands;

import Model.Store;
import interfaces.Command;

public class ReadProductsFromBinaryFileCommand implements Command<Integer> {
	private Store store;
	private String fileName;

	public ReadProductsFromBinaryFileCommand(Store store, String fileName) {
		this.store = store;
		this.fileName = fileName;
	}



	@Override
	public Integer execute() {
		try {
			store.readProductsFromBinaryFile(fileName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}

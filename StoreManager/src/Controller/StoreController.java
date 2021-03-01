package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Customer;
import Model.Product;
import Model.Store;
import View.View;
import interfaces.StoreModelListener;
import interfaces.StoreUIListener;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import store_Commands.AddProductCommandToFile;
import store_Commands.AddProductCommandToMap;
import store_Commands.FindProductCommand;
import store_Commands.GetMapCommand;
import store_Commands.ReadProductsFromBinaryFileCommand;
import store_Commands.RemoveAllProductsCommand;
import store_Commands.RemoveLastProductCommand;
import store_Commands.RemoveProductCommand;
import store_Commands.SendMassagesCommand;
import store_Commands.UpdateMapCommand;

public class StoreController implements StoreModelListener, StoreUIListener {
	// private Store model;
	// private View view;

	private Store theModel;
	private View theView;

	public StoreController(Store model, View view) {
		theModel = model;
		theView = view;

		theModel.registerListener(this);
		theView.registerListener(this);

		int result = 0;
		try {
			result = theModel.readProductsFromBinaryFile("products txt");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == 0) {
			Label l = new Label("There are no products inside the file! ");
			l.setTextFill(Color.RED);
			theView.OpenErrorStage(l);
		} else {
			Label l = new Label("Products loaded from file.");
			l.setTextFill(Color.GREEN);
			theView.OpenErrorStage(l);
		}
	}
	@Override
	public void fireSearchForCatalogeNumber(String strCatalogNumber) {
		theView.setFindedProduct(new FindProductCommand(theModel, strCatalogNumber).execute());

	}
	@Override
	public void fireRemoveProductByCatalogeNumber(String catalogNumber) {
		int res = new RemoveProductCommand(theModel, catalogNumber).execute();
		theView.setRemoveProductResults(res);

	}

	@Override
	public void fireUpdateMapType(int mapType) {
		System.out.println(mapType);
		new UpdateMapCommand(theModel, mapType).execute();
	}

	@Override
	public int firereadProductsFromBinaryFile(String F_NAME) {
		return new ReadProductsFromBinaryFileCommand(theModel, F_NAME).execute();
	}

	@Override
	public void fireAddProductToFile() {
		new AddProductCommandToFile(theModel).execute();
	}


	@Override
	public int fireremoveLastProduct() {
		return new RemoveLastProductCommand(theModel).execute();
	}

	@Override
	public Map<String, Product> getMap() {
		return new GetMapCommand(theModel).execute();
	}

	@Override
	public int removeAllProducts() {
		return new RemoveAllProductsCommand(theModel).execute();
	}
	@Override
	public int fireSendUpdateMassages(String massage) {
		return new SendMassagesCommand(theModel,massage, this).execute();
	}
	public void startThreadByRequest(String[] customersNames) {
		theModel.setCustomersNames(customersNames);
		
	}
	@Override
	public void fireShowAllCustomersWhoRecievedMSG() {
		theModel.getStoreUpdates().showCustomerNames(theModel.getCustomersNames(),this);
		
	}
	public void fireLabel(String string) {
		theView.addNameCustomerToPane(string);
		
	}
	@Override
	public void fireAddProductToMap(String clientName, String phoneNum, boolean clientInterested,
			String productNameString, double costPrice, double sellingPrice,long epochTime,String catalogNum) {

		
		new AddProductCommandToMap(theModel, catalogNum, new Product(productNameString,
																		costPrice, sellingPrice,
																		new Customer(clientName, phoneNum, clientInterested), epochTime))
																		.execute();
		
	}

}

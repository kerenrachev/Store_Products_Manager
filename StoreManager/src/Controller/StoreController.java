package Controller;

import Model.Store;
import View.View;
import interfaces.Command;
import interfaces.StoreModelListener;
import interfaces.StoreUIListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import storeExceptions.UnableToRecoveryLastProductException;
import Model.TableRows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import Model.Customer;
import Model.Product;

public class StoreController implements StoreModelListener , StoreUIListener{
	//private Store model;
	//private View view;
	
	private Store theModel;
	private View theView;
	
	
	public StoreController(Store model , View view)
	{
		theModel  = model;
		theView = view;
		
		theModel.registerListener(this);
		theView.registerListener(this);
		
		int result=0;
		try {
			result = theModel.readProductsFromBinaryFile("products txt");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==0)
		{
			Label l = new Label("There are no products inside the file! ");
			l.setTextFill(Color.RED);
			theView.OpenErrorStage(l);
		}
		else
		{
			Label l = new Label("Products loaded from file.");
			l.setTextFill(Color.GREEN);
			theView.OpenErrorStage(l);
		}
	}


	@Override
	public void executeCommand(Command c) {
		c.execute();
	}


	@Override
	public void fireSearchForCatalogeNumber(String strCatalogNumber) {
		theView.setFindedProduct(theModel.findProduct(strCatalogNumber));
	}


	@Override
	public void fireRemoveProductByCatalogeNumber(String catalogNumber) {
		int res = theModel.removeProduct(catalogNumber);
		theView.setRemoveProductResults(res);
		
	}


	@Override
	public void fireUpdateMapType(int mapType) {
		theModel.updateMapType(mapType);
		
	}

	@Override
	public int firereadProductsFromBinaryFile(String F_NAME) {
		int res=0;
		try {
			res = theModel.readProductsFromBinaryFile(F_NAME);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return res;	
	}


	@Override
	public void fireAddProduct(String catalogNumber, Product p) {
		theModel.addProduct(catalogNumber, p);
		
	}


	@Override
	public int fireremoveLastProduct() {
		int res=0;
		try {
			res = theModel.removeLastProduct();
		} catch (UnableToRecoveryLastProductException e) {
			e.printStackTrace();
		}
		return res;
	}


	@Override
	public Map getMap() {
		return theModel.getMap();
	}


	@Override
	public int removeAllProducts() {
		int res=0;
		res = theModel.removeAllProducts();
		return res;
	}


	@Override
	public int fireSendUpdateMassages() {
		int res=0;
		res = theModel.sendMassages();
		return res;
	}
	
	
}

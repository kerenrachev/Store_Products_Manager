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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.TableRows;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	
}

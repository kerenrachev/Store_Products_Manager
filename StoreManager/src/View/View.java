package View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Controller.StoreController;
import Model.Customer;
import Model.Product;
import Model.TableRows;
import interfaces.StoreModelListener;
import interfaces.StoreUIListener;
import interfaces.Store_viewable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View implements Store_viewable{
	
	private Button add;
	private Label ins1;
	private Label ins2;
	private Label ins3;
	private Label ins4;
	private Label ins5;
	private Label ins6;
	private Label ins7;
	private Label ins8;
	private Label ins9;
	private TextField catalog;
	private TextField productName;
	private TextField storeCost;
	private TextField sellingCost;
	private TextField customerName;
	private TextField CustomerPhone;
	private Label mainLabel;
	private Label insLabel;
	private TextField catalogNumber;
	private Button searchButtonByCatalogNumber;
	private RadioButton r1;
	private RadioButton r2;
	private RadioButton r3;
	private RadioButton r4;
	private Button sortButton;
	private Button bSearch;
	private Button bSort;
	private Button bAddProduct;
	private Button bShowAll;
	private Button sendUpdateMassages;
	private Button removeProduct;
	private ToggleGroup tg;
	private Background buttondBackground;

	private Set<StoreUIListener> allListeners;
	private StoreModelListener modelListener;
	public View(Stage primaryStage) {
		allListeners = new HashSet<StoreUIListener>();

		
		BorderPane bPane = new BorderPane();
		Background background = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
		buttondBackground = new Background(
				new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
		Background navButtonsBackground = new Background(
				new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY));
		bPane.setBackground(background);
		insLabel = new Label("Welcome to the store manager!");
		mainLabel = new Label("Please choose your action from the navigator above.");

		insLabel.setAlignment(Pos.CENTER);
		insLabel.setFont(Font.font("Tahoma", 40));

		mainLabel.setAlignment(Pos.CENTER);
		mainLabel.setFont(Font.font("Tahoma", 20));

		// Navigator Buttons
		bSearch = new Button("Search for a produt");
		bSearch.setPrefSize(200, 40);
		bSearch.setBackground(navButtonsBackground);
		bSort = new Button("Sort the produts");
		bSort.setPrefSize(200, 40);
		bSort.setBackground(navButtonsBackground);
		bAddProduct = new Button("Add a produt");
		bAddProduct.setPrefSize(200, 40);
		bAddProduct.setBackground(navButtonsBackground);
		bShowAll = new Button("Manage all products");
		bShowAll.setPrefSize(200, 40);
		bShowAll.setBackground(navButtonsBackground);

		// Navigator with the buttons
		HBox navBox = new HBox();
		navBox.setPadding(new Insets(35, 12, 15, 12));
		navBox.setSpacing(20);
		bPane.setTop(navBox);
		sendUpdateMassages= new Button ("Send massages");
		sendUpdateMassages.setPrefSize(200, 40);
		sendUpdateMassages.setBackground(navButtonsBackground);
		navBox.getChildren().addAll(bSearch, bSort, bAddProduct, bShowAll,sendUpdateMassages);

		// Right
		VBox rightBox = new VBox();
		rightBox.setPadding(new Insets(35, 150, 15, 12));
		rightBox.setSpacing(30);
		bPane.setRight(rightBox);
		
		// Left
		VBox leftBox = new VBox();
		leftBox.setPadding(new Insets(35, 150, 15, 12));
		leftBox.setSpacing(30);
		bPane.setLeft(leftBox);

		// The center of the BorderPane
		VBox centerBox = new VBox();
		bPane.setCenter(centerBox);
		centerBox.setSpacing(10);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().addAll(insLabel, mainLabel);

		// ********* Components for searching after a product ********
		catalogNumber = new TextField();
		catalogNumber.setPrefSize(200, 60);
		ins1 = new Label("Please insert the catalog number of the product : ");
		ins1.setFont(Font.font("Tahoma", 25));
		searchButtonByCatalogNumber = new Button("Search");
		searchButtonByCatalogNumber.setBackground(buttondBackground);
		searchButtonByCatalogNumber.setPrefSize(300, 50);
		centerBox.getChildren().addAll(ins1, catalogNumber, searchButtonByCatalogNumber);

		// *********Components for sorting a product*********

		ins2 = new Label("Please choose in which way you would want to sort the products");
		ins2.setFont(Font.font("Tahoma", 20));
		tg = new ToggleGroup();
		r1 = new RadioButton("Sort by catalog number in ascending order");
		r2 = new RadioButton("Sort by catalog number in descending order");
		r3 = new RadioButton("Sort by the inserting of the products order.");
		r1.setFont(Font.font("Tahoma", 20));
		r1.setToggleGroup(tg);
		r2.setFont(Font.font("Tahoma", 20));
		r2.setToggleGroup(tg);
		r3.setFont(Font.font("Tahoma", 20));
		r3.setToggleGroup(tg);
		r1.setSelected(true);
		sortButton = new Button("Sort");
		sortButton.setPrefSize(300, 50);
		sortButton.setBackground(buttondBackground);
		centerBox.getChildren().addAll(ins2, r1, r2, r3, sortButton);

		// *********Components for adding a product*********
		ins3 = new Label("Please fill in the following details (You must at least fill the catalog number)");
		ins4 = new Label("Catalog namber:");
		catalog = new TextField();
		catalog.setPrefWidth(10);
		ins5 = new Label("Product name:");
		productName = new TextField();
		ins6 = new Label("Store cost:");
		storeCost = new TextField();
		ins7 = new Label("Selling cost:");
		sellingCost = new TextField();
		ins8 = new Label("Customer name:");
		customerName = new TextField();
		ins9 = new Label("Customer phone:");
		CustomerPhone = new TextField();
		r4 = new RadioButton("Customer wants to get updates about discounts");
		add = new Button("Add");
		centerBox.getChildren().addAll(ins3, ins4, catalog, ins5, productName, ins6, storeCost, ins7, sellingCost, ins8,
				customerName, ins9, CustomerPhone, r4, add);
		add.setBackground(buttondBackground);
		add.setPrefSize(300, 50);
		ins3.setFont(Font.font("Tahoma", 17));
		ins4.setFont(Font.font("Tahoma", 17));
		ins5.setFont(Font.font("Tahoma", 17));
		ins6.setFont(Font.font("Tahoma", 17));
		ins7.setFont(Font.font("Tahoma", 17));
		ins8.setFont(Font.font("Tahoma", 17));
		ins9.setFont(Font.font("Tahoma", 17));
		r4.setFont(Font.font("Tahoma", 18));

		
		bSearch.setOnAction((ActionEvent event) -> {
			// set the view frame
			setBsearchView();
 
			searchButtonByCatalogNumber.setOnAction((ActionEvent event2) -> {
				if (catalogNumber.getText().isEmpty()) {
					Label l = new Label("You cant search for a product without a catalog number!");
					l.setTextFill(Color.RED);
					OpenErrorStage(l);
					return;
				}
				
				String strCatalogNumber = catalogNumber.getText().toString();
				for(StoreUIListener listener : allListeners)
					listener.fireSearchForCatalogeNumber(strCatalogNumber);
				
				removeProduct.setOnAction((ActionEvent event3) -> {
					for(StoreUIListener listener : allListeners)
						listener.fireRemoveProductByCatalogeNumber(strCatalogNumber);
				});
			});
		});
		
		
		tg.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
			int mapType=0;
		    if (r1.isSelected()) {
		    	mapType=1;
		    }
		    else if (r2.isSelected()) {
		    	mapType=2;
		    }
		    else  {
		    	mapType=3;
		    }
		    for(StoreUIListener listener : allListeners)
				listener.fireUpdateMapType(mapType);
		});    
		
		

		sendUpdateMassages.setOnAction((ActionEvent event) -> {

			int res=0;
			 for(StoreUIListener listener : allListeners)
				 // need to fix and get the massage form the gui
					res = listener.fireSendUpdateMassages("Massage");
			if(res==0)
			{
				Label l = new Label("There are no customers who are insetersted in updates!");
				l.setTextFill(Color.RED);
				OpenErrorStage(l);
				return;
			}
			else
			{
				Label l = new Label("Massages has been sent!");
				l.setTextFill(Color.GREEN);
				OpenErrorStage(l);
				return;
			}
		});
		
		

		bSort.setOnAction((ActionEvent event) -> {
			mainLabel.setVisible(false);
			mainLabel.setManaged(false);
			insLabel.setVisible(false);
			insLabel.setManaged(false);
			clearAll();
			ins2.setVisible(true);
			ins2.setManaged(true);
			r1.setVisible(true);
			r1.setManaged(true);
			r2.setVisible(true);
			r2.setManaged(true);
			r3.setVisible(true);
			r3.setManaged(true);
			sortButton.setVisible(true);
			sortButton.setManaged(true);
			sortButton.setOnAction((ActionEvent event2) -> {
				Label l = new Label("Sort type has been selected.");
				l.setTextFill(Color.GREEN);
				OpenErrorStage(l);
				bSort.setDisable(true);
				
				ins2.setVisible(false);
				ins2.setManaged(false);
				r1.setVisible(false);
				r1.setManaged(false);
				r2.setVisible(false);
				r2.setManaged(false);
				r3.setVisible(false);
				r3.setManaged(false);
				sortButton.setVisible(false);
				sortButton.setManaged(false);
				return;
			});
		});
		
		bAddProduct.setOnAction((ActionEvent event) -> {
			clearAll();
			mainLabel.setVisible(false);
			mainLabel.setManaged(false);
			insLabel.setVisible(false);
			insLabel.setManaged(false);
			add.setVisible(true);
			add.setManaged(true);
			ins3.setVisible(true);
			ins3.setManaged(true);
			ins4.setVisible(true);
			ins4.setManaged(true);
			ins5.setVisible(true);
			ins5.setManaged(true);
			ins6.setVisible(true);
			ins6.setManaged(true);
			ins7.setVisible(true);
			ins7.setManaged(true);
			ins8.setVisible(true);
			ins8.setManaged(true);
			ins9.setVisible(true);
			ins9.setManaged(true);
			catalog.setVisible(true);
			catalog.setManaged(true);
			productName.setVisible(true);
			productName.setManaged(true);
			storeCost.setVisible(true);
			storeCost.setManaged(true);
			sellingCost.setVisible(true);
			sellingCost.setManaged(true);
			customerName.setVisible(true);
			customerName.setManaged(true);
			CustomerPhone.setVisible(true);
			CustomerPhone.setManaged(true);
			r4.setVisible(true);
			r4.setManaged(true);

			add.setOnAction((ActionEvent e) -> {
				String catalogNumber = catalog.getText().toString();
				if (catalogNumber.isEmpty()) {
					Label l = new Label("You cant add a new product without a catalog number!");
					l.setTextFill(Color.RED);
					OpenErrorStage(l);
					return;
				}
				String productNameString = productName.getText().toString();
				int costPrice = 0, sellingPrice = 0;
				if (!storeCost.getText().toString().isEmpty()) {
					try {
						costPrice = Integer.parseInt(storeCost.getText().toString());
						sellingPrice = Integer.parseInt(sellingCost.getText().toString());

					} catch (Exception ex) {
						Label l = new Label("Prices needs to be positive namber ! ");
						l.setTextFill(Color.RED);
						OpenErrorStage(l);
						return;
					}
				}
				if (!sellingCost.getText().toString().isEmpty()) {
					try {
						sellingPrice = Integer.parseInt(sellingCost.getText().toString());
					} catch (Exception ex) {
						Label l = new Label("You cant put a string in a selling price field");
						l.setTextFill(Color.RED);
						OpenErrorStage(l);
						return;
					}
				}
				String clientName = customerName.getText().toString();
				String phoneNum = CustomerPhone.getText().toString();
				boolean clientInterested = false;
				if (r4.isSelected())
					clientInterested = true;
				Customer c = new Customer(clientName, phoneNum, clientInterested);
				Product p = new Product(productNameString, costPrice, sellingPrice, c);
				 for(StoreUIListener listener : allListeners)
						listener.fireAddProduct(catalogNumber, p);
				Label l = new Label("Product added sucsessfuly !");
				l.setTextFill(Color.GREEN);
				Button button = new Button ("Cancel");
				button.setPrefSize(100, 30);
				Background b= new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY));
				button.setBackground(b);
				Stage s2=OpenAddingFile(l,button);
				button.setOnAction((ActionEvent event3) -> {
					int res=0;
					 for(StoreUIListener listener : allListeners)
							res = listener.fireremoveLastProduct();
					if(res==0)
					{
						Label ll = new Label("Cant remove this product");
						ll.setTextFill(Color.RED);
						OpenErrorStage(ll);
						return;
					}
					else
					{
						Label l3 = new Label("Operation has been canceled");
						l3.setTextFill(Color.GREEN);
						OpenErrorStage(l3);
						s2.close();
					}
				});
				return;
			});

		});

		
		
		bShowAll.setOnAction((ActionEvent event) -> {
			TableView tableView = new TableView();
			TableColumn<Product, String> c1 = new TableColumn<>("CatalogNum");
			c1.setCellValueFactory(new PropertyValueFactory<>("catalogNum"));
			TableColumn<Product, String> c2 = new TableColumn<>("ProductName");
			c2.setCellValueFactory(new PropertyValueFactory<>("productName"));
			TableColumn<Product, String> c3 = new TableColumn<>("CostPrice");
			c3.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
			TableColumn<Product, String> c4 = new TableColumn<>("SellPrice");
			c4.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
			TableColumn<Product, String> c8 = new TableColumn<>("Profit");
			c8.setCellValueFactory(new PropertyValueFactory<>("profit"));
			TableColumn<Product, String> c5 = new TableColumn<>("CustomerName");
			c5.setCellValueFactory(new PropertyValueFactory<>("customerName"));
			TableColumn<Product, String> c6 = new TableColumn<>("CustomerPhone");
			c6.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
			TableColumn<Product, String> c7 = new TableColumn<>("Intersetd");
			c7.setCellValueFactory(new PropertyValueFactory<>("intersetd"));
			c1.setPrefWidth(160);
			c2.setPrefWidth(160);
			c3.setPrefWidth(160);
			c4.setPrefWidth(160);
			c5.setPrefWidth(160);
			c6.setPrefWidth(160);
			c7.setPrefWidth(160);
			c8.setPrefWidth(160);
			
			ObservableList<TableRows> data = FXCollections.observableArrayList();
		    tableView.getColumns().addAll(c1,c2,c3,c4,c8,c5,c6,c7);
		    VBox vbox = new VBox(tableView);
			ArrayList <TableRows> array = new ArrayList<>();
			Iterator<String> itr = modelListener.getMap().keySet().iterator();
			TableRows t;
	        while (itr.hasNext()) {
	        	String key=itr.next();
	        	Product p =(Product) modelListener.getMap().get(key);
	        	t= new TableRows(key,p.getProductName(),"" +p.getStoreCostPrice(),p.getSellingPrice()+"",""+(p.getSellingPrice()-p.getStoreCostPrice()),p.getCustomer().getName(),
	        			p.getCustomer().getPhoneNum(),p.getCustomer().isWantsUpdates()+"");
	        	data.add(t);
	        }
	        tableView.setItems(data);
		    Button removeAll = new Button("Delete all products.");
		    removeAll.setPrefSize(200, 50);
		    removeAll.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
		    vbox.getChildren().add(removeAll);
		    vbox.setAlignment(Pos.CENTER);
		    vbox.setSpacing(30);
	        Stage stage= openProductsList(vbox);
	        
	        removeAll.setOnAction((ActionEvent event2) -> {
				 for(StoreUIListener listener : allListeners) {
					int res = modelListener.removeAllProducts();
					if (res == 0) {
						Label ll = new Label("There are no products");
						ll.setTextFill(Color.RED);
						OpenErrorStage(ll);
						return;
					} else {
						Label ll = new Label("All products have been removed");
						ll.setTextFill(Color.GREEN);
						OpenErrorStage(ll);
						stage.close();
						return;
					}
				 }
	        });
		});


	    clearAll();
		primaryStage.setScene(new Scene(bPane, 900, 700));
		primaryStage.show();

	}
		




	public void clearAll() {
		add.setVisible(false);
		add.setManaged(false);
		ins3.setVisible(false);
		ins3.setManaged(false);
		ins4.setVisible(false);
		ins4.setManaged(false);
		ins5.setVisible(false);
		ins5.setManaged(false);
		ins6.setVisible(false);
		ins6.setManaged(false);
		ins7.setVisible(false);
		ins7.setManaged(false);
		ins8.setVisible(false);
		ins8.setManaged(false);
		ins9.setVisible(false);
		ins9.setManaged(false);
		catalog.setVisible(false);
		catalog.setManaged(false);
		productName.setVisible(false);
		productName.setManaged(false);
		storeCost.setVisible(false);
		storeCost.setManaged(false);
		sellingCost.setVisible(false);
		sellingCost.setManaged(false);
		customerName.setVisible(false);
		customerName.setManaged(false);
		CustomerPhone.setVisible(false);
		CustomerPhone.setManaged(false);
		r4.setVisible(false);
		r4.setManaged(false);

		r1.setVisible(false);
		r1.setManaged(false);
		ins2.setVisible(false);
		ins2.setManaged(false);
		r1.setVisible(false);
		r1.setManaged(false);
		r2.setVisible(false);
		r2.setManaged(false);
		r3.setVisible(false);
		r3.setManaged(false);
		sortButton.setVisible(false);
		sortButton.setManaged(false);
		// ALL WE NEED TO SHOW
		ins1.setVisible(false);
		ins1.setManaged(false);
		catalogNumber.setVisible(false);
		catalogNumber.setManaged(false);
		searchButtonByCatalogNumber.setVisible(false);
		searchButtonByCatalogNumber.setManaged(false);
	}

	public Stage OpenErrorStage(Label l) {
		Stage errorStage= new Stage();
		StackPane pane= new StackPane();
		pane.getChildren().addAll(l);
		errorStage.setScene(new Scene(pane, 400,200));		
		errorStage.show();
		return errorStage;
	}

	public Stage OpenProductDetailsStage(Label l, Label pName, Label cost, Label sell, Label customerName2,
			Label phone, Label interested,Label profit) {
		Stage productStage= new Stage();
		VBox pane= new VBox();
		pane.setSpacing(10);
		removeProduct= new Button("Remove");
		removeProduct.setPrefSize(200, 40);
		removeProduct.setBackground(buttondBackground);
		pane.getChildren().addAll(l,pName,cost,sell,customerName2,profit,phone, interested,removeProduct);
		productStage.setScene(new Scene(pane, 400,500));		
		productStage.show();
		return productStage;
		
	}

	public Stage OpenAddingFile(Label l, Button b) {
		Stage errorStage= new Stage();
		VBox pane= new VBox();
		pane.getChildren().addAll(l,b);
		pane.setSpacing(10);
		errorStage.setScene(new Scene(pane, 400,200));		
		errorStage.show();
		return errorStage;
		
	}

	public Stage openProductsList(VBox vBox) {
	    Stage products = new Stage();
		products.setScene(new Scene(vBox, 1250,800));		
		products.show();
		return products;
	}

	@Override
	public void registerListener(StoreController controller) {
		allListeners.add(controller);
		this.modelListener = controller;
	}


	public void setFindedProduct(Product p) {
		if (p == null) {
			Label l = new Label("No such product..");
			l.setTextFill(Color.RED);
			OpenErrorStage(l);
			return;
		}
		Label l = new Label("Product has been found:");
		l.setTextFill(Color.GREEN);
		Label pName = new Label();
		pName.setText("Product name: " + p.getProductName());
		Label cost = new Label();
		cost.setText("The cost for store: " + p.getStoreCostPrice());
		Label sell = new Label();
		sell.setText("The selling price to customer: " + p.getSellingPrice());
		Label customerName = new Label();
		customerName.setText("Customer's name: " + p.getCustomer().getName());
		Label customerPhone = new Label();
		customerPhone.setText("Customer's phone: " + p.getCustomer().getPhoneNum());
		Label isInterested = new Label();
		isInterested.setText("Wants updates about discount " + p.getCustomer().isWantsUpdates());
		Label profit = new Label();
		profit.setText("Clean profit is : " + (p.getSellingPrice() - p.getStoreCostPrice()));
		Stage s=OpenProductDetailsStage(l, pName, cost, sell, customerName, customerPhone, isInterested, profit);
		catalogNumber.clear();		
	}

	private void setBsearchView() {
		
		mainLabel.setVisible(false);
		mainLabel.setManaged(false);
		insLabel.setVisible(false);
		insLabel.setManaged(false);
		clearAll();
		ins1.setVisible(true);
		ins1.setManaged(true);
		catalogNumber.setVisible(true);
		catalogNumber.setManaged(true);
		searchButtonByCatalogNumber.setVisible(true);
		searchButtonByCatalogNumber.setManaged(true);		
	}
	
	public void setRemoveProductResults(int res) {
		Label l2 = new Label();
		switch(res)
		{
		case 1:
			 l2.setText("Product has been removed ");
			l2.setTextFill(Color.GREEN);
			OpenErrorStage(l2);
			break;
			
		case -1:
			l2.setText("Product has been removed ");
			l2.setTextFill(Color.GREEN);
			OpenErrorStage(l2);
			break;
		}
			
		
	}

}

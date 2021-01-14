package Controller;

import Model.Store;
import View.View;
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

public class Controller {
	private Store model;
	private View view;

	public Controller(Store model, View view) {
		this.view = view;
		this.model = model;
		view.bSearch.setOnAction((ActionEvent event) -> {
			view.mainLabel.setVisible(false);
			view.mainLabel.setManaged(false);
			view.insLabel.setVisible(false);
			view.insLabel.setManaged(false);
			view.clearAll();
			view.ins1.setVisible(true);
			view.ins1.setManaged(true);
			view.catalogNumber.setVisible(true);
			view.catalogNumber.setManaged(true);
			view.searchButtonByCatalogNumber.setVisible(true);
			view.searchButtonByCatalogNumber.setManaged(true);

			view.searchButtonByCatalogNumber.setOnAction((ActionEvent event2) -> {
				String catalogNumber = view.catalogNumber.getText().toString();
				if (catalogNumber.isEmpty()) {
					Label l = new Label("You cant search for a product without a catalog number!");
					l.setTextFill(Color.RED);
					view.OpenErrorStage(l);
					return;
				}
				Product p = model.fintProduct(catalogNumber);
				if (p == null) {
					Label l = new Label("No such product..");
					l.setTextFill(Color.RED);
					view.OpenErrorStage(l);
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
				Stage s=view.OpenProductDetailsStage(l, pName, cost, sell, customerName, customerPhone, isInterested, profit);
				view.catalogNumber.clear();
				
				view.removeProduct.setOnAction((ActionEvent event3) -> {
					model.remove(catalogNumber);
					Label l2 = new Label("Product has been removed ");
					l.setTextFill(Color.GREEN);
					view.OpenErrorStage(l2);
					s.close();
				});
				
				
			});

		});
		
		view.tg.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
		    if (view.r1.isSelected()) {
		        model.updateMapType(1);
		    }
		    else if (view.r2.isSelected()) {
		        model.updateMapType(2);
		    }
		    else  {
		    	model.updateMapType(3);
		    }
		});    
		
		
		view.loadFromFile.setOnAction((ActionEvent event) -> {
			int res= model.readProductsFromBinaryFile();
			if(res==0)
			{
				Label l = new Label("There are no products inside the file! ");
				l.setTextFill(Color.RED);
				view.OpenErrorStage(l);
				return;
			}
			else
			{
				Label l = new Label("Reading from file was sucssessful.");
				l.setTextFill(Color.GREEN);
				view.OpenErrorStage(l);
				view.loadFromFile.setDisable(true);
				return;
			}
		});

		view.bSort.setOnAction((ActionEvent event) -> {
			view.mainLabel.setVisible(false);
			view.mainLabel.setManaged(false);
			view.insLabel.setVisible(false);
			view.insLabel.setManaged(false);
			view.clearAll();
			view.ins2.setVisible(true);
			view.ins2.setManaged(true);
			view.r1.setVisible(true);
			view.r1.setManaged(true);
			view.r2.setVisible(true);
			view.r2.setManaged(true);
			view.r3.setVisible(true);
			view.r3.setManaged(true);
			view.sortButton.setVisible(true);
			view.sortButton.setManaged(true);
			view.sortButton.setOnAction((ActionEvent event2) -> {
				Label l = new Label("Sort type has been selected.");
				l.setTextFill(Color.GREEN);
				view.OpenErrorStage(l);
				view.bSort.setDisable(true);
				return;
			});
		});

		view.bAddProduct.setOnAction((ActionEvent event) -> {
			view.clearAll();
			view.mainLabel.setVisible(false);
			view.mainLabel.setManaged(false);
			view.insLabel.setVisible(false);
			view.insLabel.setManaged(false);
			view.add.setVisible(true);
			view.add.setManaged(true);
			view.ins3.setVisible(true);
			view.ins3.setManaged(true);
			view.ins4.setVisible(true);
			view.ins4.setManaged(true);
			view.ins5.setVisible(true);
			view.ins5.setManaged(true);
			view.ins6.setVisible(true);
			view.ins6.setManaged(true);
			view.ins7.setVisible(true);
			view.ins7.setManaged(true);
			view.ins8.setVisible(true);
			view.ins8.setManaged(true);
			view.ins9.setVisible(true);
			view.ins9.setManaged(true);
			view.catalog.setVisible(true);
			view.catalog.setManaged(true);
			view.productName.setVisible(true);
			view.productName.setManaged(true);
			view.storeCost.setVisible(true);
			view.storeCost.setManaged(true);
			view.sellingCost.setVisible(true);
			view.sellingCost.setManaged(true);
			view.customerName.setVisible(true);
			view.customerName.setManaged(true);
			view.CustomerPhone.setVisible(true);
			view.CustomerPhone.setManaged(true);
			view.r4.setVisible(true);
			view.r4.setManaged(true);

			view.add.setOnAction((ActionEvent e) -> {
				String catalogNumber = view.catalog.getText().toString();
				if (catalogNumber.isEmpty()) {
					Label l = new Label("You cant add a new product without a catalog number!");
					l.setTextFill(Color.RED);
					view.OpenErrorStage(l);
					return;
				}
				String productName = view.productName.getText().toString();
				double costPrice = 0, sellingPrice = 0;
				if (!view.storeCost.getText().toString().isEmpty()) {
					try {
						costPrice = Integer.parseInt(view.storeCost.getText().toString());
						sellingPrice = Integer.parseInt(view.sellingCost.getText().toString());

					} catch (Exception ex) {
						Label l = new Label("Prices needs to be positive namber ! ");
						l.setTextFill(Color.RED);
						view.OpenErrorStage(l);
						return;
					}
				}
				if (!view.sellingCost.getText().toString().isEmpty()) {
					try {
						sellingPrice = Integer.parseInt(view.sellingCost.getText().toString());
					} catch (Exception ex) {
						Label l = new Label("You cant put a string in a selling price field");
						l.setTextFill(Color.RED);
						view.OpenErrorStage(l);
						return;
					}
				}
				String clientName = view.customerName.getText().toString();
				String phoneNum = view.CustomerPhone.getText().toString();
				boolean clientInterested = false;
				if (view.r4.isSelected())
					clientInterested = true;
				Customer c = new Customer(clientName, phoneNum, clientInterested);
				Product p = new Product(productName, costPrice, sellingPrice, c);
				model.addProduct(catalogNumber, p);
				Label l = new Label("Product added sucsessfuly !");
				l.setTextFill(Color.GREEN);
				Button button = new Button ("Cancel");
				button.setPrefSize(100, 30);
				Background b= new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY));
				button.setBackground(b);
				Stage s2=view.OpenAddingFile(l,button);
				button.setOnAction((ActionEvent event3) -> {
					int res= model.removeLastProduct();
					if(res==0)
					{
						Label ll = new Label("Cant remove this product");
						ll.setTextFill(Color.RED);
						view.OpenErrorStage(ll);
						return;
					}
					else
					{
						Label l3 = new Label("Operation has been canceled");
						l3.setTextFill(Color.GREEN);
						view.OpenErrorStage(l3);
						s2.close();
					}
				});
				return;
			});

		});

		view.bShowAll.setOnAction((ActionEvent event) -> {
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
			Iterator<String> itr = model.getMap().keySet().iterator();
			TableRows t;
	        while (itr.hasNext()) {
	        	String key=itr.next();
	        	Product p =(Product) model.getMap().get(key);
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
	        Stage stage= view.openProductsList(vbox);
	        
	        removeAll.setOnAction((ActionEvent event2) -> {
	        	int res = model.removeAllProducts();
	        	if(res==0)
	        	{
	        		Label ll = new Label("There are no products");
					ll.setTextFill(Color.RED);
					view.OpenErrorStage(ll);
					return;
	        	}
	        	else {
	        		Label ll = new Label("All products have been removed");
					ll.setTextFill(Color.GREEN);
					view.OpenErrorStage(ll);
					stage.close();
					return;
	        	}
	        });
		});
		

	}
	
}

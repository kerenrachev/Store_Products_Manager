package View;

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

public class View {
	public Button add;
	public Label ins1;
	public Label ins2;
	public Label ins3;
	public Label ins4;
	public Label ins5;
	public Label ins6;
	public Label ins7;
	public Label ins8;
	public Label ins9;
	public TextField catalog;
	public TextField productName;
	public TextField storeCost;
	public TextField sellingCost;
	public TextField customerName;
	public TextField CustomerPhone;
	public Label mainLabel;
	public Label insLabel;
	public TextField catalogNumber;
	public Button searchButtonByCatalogNumber;
	public RadioButton r1;
	public RadioButton r2;
	public RadioButton r3;
	public RadioButton r4;
	public Button sortButton;
	public Button bSearch;
	public Button bSort;
	public Button bAddProduct;
	public Button bShowAll;
	public Button loadFromFile;
	public Button removeProduct;
	public ToggleGroup tg;
	private Background buttondBackground;
	public View(Stage primaryStage) {
		BorderPane bPane = new BorderPane();
		Background b = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
		buttondBackground = new Background(
				new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
		Background navButtonsBackground = new Background(
				new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY));
		bPane.setBackground(b);
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
		loadFromFile= new Button ("Load all from file");
		loadFromFile.setPrefSize(200, 40);
		loadFromFile.setBackground(navButtonsBackground);
		navBox.getChildren().addAll(bSearch, bSort, bAddProduct, bShowAll,loadFromFile);

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
		// *********Component for showing all the products*********


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

}

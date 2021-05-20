package main.product;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import main.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProductsC implements Initializable{

	String cb;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane pane;

    @FXML
    private TextField search;

    @FXML
    private JFXComboBox<String> combosearch;

    @FXML
    private JFXButton addnew;

    @FXML
    private HBox refresh;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, Integer> id;

    @FXML
    private TableColumn<Product, String> pname;

    @FXML
    private TableColumn<Product, Float> dtouch;

    @FXML
    private TableColumn<Product, Float> dlabour;

    @FXML
    private TableColumn<Product, String> ev;

    ObservableList<Product> list;
    int index = -1;
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
///////////////////////////////////////////////////////////////////////////////////
    //add product
    
    
    public void add(ActionEvent event) {

    		try {
			
			Parent root=FXMLLoader.load(getClass().getResource("/main/product/Addproduct.fxml"));
			
			Scene scene = new Scene(root);	
			Stage stage=new Stage();
			stage.setTitle("Add Product");			
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(pane.getScene().getWindow());
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
    }

   
/////////////////////////////////////////////////////////////////////////////
    //table view
    
    
    public void update() {
    	
    	Connection conn = DB.dbconnect();
		ObservableList<Product> list= FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps= conn.prepareStatement("SELECT * FROM `productdetails` WHERE 1");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				list.add(new Product(Integer.parseInt(rs.getString("productid")), rs.getString("productname"),Float.parseFloat(rs.getString("dtouch")), Float.parseFloat(rs.getString("dlabour"))));
				
			}
		} catch (Exception e) {
			Alert a3= new Alert(Alert.AlertType.INFORMATION);
			a3.setTitle("data adding error");
			a3.showAndWait();
			
		}
		
	table.setItems(list);
	id.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
	pname.setCellValueFactory(new PropertyValueFactory<Product, String>("pname"));
	dtouch.setCellValueFactory(new PropertyValueFactory<Product, Float>("dtouch"));
	dlabour.setCellValueFactory(new PropertyValueFactory<Product, Float>("dlabour"));
	
	
/////////////////////////////////////////////////////////////////////////////////////
	
	
		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Product> filteredData = new FilteredList<>(list, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.


		System.out.println(cb);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(Product -> {
		// If filter text is empty, display all persons.


			if (newValue == null || newValue.isEmpty()) {
				return true;
			}

			else
			{
				System.out.println(cb);	
				
// 	Compare first name and last name of every person with filter text.
				if(cb.equals("All")) {

					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(Product.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
						return true; // Filter matches first name.
					} else if (Product.getPname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					}
					else if (String.valueOf(Product.getDtouch()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
						return true;
					else if (String.valueOf(Product.getDlabour()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
						return true;
					else  
						return false; // Does not match.
				}
				
				else if (cb.equals("Product's ID")) {
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(Product.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
						return true; // Filter matches first name.
					} 
					else  
						return false; // Does not match.
					
				}
				
				else if(cb.equals("Product's Name")) {
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(Product.getPname()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
						return true;
					else  
						return false; // Does not match.
					
				}
				
				else if(cb.equals("Product's Touch")) {
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(Product.getDtouch()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
						return true;
					else  
						return false; // Does not match.
					
				}
				else if(cb.equals("Product's Labour")) {
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(Product.getDlabour()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
						return true;
					else  
						return false; // Does not match.
					
				}
				
			}
			return false;
			
		});
		});

// 	3. Wrap the FilteredList in a SortedList. 
		SortedList<Product> sortedData = new SortedList<>(filteredData);
		
// 	4. Bind the SortedList comparator to the TableView comparator.
// 	  	Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
// 	5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
		
    	}
    
    
    
    
//////////////////////////////////////////////////////////////////////////////
    
    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		update();
		
		combosearch.getItems().add("All");
		combosearch.getItems().add("Product's ID");
		combosearch.getItems().add("Product's Name");
		combosearch.getItems().add("Product's Touch");
		combosearch.getItems().add("Product's Labour");
		cb="All";
		
		combosearch.setOnAction(e -> cb=combosearch.getValue());
	}
}

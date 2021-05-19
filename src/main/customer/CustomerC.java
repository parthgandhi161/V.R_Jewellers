package main.customer;


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

public class CustomerC implements Initializable{
	
	@FXML
	private BorderPane mainpane;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField search;

    @FXML
    private JFXComboBox<String> combosearch;

    @FXML
    private JFXButton addnew;

    @FXML
    private HBox refresh;

    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, Integer> id;

    @FXML
    private TableColumn<Customer, String> cname;

    @FXML
    private TableColumn<Customer, String> sname;

    @FXML
    private TableColumn<Customer, String> sadd;

    @FXML
    private TableColumn<Customer, Integer> conno;

    @FXML
    private TableColumn<Customer, String> ev;
    
    String cb="All";
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////
    //add customer
    
    public void add(ActionEvent event) {
    	
    		try {
			
			Parent root=FXMLLoader.load(getClass().getResource("/main/customer/AddCustomer.fxml"));
			
			Scene scene = new Scene(root);	
			Stage stage=new Stage();
			stage.setTitle("Add Customer");			
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(mainpane.getScene().getWindow());
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /////////////////////////////////////////////////////////
    
    // table view
    
    ObservableList<Customer> list;
    int index = -1;
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void update()
	{
		Connection conn = DB.dbconnect();
		ObservableList<Customer> list= FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps= conn.prepareStatement("SELECT * FROM `customerdetails` WHERE 1");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				list.add(new Customer(Integer.parseInt(rs.getString("customerid")), rs.getString("custname"), rs.getString("shopname"), rs.getString("shopadd"), Integer.parseInt(rs.getString("contactno"))));
				
			}
		} catch (Exception e) {
			Alert a3= new Alert(Alert.AlertType.INFORMATION);
			a3.setTitle("data adding error");
			a3.showAndWait();
			
		}
		
	table.setItems(list);
	id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
	cname.setCellValueFactory(new PropertyValueFactory<Customer, String>("cname"));
	sname.setCellValueFactory(new PropertyValueFactory<Customer, String>("sname"));
	sadd.setCellValueFactory(new PropertyValueFactory<Customer, String>("sadd"));
	conno.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("conno"));
	
	
////////////////////////////////////////////////////////////////////////////////////
	

	
	
/////////////////////////////////////////////////////////////////////////////////////
	
	
	// Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<Customer> filteredData = new FilteredList<>(list, b -> true);
	
	// 2. Set the filter Predicate whenever the filter changes.
	
		
		System.out.println(cb);
    search.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(customer -> {
			// If filter text is empty, display all persons.
						
			
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			else
			{
				System.out.println(cb);	
				
				// Compare first name and last name of every person with filter text.
			if(cb.equals("All")) {
				
			String lowerCaseFilter = newValue.toLowerCase();
			
			if (String.valueOf(customer.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
				return true; // Filter matches first name.
			} else if (customer.getCname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; // Filter matches last name.
			}
			else if (customer.getSname().toLowerCase().indexOf(lowerCaseFilter)!=-1)
			     return true;
			else if (customer.getSadd().toLowerCase().indexOf(lowerCaseFilter)!=-1)
			     return true;
			else if (String.valueOf(customer.getConno()).toLowerCase().indexOf(lowerCaseFilter)!=-1)
			     return true;
			     else  
			    	 return false; // Does not match.
			}
			
			else if (cb.equals("Customer's ID")) {
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(customer.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				     else  
				    	 return false; // Does not match.
				
			}
			
			else if(cb.equals("Customer's Name")) {
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (customer.getCname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
				
			}
			
			else if(cb.equals("Customer's Shop Name")) {
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (customer.getSname().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				else  
				    	 return false; // Does not match.
							
			}
			
			}
			return false;
			
			});
	});
	
	
	
	/*if (cb.equals("Customer's ID")) {
	// search by id
	search.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(customer -> {
			// If filter text is empty, display all persons.
							
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			// Compare first name and last name of every person with filter text.
			
		});
	});
	
	}
	
	else if(cb.equals("Customer's Name")) {
	// search by name
	
	search.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(customer -> {
			// If filter text is empty, display all persons.
							
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			// Compare first name and last name of every person with filter text.
			
		});
	});
	
	}
	
	else if(cb.equals("Customer's Shop Name")) {
	// search by shop name
	
	search.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(customer -> {
			// If filter text is empty, display all persons.
							
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			// Compare first name and last name of every person with filter text.
			
		});
	});*/
	
	
	
	// 3. Wrap the FilteredList in a SortedList. 
	SortedList<Customer> sortedData = new SortedList<>(filteredData);
	
	// 4. Bind the SortedList comparator to the TableView comparator.
	// 	  Otherwise, sorting the TableView would have no effect.
	sortedData.comparatorProperty().bind(table.comparatorProperty());
	
	// 5. Add sorted (and filtered) data to the table.
	table.setItems(sortedData);
	

	
	}
    
    public void initialize(URL location, ResourceBundle resources) {
		
			System.out.println("ini cust");
    		update();
			
    		System.out.println("done update");
    		
			combosearch.getItems().add("All");
			combosearch.getItems().add("Customer's ID");
			combosearch.getItems().add("Customer's Name");
			combosearch.getItems().add("Customer's Shop Name");
			
			System.out.println("done combo");
			
			combosearch.setOnAction(e -> cb=combosearch.getValue());
			
			
			
		
		
	}

}

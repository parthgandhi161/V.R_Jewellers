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
    private BorderPane mainpane;

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
    
    
    void add(ActionEvent event) {

    		try {
			
			Parent root=FXMLLoader.load(getClass().getResource("/main/product/Addproduct.fxml"));
			
			Scene scene = new Scene(root);	
			Stage stage=new Stage();
			stage.setTitle("Add Product");			
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

   
/////////////////////////////////////////////////////////////////////////////
    //table view
    
    
    void update() {
    	
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
		
		
		combosearch.setOnAction(e -> cb=combosearch.getValue());
	}
}

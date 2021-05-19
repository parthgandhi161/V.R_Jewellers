package main.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.DB;

public class AddCustomerC {

	@FXML
	private BorderPane mainpane;
	@FXML
	private TextField cname;
	@FXML
	private TextField sname;
	@FXML
	private TextField sadd;
	@FXML
	private TextField conno;
	@FXML
	private Button saveb;
	@FXML
	private Button cancelb;
	@FXML
	private Label errorlabel;
	
	String custname,shopname,shopadd;
	String contno;
	int contactno;
	Connection con;
	int nc=1;
	int n;
	
	public void add(ActionEvent event) throws SQLException {
	
		
	contno=conno.getText();
	custname=cname.getText();
	shopname=sname.getText();
	shopadd=sadd.getText();
	
	n=contno.length();
	
	for (int i = 0; i < n; i++) {
		  
        
        if (contno.charAt(i) >= '0'
            && contno.charAt(i) <= '9') {
            
        }
        else {
            nc=0;
        }
	}
	n=0;
	if(contno.equals("")||custname.equals("")||shopname.equals("")||shopadd.equals(""))
	{
		errorlabel.setText("Please fill all details correctly.");
	}
	else if (contno. length()<10 || contno.length()>10) {
		
		errorlabel.setText("Please fill all details correctly.");
		
	}
	else if(nc==0) {
		
		errorlabel.setText("Please fill all details correctly.");
		nc=1;
	}
	else
	{	
	
	contactno=Integer.parseInt(contno);
	
	Connection con = DB.dbconnect();
	PreparedStatement pst=con.prepareStatement("insert into customerdetails(custname,shopname,shopadd,contactno) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
	pst.setString(1, custname);
	pst.setString(2, shopname);
	pst.setString(3, shopadd);
	pst.setInt(4, contactno);
	pst.executeUpdate();
	
	
	ResultSet rs = pst.getGeneratedKeys();
	if(rs.next()) 
	{
		String id=rs.getString(1);
		Alert a1= new Alert(Alert.AlertType.INFORMATION);
		a1.setTitle("customer Added");
		a1.setHeaderText(custname+" is added successfully.\nCustomer ID is "+id);
		a1.showAndWait();
	}
	
	con.close();
	Stage stage = (Stage) mainpane.getScene().getWindow();
	stage.close();

	}
	}
	

	
	public void cancel(ActionEvent event) throws SQLException {
		
		Stage stage = (Stage) mainpane.getScene().getWindow();
		stage.close();
	}
}

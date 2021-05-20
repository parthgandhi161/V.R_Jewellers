package main.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddproductC {
	
	
	@FXML
	private BorderPane mainpane;
	@FXML
	private TextField dtouch;
	@FXML
	private TextField dlabour;
	@FXML
	private TextField productname;
	@FXML
	private Button saveb;
	@FXML
	private Button cancelb;
	@FXML
	private Label errorlabel;
	
	public void add(ActionEvent event) throws SQLException
	{
	
		String pn,dt,dl;
	
	
	pn=productname.getText();
	dt=dtouch.getText();
	dl=dlabour.getText();
	
	
	if(pn.equals("")||dt.equals("")||dl.equals(""))
	{
		errorlabel.setText("Please fill all details correctly.");
	}
	else
	{
	
	Connection con = DB.dbconnect();
	PreparedStatement pst=con.prepareStatement("insert into productdetails(productname,dtouch,dlabour) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
	pst.setString(1, pn);
	pst.setString(2, dt);
	pst.setString(3, dl);
	pst.executeUpdate();
	
	ResultSet rs = pst.getGeneratedKeys();
	if(rs.next()) 
	{
		String pid=rs.getString(1);
		Alert a1= new Alert(Alert.AlertType.INFORMATION);
		a1.setTitle("customer Added");
		a1.setHeaderText(pn+" is added successfully.\nProduct ID is "+pid);
		a1.showAndWait();
	}
	con.close();
	Stage stage = (Stage) mainpane.getScene().getWindow();
	stage.close();
	}	

}

public void cancel(ActionEvent event)
{
	Stage stage = (Stage) mainpane.getScene().getWindow();
	stage.close();
}

}

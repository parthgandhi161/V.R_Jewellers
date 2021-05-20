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
	
	String pn,dt,dl;
	int tnc=1,lnc=1,n,n1;
	Character dot='.';
	int tdotcount=0;
	int ldotcount=0;
	
	public void add(ActionEvent event) throws SQLException
	{
	
		
	
	
	pn=productname.getText();
	dt=dtouch.getText();
	dl=dlabour.getText();
	
	
	//if(pn.equals("")||dt.equals("")||dl.equals("")) {	
		
		tdotcount=0;
		n = dt.length();  
	
	
		for (int i = 0; i < n; i++) 
		{
		  
        
			if (dt.charAt(i) >= '0' && dt.charAt(i) <= '9'|| dot.equals(dt.charAt(i))) {
        	
        	
				if(dot.equals(dt.charAt(i)) && tdotcount==0) 
				{
					tdotcount=1;
				}
				else if(tdotcount>=1)
					tnc=0;
            
			}
			else 
			{
            tnc=0;
			}
		}
	
	
	
	ldotcount=0;
	n1=dl.length();
	
	
	for (int i = 0; i < n1; i++) {
		  
        
        if (dl.charAt(i) >= '0'&& dl.charAt(i) <= '9'|| dot.equals(dl.charAt(i))) {
        	
        	if(dot.equals(dl.charAt(i)) && ldotcount==0) {
        		ldotcount=1;
        	}
			else if(ldotcount>=1)
				lnc=0;
            
        }
        else {
            lnc=0;
        }
	}
	
	
	if(pn.equals("")||dt.equals("")||dl.equals(""))
	{
		errorlabel.setText("Please fill all details correctly.");
	}
	else if(lnc==0||tnc==0) {	
		
	////////////////////////////////////////////////////////////////////	
		errorlabel.setText("Please fill all details correctly.");
		lnc=1;
		tnc=1;
   }
	else
	{
	
	float dtf = Float.parseFloat(dt);
	float dlf = Float.parseFloat(dl);
	Connection con = DB.dbconnect();
	PreparedStatement pst=con.prepareStatement("insert into productdetails(productname,dtouch,dlabour) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
	pst.setString(1, pn);
	pst.setFloat(2, dtf);
	pst.setFloat(3, dlf);
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

package main.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.DB;

public class LoginC {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
    private TextField uname;

    @FXML
    private PasswordField pass;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton close;
    
    @FXML
	private Label error;
    
    public void login(ActionEvent event) throws Exception
	{
		//String aun="1",aps="2";
    	int pc=0;
		
    	
		//veryfication 
    	DB.dbconnect();
    	Connection conn = DB.dbconnect();
    	
    	if(conn==null) {	
			
			Alert netc=new Alert(Alert.AlertType.ERROR);
			netc.setTitle("Error");
			netc.setHeaderText("Check your Internet connection and try it again");
			netc.showAndWait();
		}
    	
    	else {
    	
    	PreparedStatement ps= conn.prepareStatement("SELECT * FROM `userlogin` WHERE Username='"+uname.getText()+"' AND PASSWORD=md5('"+pass.getText()+"')");
    	ResultSet count=ps.executeQuery();
    	
    	while (count.next())
    	{
    		pc++;
    	}
		
		//if (aun.equals(uname.getText()) && aps.equals(pass.getText()))
		
    	if(pc==1)
    	{	
		
		//close currunt window
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
    	
		
		//new window
		
		try {
			
			Parent root=FXMLLoader.load(getClass().getResource("/main/dashboardController/Dashboard.fxml"));
			
			Scene scene = new Scene(root);	
			Stage stage1=new Stage();
			stage1.setTitle("DashBoard");			
			stage1.setScene(scene);
			stage1.setMaximized(true);
			stage1.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}	
		}
		
		{
			error.setText("Please Check Your Username & Password.");
		}
    
    	}
	}
	
	
	public void close(ActionEvent event)
	{
		System.exit(0);
	}
}




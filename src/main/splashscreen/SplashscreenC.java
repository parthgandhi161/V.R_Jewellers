package main.splashscreen;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.DB;

public class SplashscreenC implements Initializable {
	
	String string="Loading...";
	int i = 0;
	
	private double xOffset;
    private double yOffset;
    
	@FXML
	private AnchorPane pane;
	@FXML
    private Label loading;

    private ScheduledExecutorService executorservice;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("inisializ start");
		inischeduler();
	}

	private void inischeduler() {
		executorservice=Executors.newSingleThreadScheduledExecutor();
		executorservice.scheduleWithFixedDelay(this:: loaddata, 0,100, TimeUnit.MILLISECONDS);
		
		System.out.println("inis start");
		
	}

	private void loaddata() {
		
		javafx.application.Platform.runLater(() -> {
			try {
				data();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private void data() throws InterruptedException {	
		
		
		
		
		i++;
		
		loading.setText(string+"  "+i+"%");
		
		if (i==8) {
			
			string="Loading Modules...";
		}
		else if(i==33)
		{
			string="Initializing Modules...";
		}
		else if(i==87)
		{
			string="Connecting DataBase...";	    	
		}
		else if(i==90)
		{
			string="Checking DataBase Connection...";
			
				DB.dbconnect();
				Connection conn = DB.dbconnect();
			
			if(conn==null) {	
				
				Alert netc=new Alert(Alert.AlertType.ERROR);
				netc.setTitle("Error");
				netc.setHeaderText("Check your Internet connection and try it again");
				netc.showAndWait();
				System.exit(0);
			}
			else
			{
				executorservice.shutdownNow();
				Stage stage = (Stage) pane.getScene().getWindow();
				stage.close();
				
				//new window
				
				try {
					
					Parent root=FXMLLoader.load(getClass().getResource("/main/login/login.fxml"));
					
					Scene scene1 = new Scene(root);	
					Stage stage1=new Stage();
					stage1.setTitle("login");			
					stage1.setScene(scene1);
					stage1.initStyle(StageStyle.UNDECORATED);
					stage1.show();
					
					
					{
						Stage secondaryStage = new Stage();
				        secondaryStage.initStyle(StageStyle.UNDECORATED);
				        secondaryStage.initOwner(stage1);

				        secondaryStage.setScene(scene1);
				        secondaryStage.show();

				 

				        //Add support for drag and move
				        //Drag = mouse click + drag
				        scene1.setOnMousePressed(event -> {
				            xOffset = secondaryStage.getX() - event.getScreenX();
				            yOffset = secondaryStage.getY() - event.getScreenY();
				        });
				        scene1.setOnMouseDragged(event -> {
				            secondaryStage.setX(event.getScreenX() + xOffset);
				            secondaryStage.setY(event.getScreenY() + yOffset);
				        });
					}
					
					}
					catch(Exception e) {
						e.printStackTrace();
					}	
				}
				
				
			}
		else if(i>90)
		{
			i--;
		}
		
		}
		
	}
	



package main.dashboardController;

import java.net.URL;
import main.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class MenuitemLoader {///VR_Jewellers/src/main/.fxml
	
	private Pane view;
	public Pane getpage(String filename)
	{
		try
		{
		
		URL fileurl = Main.class.getResource("/main/" +filename+".fxml");
		if (fileurl == null) 
		{
			throw new java.io.FileNotFoundException("Fxml File Can't be found");
			
		}
		new FXMLLoader();
		view = FXMLLoader.load(fileurl);
		
		} catch (Exception e) {
			System.out.println("no page"  +" " +filename+" "+ "please check menu item loader.");
		}
		
		return view;
	}	
		

}

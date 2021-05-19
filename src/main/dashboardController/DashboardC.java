package main.dashboardController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.login.LoginC;

public class DashboardC implements Initializable {
	
	@FXML
	private BorderPane mainpane;
	
	@FXML
    private Label price;

    @FXML
    private ImageView notification;

    @FXML
    private ImageView settings;

    @FXML
    private HBox home;

    @FXML
    private HBox dashboard;

    @FXML
    private HBox orders;

    @FXML
    private HBox customers;

    @FXML
    private HBox products;

    @FXML
    private HBox help;

    @FXML
    private HBox exit;
    
    int i=0;
    
    private ScheduledExecutorService executorservice;

	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/////////////////////////////////////////////////////////////////////
    
    
    
    public void showhome(MouseEvent event)
	{
		
		mainpane.setCenter(null);
		
		dashboard.setStyle(null);
		home.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
		orders.setStyle(null);
		customers.setStyle(null);
		products.setStyle(null);
		help.setStyle(null);
	}
	public void showdashboard(MouseEvent event)
	{
		MenuitemLoader object = new MenuitemLoader();
		Pane view = object.getpage("dashboard/Dashborad");
		mainpane.setCenter(view);
		dashboard.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
		home.setStyle(null);
		orders.setStyle(null);
		customers.setStyle(null);
		products.setStyle(null);
		help.setStyle(null);
	}
	public void showorders(MouseEvent event)
	{
		MenuitemLoader object = new MenuitemLoader();
		Pane view = object.getpage("orders/Orders");
		mainpane.setCenter(view);	
		
		dashboard.setStyle(null);
		home.setStyle(null);
		orders.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
		customers.setStyle(null);
		products.setStyle(null);
		help.setStyle(null);
	}
	public void showcustomers(MouseEvent event)
	{
		MenuitemLoader object = new MenuitemLoader();
		Pane view = object.getpage("customer/Customers");
		mainpane.setCenter(view);
		
		dashboard.setStyle(null);
		home.setStyle(null);
		orders.setStyle(null);
		customers.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
		products.setStyle(null);
		help.setStyle(null);
	}
	public void showproducts(MouseEvent event)
	{
		MenuitemLoader object = new MenuitemLoader();
		Pane view = object.getpage("product/Products");
		mainpane.setCenter(view);
		
		dashboard.setStyle(null);
		home.setStyle(null);
		orders.setStyle(null);
		customers.setStyle(null);
		products.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
		help.setStyle(null);
	}
	public void showabout(MouseEvent event)
	{
		MenuitemLoader object = new MenuitemLoader();
		Pane view = object.getpage("about/AboutSoftware");
		mainpane.setCenter(view);	
		
		dashboard.setStyle(null);
		home.setStyle(null);
		orders.setStyle(null);
		customers.setStyle(null);
		products.setStyle(null);
		help.setStyle("-fx-border-color: #F23B52;"
				+ "	-fx-border-width: 0px 0px 0px 4px;\r\n"
				+ "	-fx-background-color: #feebee;");
	}
	public void Exit(MouseEvent event)
	{
		System.exit(0);	
	}
    
    
/////////////////////////////////////////////////////////////////
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("inisializ start");
		inischeduler();
		
		
		
	}

	private void inischeduler() {
		
		executorservice=Executors.newSingleThreadScheduledExecutor();
		executorservice.scheduleAtFixedRate(this:: loaddata, 0,10000, TimeUnit.MILLISECONDS);
		
		System.out.println("inis start");
	}

	private void loaddata() {
		
		javafx.application.Platform.runLater(() -> {
			data();
		});
	    
		
	}

	private void data() {
		Document doc = null ;
		
		
		
		try {
			
			doc = Jsoup.connect("https://www.moneycontrol.com/commodity/silver-price.html").get();
			if(doc!=null)
			{
				i=0;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			price.setText(". . . . . . . .");
			if(i==0) {
			i=2;
			Alert net= new Alert(Alert.AlertType.ERROR);
			net.setTitle("Error");
			net.setHeaderText("Check your Internet connection");
			net.showAndWait();
			
			}
			
		}
	
		Elements temp= doc.select("div#commodity_innertab0");

	   // DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	   // LocalDateTime now= LocalDateTime.now();
	    //System.out.println(dtf.format(now));
	    
		//System.out.println(temp.select("span.rd_30").size());
		
		
	    price.setText(temp.select("span.rd_30").text()+temp.select("span.gr_30").text());
		System.out.println("Silver price is :"+temp.select("span.rd_30").text()+temp.select("span.gr_30").text());
	    
		
	}
    
    

}

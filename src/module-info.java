module VR_Jewellers {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.graphics;
	requires java.sql;
	requires org.jsoup;
	requires javafx.media;

	
	opens main to javafx.graphics, javafx.fxml;
	opens main.login to javafx.graphics, javafx.fxml;
	opens main.dashboardController to javafx.graphics, javafx.fxml;
	opens main.about to javafx.graphics, javafx.fxml;
	opens main.customer to javafx.graphics, javafx.fxml;
	opens main.orders to javafx.graphics, javafx.fxml;
	opens main.product to javafx.graphics, javafx.fxml;
	opens main.splashscreen to javafx.graphics, javafx.fxml;
	opens main.dashboard to javafx.graphics, javafx.fxml;
	
}

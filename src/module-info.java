module VR_Jewellers {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.graphics;
	requires java.sql;
	requires org.jsoup;
	requires javafx.media;
	requires javafx.base;

	
	opens main to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.login to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.dashboardController to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.about to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.customer to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.orders to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.product to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.splashscreen to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	opens main.dashboard to javafx.graphics, javafx.fxml,javafx.base,java.desktop;
	
}

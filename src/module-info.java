module PedroConsole {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.json;

	
	opens application to javafx.graphics, javafx.fxml;
}

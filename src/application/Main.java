package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	@Override
	public void init() throws Exception {
		super.init();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("PedroLogin.fxml"));
			Scene scene = new Scene(root,200,100);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setScene(scene);
			primaryStage.setTitle("PedroDemo");
			primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

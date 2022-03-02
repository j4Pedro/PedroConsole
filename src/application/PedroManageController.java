package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

public class PedroManageController implements Initializable {
	@FXML
	private Button btn_logout;
	@FXML
	private Label label_hello;
	@FXML
	private Label label_userchannel;



	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		btn_logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "PedroLogin.fxml", "Log in", null, null);
			}
		});
		
	}
	
	public void setUserInfo(String username, String userchannel) {
		label_hello.setText("Hello," + username + "!");
		label_userchannel.setText("Your channel is " + userchannel + ".");
	}
}

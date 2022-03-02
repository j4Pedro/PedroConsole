package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PedroLoginController implements Initializable {

	@FXML
	private Button btn_login;
	@FXML
	private TextField tf_username;
	@FXML
	private TextField tf_password;


	@Override
	public void initialize(URL location, ResourceBundle resource) {


		btn_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());
			}
		});

	}


}

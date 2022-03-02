package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DBUtils {
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username,
			String userchannel) {
		Parent root = null;

		if (username != null && userchannel != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				PedroManageController pedroManageController = loader.getController();
				pedroManageController.setUserInfo(username, userchannel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		if (!fxmlFile.equals("PedroManage.fxml")) {
			stage.setScene(new Scene(root, 200, 100));
		}else {
			stage.setScene(new Scene(root, 600, 400));
			stage.setMinWidth(600);
			stage.setMinHeight(400);
		}
		stage.setAlwaysOnTop(true);
		stage.show();
	}

	public static void signUpUser(ActionEvent event, String username, String password, String userchannel) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedroconsole", "pedro", "123456");
			psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			psCheckUserExists.setString(1, username);
			resultSet = psCheckUserExists.executeQuery();

			if (resultSet.isBeforeFirst()) {
				System.out.println("User already exists!");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("You cannot use this user name.");
				alert.show();
			} else {
				psInsert = connection
						.prepareStatement("INSERT INTO users (username,password,userchannel VALUES (?,?,?))");
				psInsert.setString(1, username);
				psInsert.setString(2, password);
				psInsert.setString(3, userchannel);
				psInsert.executeUpdate();

				changeScene(event, "PedroManage.fxml", "Pedro Management System", username, userchannel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logInUser(ActionEvent event, String username, String password) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedroconsole?serverTimezone=GMT",
					"root", "root");
			pStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			pStatement.setString(1, username);
			resultSet = pStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("Username is not exist!");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Provided credentials are incorrect.");
				alert.show();
			} else {
				while (resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					String retrievedChannel = resultSet.getString("userchannel");
					if (retrievedPassword.equals(password)) {
						changeScene(event, "PedroManage.fxml", "Pedro Management System", username, retrievedChannel);
					} else {
						System.out.println("Password is not match!");
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("The provided credentials are incorrect!");
						alert.show();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStatement != null) {
				try {
					pStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

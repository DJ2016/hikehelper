package test;


import java.awt.event.ActionEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.*;

public class FXHelloWorld extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	protected void buttonAction(ActionEvent e){
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxmls/menuFace.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Собери рюкзак в поход!");
		stage.show();

	}

}

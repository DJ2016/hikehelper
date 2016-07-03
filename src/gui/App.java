package gui;

import java.io.IOException;
import java.util.Optional;

import fxmls.FXMLFrameLoader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	protected static Scene scene;
	protected static Stage stage;

	public static Scene getScene(){
		return scene;
	}

	public static Stage getStage(){
		return stage;
	}


	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		App.stage = stage;
		setFrame(FXMLFrameLoader.getMainFrame(), "Собери Рюкзак в поход!");
		stage.setOnCloseRequest(confirmCloseEventHandler);
		
	}
	
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Вы уверены, что хотите выйти?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Выход");
        closeConfirmation.setHeaderText("Подтвердите выход");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };


	public static void setFrame(Parent root) throws IOException{
		setFrame(root, "");
	}

	public static void setFrame(String frameName, String title) throws IOException{
		setFrame(FXMLFrameLoader.getRoot(frameName), title);

	}

	public static void setFrame(String frameName) throws IOException{
		setFrame(frameName, "");
	}

	public static void setFrame(Parent root, String title) throws IOException{
		scene = new Scene(root);

		if (stage.isShowing())
			stage.close();

		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

}

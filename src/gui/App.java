package gui;

import java.io.IOException;
import java.util.Optional;

import fxmls.FXMLFrameLoader;
import gui.controllers.FunctionalController.AutoShowableAlert;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

public class App extends Application {

	protected static Scene scene;
	protected static Stage stage;

	public static Scene getScene() {
		return scene;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		App.stage = stage;
		setFrame(FXMLFrameLoader.getMainFrame());
		stage.setOnCloseRequest(confirmCloseEventHandler);
		stage.getIcons().add(new Image("/gui/images/elka2.jpg"));
		stage.setResizable(false);

	}

	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
		Alert closeConfirmation = AutoShowableAlert
				.lookupInstance(new Pair<>(ButtonType.OK, "Выход"), "Вы уверены, что хотите выйти?", "Подтвердите выход");
		Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
		if (!ButtonType.OK.equals(closeResponse.get())) {
			event.consume();
		}
	};

	public static void setFrame(Parent root) throws IOException {
		setFrame(root, title);
	}

	public static void setFrame(String frameName, String title) throws IOException {
		setFrame(FXMLFrameLoader.getRoot(frameName), title);

	}

	public static void setFrame(String frameName) throws IOException {
		setFrame(frameName, "");
	}

	private static final String title = "Собери Рюкзак в поход!";

	
	public static void setFrame(Parent root, String title) throws IOException {
		scene = new Scene(root);

		if (stage.isShowing())
			stage.close();

		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

}

package gui;

import java.io.IOException;

import fxmls.FXMLFrameLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class App extends Application {

	protected static Scene scene;
	protected static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		App.stage = stage;
		setFrame(FXMLFrameLoader.getMainFrame(), "Собери Рюкзак в поход!");
	}
	
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

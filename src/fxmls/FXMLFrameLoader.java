package fxmls;

import java.io.IOException;

import gui.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FXMLFrameLoader {
	
	protected static final String folderName = "/fxmls/";
	protected static final String mainFramePath = "menuFace.fxml";
	protected static final String yourselfListFramePath ="mybackpackFaceOne.fxml";
	protected static final String createListFramePath ="ownListFace.fxml";
	protected static final String sportmasterFramePath ="sportmasterList.fxml";
	protected static final String scrollingImageFramePath = "scrollingImage.fxml";
	
	public static Parent getMainFrame() throws IOException{
		return getRoot(mainFramePath);
		
	}
	public static Parent getYourselfListFrame() throws IOException{
		return getRoot(yourselfListFramePath);
		
	}

	public static Parent getCreateListFrame() throws IOException{
		return getRoot(createListFramePath);
		
	}
	public static Parent getSportmasterFrame() throws IOException{
		return getRoot(sportmasterFramePath);
		
	}
	
	public static Parent getScrollingImageFrame() throws IOException{
		return getRoot(scrollingImageFramePath);
	}
	
	public static Parent getRoot(String frameName) throws IOException{
		return FXMLLoader.load(App.class.getResource(folderName + frameName));
	}
	

}

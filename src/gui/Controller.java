package gui;

import java.io.IOException;

import fxmls.FXMLFrameLoader;
import javafx.fxml.FXML;

public class Controller {
	
	@FXML
    public void onFindYourSelfListButtonClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getYourselfListFrame());
    }
	
	@FXML
	public void onFindTeamListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getTeamListFrame());
	}
	
	@FXML
	public void onCreateListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame());
	}
	
	@FXML
	public void onSearchOnSportmasterClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getSportmasterFrame());
	}
}

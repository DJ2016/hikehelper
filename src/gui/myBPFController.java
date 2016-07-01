package gui;

import java.io.IOException;

import entities.Things;
import fxmls.FXMLFrameLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class myBPFController {

	@FXML private TableView<Things> tableThings;

	private ObservableList<Things> thingsList = FXCollections.observableArrayList();


	@FXML
	public void onSearchOnSportmasterClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getSportmasterFrame());
	}

	@FXML
	public void onReturnToMenuClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getMainFrame());
	}

	@FXML
	private void deleteClickedButton(){
		if(thingsList.size()==0){
			AlertWindow("Ошибка","Список пуст");
			return;
		}else{
			try{
				thingsList.remove(tableThings.getSelectionModel().getSelectedIndex());
				System.out.println("Delete: "+thingsList.size());
			}catch(ArrayIndexOutOfBoundsException ex){
				AlertWindow("Ошибка","Не выделена строка для удаления.");
			}
		}
	}


	private void AlertWindow(String title, String information){
		Alert alertWindow = new Alert(Alert.AlertType.ERROR);
		alertWindow.setTitle(title);
		alertWindow.setHeaderText(information);
		alertWindow.show();
	}

}

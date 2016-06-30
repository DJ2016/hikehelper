package gui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Things;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class CreateYourListController{
	
	@FXML private TableView<Things> tableThings;
	
	@FXML
	public void initialize(){
		List<Things> things  = new ArrayList<Things>();
		things.add(new Things("������",5));
		things.add(new Things("������",1));
		things.add(new Things("�������",2));
		
		tableThings.setItems(FXCollections.observableArrayList(things));
	}

	@FXML
	public void onReturnToMenuClicked() throws IOException{
		Controller.returnToMenu();
	}
}

	
	

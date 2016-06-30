package gui;


import java.io.IOException;

import entities.Things;
import fxmls.FXMLFrameLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateYourListController{
	
	@FXML private TableView<Things> tableThings;
	@FXML private TableColumn<Things,String> nameColumn;
	@FXML private TableColumn<Things, Integer> quanColumn;
	@FXML private TextField nameInput = new TextField();
	@FXML private TextField quanInput = new TextField();
	private ObservableList<Things> thingsList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		nameInput.setPromptText("Предмет");
		quanInput.setPromptText("Кол-во");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Things, String>("thingName"));
		quanColumn.setCellValueFactory(new PropertyValueFactory<Things, Integer>("quantity"));
		tableThings.setItems(thingsList);
		
	}
	
	@FXML
	private void addClickedButton(){
		if((nameInput.getText().isEmpty())){
			AlertWindow("Ошибка","Заполните поле Предмет.");
			return;
		}else{
		try{
		Things nthing = new Things();
		nthing.setThing(nameInput.getText(), Integer.parseInt(quanInput.getText()));
		thingsList.add(nthing);
		nameInput.clear();
		quanInput.clear();
		System.out.println();
		}catch(NumberFormatException ex){
			AlertWindow("Ошибка ввода", "Заполните поле Кол-во.");
		}
		}
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
	
	@FXML
	private void onSportMasterClicked(){
		try{
		App.setFrame(FXMLFrameLoader.getSportmasterFrame());
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	@FXML
	public void onReturnToMenuClicked() throws IOException{
		Controller.returnToMenu();
	}
}

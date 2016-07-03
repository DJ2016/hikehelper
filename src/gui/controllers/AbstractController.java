package gui.controllers;

import javafx.scene.control.TableView;

import java.io.IOException;

import javafx.scene.Node;
import entities.Things;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public abstract class AbstractController implements FunctionalController<Node>{

	/**
	 * a scene node represented on .fxml
	 */
	@FXML 
	protected TableView<Things> tableThings;
	
	/**
	 * a list for fill {@tableThings}
	 * @see #tableThings
	 */
	protected ObservableList<Things> things = FXCollections.observableArrayList();

	/**
	 * FXMLLoader will now automatically call any suitably 
	 * annotated no-arg initialize() method defined by the controller. 
	 * It is recommended that the injection approach be used whenever possible.
	 */
	@FXML
	public abstract void initialize();
	
	
	
	/**
	 * invoked when sportmaster search button had been pressed
	 * sets the scene to sportmaster frame
	 * @see fxmls.FXMLFrameLoader
	 */
	@FXML
	public void onSearchAtSportmasterClicked() throws IOException{
		FunctionalController.super.onSearchAtSportmasterClicked();
	}

	/**
	 * invoked when return to menu button had been pressed
	 * sets the scene to menu frame
	 * @see fxmls.FXMLFrameLoader
	 */
	@FXML
	public void onReturnToMenuClicked() throws IOException{
		FunctionalController.super.onReturnToMenuClicked();
	}
	
	/**
	 * invoked when delete button had been pressed
	 * 
	 * @see AbstractController.AutoShowableAlert
	 * @see AbstractController#things
	 * @see AbstractController#tableThings
	 */
	@FXML
	protected void deleteClickedButton(){
		int index = tableThings.getSelectionModel().getSelectedIndex();
		if(things.isEmpty()){
			new AutoShowableAlert("Ошибка","Список пуст");
		} else if (index < 0){
			new AutoShowableAlert("Ошибка","Не выделена строка для удаления.");
		} else {
			things.remove(index);
		}
	}
	
	/**
	 * @param value false - textfield text will changed on gray
	 *              and on black otherwise
	 */
	protected void setNodeStyle(boolean value, Node field){
		String style = "-fx-text-fill: ";
		if (!value)
			style += "gray";
		else style += "black";
		field.setStyle(style);
	}
}

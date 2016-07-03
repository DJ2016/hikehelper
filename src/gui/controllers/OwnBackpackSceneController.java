package gui.controllers;


import javafx.event.EventHandler;

import java.util.Arrays;

import entities.Things;
import gui.listeners.AcceptChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.text.Text;

public class OwnBackpackSceneController extends AbstractController {

	@FXML
	private TableView<Things> tableThings;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private TextField quanInput;

	@FXML @Override
	public void initialize() {
		tableThings.setItems(things);
		quanInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(quanInput, this::setNodeStyle, false));
		nameInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(nameInput, this::setNodeStyle, false));
	}

	
	@FXML
	private void addClickedButton() {
		if (nameInput.getText().isEmpty()) {
			new AutoShowableAlert("Ошибка", "Заполните поле Предмет.");
		} else {
			things.add(new Things().safeSet(nameInput.getText(), quanInput.getText()));
			//Util.invokeAll(Arrays.asList(nameInput, quanInput), TextField.class, "clear");
		}
	}

	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}

}

package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class SingleBackpackSceneController extends AbstractController{

	@FXML private CheckBox checkBox1;
	@FXML private CheckBox checkBox2;

	@FXML
	public void isChecked(){
		checkBox1.setDisable(checkBox2.isSelected());
		checkBox2.setDisable(checkBox1.isSelected());

	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}


	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

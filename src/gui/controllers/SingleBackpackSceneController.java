package gui.controllers;

import java.sql.SQLException;

import connection.ConnectionFacade;
import entities.Params;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SingleBackpackSceneController extends AbstractController{

	@FXML private CheckBox checkBox1;
	@FXML private CheckBox checkBox2;

	@FXML
	private ChoiceBox<String> boxPrecipitation;
	
	@FXML
	private ChoiceBox<String> boxTipeTp;
	
	@FXML
	private ChoiceBox<String> boxCountDays;
	
	@FXML
	private TextField fieldFrom;
	
	@FXML
	private TextField fieldTo;
	
	@FXML
	private TextField fieldWeight;
	
	@FXML
	private TextField fieldAge;
	
	
	
	@FXML
	public void isChecked(){
		checkBox1.setDisable(checkBox2.isSelected());
		checkBox2.setDisable(checkBox1.isSelected());

	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@FXML
	public void onCreatedClicked() throws ClassNotFoundException, SQLException{
		Params params = new Params()
				.setPrecipitation(boxPrecipitation.getValue())
				.setCountD(boxCountDays.getValue())
				.setCountPR("1")
				.setRange(fieldFrom.getText() + "-" + fieldTo.getText())
				.setTipeTp(boxTipeTp.getValue());
		tableThings.setItems(ConnectionFacade.sortedObservableQuery(params));
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

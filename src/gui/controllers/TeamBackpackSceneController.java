package gui.controllers;

import javafx.scene.Node;

import java.sql.SQLException;

import connection.ConnectionFacade;
import entities.Params;
import entities.Things;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

public class TeamBackpackSceneController extends AbstractController{

	@FXML
	private ChoiceBox<String> boxPrecipitation;
	
	@FXML
	private ChoiceBox<String> boxTipeTp;
	
	@FXML
	private ChoiceBox<String> boxCountDays;
	
	@FXML
	private TextField fieldCountPersons;
	
	@FXML
	private TextField fieldFrom;
	
	@FXML
	private TextField fieldTo;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	
	@FXML
	public void onCreatedClicked() throws ClassNotFoundException, SQLException{
		Params params = new Params()
				.setPrecipitation(boxPrecipitation.getValue())
				.setCountD(boxCountDays.getValue())
				.setCountPR(fieldCountPersons.getText())
				.setRange(fieldFrom.getText() + "-" + fieldTo.getText())
				.setTipeTp(boxTipeTp.getValue());
		tableThings.setItems(ConnectionFacade.observableQuery(params));
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

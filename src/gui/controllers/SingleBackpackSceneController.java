package gui.controllers;

import java.io.IOException;
import java.sql.SQLException;

import connection.ConnectionFacade;
import entities.Params;
import entities.Things;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SingleBackpackSceneController extends AbstractController{

	
	@FXML private CheckBox checkBox1;
	@FXML private CheckBox checkBox2;

	@FXML
	private ChoiceBox<String> boxPrecipitation;
	
	@FXML
	private ChoiceBox<String> boxTipeTp;
	
	@FXML
	private TextField saveField;
	
	@FXML
	private Label rekvesbp;
	
	@FXML
	private Label vespredbp;
	
	@FXML
	private ChoiceBox<String> boxCountDays;
	
	@FXML
	private ChoiceBox<String> fieldFrom;
	
	@FXML
	private ChoiceBox<String> fieldTo;
	
	@FXML
	private TextField fieldWeight;
	
	@FXML
	private TextField fieldAge;
	
	
	@FXML
	public void isChecked(){
		checkBox1.setDisable(checkBox2.isSelected());
		checkBox2.setDisable(checkBox1.isSelected());

	}

	@FXML
	public void onCreateClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame());
	}
	

	
	@FXML
	public void onSaveClicked(){
		
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
				.setRange(fieldFrom.getValue() + "-" + fieldTo.getValue())
				.setTipeTp(boxTipeTp.getValue());
		rekvesbp.setText("Рекомендуемый вес рюкзака " + fieldWeight.getText() + " кг");
		tableThings.setItems(ConnectionFacade.sortedObservableQuery(params));
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

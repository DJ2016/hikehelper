package gui.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SingleBackpackSceneController extends AbstractController{
	
	@FXML 
	CheckBox checkBox1;
	
	@FXML 
	private CheckBox checkBox2;

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
	private TextField fileNameInput;
	
	@FXML
	private TextField fieldWeight;
	
	@FXML
	private TextField fieldAge;
	
	@FXML
	private TableColumn<Things,String> nameColumn;
	
	@FXML
	private TableColumn<Things,String> quanColumn;
	
	final String fullFname =  "mybackpack/";
	
	
	@FXML
	public void isChecked(){
		Arrays.asList(checkBox1, checkBox2).forEach(c -> c.setSelected(c.isFocused()));
    }

	@FXML
	public void onCreateClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame(), "������ ������ � �����!");
	}
	
	private void FileSave(String fname) throws FileNotFoundException{
	//	System.out.println(tableThings.getColumns().get(0).getCellData(4));
		String f  = fullFname +fname;
		PrintWriter pw = new PrintWriter(new File(f));
		tableThings.getItems().forEach(c -> pw.println(c.getThingName() + " : " + c.getValue()));
		pw.close();
		
	}

	@FXML
	public void onSaveClicked() throws FileNotFoundException{
		if(!fileNameInput.getText().isEmpty()){
			FileSave(fileNameInput.getText());
		}
	}

	@Override
	public void initialize() {
		checkBox1.setSelected(true);
		
	}

	@FXML
	public void onCreatedClicked() throws ClassNotFoundException, SQLException{
		tableThings.getItems().clear();
		Params params = new Params()
				.setPrecipitation(boxPrecipitation.getValue())
				.setCountD(boxCountDays.getValue())
				.setCountPR("1")
				.setRange(fieldFrom.getValue())
				.setTipeTp(boxTipeTp.getValue());
		int weight = Integer.parseInt(fieldWeight.getText());
		if(checkBox1.isSelected()){weight = weight/3;}
		if(checkBox2.isSelected()){weight=weight/4;}
		tableThings.setItems(ConnectionFacade.observableQuery(params));
		rekvesbp.setText("������������� ��� ������� " + weight + " ��");
		vespredbp.setText("��� ������������� ������� " + ConnectionFacade.sumMass/1000 + " ��");
		ConnectionFacade.sumMass = 0;
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

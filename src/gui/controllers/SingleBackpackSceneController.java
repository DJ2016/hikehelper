package gui.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import connection.ConnectionFacade;
import entities.Params;
import entities.Things;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import java.util.Optional;
import util.BackpackWriter;

import com.sun.glass.ui.Application;

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
	private BackpackWriter writer = new BackpackWriter();
	
	@FXML
	public void isChecked(){
		Arrays.asList(checkBox1, checkBox2).forEach(c -> c.setSelected(c.isFocused()));
    }
	
	@FXML
	public void onCreateClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame(), "Собери Рюкзак в поход!");
	}
	
	private void FileSave(String fname) throws FileNotFoundException{
	//	System.out.println(tableThings.getColumns().get(0).getCellData(4));
		String f  = fullFname +fname;
		PrintWriter pw = new PrintWriter(new File(f));
		tableThings.getItems().forEach(c -> pw.println(c.getThingName() + " : " + c.getValue()));
		pw.close();
	}

	@FXML
	public void onSaveClicked() throws IOException{
		if (fileNameInput.getText().isEmpty()){
			new AutoShowableAlert("Внимание!","Введите имя сохраняемого файла");
		}
		if(!fileNameInput.getText().isEmpty()){
			writer.ensureExists();
			FileSave(fileNameInput.getText());
			App.setFrame(FXMLFrameLoader.getCreateListFrame());
		}
	}

	@Override
	public void initialize() {
		checkBox1.setSelected(true);
		 fieldWeight.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                fieldWeight.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		 fieldAge.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		                fieldAge.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
	}

	@FXML
	public void onCreatedClicked() throws ClassNotFoundException, SQLException, IOException{
		
		if (fieldWeight.getText().length() >= 6 ||
			fieldAge.getText().length() >= 6){
			new AutoShowableAlert("","Введите корректные данные");
			return;
		}
		
		if(fieldWeight.getText().isEmpty()){
			new AutoShowableAlert("","Введите вес");
			return;
		}
		if(fieldAge.getText().isEmpty()){
			new AutoShowableAlert("","Введите возраст");
			return;
		}
	
		
		int weight = Integer.parseInt(fieldWeight.getText());
		int age = Integer.parseInt(fieldAge.getText());
		
		if(weight <= 15){
			new AutoShowableAlert("Внимание!","Введите корректное значение веса");
			return;
		}
		if(age < 5){
			new AutoShowableAlert("Внимание!","Введите корректное значение возраста");
			return;
		}
		
		tableThings.getItems().clear();
		Params params = new Params()
				.setPrecipitation(boxPrecipitation.getValue())
				.setCountD(boxCountDays.getValue())
				.setCountPR("1")
				.setRange(fieldFrom.getValue())
				.setTipeTp(boxTipeTp.getValue());


		
		if(checkBox1.isSelected()){weight = weight / 3;}
		if(checkBox2.isSelected()){weight=weight / 4;}
		
		tableThings.setItems(ConnectionFacade.observableQuery(params));
		
		if(age < 12){rekvesbp.setText("Рекомендуемый вес рюкзака " + (int)(age/2) + " кг");}
		else if(age >= 12 && age < 18){rekvesbp.setText("Рекомендуемый вес рюкзака " + (int)(age/1.5) + " кг");}
		else{rekvesbp.setText("Рекомендуемый вес рюкзака " + weight + " кг");}
		
		vespredbp.setText("Вес предложенного рюкзака " + ConnectionFacade.sumMass/1000 + " кг");	
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}


}

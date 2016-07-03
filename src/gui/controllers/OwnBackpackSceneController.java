package gui.controllers;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import gui.listeners.AcceptChangeListener;
import javafx.beans.value.ChangeListener;
import entities.Things;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;;

public class OwnBackpackSceneController extends AbstractController {

	@FXML
	private TableView<Things> tableThings;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private TextField quanInput;
	
	@FXML
	private TextField fileNameInput;
	
	
	/////////////////
	
	@FXML
	private TableColumn<Things,String> nameColumn;
	@FXML
	private TableColumn<Things,Integer> quanColumn;
	
	@FXML
	private ListView<String> fileList;
	
	final String fullFname =  "mybackpack/";
	
	String fileSelectedName;
	
	@FXML @Override
	public void initialize() {
		things.add(new Things("Ïðåäìåò",1));
		FileSearch();
		onClickedListFile();
		EditTable();
		tableThings.setItems(things);
		quanInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(quanInput, this::setNodeStyle, false));
		nameInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(nameInput, this::setNodeStyle, false));
	}
	
	private void EditTable(){
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Things,String>>(){
			@Override
			public void handle(TableColumn.CellEditEvent<Things, String> t){
				((Things)t.getTableView().getItems().get(t.getTablePosition().getRow())).setThingName(t.getNewValue());;
			}
		});
		
		quanColumn.setCellFactory(TextFieldTableCell.<Things,Integer>forTableColumn(new IntegerStringConverter()));
		quanColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Things,Integer>>(){
			@Override
			public void handle(TableColumn.CellEditEvent<Things, Integer> t){
				((Things) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
			}
		});
	}
	
	private void onClickedListFile(){
		fileList.getSelectionModel().selectedItemProperty().addListener((changed,oldVal,newVal)->{
					fileNameInput.setText(fileList.getSelectionModel().getSelectedItem());
					fileSelectedName = newVal;
					FileRead(fileNameInput.getText());
				});
	}
	
	private void FileSearch(){
		fileList.getItems().clear();
		File fsearch = new File(fullFname);
		for(File item: fsearch.listFiles()){
			if(!item.isDirectory())
				fileList.getItems().addAll(item.getName());
		}
	}
	
	private void FileSave(String fname){
		String f  = fullFname +fname;
		try(FileWriter fileOut = new FileWriter(f)){
			for(int i= 0; i < things.size(); i++){
				fileOut.write(things.get(i).getThingName()+" ");
				fileOut.write(things.get(i).getQuantity()+"\r\n");
			}
		}catch(IOException ex){
			System.out.println("File write error.");
		}
	}
	
	private void FileRead(String name){
		String f = fullFname + name;
		try(BufferedReader bf = new BufferedReader(new FileReader(f))){
			String s;
			things.clear();
			while((s = bf.readLine())!=null)
				things.add(new Things(stringspit(s)[0],Integer.parseInt(stringspit(s)[1])));
		}catch(IOException e){
			System.out.println("File was not openned");
		}
	}
	
	private String [] stringspit(String strSplit){
		String str[] = strSplit.split(" ");
		return str;
	}
	
	@FXML
	private void saveClickedButton(){
		if(!fileNameInput.getText().isEmpty()){
		FileSave(fileNameInput.getText());
		FileSearch();
		}
	}
	@FXML
	private void deleteListFileElement(){
		File fdelete = new File(fullFname + fileSelectedName);
		fdelete.delete();
		FileSearch();
		fileNameInput.clear();
	}
	
	@FXML
	private void addClickedButton() {
		if (nameInput.getText().isEmpty()) {
			new AutoShowableAlert("Îøèáêà", "Çàïîëíèòå ïîëå Ïðåäìåò.");
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

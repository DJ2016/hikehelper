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
import javafx.scene.control.SelectionMode;
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
	
	boolean flag = false;
	/////////////////
	
	@FXML
	private TableColumn<Things,String> nameColumn;
	@FXML
	private TableColumn<Things,String> quanColumn;
	
	@FXML
	private ListView<String> fileList;
	
	final String fullFname =  "mybackpack/";
	
	String fileSelectedName;
	
	@FXML @Override
	public void initialize() {
		fileExist();
		FileSearch();
		TableStrings();
		onClickedListFile();
		EditTable();
		tableThings.setItems(things);

	}
	
	private void EditTable(){
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Things,String>>(){
			@Override
			public void handle(TableColumn.CellEditEvent<Things, String> t){
				((Things)t.getTableView().getItems().get(t.getTablePosition().getRow())).setThingName(t.getNewValue());;
			}
		});
		
		quanColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		quanColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Things,String>>(){
			@Override
			public void handle(TableColumn.CellEditEvent<Things, String> t){
				((Things) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
			}
		});
	}
	
	private void fileExist(){
		File dir = new File("mybackpack");
		if(!dir.exists())
			dir.mkdirs();
	}
	
	private void onClickedListFile(){
		fileList.getSelectionModel().selectedItemProperty().addListener((changed,oldVal,newVal)->{
					fileNameInput.setText(fileList.getSelectionModel().getSelectedItem());
					fileSelectedName = fileNameInput.getText();
					FileRead(fileNameInput.getText());
				});
	}
	
	private void TableStrings(){
		for(int i = 0; i < 50; i++){
			things.add(new Things());
		}
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
				if(!things.get(i).getThingName().isEmpty()){
					fileOut.write(things.get(i).getThingName()+" ");
					fileOut.write(things.get(i).getQuantity()+"\r\n");
				}
			}
		}catch(IOException ex){
			System.out.println("File write error.");
		}
	}
	
	private void FileRead(String name){
		String f = fullFname + name;
		try(BufferedReader bf = new BufferedReader(new FileReader(f))){
			things.clear();
			String s;
			while((s = bf.readLine())!=null){
				if(!s.isEmpty())
				things.add(new Things(stringspit(s)[0],stringspit(s)[1]));
			}
			TableStrings();
			}catch(IOException e){
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
	private void onEditButton(){
		int i = tableThings.getSelectionModel().getSelectedIndex();
		System.out.println(i);
		System.out.println(things.size());
		if(!nameInput.getText().isEmpty())
			things.get(i).setThingName(nameInput.getText());
		if(!quanInput.getText().isEmpty())
			things.get(i).setQuantity(quanInput.getText());
		tableThings.getColumns().clear();
		tableThings.getColumns().addAll(nameColumn,quanColumn);
		nameInput.clear();
		quanInput.clear();
	}
	
	@FXML
	private void addClickedButton() {
		things.add(0,new Things());
	}
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}
}

package gui.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import entities.Things;
import gui.listeners.AcceptChangeListener;
import gui.listeners.EventMethodInvoker;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Pair;
import util.BackpackWriter;


public class OwnBackpackSceneController extends AbstractController {

	@FXML private TextField nameInput;
	@FXML private TextField quanInput;
	@FXML private TextField fileNameInput;
	@FXML private TableColumn<Things,String> nameColumn;
	@FXML private TableColumn<Things,String> quanColumn;
	@FXML private ListView<String> fileList;
	
	private String fileSelectedName;
	private BackpackWriter writer = new BackpackWriter();
	
	@FXML @Override
	public void initialize() {
		writer.setList(fileList.getItems())
		  	  .setThings(things);
		
		addListenerToListView();
		writer.search();
		addEmptyItems();
		initColumns();
		tableThings.setItems(things);
		//nameInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(quanInput, this::setNodeStyle, true));
		//quanInput.focusedProperty().addListener(new AcceptChangeListener<Node, Boolean>(nameInput, this::setNodeStyle, true));
	}
	
	
	private void initColumns(){
		Arrays.asList(new Pair<>(nameColumn, "setThingName"), new Pair<>(quanColumn, "setQuantity"))
			.forEach(column -> {
						column.getKey().setCellFactory(TextFieldTableCell.forTableColumn());
						column.getKey().setOnEditCommit(new EventMethodInvoker<Things, String>(Things.class, column.getValue()));
			});
	}

	
	private void addListenerToListView(){
		fileList.getSelectionModel()
				.selectedItemProperty()
				.addListener((changed, oldVal, newVal)->{
					fileNameInput.setText(newVal);
					fileSelectedName = newVal;
					writer.read(fileNameInput.getText());
					addEmptyItems();
				});
	}
	
	private void addEmptyItems(){
		for(int i = 0; i < 50; i++){
			things.add(new Things());
		}
	}
	


	@FXML
	private void openExplorer() throws IOException{
		String path = BackpackWriter.getDir();
		Desktop desktop = null;
		if (Desktop.isDesktopSupported()) {
		    desktop = Desktop.getDesktop();
		}
		if (path == null || path.isEmpty() || desktop == null)
			return;
		desktop.open(new File(path));
	}
	
	@FXML
	private void saveClickedButton(){
		if (fileNameInput.getText().isEmpty()){
			new AutoShowableAlert("Введите имя рюкзака", "Имя рюкзака не заполнено");
			return;
		}
		writer.save(fileNameInput.getText());
	}
	@FXML
	private void deleteListFileElement(){
		writer.delete(fileSelectedName);
		fileNameInput.clear();
		tableThings.getItems().clear();
	}
	
	
	@FXML @SuppressWarnings("unchecked")
	private void onEditButton(){
		int index = tableThings.getSelectionModel().getSelectedIndex();
		if (index < 0) return;
		things.get(index).setThingIfNotEmpty(nameInput.getText());
		things.get(index).setQuantityIfNotEmpty(quanInput.getText());
		tableThings.getColumns().clear();
		tableThings.getColumns().addAll(nameColumn,quanColumn);
		Arrays.asList(nameInput, quanInput).forEach(c -> c.clear());
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

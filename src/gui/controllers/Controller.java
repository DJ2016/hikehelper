package gui.controllers;

import java.io.IOException;

import entities.Product;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import parser.SportmasterParser;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;


public class Controller implements FunctionalController<TextField>{
	
	@FXML private TextField searchField;
	@FXML private TableView<Product> tableView;
	
	@FXML
    public void onFindYourSelfListButtonClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getYourselfListFrame());
    }
	
	@FXML
	public void onFindTeamListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getTeamListFrame());
	}
	
	@FXML
	public void onCreateListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame());;
	}
	
	@Override
	public void onReturnToMenuClicked() throws IOException {
		FunctionalController.super.onReturnToMenuClicked();
	}
	@Override
	public void onSearchAtSportmasterClicked() throws IOException {
		FunctionalController.super.onSearchAtSportmasterClicked();
	}
	
	
	@FXML 
	public void initialize(){
		tooltipInit();
	}

	private void tooltipInit(){
		tip.setWrapText(true);
		tip.setAutoHide(true);
		tip.setAutoFix(true);	
	}
	@FXML
	public void onSearchedClicked() throws IOException{
		String str = searchField.getText();
		if (str == null || str.isEmpty()){
			showTooltip(searchField, "заполните это поле");
		} else {
			hideTooltip();
			tableView.setItems(SportmasterParser.defaultSortedObservableQuery(str));
			tableView.setVisible(true);
		}
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				/*
				Dialog<?> d = new Dialog<>();
				d.getDialogPane().setContent(new ImageView(observable.getValue().getImgSource()));
				d.showAndWait();
				*/
			}
		});
	}


	@FXML
	public void onSelected(){
		System.out.println("sasdas");
	}

	@Override
	public Void apply(FunctionalController<TextField> t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}

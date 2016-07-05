package gui.controllers;

import java.io.IOException;

import javafx.application.Application;
import entities.Product;
import fxmls.FXMLFrameLoader;
import gui.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import parser.SportmasterParser;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.PopupWindow;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.Group;

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
				
				Button left = new Button("Влево");
				Button right = new Button("Вправо");
				ImageView imgview = new ImageView(observable.getValue().getImgSource());
				
				imgview.toBack();
				left.toFront();
				right.toFront();
				
				Stage stage = new Stage();
				Group group = new Group();
				
				StackPane imgPane = new StackPane(imgview);
				imgPane.setAlignment(Pos.CENTER);
				
				StackPane btnLeftPane = new StackPane(left);
				imgPane.setAlignment(Pos.CENTER);
				
				StackPane btnRightPane = new StackPane(right);
				imgPane.setAlignment(Pos.CENTER);
				
				group.getChildren().add(imgPane);
				
				Scene sc = new Scene(group);
				
				stage.setScene(sc);
				stage.show();
		
				group.getChildren().add(btnLeftPane);
				group.getChildren().add(btnRightPane);
				btnRightPane.setLayoutX(118);
				btnLeftPane.setLayoutX(2);
				System.out.println(observable.getValue().getImgSource());
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

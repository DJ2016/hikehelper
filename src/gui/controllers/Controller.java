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
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.collections.ObservableList;

public class Controller implements FunctionalController<TextField>{
	protected static String imgSource;
	protected static int index;
	protected static ObservableList<Product> list;
	protected static Stage scrollableStage;
	
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
				imgSource = observable.getValue().getImgSource();
				list = tableView.getItems();
				scrollableStage = new Stage();
				try {
					Scene sc = new Scene(FXMLFrameLoader.getScrollingImageFrame());
					scrollableStage.setScene(sc);
					scrollableStage.setResizable(false);
					scrollableStage.sizeToScene();
					scrollableStage.show();
					scrollableStage.focusedProperty().addListener((obs, oldVal, newVal) -> {
						scrollableStage.close();
					});
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}



	@Override
	public Void apply(FunctionalController<TextField> t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}

package gui;

import java.io.IOException;

import fxmls.FXMLFrameLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.collections.ObservableList;
import parser.Product;
import parser.SportmasterParser;
import javafx.scene.control.CheckBox;

public class Controller{
	
	@FXML private TextField searchField;
	@FXML private TableView<Product> tableView;
	@FXML private CheckBox checkBox1;
	@FXML private CheckBox checkBox2;
	
	@FXML
<<<<<<< HEAD
=======
	public void isChecked(){
		if (checkBox1.isSelected()){
			checkBox2.setDisable(true);
		}
		if (!checkBox1.isSelected()){
			checkBox2.setDisable(false);
		}
		if(checkBox2.isSelected()){
			checkBox1.setDisable(true);
		}
		if (!checkBox2.isSelected()){
			checkBox1.setDisable(false);
		}
		
	}
	@FXML
>>>>>>> origin/master
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
	
	@FXML
	public void onSearchOnSportmasterClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getSportmasterFrame());
	}
	
	@FXML
	public void onReturnToMenuClicked() throws IOException{
		returnToMenu();
	}
	
	public static void returnToMenu() throws IOException{
		App.setFrame(FXMLFrameLoader.getMainFrame());
	}
	
	private Tooltip tip = new Tooltip ("заполните это поле");
	
	@FXML
	public void onSearchedClicked() throws IOException{
		String str = searchField.getText();
		if (str == null || str.isEmpty()){
			showTooltip();
		} else {
			hideTooltip();
			ObservableList<Product> list = FXCollections
									.observableArrayList(
											new SportmasterParser()
												.query(str));

			FXCollections.sort(list);
			tableView.setItems(list);
			tableView.setVisible(true);
		}
	}

	
	
	private void hideTooltip(){
		tip.hide();
	}
	private void showTooltip(){
		Bounds b = searchField.getBoundsInParent();
		tip.show(App.stage, 
			   App.stage.getX() + (b.getMinX() + b.getMaxX()) / 2,
			   App.stage.getY() + b.getMinY());
	}
	
	@FXML
	public void isChecked(){
		if (checkBox1.isSelected()){
			checkBox2.setDisable(true);
		}
		if (!checkBox1.isSelected()){
			checkBox2.setDisable(false);
		}
		if(checkBox2.isSelected()){
			checkBox1.setDisable(true);
		}
		if (!checkBox2.isSelected()){
			checkBox1.setDisable(false);
		}
		
	}
}

package gui.controllers;

import java.io.IOException;
import java.util.Arrays;

import entities.Product;
import fxmls.FXMLFrameLoader;
import gui.App;
import gui.listeners.AcceptChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import parser.SportmasterParser;
import util.ExpressionConsumer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Pair;

public class Controller implements FunctionalController<Node>{

	@FXML private TextField searchField;
	@FXML private TableView<Product> tableView;
	
	@FXML private Label label;
	@FXML private ImageView imgView;
	@FXML private Button left;
	@FXML private Button right;
	@FXML private Label labelProductName;
	
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
		App.setFrame(FXMLFrameLoader.getCreateListFrame());
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
	
	@FXML private void onLeftClicked(){
		move(false);
		update();
	}
	
	@FXML private void onRightClicked(){
		move(true);
		update();
	}
	private void update(){
		ensureBounds();
		update(items.get(index));
		model.select(index);
	}
	private void update(Product p){
		imgView.setImage(p.getImage());
		label.setText(p.getPrice() + "р");
		labelProductName.setText(p.getName());
	}
	
	private void ensureBounds(){
		if (index < 0)
			index = items.size() - 1;
		if (index == items.size())
			index = 0;
	}
	
	private void move(boolean forward){
		if (forward) index += 1;
		else index -= 1;
	}
	private ObservableList<Product> items;
	private int index;
	private TableView.TableViewSelectionModel<Product> model;
	@FXML
	public void onSearchedClicked() throws IOException{
		tableView.getItems().clear();
		index = 0;
		String str = searchField.getText();
		
		ExpressionConsumer<String, Node> consumer = new ExpressionConsumer<>(this::showTooltip);
		
		if (consumer.requireNullOr(new Pair<>("Заполните это поле", searchField), str, str.isEmpty())) return;
		
		items = SportmasterParser.defaultSortedObservableQuery(str);
		
		if (consumer.requireNullOr(new Pair<>("Извините, товар временно отсутствует", imgView), items, items.isEmpty())) return;
		
		tableView.setItems(items);
		Arrays.asList(left, right, label, labelProductName).forEach(c -> c.setVisible(true));
		model = tableView.getSelectionModel();
		update(items.get(index));
		model.selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				update(observable.getValue());
				index = model.getSelectedIndex();
			}
		});
	}

	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}




	

	

}

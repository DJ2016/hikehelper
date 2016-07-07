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

	@FXML
    public void onFindYourSelfListButtonClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getYourselfListFrame(), "������ ������ � �����!");
    }
	
	@FXML
	public void onFindTeamListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getTeamListFrame(), "������ ������ � �����!");
	}
	
	@FXML
	public void onCreateListClicked() throws IOException{
		App.setFrame(FXMLFrameLoader.getCreateListFrame(), "������ ������ � �����!");
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
		update(tableView.getItems().get(index));
	}
	private void update(Product p){
		imgView.setImage(p.getImage());
		label.setText(p.getPrice() + "�");
	}
	
	private void ensureBounds(){
		if (index < 0)
			index = tableView.getItems().size() - 1;
		if (index == tableView.getItems().size())
			index = 0;
	}
	
	private void move(boolean forward){
		if (forward) index += 1;
		else index -= 1;
	}
	
	private int index;
	
	@FXML
	public void onSearchedClicked() throws IOException{
		index = 0;
		String str = searchField.getText();
		
		ExpressionConsumer<String, Node> consumer = new ExpressionConsumer<>(this::showTooltip);
		
		if (consumer.requireNullOr(new Pair<>("��������� ��� ����", searchField), str, str.isEmpty())) return;
		
		ObservableList<Product> list = SportmasterParser.defaultSortedObservableQuery(str);
		
		if (consumer.requireNullOr(new Pair<>("��������, ����� �������� �����������", imgView), list, list.isEmpty())) return;
		
		tableView.setItems(list);
		Arrays.asList(tableView, left, right).forEach(c -> c.setVisible(true));
		update(list.get(index));
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				update(observable.getValue());
			}
		});
	}

	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}




	

	

}

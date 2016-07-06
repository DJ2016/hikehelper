package gui.controllers;

import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

import entities.Product;
import javafx.collections.ObservableList;

public class ScrollingSceneController extends AbstractController{
	@FXML private Label label;
	@FXML private ImageView imgView;
	@FXML private Button left;
	@FXML private Button right;
	
	@Override
	public Void apply(FunctionalController<Node> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {		
		list = Controller.list;
		findIndex();
		update();
	}
	
	private ObservableList<Product> list;
	private int index = Controller.index;
	
	@FXML
	private void onLeftClicked(){
		move(false);
		update();
	}
	
	@FXML
	private void onRightClicked(){
		move(true);
		update();
	}

	private void update(){
		ensureBounds();
		setImageFromList();
		setData();
	}
	
	private void setData(){
		Controller.scrollableStage.setTitle(list.get(index).getName());
		label.setText(list.get(index).getPrice() + "ð.");
	}
	
	private void setImageFromList(){
		imgView.setImage(new Image(list.get(index).getImgSource()));
	}
	
	private void ensureBounds(){
		if (index < 0)
			index = list.size() - 1;
		if (index == list.size())
			index = 0;
	}
	
	private void move(boolean forward){
		if (forward) index += 1;
		else index -= 1;
	}
	
	private void findIndex(){
		index = -1;
		for (int i = 0; i < list.size(); i++)
			if (Controller.imgSource == list.get(i).getImgSource())
				index = i;
		if (index < 0)
			new FileNotFoundException().printStackTrace();
	}
}

package application;





import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateYourListController{
	
	
	@FXML
	private TextField nameInput = new TextField();
	@FXML
	private TextField quanInput = new TextField();
	@FXML
	private TableView<Things> tableThings;
	@FXML
	private TableColumn<Things, String> nameColumn;
	@FXML
	private TableColumn<Things, Integer> quanColumn;
	
	private ObservableList<Things> things = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		init();
		nameColumn.setCellValueFactory(new PropertyValueFactory<Things,String>("thingName"));
		quanColumn.setCellValueFactory(new PropertyValueFactory<Things,Integer>("quantity"));
		tableThings.setItems(things);
	}
	
	void init(){
		things.add(new Things("Спички",5));
		things.add(new Things("Мобила",1));
		things.add(new Things("Фонарик",2));
	}
	
	
	

}

	
	

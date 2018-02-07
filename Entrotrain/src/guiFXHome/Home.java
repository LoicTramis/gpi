package guiFXHome;

import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.fxml.*;

public class Home implements Initializable {
	ObservableList<String> themeList = FXCollections.observableArrayList(
				"Faune",
				"Culture",
				"Sport",
				"Astronomie"
			);
	@FXML
	private ComboBox<String> themeBox;
	@FXML
	private Label themeLabel;
	@FXML
	private Label clientLabel;
	@FXML
	private JFXTextField userInput;
	
	/**
	 * Show the items of the choice box
	 */
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		themeBox.setItems(themeList);
	}
	
	@FXML
	public void play() {
	}
}
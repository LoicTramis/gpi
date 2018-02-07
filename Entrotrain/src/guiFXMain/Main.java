package guiFXMain;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	private static String screenHomeFile = "/guiFXHome/HomePanel.fxml";
	private static String applicationCssFile = "/guiFXMain/application.css";
	private String titleApp = "Entroquizz";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(screenHomeFile));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource(applicationCssFile).toExternalForm());
			primaryStage.setTitle(titleApp);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
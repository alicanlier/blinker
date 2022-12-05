package blinker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlinkerMain extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));	
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("단어암기깜빡이1.0");
		primaryStage.show();
	}

}

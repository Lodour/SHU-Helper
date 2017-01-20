package shuhelper.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 

public class shuhelpapp extends Application{
	
	 public static void main(String[] args) 
	 {
	        Application.launch(shuhelpapp.class, args);
	 }
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("LoginFrame.fxml"));
		Scene scene = new Scene(root,600,400);
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
        stage.setTitle("SHU-Helper_Login");
        stage.show();
	}

}

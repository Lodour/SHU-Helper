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
		WindowFrame Login = new WindowFrame("LoginFrame");
		Login.Start();
		Login.SetTitle("SHU-Helper_Login");
	}

}

package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginFrameWindow {
	
	private LoginFrame controller = new LoginFrame();
	Stage stage = new Stage();

	public LoginFrameWindow() throws IOException
	{	
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("LoginFrame.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,500,347);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
	}
}

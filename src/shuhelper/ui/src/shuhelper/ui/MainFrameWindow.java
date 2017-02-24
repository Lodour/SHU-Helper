package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFrameWindow{

	MainFrame controller = new MainFrame();
	Stage stage = new Stage();
	
	MainFrameWindow() throws IOException
	{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,600,400);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.show();
	}

	
}

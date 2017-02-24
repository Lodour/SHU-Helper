package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CJ_IdentFrameWindow {
	private CJ_IdentFrame controller = new CJ_IdentFrame();
	Stage stage = new Stage();
	
	CJ_IdentFrameWindow() throws IOException
	{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("CJ_IdentFrame.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,266,294);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
	}

}

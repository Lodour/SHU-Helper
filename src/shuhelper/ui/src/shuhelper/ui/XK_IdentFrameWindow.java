package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class XK_IdentFrameWindow {
	XK_IdentFrame controller = new XK_IdentFrame();
	Stage stage = new Stage();
	
	XK_IdentFrameWindow() throws IOException
	{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("XK_IdentFrame.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,266,294);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
	}

}

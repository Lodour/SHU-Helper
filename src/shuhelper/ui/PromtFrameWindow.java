package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PromtFrameWindow {
	public PromtFrame controller = new PromtFrame();
	Stage stage = new Stage();
	
	PromtFrameWindow() throws IOException
	{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("PromtFrame.fxml"));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,300,90);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.initStyle(StageStyle.TRANSPARENT);
	}
}

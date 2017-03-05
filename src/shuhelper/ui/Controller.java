package shuhelper.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class Controller {
	protected Stage stage;
	protected Scene scene;
	public  String name;
	public Label label;

	public void setStage(Stage stage){
	     this.stage = stage;
	}
	public void setScene(Scene scene){
	     this.scene = scene;
	}
	public void Close()
	{
		stage.close();
	}
}
	
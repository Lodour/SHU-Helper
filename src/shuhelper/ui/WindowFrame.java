package shuhelper.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowFrame {
	String FrameName;
	Stage stage;
	WindowFrame(String a)
	{
		FrameName = a + ".fxml";
		stage = new Stage();
	}
	public void Start() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource(FrameName));
		Scene scene = new Scene(root,600,400);
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
        stage.show();
	}
	public void SetTitle(String name)
	{
		 stage.setTitle(name);
	}
	public void Close()
	{
		stage.close();
	}

}

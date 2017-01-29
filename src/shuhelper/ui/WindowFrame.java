package shuhelper.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowFrame {
	String FrameName;
	Stage stage;
	private double height,weight;
	
	WindowFrame(String a,double h,double w)
	{
		FrameName = a + ".fxml";
		stage = new Stage();
		height = h;
		weight = w;
	}
	public void Start() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource(FrameName));
		Scene scene = new Scene(root,height,weight);
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
	}
	public void SetTitle(String name)
	{
		 stage.setTitle(name);
	}
	public void Show()
	{
		stage.show();
	}
	
	public void Close()
	{
		stage.close();
	}


}

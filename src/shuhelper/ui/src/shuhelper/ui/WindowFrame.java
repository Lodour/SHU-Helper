package shuhelper.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowFrame {
	Controller controller;
	String FrameName;
	Stage stage;
	boolean istransparent;
	private double height,weight;
	
	WindowFrame(String a,double h,double w,boolean transparent)
	{
		FrameName = a + ".fxml";
		stage = new Stage();
		height = h;
		weight = w;
		istransparent = transparent;
	}
	public void Start() throws Exception
	{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(FrameName));
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root,height,weight);
		controller = fxmlloader.getController(); 
		controller.setStage(stage);
	    controller.setScene(scene);
		stage.setScene(scene);
		if(istransparent)
		{
			stage.initStyle(StageStyle.TRANSPARENT);
		}
		else
		{
			stage.initStyle(StageStyle.DECORATED);
		}
		stage.show();
	}
	public void SetTitle(String name)
	{
		 stage.setTitle(name);
	}
//	public void Show()
//	{
//		System.out.println(FrameName);
//		stage.show();
//	}
//	
	public void Close()
	{
		stage.close();
	}
}

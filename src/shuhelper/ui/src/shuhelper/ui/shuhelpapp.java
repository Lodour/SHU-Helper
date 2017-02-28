package shuhelper.ui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shuhelper.scheduing.Schedule;
import shuhelper.scheduing.TestSchedule;
import shuhelper.web.*;

public class shuhelpapp extends Application{
	public static CJWebAPI CJ;
	public static XKWebAPI XK;
	public static LoginFrameWindow Login;
	public static Schedule test;//≤È—ØøŒ≥Ã¿‡
	
	
	 public static void main(String[] args) throws Exception 
	 {
		    CJ = new CJWebAPI();
		    XK = new XKWebAPI();
		    test = new Schedule();
	        Application.launch(shuhelpapp.class, args);
	 }
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Login = new LoginFrameWindow();
		Login.stage.show();
	}
}

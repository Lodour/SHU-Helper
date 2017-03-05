package shuhelper.ui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shuhelper.schedule.Schedule;
import shuhelper.schedule.TestSchedule;
import shuhelper.web.*;

public class shuhelpapp extends Application{
	public static CJWebAPI CJ;
	public static XKWebAPI XK;
	public static LoginFrameWindow Login;//登录窗口
	public static MainFrameWindow MainFrame ;//主界面
	//验证码窗口
	public static CJ_IdentFrameWindow CJ_IdentFrame;
	public static XK_IdentFrameWindow XK_IdentFrame;
	//账号密码错误窗口
	public static PromtFrameWindow PromtFrame;
	//错误提示窗
	public static WindowFrame ErrorFrame;
	public static Schedule test;//查询课程类
	
	//检测是否已经登录
	public static Boolean CJ_Islogin = false;
	public static Boolean XK_Islogin = false;
	
	
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
		//登录窗口
		Login = new LoginFrameWindow();
		//主窗口
		MainFrame = new MainFrameWindow();
//		验证码 
		CJ_IdentFrame = new CJ_IdentFrameWindow();
		CJ_IdentFrame.stage.setTitle("CJ_IdentFrame");
		XK_IdentFrame = new XK_IdentFrameWindow();
		XK_IdentFrame.stage.setTitle("XK_IdentFrame");
		
		PromtFrame = new PromtFrameWindow();

		Login.stage.show();
	}
}

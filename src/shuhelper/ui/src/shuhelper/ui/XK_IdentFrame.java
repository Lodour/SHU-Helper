package shuhelper.ui;

import java.io.IOException;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class XK_IdentFrame extends Controller{

	private String username = LoginFrame.username;
	private String password = LoginFrame.password;
	//验证码
	private String XK_identify;
	//验证码地址
	private String XK_validatePath;
	//按钮
	@FXML
	private Button  XK_Button;
	//输入数据
	@FXML
	private TextField XK_Text;
	//验证码图片
	@FXML
	private ImageView XK_Image;
	//提示信息
	@FXML
	private Label XK_Prompt;
	
	//错误窗口
	private WindowFrame PromtFrame = new WindowFrame("PromtFrame",300,90,true);
	
	//更换验证码	
	@FXML
	public void XK_ChangeImage(MouseEvent e) throws IOException
	{
		XKvalidate task = new XKvalidate();
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	XK_validatePath = task.GetPath();
            	Image xkimage = new Image("File:"+XK_validatePath);
        		XK_Image.setImage(xkimage);
        		wait.cancelProgressBar();
            }
        });
	}
	
	@FXML
	public void XK_ButtonAction(ActionEvent e) throws Exception
	{
		XK_identify = XK_Text.getText();
		XKlogintask task = new XKlogintask(username,password,XK_identify);
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	String res = task.GetPath();
            	wait.cancelProgressBar();
            	System.out.println(res);
        		if(res.equals("OK"))
        		{
        			String labelText = "登录成功！";
        			XK_Prompt.setText(labelText);
        			stage.close();
        			try {
						shuhelpapp.test.initCourseStatus(shuhelpapp.test.selectedCourse(shuhelpapp.XK));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        		else if(res.equals("验证码错误！"))
        		{
        			String labelText = "验证码不正确请重新输入";
        			XK_Prompt.setText(labelText);
        		}
        		else
        		{
        			String labelText = "账号密码错误请重新输入";
        			XK_Prompt.setText(labelText);
        			stage.close();
        			try {
						PromtFrame.Start();
						PromtFrame.Show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			PromtFrame.SetTitle("SHU-Helper_Error");
        		}
            }
        });
		 
	}	
	public void CloseFrame()
	{
		stage.close();
	}
}

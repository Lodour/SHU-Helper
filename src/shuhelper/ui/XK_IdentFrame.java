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
	

	private void Change()
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
	//更换验证码	
	@FXML
	public void XK_ChangeImage(MouseEvent e) throws IOException
	{
		Change();
	}
	
	@FXML
	public void XK_ButtonAction(ActionEvent e) throws Exception
	{
		XK_identify = XK_Text.getText();
		XKlogintask task = new XKlogintask(LoginFrame.username,LoginFrame.password,XK_identify);
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	//登录XK线程
            	String res = task.GetPath();
            	System.out.println(res);
        		if(res.equals("OK"))
        		{
        			String labelText = "登录成功！";
        			//登录成功更新XK登录状态
        			shuhelpapp.XK_Islogin = true;
        			XK_Prompt.setText(labelText);
        			stage.close();
        			//更新当前课表状态
        			try {
						shuhelpapp.test.initCourseStatus(shuhelpapp.test.selectedCourse(shuhelpapp.XK));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			//获取已选课程和排名
        			try {
						shuhelpapp.MainFrame.controller.enrollRank = shuhelpapp.XK.getEnrollRankArrayList();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			//获取已选课程信息
        			try {
						shuhelpapp.MainFrame.controller.courseTable = shuhelpapp.XK.getCourseTableArrayList();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			//初始化课表
        			wait.cancelProgressBar();
        		}
        		else if(res.equals("验证码错误！"))
        		{
        			wait.cancelProgressBar();
        			String labelText = "验证码不正确请重新输入";
        			XK_Prompt.setText(labelText);
        			Change();
        		}
        		else
        		{
        			wait.cancelProgressBar();
        			String labelText = "账号密码错误请重新输入";
        			//更新验证码
        			Change();
        			stage.close();
        			shuhelpapp.MainFrame.stage.close();
        			XK_Prompt.setText(labelText);
        			try {
        				shuhelpapp.PromtFrame.controller.label.setText("账号密码错误，请重新输入");
						shuhelpapp.PromtFrame.stage.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
            }
        });
		 
	}	
	public void CloseFrame()
	{
		stage.close();
	}
}

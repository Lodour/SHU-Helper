package shuhelper.ui;


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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CJ_IdentFrame extends Controller{
	private String username = LoginFrame.username;
	private String password = LoginFrame.password;
	//验证码
	private String CJ_identify;
	//验证码地址
	private String CJ_validatePath;
	//按钮
	@FXML
	private Button  CJ_Button;
	//输入数据
	@FXML
	private TextField CJ_Text;
	//验证码图片
	@FXML
	private ImageView CJ_Image;
	//提示信息
	@FXML
	private Label CJ_Prompt;
	
	//错误窗口
	private WindowFrame PromtFrame = new WindowFrame("PromtFrame",300,90,true);
	
	//更换验证码
	@FXML
	public void CJ_ChangeImage(MouseEvent e) throws Exception
	{
		CJvalidate task = new CJvalidate();
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	CJ_validatePath = task.GetPath();
            	Image cjimage = new Image("File:"+CJ_validatePath);
        		CJ_Image.setImage(cjimage);
        		wait.cancelProgressBar();
            }
        });
	}
	@FXML
	public void CJ_ButtonAction(ActionEvent e) throws Exception
	{
		CJ_identify = CJ_Text.getText();
		CJlogintask task = new CJlogintask(username,password,CJ_identify);
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
            	String res = task.GetPath();
            	wait.cancelProgressBar();
        		if(res == "OK")
        		{
        			String labelText = "登录成功！";
        			CJ_Prompt.setText(labelText);
        			stage.close();
        			
        		}
        		else if(res.compareTo("提供的验证码不正确！") == 0)
        		{
        			String labelText = "验证码不正确请重新输入";
        			CJ_Prompt.setText(labelText);
        		}
        		else
        		{
        			String labelText = "账号密码错误请重新输入";
        			CJ_Prompt.setText(labelText);
        			stage.close();
        			try {
						PromtFrame.Start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			PromtFrame.SetTitle("SHU-Helper_Error");
        		}
            }
        });
	}
	@FXML
	public void CloseFrame()
	{
		stage.close();
	}
}

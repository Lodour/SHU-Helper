package shuhelper.ui;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent; 


import shuhelper.web.*;

public class IdentifyFrame {
	private String username = LoginFrame.username;
	private String password = LoginFrame.password;
	//验证码
	private String CJ_identify,XK_identify;
	//验证码地址
	private String CJ_validatePath,XK_validatePath;
	//按钮
	@FXML
	private Button  CJ_Button,XK_Button;
	//输入数据
	@FXML
	private TextField CJ_Text,XK_Text;
	//验证码图片
	@FXML
	private ImageView CJ_Image,XK_Image;
	//提示信息
	@FXML
	private Label CJ_Prompt,XK_Prompt;
	
	//错误窗口
	private WindowFrame PromtFrame = new WindowFrame("PromtFrame",600,400);
	
	//更换验证码
	@FXML
	public void CJ_ChangeImage(MouseEvent e) throws IOException
	{
		CJ_validatePath = shuhelpapp.CJ.getCaptcha();
		Image cjimage = new Image("File:"+CJ_validatePath);
		CJ_Image.setImage(cjimage);
	}
	
	@FXML
	public void XK_ChangeImage(MouseEvent e) throws IOException
	{
		XK_validatePath = shuhelpapp.XK.getCaptcha();
		Image xkimage = new Image("File:"+XK_validatePath);
		XK_Image.setImage(xkimage);
	}
	
	@FXML
	public void CJ_ButtonAction(ActionEvent e) throws Exception
	{
		CJ_identify = CJ_Text.getText();
		String res = shuhelpapp.CJ.login(username, password, CJ_identify);
		System.out.println(res);
		if(res == "OK")
		{
			String labelText = "登录成功！";
			CJ_Prompt.setText(labelText);
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
			
			CJ_Button.getScene().getWindow().hide();
			PromtFrame.Start();
			PromtFrame.SetTitle("SHU-Helper_Error");
			PromtFrame.Show();
		}
		
	}
	@FXML
	public void XK_ButtonAction(ActionEvent e) throws Exception
	{
		XK_identify = XK_Text.getText();
		String res = shuhelpapp.XK.login(username, password, XK_identify);
		System.out.println(res);
		if(res == "OK")
		{
			String labelText = "登录成功！";
			XK_Prompt.setText(labelText);
		}
		else if(res.compareTo("验证码错误！") == 0)
		{
			String labelText = "验证码不正确请重新输入";
			XK_Prompt.setText(labelText);
		}
		else
		{
			String labelText = "账号密码错误请重新输入";
			XK_Prompt.setText(labelText);
			
			XK_Button.getScene().getWindow().hide();
			PromtFrame.Start();
			PromtFrame.SetTitle("SHU-Helper_Error");
			PromtFrame.Show();
		}
	}
}

package shuhelper.ui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class LoginFrame {
	//账号密码数据
	private String username,password;
	//账号密码文本框
	@FXML
	private TextField UsernameText;
	@FXML
	private PasswordField  PasswordText;
	//按钮
	@FXML
	private Button  LoginButton,ResetButton;
	@FXML
	private ImageView Logo;
	
	//获取文本信息
	private void GetText()
	{
		
		username = UsernameText.getText();
		password = new String(PasswordText.getText());
	}
		
	//按钮事件
	@FXML
	private void LoginButtonEvent(ActionEvent e)
	{
		GetText();
		System.out.println(username);
		System.out.println(password);
	}
	@FXML
	private void ResetButtonEvent(ActionEvent e)
	{
		UsernameText.setText("");
		PasswordText.setText("");
	}
	

}

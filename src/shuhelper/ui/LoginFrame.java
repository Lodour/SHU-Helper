package shuhelper.ui;

import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


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
	
	WindowFrame Identify ;
	
	//获取文本信息
	private void GetText()
	{
		username = UsernameText.getText();
		password = new String(PasswordText.getText());		
	}
		
	//按钮事件
	@FXML
	private void LoginButtonEvent(ActionEvent e) throws Exception
	{
		GetText();
		System.out.println(username);
		System.out.println(password);
		LoginButton.getScene().getWindow().hide();
		WindowFrame Identify = new WindowFrame("IdentifyFrame");
		Identify.Start();
		Identify.SetTitle("SHU-Helper");
	}
	@FXML
	private void ResetButtonEvent(ActionEvent e)
	{
		UsernameText.setText("");
		PasswordText.setText("");
		Identify.Close();
	}
	

}

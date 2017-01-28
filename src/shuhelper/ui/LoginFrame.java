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
	//�˺���������
	private String username,password;
	//�˺������ı���
	@FXML
	private TextField UsernameText;
	@FXML
	private PasswordField  PasswordText;
	//��ť
	@FXML
	private Button  LoginButton,ResetButton;
	@FXML
	private ImageView Logo;
	
	WindowFrame Identify ;
	
	//��ȡ�ı���Ϣ
	private void GetText()
	{
		username = UsernameText.getText();
		password = new String(PasswordText.getText());		
	}
		
	//��ť�¼�
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

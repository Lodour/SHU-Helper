package shuhelper.ui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


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
	
	//��ȡ�ı���Ϣ
	private void GetText()
	{
		
		username = UsernameText.getText();
		password = new String(PasswordText.getText());
	}
		
	//��ť�¼�
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

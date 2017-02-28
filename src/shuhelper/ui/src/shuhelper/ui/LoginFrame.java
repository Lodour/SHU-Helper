package shuhelper.ui;

import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginFrame extends Controller{
	//�˺���������
	public static String username;
	public static String password;
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
	
	MainFrameWindow MainFrame ;
	
	
	public LoginFrame()
	{
		name = "Login";
	}
	
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
		LoginButton.getScene().getWindow().hide();
		stage.close();
		MainFrame = new MainFrameWindow();
		MainFrame.stage.show();
		
	}
	@FXML
	private void ResetButtonEvent(ActionEvent e)
	{
		UsernameText.setText("");
		PasswordText.setText("");
	}
	

}

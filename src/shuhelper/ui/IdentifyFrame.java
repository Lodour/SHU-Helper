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
	//��֤��
	private String CJ_identify,XK_identify;
	//��֤���ַ
	private String CJ_validatePath,XK_validatePath;
	//��ť
	@FXML
	private Button  CJ_Button,XK_Button;
	//��������
	@FXML
	private TextField CJ_Text,XK_Text;
	//��֤��ͼƬ
	@FXML
	private ImageView CJ_Image,XK_Image;
	//��ʾ��Ϣ
	@FXML
	private Label CJ_Prompt,XK_Prompt;
	
	//���󴰿�
	private WindowFrame PromtFrame = new WindowFrame("PromtFrame",600,400);
	
	//������֤��
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
			String labelText = "��¼�ɹ���";
			CJ_Prompt.setText(labelText);
		}
		else if(res.compareTo("�ṩ����֤�벻��ȷ��") == 0)
		{
			String labelText = "��֤�벻��ȷ����������";
			CJ_Prompt.setText(labelText);
		}
		else
		{
			String labelText = "�˺������������������";
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
			String labelText = "��¼�ɹ���";
			XK_Prompt.setText(labelText);
		}
		else if(res.compareTo("��֤�����") == 0)
		{
			String labelText = "��֤�벻��ȷ����������";
			XK_Prompt.setText(labelText);
		}
		else
		{
			String labelText = "�˺������������������";
			XK_Prompt.setText(labelText);
			
			XK_Button.getScene().getWindow().hide();
			PromtFrame.Start();
			PromtFrame.SetTitle("SHU-Helper_Error");
			PromtFrame.Show();
		}
	}
}

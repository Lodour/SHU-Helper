package shuhelper.ui;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class IdentifyFrame {
	//��֤��
	private String CJ_identify,XK_identify;
	//��֤���ַ
	private String validatePath;
	//��ť
	@FXML
	private Button  CJ_Button,XK_Button;
	//��֤��ͼƬ
	@FXML
	private ImageView CJ_Image,XJ_image;
	//������֤��
	@FXML
	public void CJ_ChangeImage(ActionEvent e)
	{
		System.out.println("changeIMage");
//		validatePath = web.getCaptcha();
//		Image validate = new Image(validatePath);
//		CJ_Image.setImage(validate);
	}
	
	@FXML
	public void XK_ChangeImage(ActionEvent e)
	{
//		validatePath = web.getCaptcha();
//		Image validate = new Image(validatePath);
//		CJ_Image.setImage(validate);
	}
	
	@FXML
	public void CJ_ButtonAction(ActionEvent e)
	{
//		validatePath = web.getCaptcha();
//		Image validate = new Image(validatePath);
//		CJ_Image.setImage(validate);
	}
	@FXML
	public void XK_ButtonAction(ActionEvent e)
	{
//		validatePath = web.getCaptcha();
//		Image validate = new Image(validatePath);
//		CJ_Image.setImage(validate);
	}
}

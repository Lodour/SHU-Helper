package shuhelper.ui;

import java.io.File;
import java.io.IOException;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.http.client.ClientProtocolException;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;   
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent; 
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import shuhelper.web.*;

public class MainFrame extends Controller{
	//ÑéÖ¤Âë´°¿Ú
	private CJ_IdentFrameWindow CJ_IdentFrame;
	private XK_IdentFrameWindow XK_IdentFrame;
	
	@FXML
	private TabPane ChosePane;
	//Tab
	@FXML
	private Tab CJ_IdentTab, XK_IdentTab;
	
	@FXML
	private Button CJ_Button,XK_Button;
	
	//µÇÂ¼×´Ì¬
	private boolean CJ_status = false;
	private boolean XK_status = false;
	
	public MainFrame() throws IOException
	{
		CJ_IdentFrame = new CJ_IdentFrameWindow();
		CJ_IdentFrame.stage.setTitle("CJ_IdentFrame");
		XK_IdentFrame = new XK_IdentFrameWindow();
		XK_IdentFrame.stage.setTitle("XK_IdentFrame");
	}
	public void CJ_Offline() throws Exception
	{
		try {
			CJ_status = shuhelpapp.CJ.isLogin();
		} catch (Exception e) {
			// Do nothing
		}
		if(CJ_status == false)
		{
			XK_IdentFrame.stage.close();
			CJ_IdentFrame.stage.show();
//			CJ_IdentFrame.Show();
//			CJ_IdentFrame.stage.setAlwaysOnTop(true);
			CJ_IdentFrame.stage.setResizable(false);
		}
	}
	
	public void XK_Offline() throws Exception
	{
		try {
			XK_status = shuhelpapp.XK.isLogin();
		} catch (Exception e) {
			// Do nothing
		}
		if(XK_status == false)
		{
			CJ_IdentFrame.stage.close();
			XK_IdentFrame.stage.show();
//			XK_IdentFrame.Show();
			XK_IdentFrame.stage.setResizable(false);
		}
	}
	@FXML
	public void XK_Tabaction() throws Exception
	{
		if(XK_IdentTab.isSelected())
		{
			XK_Offline();
		}
		else
		{
			CJ_Offline();
		}
	}
		
}

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
	//��֤��
	private String CJ_identify;
	//��֤���ַ
	private String CJ_validatePath;
	//��ť
	@FXML
	private Button  CJ_Button;
	//��������
	@FXML
	private TextField CJ_Text;
	//��֤��ͼƬ
	@FXML
	private ImageView CJ_Image;
	//��ʾ��Ϣ
	@FXML
	private Label CJ_Prompt;
	
	//���󴰿�
	private WindowFrame PromtFrame = new WindowFrame("PromtFrame",300,90,true);
	
	//������֤��
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
        			String labelText = "��¼�ɹ���";
        			CJ_Prompt.setText(labelText);
        			stage.close();
        			
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

package shuhelper.ui;

import java.io.IOException;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ChoseFrame extends Controller{
	@FXML
	private Button FirstChose;
	@FXML
	private Button SecondChose;
	@FXML
	private Button ChoseButton;
	@FXML
	private Pane ChosePane;
	@FXML
	private AnchorPane anchorPane;
	private String Firstterm,Secondterm;
	@FXML
	public void ChoseAction() throws Exception
	{
		ChoseFrameTask task = new ChoseFrameTask();
		waitframe wait = new waitframe(stage);
		wait.activateProgressBar();
        
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent event) {
				String[] termInfo = task.GetPath();
				Firstterm = new String(termInfo[0]);
				Secondterm = new String(termInfo[1]);
				FirstChose.setText(Firstterm);
				SecondChose.setText(Secondterm);
				stage.setHeight(160);
				ChosePane.setVisible(true);
				wait.cancelProgressBar();
			}
        });
	}
	public void FirstAction() throws Exception
	{
		shuhelpapp.XK.setTerm(0);
		stage.close();
		MainFrame.CJ_IdentFrame.stage.close();
		MainFrame.XK_IdentFrame.stage.show();
		MainFrame.XK_IdentFrame.stage.setResizable(false);
	}
	public void SecondAction() throws Exception
	{
		shuhelpapp.XK.setTerm(1);
		stage.close();
		MainFrame.CJ_IdentFrame.stage.close();
		MainFrame.XK_IdentFrame.stage.show();
		MainFrame.XK_IdentFrame.stage.setResizable(false);
	}
	public void SureAction()
	{
		stage.close();
	}
	
}

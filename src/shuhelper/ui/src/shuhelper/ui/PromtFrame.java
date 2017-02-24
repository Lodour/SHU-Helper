package shuhelper.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView; 

public class PromtFrame extends Controller{
	@FXML
	private ImageView Sure;
	
	public PromtFrame()
	{
		name = "Promt";
	}
	@FXML
	private void ButtonAction()
	{
		stage.close();
		shuhelpapp.Login.stage.show();	
	}

}

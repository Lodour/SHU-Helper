package shuhelper.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button; 

public class PromtFrame {
	@FXML
	private Button Sure;
	@FXML
	private void ButtonAction()
	{
		Sure.getScene().getWindow().hide();
		shuhelpapp.Login.Show();
	}

}

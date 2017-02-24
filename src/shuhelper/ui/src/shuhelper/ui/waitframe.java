package shuhelper.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class waitframe extends Controller{
	private Stage dialogStage;
    private ProgressIndicator progressIndicator;

    public waitframe(Stage primaryStage) {

        dialogStage = new Stage();
        progressIndicator = new ProgressIndicator();

        // 窗口父子关系
        dialogStage.initOwner(primaryStage);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        // progress bar
        Label label = new Label("数据加载中, 请稍后...");
        label.setFont(new Font(16));
        label.setTextFill(Color.BLUE);
        progressIndicator.setProgress(-1F);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);

        Scene scene = new Scene(vBox);
        scene.setFill(null);
        dialogStage.setScene(scene);
    }

    public void activateProgressBar() {
        dialogStage.show();
    }

    public Stage getDialogStage(){
        return dialogStage;
    }

    public void cancelProgressBar() {
        dialogStage.close();
    }
}


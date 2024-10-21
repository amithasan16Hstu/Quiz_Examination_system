package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentHomePage {

    @FXML
    private Button backButton;

    @FXML
    private Button previousQuestionButton;


    @FXML
    private Button detailsButton;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button hstuButton;

    @FXML
    private Button resultButton;

    @FXML
    private Button startQuizButton;

    @FXML
    void onActionBackButton(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("studentLogin.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) resultButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionStartQuizButton(ActionEvent event) {
        try {
            // Load the StudentClient application
            Stage studentClientStage = new Stage();
            StudentClient studentClient = new StudentClient();
            studentClient.start(studentClientStage);

            // Close the current window (StudentHomePage)
            Stage currentStage = (Stage) startQuizButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ckickHstuButton(ActionEvent event) {
        FxmlLoaderTeacher obj=new FxmlLoaderTeacher();
        Pane view=obj.getPage("hstu");
        mainBorderPane.setCenter(view);
    }

    @FXML
    void previousQuestionButtonOnAction(ActionEvent event) {
        FxmlLoaderTeacher obj=new FxmlLoaderTeacher();
        Pane view=obj.getPage("privousQuestion");
        mainBorderPane.setCenter(view);
    }

    @FXML
    void onActionDetailsButton(ActionEvent event) {
        FxmlLoaderTeacher obj=new FxmlLoaderTeacher();
        Pane view=obj.getPage("studentInfo");
        mainBorderPane.setCenter(view);
    }

    @FXML
    void onActionResutlButton(ActionEvent event) {
        FxmlLoaderTeacher obj=new FxmlLoaderTeacher();
        Pane view=obj.getPage("result");
        mainBorderPane.setCenter(view);
    }
}

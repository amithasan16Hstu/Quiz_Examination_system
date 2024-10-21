package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherHomePage {

    @FXML
    private Button detailsButton;

    @FXML
    private Button backButton;

    @FXML
    private BorderPane mainBorderPane;


    @FXML
    private Button setQuizButton;

    @FXML
    private Button studentListButton;


    @FXML
    void OnActionSetQuizButton(ActionEvent event) {
        // Open SetQuestionPage when Set Quiz button is clicked
        Stage primaryStage = (Stage) mainBorderPane.getScene().getWindow();
        SetQuestionPage setQuestionPage = new SetQuestionPage(primaryStage);
        Scene setQuestionScene = new Scene(setQuestionPage.getLayout(), 1300, 770);
        primaryStage.setScene(setQuestionScene);
    }


    @FXML
    void onActionDetailsButton(ActionEvent event) {
        FxmlLoaderTeacher obj = new FxmlLoaderTeacher();
        Pane view = obj.getPage("teacherDetails");
        mainBorderPane.getChildren().setAll(view);
    }

    @FXML
    void onActionBackButton(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

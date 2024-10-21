package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class HelpDesk {

    @FXML
    private ImageView backButton;

    @FXML
    private Button course;

    @FXML
    private Text helpDeskText;

    @FXML
    private Button hstuWebsiteButton;

    @FXML
    private Button teacherButton;

    @FXML
    void onActionBackButton(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("teacherOrStudent.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) course.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionTeacherButton(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("teacher.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) course.getScene().getWindow();
            stage.setTitle("Teacher");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActioncourse(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("course.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) course.getScene().getWindow();
            stage.setTitle("Course");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionhstuWebsiteButton(ActionEvent event) {
        try {
            // Open the HSTU website in the default browser
            Desktop.getDesktop().browse(new URI("https://www.hstu.ac.bd/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

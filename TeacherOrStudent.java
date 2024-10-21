package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

public class TeacherOrStudent {

    @FXML
    private Button helpDeskButton;

    @FXML
    private Button studentButton;

    @FXML
    private Button teacherButton;

    // Initialize method to set up button effects
    @FXML
    public void initialize() {
        // Set common hover effects for all buttons
        setButtonHoverEffect(helpDeskButton);
        setButtonHoverEffect(studentButton);
        setButtonHoverEffect(teacherButton);

        // Optionally, add other effects like shadow or glow
        addDropShadowEffect(helpDeskButton);
        addDropShadowEffect(studentButton);
        addDropShadowEffect(teacherButton);
    }

    // Method to add hover effect to a button
    private void setButtonHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;"));
    }

    // Method to add drop shadow effect
    private void addDropShadowEffect(Button button) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY);
        button.setEffect(dropShadow);

        // You can add a glow effect too, like this:
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
            button.setEffect(new Glow(0.3));  // Add glow when hovered
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
            button.setEffect(dropShadow);  // Reset to drop shadow
        });
    }

    @FXML
    void onActionHelpDesk(ActionEvent event) {
        Track.student = true;
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("helpDesk.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) studentButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void studentButtonOnAction(ActionEvent event) {
        Track.student = true;
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("studentLogin.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) studentButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teacherButtonOnAction(ActionEvent event) {
        Track.student = false;
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) studentButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

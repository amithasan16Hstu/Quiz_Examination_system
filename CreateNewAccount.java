package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateNewAccount implements Initializable {

    @FXML
    private ImageView backButton;

    @FXML
    private PasswordField verify;

    @FXML
    private Button createButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogIn;

    @FXML
    private PasswordField reEnterPassword;

    @FXML
    private Label passwordNotSame;

    @FXML
    private DatePicker dateOfbirth;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    void createbuttonOnAction(ActionEvent event) {
        // Clear previous styles
        clearStyles();

        boolean hasErrors = false;

        // Check if fields are empty and highlight them
        if (username.getText().isEmpty()) {
            username.setStyle("-fx-border-color: red;");
            hasErrors = true;
        }
        if (password.getText().isEmpty()) {
            password.setStyle("-fx-border-color: red;");
            hasErrors = true;
        }
        if (reEnterPassword.getText().isEmpty()) {
            reEnterPassword.setStyle("-fx-border-color: red;");
            hasErrors = true;
        }
        if (phoneNumber.getText().isEmpty()) {
            phoneNumber.setStyle("-fx-border-color: red;");
            hasErrors = true;
        }
        if (verify.getText().isEmpty()) {
            verify.setStyle("-fx-border-color: red;");
            hasErrors = true;
        }

        if (hasErrors) {
            Notifications.create()
                    .text("Please fill all fields.")
                    .title("Fill Up Correctly")
                    .darkStyle()
                    .hideAfter(Duration.seconds(2))
                    .showError();
            return;
        }

        String v = verify.getText();
        String user = username.getText();
        String pass = password.getText();
        String rePass = reEnterPassword.getText();
        LocalDate localDate = dateOfbirth.getValue();
        String pattern = "MMMM dd, yyyy";
        String date = localDate.format(DateTimeFormatter.ofPattern(pattern));
        String gend = gender.getValue();
        String phoneNum = phoneNumber.getText();

        // Check verification code
        if (!fileHandling.readVerify(v)) {
            Notifications.create()
                    .text("Please enter correct code.")
                    .title("Verification Error")
                    .darkStyle()
                    .hideAfter(Duration.seconds(2))
                    .showError();
            return;
        }

        // Check if passwords match
        if (!pass.equals(rePass)) {
            passwordNotSame.setText("Passwords are not the same");
            password.setStyle("-fx-border-color: red;");
            reEnterPassword.setStyle("-fx-border-color: red;");
            return;
        }

        // Append to file if all checks pass
        fileHandling.appenedFile("teacherEmailPassword.txt", user, pass, phoneNum, gend, date);

        // Load the login scene
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setTitle("Create New Account");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearStyles() {
        username.setStyle(null);
        password.setStyle(null);
        reEnterPassword.setStyle(null);
        phoneNumber.setStyle(null);
        verify.setStyle(null);
    }

    @FXML
    void OnActionReEnterPassword(ActionEvent event) {
        // Handle action if needed
    }

    @FXML
    void onActionBackButton(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("teacherOrStudent.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage=(Stage) backButton.getScene().getWindow();
            stage.setTitle("Teacher or Student");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Male", "Female");
    }
}

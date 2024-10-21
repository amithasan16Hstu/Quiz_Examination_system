package org.example.newq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherDetails implements Initializable {
    @FXML
    private ImageView backButton;
    @FXML
    private Text dateOfBirth;

    @FXML
    private  Text gender;

    @FXML
    private  Text name;

    @FXML
    private  Text password;

    @FXML
    private  Text phoneNumber;

    static String n,p,ph,g,d;
    public static void sss(String name, String pass, String phoneN, String genderN, String dateOfBirthN) {
        n=name;
        p=pass;
        ph=phoneN;
        g=genderN;
        d=dateOfBirthN;
    }

    @FXML
    void onActionBackButton(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("teacherHomePage.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage=(Stage) backButton.getScene().getWindow();
            stage.setTitle("Teacher Home Page");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(n);
        password.setText(p);
        phoneNumber.setText(ph);
        gender.setText(g);
        dateOfBirth.setText(d);
    }
}

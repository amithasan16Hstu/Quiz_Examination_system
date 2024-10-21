package org.example.newq;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentInfo implements Initializable {

    @FXML
    private  Text dateOfBirth;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(n);
        password.setText(p);
        phoneNumber.setText(ph);
        gender.setText(g);
        dateOfBirth.setText(d);
    }
}

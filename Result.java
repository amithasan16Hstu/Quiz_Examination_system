package org.example.newq;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Result implements Initializable {

    @FXML
    private TextArea resultArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultArea.setWrapText(true);
        File file=new File("result.txt");
        int i=0;
        resultArea.appendText("\n");
        try {
            Scanner sc=new Scanner(file);
            while (sc.hasNextLine()){
                i++;
                String st=sc.nextLine();
                resultArea.appendText("     "+ i +"               "+st+'\n');
                resultArea.appendText("--------------------------------\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}

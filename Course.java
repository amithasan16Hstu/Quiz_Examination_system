package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Course {

    @FXML
    private ImageView backButton;
    @FXML
    private Button CSEButton;

    @FXML
    private Button ECEButton;

    @FXML
    private Button EEEButton;

    @FXML
    void OnActionBackButton(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("helpDesk.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) CSEButton.getScene().getWindow();
            stage.setTitle("Help Desk");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void cseButtonOnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\CSE_all_upadate.pdf"); // Change this to the path of your PDF file
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported");
                }
            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void eceButtonOnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\B_Sc_Engg_ECE_2018.pdf"); // Change this to the path of your PDF file
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported");
                }
            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void eeeButtonOnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\EEE_all_update.pdf"); // Change this to the path of your PDF file
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported");
                }
            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

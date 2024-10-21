package org.example.newq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Teacher {

    @FXML
    private ImageView backButton;
    @FXML
    private ImageView cseButton;

    @FXML
    private ImageView eceButton;

    @FXML
    private ImageView eeeButton;

    @FXML
    void OnActionBackButton(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(Main.class.getResource("helpDesk.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = (Stage) eeeButton.getScene().getWindow();
            stage.setTitle("Help Desk");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cseOnAction(MouseEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\cseTeacher.pdf"); // Change this to the path of your PDF file
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
    void eceOnAction(MouseEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\eceTeacher.pdf"); // Change this to the path of your PDF file
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
    void eeeOnAction(MouseEvent event) {
        try {
            File pdfFile = new File("D:\\JAVA CODE\\FX\\HSTU HELPER\\src\\main\\resources\\eeeTeacher.pdf"); // Change this to the path of your PDF file
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

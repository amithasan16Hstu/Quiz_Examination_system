package org.example.newq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PrivousQuestion {

    @FXML
    private Button l11;

    @FXML
    private Button l12;

    @FXML
    private Button l21;

    @FXML
    private Button l22;

    @FXML
    private Button l31;

    @FXML
    private Button l32;

    @FXML
    private Button l41;

    @FXML
    private Button l42;

    @FXML
    void l11OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L1_S1.pdf"); // Change this to the path of your PDF file
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
    void l12OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\Final L-1_S-2.pdf"); // Change this to the path of your PDF file
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
    void l21OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L2_S1.pdf"); // Change this to the path of your PDF file
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
    void l22OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L2_S2.pdf"); // Change this to the path of your PDF file
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
    void l31OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\LEVEL 3_SEMESTER 1_SULTAN.pdf"); // Change this to the path of your PDF file
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
    void l32OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L3_S2.pdf"); // Change this to the path of your PDF file
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
    void l41OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L4_S1.pdf"); // Change this to the path of your PDF file
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
    void l42OnAction(ActionEvent event) {
        try {
            File pdfFile = new File("D:\\FX\\NEWQ\\src\\main\\resources\\L4_S2.pdf"); // Change this to the path of your PDF file
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

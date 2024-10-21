package org.example.newq;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SetQuestionPage {
    private VBox layout;
    private Label questionCountLabel;

    public SetQuestionPage(Stage primaryStage) {
        // Initialize layout
        layout = new VBox(20);
        layout.setPadding(new Insets(20));

        // Set gradient background
        Background background = new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom, #757575, #424242)"), // Dark gradient color
                CornerRadii.EMPTY, // Optional: You can adjust corner radii if needed
                Insets.EMPTY));
        layout.setBackground(background);

        // Title Label
        Label titleLabel = new Label("Set Number of Questions");
        titleLabel.setFont(Font.font("Arial", 28));
        titleLabel.setTextFill(Color.WHITE); // White text color

        // Question Count Field
        TextField questionCountField = new TextField();
        questionCountField.setPromptText("Enter number of questions");
        questionCountField.setStyle("-fx-font-size: 18px; -fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Label Box
        questionCountLabel = new Label("Number of Questions:");
        questionCountLabel.setFont(Font.font("Arial", 16));
        questionCountLabel.setTextFill(Color.LIGHTGRAY); // Light gray text color
        HBox labelBox = new HBox(questionCountLabel);
        labelBox.setPadding(new Insets(0, 0, 10, 0));

        // Set Number Button
        Button setNumberButton = new Button("Set Number");
        setNumberButton.setFont(Font.font("Arial", 18));
        setNumberButton.setStyle("-fx-background-color: linear-gradient(to right, #4CAF50, #45A049); -fx-text-fill: white; -fx-padding: 12px 24px; -fx-border-radius: 5px;");
        setNumberButton.setOnAction(e -> {
            int questionCount = Integer.parseInt(questionCountField.getText());
            openQuestionDetailsPage(questionCount, primaryStage);
        });

        // Add drop shadow effect to button
        DropShadow shadow = new DropShadow();
        setNumberButton.setOnMouseEntered(e -> setNumberButton.setEffect(shadow));
        setNumberButton.setOnMouseExited(e -> setNumberButton.setEffect(null));

        // Add components to layout
        layout.getChildren().addAll(titleLabel, labelBox, questionCountField, setNumberButton);
    }

    public VBox getLayout() {
        return layout;
    }

    private void openQuestionDetailsPage(int questionCount, Stage primaryStage) {
        QuestionDetailsPage questionDetailsPage = new QuestionDetailsPage(questionCount, primaryStage);
        Scene questionDetailsScene = new Scene(questionDetailsPage.getLayout(),1300,770);
        primaryStage.setScene(questionDetailsScene);
        // Enable full-screen mode
//        primaryStage.setFullScreen(true);
    }
}

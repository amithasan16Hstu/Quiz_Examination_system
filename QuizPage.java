package org.example.newq;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class QuizPage {
    private Stage primaryStage;

    public QuizPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public VBox getLayout() {
        // Buttons
        Button updateQuestionButton = createStyledButton("Update Question", "#4CAF50", "#45A049");
        updateQuestionButton.setOnAction(e -> showUpdateOptions());
        setButtonEffects(updateQuestionButton);

        // VBox Layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50, 80, 50, 80)); // Insets: top, right, bottom, left
        layout.setBackground(new Background(new BackgroundFill(
                Color.web("#f0f0f0"), CornerRadii.EMPTY, Insets.EMPTY))); // Light background

        // Title Rectangle
        Rectangle titleRect = new Rectangle(400, 50);
        titleRect.setFill(Color.web("rgba(0, 0, 0, 0.05)"));
        titleRect.setArcWidth(20);
        titleRect.setArcHeight(20);

        // Title Label
        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label("Quiz Page");
        titleLabel.setFont(Font.font("Arial", 24));
        titleLabel.setTextFill(Color.web("#333333"));

        // Title Box
        VBox titleBox = new VBox(titleLabel);
        titleBox.setPadding(new Insets(10));

        // Add components to layout
        layout.getChildren().addAll(titleBox, updateQuestionButton);

        return layout;
    }

    private Button createStyledButton(String text, String bgColorStart, String bgColorEnd) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; " +
                "-fx-background-color: linear-gradient(to right, " + bgColorStart + ", " + bgColorEnd + "); " +
                "-fx-text-fill: white; -fx-padding: 10px 20px;");
        return button;
    }

    private void setButtonEffects(Button button) {
        // Add drop shadow effect on button hover
        button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
        button.setOnMouseExited(e -> button.setEffect(null));
    }

    private void showUpdateOptions() {
        // Replace with your update options logic
        System.out.println("Update options shown!");
    }
}

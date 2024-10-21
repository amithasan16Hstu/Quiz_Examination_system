package org.example.newq;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class StudentClient extends Application {
    private List<Question> questions;
    private int currentQuestion = 0;
    private int score = 0;
    private long startTime;
    private long endTime;
    private Timeline timeline;
    private Label timerLabel;
    private VBox layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quiz Application");

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);

        // Background with gradient
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.LIGHTCYAN)};
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, null, stops);
        layout.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Header Label
        Label headerLabel = new Label("Welcome to the Quiz");
        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #003366;");
        headerLabel.setEffect(new DropShadow(5, Color.GRAY));
        headerLabel.setPadding(new Insets(10, 0, 10, 0));

        // Timer Label
        timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ff3333;");
        timerLabel.setEffect(new DropShadow(3, Color.GRAY));
        timerLabel.setPadding(new Insets(0, 0, 10, 0));

        // Add header and timer to layout
        layout.getChildren().addAll(headerLabel, timerLabel);

        // Fetch questions from server
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", 12345)) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                questions = (List<Question>) in.readObject();
                startTime = System.currentTimeMillis();
                Platform.runLater(() -> showQuestion(layout, primaryStage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showQuestion(VBox layout, Stage primaryStage) {
        if (currentQuestion < questions.size()) {
            Question question = questions.get(currentQuestion);

            // Question Label
            Label questionLabel = new Label(question.getQuestionText());
            questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #003366;");
            questionLabel.setEffect(new DropShadow(3, Color.GRAY));
            questionLabel.setPadding(new Insets(10, 0, 10, 0));

            // Options
            VBox optionsBox = new VBox(10);
            optionsBox.setAlignment(Pos.TOP_LEFT);
            optionsBox.setPadding(new Insets(10));
            optionsBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
            optionsBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));

            ToggleGroup group = new ToggleGroup();
            for (String option : List.of(question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4())) {
                RadioButton optionButton = new RadioButton(option);
                optionButton.setToggleGroup(group);
                optionButton.setStyle("-fx-font-size: 16px; -fx-text-fill: #003366;");
                optionButton.setEffect(new DropShadow(3, Color.GRAY));
                optionButton.setOnMouseEntered(e -> optionButton.setStyle("-fx-font-size: 16px; -fx-text-fill: #0066cc;"));
                optionButton.setOnMouseExited(e -> optionButton.setStyle("-fx-font-size: 16px; -fx-text-fill: #003366;"));
                optionsBox.getChildren().add(optionButton);
            }

            // Next Button with stylish look
            Button nextButton = new Button("Next");
            nextButton.setStyle("-fx-background-color: linear-gradient(#4CAF50, #45a049); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");
            nextButton.setPadding(new Insets(10));
            nextButton.setEffect(new DropShadow(5, Color.GRAY));
            nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: linear-gradient(#45a049, #4CAF50); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;"));
            nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: linear-gradient(#4CAF50, #45a049); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;"));
            nextButton.setOnAction(e -> {
                RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
                if (selectedRadioButton != null && selectedRadioButton.getText().equals(question.getAnswer())) {
                    score++;
                }
                currentQuestion++;
                timeline.stop(); // Stop the current timeline
                showQuestion(layout, primaryStage);
            });

            // Add components to layout
            layout.getChildren().clear();
            layout.getChildren().addAll(questionLabel, optionsBox, nextButton, timerLabel);

            // Start timer for the current question
            startTimer(question.getTimer(), layout, primaryStage);
        } else {
            endTime = System.currentTimeMillis();
            showResults(layout, primaryStage);
        }
    }

    private void startTimer(int seconds, VBox layout, Stage primaryStage) {
        IntegerProperty timeSeconds = new SimpleIntegerProperty(seconds);
        timerLabel.textProperty().bind(timeSeconds.asString("Time left: %d seconds"));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> timeSeconds.set(timeSeconds.get() - 1)));
        timeline.setCycleCount(seconds);
        timeline.setOnFinished(event -> {
            currentQuestion++;
            showQuestion(layout, primaryStage);
        });
        timeline.playFromStart();
    }

    private void showResults(VBox layout, Stage primaryStage) {
        long timeTaken = (endTime - startTime) / 1000;
        int wrongAnswers = questions.size() - score;

        // Score and time taken
        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #003366;");
        scoreLabel.setEffect(new DropShadow(3, Color.GRAY));
        scoreLabel.setPadding(new Insets(10, 0, 10, 0));

        Label timeLabel = new Label("Time taken: " + timeTaken + " seconds");
        timeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #003366;");
        timeLabel.setEffect(new DropShadow(3, Color.GRAY));
        timeLabel.setPadding(new Insets(10, 0, 20, 0)); // Extra padding for spacing

        // Calculate percentages
        int totalQuestions = questions.size();
        double correctPercentage = (score / (double) totalQuestions) * 100;
        double wrongPercentage = (wrongAnswers / (double) totalQuestions) * 100;

        // PieChart for result
        PieChart resultChart = new PieChart();
        PieChart.Data correctData = new PieChart.Data("Correct: " + score + " (" + String.format("%.1f", correctPercentage) + "%)", score);
        PieChart.Data wrongData = new PieChart.Data("Wrong: " + wrongAnswers + " (" + String.format("%.1f", wrongPercentage) + "%)", wrongAnswers);

        resultChart.getData().addAll(correctData, wrongData);
        resultChart.setTitle("Results");
        resultChart.setLabelsVisible(true);
        resultChart.setLegendVisible(true);

        // Set the preferred size for the PieChart
        resultChart.setPrefSize(800, 600); // Adjusted size for the pie chart
        resultChart.setMaxSize(800, 600); // Set maximum size to ensure it doesn't shrink

        // Back button
        Button backButton = new Button("Back to Home");
        backButton.setStyle("-fx-background-color: linear-gradient(#1E90FF, #1C86EE); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        backButton.setEffect(new DropShadow(5, Color.GRAY));
        backButton.setPadding(new Insets(10));
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: linear-gradient(#1C86EE, #1E90FF); -fx-text-fill: white;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: linear-gradient(#1E90FF, #1C86EE); -fx-text-fill: white;"));
        backButton.setOnAction(e -> showHomePage(primaryStage));

        // Clear the layout and add the components in the desired order
        layout.getChildren().clear();
        layout.getChildren().addAll(scoreLabel, timeLabel, resultChart, backButton);

        // Adjust the layout size to ensure everything fits well
        layout.setPrefSize(700, 500); // Increased layout size
    }







    // Method to navigate back to the login page (stub for now)
    // Method to navigate back to the login page (fixed version)
    private void showHomePage(Stage primaryStage) {
        try {
            // Use the correct path for the FXML resource
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/newq/studentHomePage.fxml"));

            // Load the FXML and create a new Scene
            Scene scene = new Scene(loader.load());

            // Set up the stage and show the home page
            primaryStage.setTitle("Home");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

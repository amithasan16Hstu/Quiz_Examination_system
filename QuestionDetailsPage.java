package org.example.newq;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class QuestionDetailsPage {
    private Button updateButton;
    private ComboBox<String> questionIdComboBox;
    private VBox layout;
    private int questionCount;
    private List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private TextField idField, questionField, option1Field, option2Field, option3Field, option4Field, answerField, timerField;
    private Button addButton, startQuizButton;
    private Stage primaryStage;
    private Stage smallStage; // Small window stage

    public QuestionDetailsPage(int questionCount, Stage primaryStage) {
        this.questionCount = questionCount;
        this.primaryStage = primaryStage;
        layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Set gradient background
        Background background = new Background(new BackgroundFill(
                LinearGradient.valueOf("linear-gradient(to bottom, #757575, #424242)"), // Dark gradient color
                CornerRadii.EMPTY, // Optional: You can adjust corner radii if needed
                Insets.EMPTY));
        layout.setBackground(background);

        idField = createStyledTextField("Question ID");
        questionField = createStyledTextField("Question");
        option1Field = createStyledTextField("Option 1");
        option2Field = createStyledTextField("Option 2");
        option3Field = createStyledTextField("Option 3");
        option4Field = createStyledTextField("Option 4");
        answerField = createStyledTextField("Answer");
        timerField = createStyledTextField("Timer (seconds)");

        addButton = createStyledButton("Add Question");
        addButton.setOnAction(e -> addQuestion());

        startQuizButton = createStyledButton("Start Quiz");
        startQuizButton.setOnAction(e -> startQuiz());
        startQuizButton.setDisable(true);

        questionIdComboBox = new ComboBox<>(FXCollections.observableArrayList(getQuestionIds()));
        questionIdComboBox.setPromptText("Select Question ID to Update");
        questionIdComboBox.setOnAction(e -> loadQuestionDetails(questionIdComboBox.getValue()));

        updateButton = createStyledButton("Update Question");
        updateButton.setOnAction(e -> updateQuestion());
        updateButton.setDisable(true); // Disable initially

        // Add fields, buttons, and combo box to layout
        layout.getChildren().addAll(
                createFieldWithLabel("Question ID", idField),
                createFieldWithLabel("Question", questionField),
                createFieldWithLabel("Option 1", option1Field),
                createFieldWithLabel("Option 2", option2Field),
                createFieldWithLabel("Option 3", option3Field),
                createFieldWithLabel("Option 4", option4Field),
                createFieldWithLabel("Answer", answerField),
                createFieldWithLabel("Timer (seconds)", timerField),
                addButton, startQuizButton,
                questionIdComboBox, updateButton); // Add ComboBox and Update Button last

        // Enable full-screen mode
//        primaryStage.setFullScreen(true);

        // Set up small window
        smallStage = new Stage();
        smallStage.setTitle("Small Window");
        VBox smallLayout = new VBox(10);
        smallLayout.setPadding(new Insets(20));
        smallLayout.getChildren().add(new Label("This is a small window."));
        Scene smallScene = new Scene(smallLayout, 400, 300); // Define your small window size
        smallStage.setScene(smallScene);
    }

    private List<String> getQuestionIds() {
        List<String> ids = new ArrayList<>();
        for (Question q : questions) {
            ids.add(q.getId());
        }
        return ids;
    }

    public VBox getLayout() {
        return layout;
    }

    private VBox createFieldWithLabel(String labelText, TextField textField) {
        VBox fieldBox = new VBox(5);
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        fieldBox.getChildren().addAll(label, textField);
        return fieldBox;
    }

    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-color: #ffffff; " +
                "-fx-border-color: #cccccc; -fx-border-width: 1px; -fx-border-radius: 4px; " +
                "-fx-text-fill: #333333;"); // Dark text color

        // Add red border and shadow effect for highlighting
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.RED); // Red shadow color

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Focus lost
                if (textField.getText().isEmpty() || textField.getText().trim().isEmpty()) {
                    textField.setStyle("-fx-background-color: #ffcccc; -fx-border-color: #ff0000;");
                    textField.setEffect(dropShadow);
                } else {
                    textField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc;");
                    textField.setEffect(null);
                }
            }
        });

        return textField;
    }



    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: linear-gradient(to right, #4CAF50, #45A049); -fx-text-fill: white; -fx-padding: 10px 20px;");

        // Add drop shadow effect on button hover
        DropShadow shadow = new DropShadow();
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));

        return button;
    }

    // Method to validate input fields
    private boolean validateFields() {
        boolean isValid = true;

        if (idField.getText().isEmpty()) {
            highlightField(idField);
            isValid = false;
        } else {
            unhighlightField(idField);
        }

        if (questionField.getText().isEmpty()) {
            highlightField(questionField);
            isValid = false;
        } else {
            unhighlightField(questionField);
        }

        if (option1Field.getText().isEmpty()) {
            highlightField(option1Field);
            isValid = false;
        } else {
            unhighlightField(option1Field);
        }

        if (option2Field.getText().isEmpty()) {
            highlightField(option2Field);
            isValid = false;
        } else {
            unhighlightField(option2Field);
        }

        if (option3Field.getText().isEmpty()) {
            highlightField(option3Field);
            isValid = false;
        } else {
            unhighlightField(option3Field);
        }

        if (option4Field.getText().isEmpty()) {
            highlightField(option4Field);
            isValid = false;
        } else {
            unhighlightField(option4Field);
        }

        if (answerField.getText().isEmpty()) {
            highlightField(answerField);
            isValid = false;
        } else {
            unhighlightField(answerField);
        }

        try {
            Integer.parseInt(timerField.getText()); // Check if timerField is a valid integer
            unhighlightField(timerField);
        } catch (NumberFormatException e) {
            highlightField(timerField);
            isValid = false;
        }

        if (!isValid) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all fields correctly.");
        }

        return isValid;
    }

    private void highlightField(TextField field) {
        field.setStyle("-fx-font-size: 14px; " +
                "-fx-padding: 8px; " +
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffe6e6, #ffcccc); " +
                "-fx-border-color: #d32f2f; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 8px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(211,47,47,0.5), 10, 0, 0, 0); " +
                "-fx-text-fill: #333333;");
    }

    private void unhighlightField(TextField field) {
        field.setStyle("-fx-font-size: 14px; " +
                "-fx-padding: 8px; " +
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffffff, #f0f0f0); " +
                "-fx-border-color: #cccccc; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 8px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0); " +
                "-fx-text-fill: #333333;");
    }




    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);

            // Create a custom dialog pane with gradient background
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setBackground(new Background(new BackgroundFill(
                    new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                            new Stop(0, Color.web("#89CFF0")),  // Light blue
                            new Stop(1, Color.web("#FF6347"))   // Tomato red
                    ),
                    CornerRadii.EMPTY, Insets.EMPTY
            )));

            // Customize buttons (optional)
            dialogPane.getButtonTypes().forEach(buttonType -> {
                dialogPane.lookupButton(buttonType).setStyle(
                        "-fx-background-color: #1E90FF; -fx-text-fill: white;"  // Dodger blue
                );
            });

            // Customize the text (optional)
            dialogPane.lookup(".content.label").setStyle(
                    "-fx-font-size: 14px; -fx-text-fill: #333333;"
            );

            alert.showAndWait();
        });
    }

    private void addQuestion() {
        if (currentQuestion < questionCount) {
            if (!validateFields()) {
                return;
            }

            String id = idField.getText();
            String questionText = questionField.getText();
            String option1 = option1Field.getText();
            String option2 = option2Field.getText();
            String option3 = option3Field.getText();
            String option4 = option4Field.getText();
            String answer = answerField.getText();
            int timer = Integer.parseInt(timerField.getText());

            Question question = new Question(id, questionText, option1, option2, option3, option4, answer, timer);
            questions.add(question);

            currentQuestion++;
            if (currentQuestion == questionCount) {
                addButton.setDisable(true);
                startQuizButton.setDisable(false);
            } else {
                clearFields();
            }

            refreshQuestionIdComboBox(); // Refresh ComboBox with new question IDs
        }
    }

    private void clearFields() {
        idField.clear();
        questionField.clear();
        option1Field.clear();
        option2Field.clear();
        option3Field.clear();
        option4Field.clear();
        answerField.clear();
        timerField.clear();
    }

    private void startQuiz() {
        // Send questions to students via socket
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(questions);
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Show confirmation window
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quiz Information");
            alert.setHeaderText(null);
            alert.setContentText("Questions are ready. Please start the exam.");
            alert.showAndWait();

            // Clear fields and update UI elements if needed
            clearFields();
            addButton.setDisable(true);
            startQuizButton.setDisable(true);
        });
    }

    private void loadQuestionDetails(String questionId) {
        for (Question q : questions) {
            if (q.getId().equals(questionId)) {
                idField.setText(q.getId());
                questionField.setText(q.getQuestionText());
                option1Field.setText(q.getOption1());
                option2Field.setText(q.getOption2());
                option3Field.setText(q.getOption3());
                option4Field.setText(q.getOption4());
                answerField.setText(q.getAnswer());
                timerField.setText(String.valueOf(q.getTimer()));
                updateButton.setDisable(false);
                break;
            }
        }
    }

    private void updateQuestion() {
        if (!validateFields()) {
            return;
        }

        String id = idField.getText();
        String questionText = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        String answer = answerField.getText();
        int timer = Integer.parseInt(timerField.getText());

        for (Question q : questions) {
            if (q.getId().equals(id)) {
                q.setQuestionText(questionText);
                q.setOption1(option1);
                q.setOption2(option2);
                q.setOption3(option3);
                q.setOption4(option4);
                q.setAnswer(answer);
                q.setTimer(timer);
                break;
            }
        }

        clearFields();
        updateButton.setDisable(true);
        questionIdComboBox.getSelectionModel().clearSelection(); // Clear ComboBox selection
        refreshQuestionIdComboBox(); // Refresh ComboBox with updated question IDs

        // Show update successful confirmation
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText(null);
            alert.setContentText("Question updated successfully.");

            // Create a custom dialog pane with gradient background
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setBackground(new Background(new BackgroundFill(
                    new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                            new Stop(0, Color.web("#333333")),  // Dark gray
                            new Stop(1, Color.web("#1A1A1A"))   // Very dark gray
                    ),
                    CornerRadii.EMPTY, Insets.EMPTY
            )));

            // Customize buttons
            dialogPane.getButtonTypes().forEach(buttonType -> {
                ButtonType bt = (ButtonType) buttonType;
                ButtonBar.ButtonData buttonData = bt.getButtonData();
                if (buttonData == ButtonBar.ButtonData.OK_DONE) {
                    dialogPane.lookupButton(bt).setStyle(
                            "-fx-background-color: #4CAF50; -fx-text-fill: white;"  // Green button color
                    );
                } else {
                    dialogPane.lookupButton(bt).setStyle(
                            "-fx-background-color: #383838; -fx-text-fill: white;"  // Darker button color
                    );
                }
                // Add hover effect
                dialogPane.lookupButton(bt).setOnMouseEntered(event -> {
                    dialogPane.lookupButton(bt).setEffect(new DropShadow());
                });
                dialogPane.lookupButton(bt).setOnMouseExited(event -> {
                    dialogPane.lookupButton(bt).setEffect(null);
                });
            });

            // Customize the text
            dialogPane.lookup(".content.label").setStyle(
                    "-fx-font-size: 14px; -fx-text-fill: white;"
            );

            alert.showAndWait();
        });
    }

    private void refreshQuestionIdComboBox() {
        Platform.runLater(() -> {
            questionIdComboBox.setItems(FXCollections.observableArrayList(getQuestionIds()));
        });
    }

    // Method to toggle between full-screen and small window mode
    public void toggleSmallWindow() {
        if (smallStage.isShowing()) {
            smallStage.hide();
            primaryStage.setFullScreen(true); // Switch back to full-screen mode
        } else {
            smallStage.show();
            primaryStage.setFullScreen(false); // Exit full-screen mode
        }
    }
}

package org.example.newq;

import java.io.Serializable;

public class Question implements Serializable {
    private String id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private int timer;

    public Question(String id, String questionText, String option1, String option2, String option3, String option4, String answer, int timer) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.timer = timer;
    }

    public String getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public int getTimer() {
        return timer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}

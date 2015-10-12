package com.piquor.app.quiz.model;

public class Question extends BaseModel{

    private String category_id;
    private String question_id;
    private String question_text;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private String answer;
    private boolean checked;

    public int current = NONE; // hold the answer picked by the user, initial is NONE
    public static final int NONE = 1000; // No answer selected
    public static final int ANSWER_ONE_SELECTED = 0; // first answer selected
    public static final int ANSWER_TWO_SELECTED = 1; // second answer selected
    public static final int ANSWER_THREE_SELECTED = 2; // third answer selected
    public static final int ANSWER_FOUR_SELECTED = 3;// fourth answer selected

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

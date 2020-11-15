package com.sevrep.myquizapp;

class QuizModel {

    int mQuestion;
    boolean mAnswer;

    public QuizModel(int question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}

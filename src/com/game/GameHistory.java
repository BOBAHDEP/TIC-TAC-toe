package com.game;


public class GameHistory {

    private static final int MAX_NUMBER_OF_STEPS = 9;

    private static final int MIN_NUMBER_OF_STEPS = 0;

    private int currentStep = 0;

    private Field[] history;

    public GameHistory() {
        history = new Field[MAX_NUMBER_OF_STEPS];
        for (int i = 0; i < MAX_NUMBER_OF_STEPS; i++){
            history[i] = new Field();
            history[i].emptyField();
        }
    }

    public void addStep(Field field){
        if (currentStep < MAX_NUMBER_OF_STEPS){
            history[currentStep].setField(field);
            currentStep+=1;
        }
    }

    public void deleteStep(){
        if (currentStep >= MIN_NUMBER_OF_STEPS) {
            history[currentStep].emptyField();
            currentStep--;
        }
    }

    public void showHistory(){
        for (int i = 0; i < currentStep; i++){
            history[i].showField();
        }
    }

    public Field getCurrentField(){
        return getField(currentStep);
    }

    public Field getField(int stepNumber){
        if (stepNumber >= MIN_NUMBER_OF_STEPS && stepNumber < MAX_NUMBER_OF_STEPS){
            return history[stepNumber];
        } else{
            return getCurrentField();
        }
    }

    public void emptyHistory(){
        for (int i = 0; i < MAX_NUMBER_OF_STEPS; i++){
            history[i].emptyField();
        }
    }

}

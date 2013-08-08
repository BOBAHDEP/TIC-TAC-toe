package com.game;

public class Field {

    private static final char DEFAULT_CELL_VALUE = ' ';

    private static final int MAX_FIELD_SIZE = 100;

    private static final int MIN_FIELD_SIZE = 0;

    private static final int DEFAULT_FIELD_SIZE = 3;

    private static final char FIRST_PLAYER_CELL_VALUE = 'X';

    private static final char SECOND_PLAYER_CELL_VALUE = 'O';

    private static final int WIN_NUMBER_OF_CELLS = 3;

    private final int fieldSize;

    private final char [][] field;

    public Field(){
        this(DEFAULT_FIELD_SIZE);
    }

    public Field(int size){
        fieldSize = size;
        field = new char[fieldSize][fieldSize];
    }

    public int getMinCoordinateValue(){
        return 0;
    }

    public int getFieldSize(){
        return fieldSize;
    }

    public void emptyField(){
        for(int i = 0; i < fieldSize; i++) {
            araseLine(i);
        }
    }

    public void showField(){

        for (int i = 0; i < fieldSize; i++){
            System.out.println();
            showLine(i);
        }
        System.out.println();
    }

    private void showLine(int lineNumber){
        for(int i = 0; i < fieldSize; i++){
            showCell(i,lineNumber);
        }
    }

    private void showCell(int x, int y){
        if((x < fieldSize)&&(y < fieldSize)) {
            System.out.print("[" + field[x][y] + "]");
        }
        else {
            System.out.println("Wrong data in showCell");
        }
    }

    private void araseLine(int lineNumber){
        if(lineNumber < fieldSize) {
            for (int i = 0; i < fieldSize; i++){
                field[i][lineNumber] = DEFAULT_CELL_VALUE;
            }
        }
        else {
            System.out.println("Wrong data in araseLine");
        }
    }

    public boolean setCellFirstPlayer(int x, int y){
        if (cellIsFree(x,y)){
            setCell(x,y, FIRST_PLAYER_CELL_VALUE);
            return true;
        } else {
            System.out.println("This cell is not empty");
            return false;
        }
    }

    public boolean setCellSecondPlayer(int x, int y){
        if (cellIsFree(x,y)){
            setCell(x,y, SECOND_PLAYER_CELL_VALUE);
            return true;
        } else {
            System.out.println("This cell is not empty");
            return false;
        }

    }

    private void setCell(int x, int y, char cellValue){
        if(x < fieldSize && y < fieldSize && x >= 0 && y >= 0) {
            field[x][y] = cellValue;
        }
        else {
            System.out.println("Wrong data in setCell");
        }
    }

    public boolean checkFirstPlayerWinn(){
        return checkFieldWinn(FIRST_PLAYER_CELL_VALUE);
    }

    public boolean checkSecondPlayerWinn(){
        return checkFieldWinn(SECOND_PLAYER_CELL_VALUE);
    }

    private boolean checkFieldWinn(char cellValue){
        for (int i = 0; i < fieldSize; i++){
            if(checkFieldColumnWinn(i, cellValue) || checkFieldLineWinn(i, cellValue)){
                return true;
            }
        }
        if(checkFieldInclineWinn(cellValue)){
            return true;
        }
        return false;
    }

    private boolean checkFieldLineWinn(int lineNumber, char cellValue){
        boolean checkLine = true;
        for (int i = 0; i <= fieldSize - WIN_NUMBER_OF_CELLS ; i++){

            checkLine = true;

            for(int j = i; j < i + WIN_NUMBER_OF_CELLS; j++){
                if( field[j][lineNumber] != cellValue) checkLine = false;
            }
            if(checkLine){
                return true;
            }

        }
        return false;
    }

    private boolean checkFieldColumnWinn(int columnNumber, char cellValue){
        boolean checkColumn = true;
        for (int i = 0; i <= fieldSize - WIN_NUMBER_OF_CELLS ; i++){

            checkColumn = true;

            for(int j = i; j < i + WIN_NUMBER_OF_CELLS; j++){
                if( field[columnNumber][j] != cellValue) checkColumn = false;
            }
            if(checkColumn){
                return true;
            }

        }
        return false;
    }

    private boolean checkInclineDownWinn(char cellValue){

            for (int i = 0; i < WIN_NUMBER_OF_CELLS ; i++){
                if( field[i][i] != cellValue) return false;
            }
            return true;
        }


    private boolean checkInclineUpWinn(char cellValue){
            for (int i = 0; i < WIN_NUMBER_OF_CELLS ; i++){
                if( field[i][fieldSize - i - 1] != cellValue) return false;
            }
            return true;
        }


    private boolean checkFieldInclineWinn(char cellValue){
                if(checkInclineDownWinn(cellValue) || checkInclineUpWinn(cellValue)){
                    return true;
                }
        return false;
    }

    private boolean cellIsFree(int x, int y){
        if(field[x][y] == DEFAULT_CELL_VALUE){
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull(){
        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++){
                if(field[i][j] == DEFAULT_CELL_VALUE){
                    return false;
                }
            }
        }
        return true;
    }

    public void setField(Field fieldIn){
        if(fieldSize == fieldIn.fieldSize){
            //System.arraycopy(fieldIn.field,0,this.field,0,fieldSize);   Does not work
            for (int i = 0; i < fieldSize; i++){
                for (int j = 0; j < fieldSize; j++){
                    this.field[i][j] = fieldIn.field[i][j];
                }
            }
        }

    }

    public void setDefaultCellValue(int x, int y){
        setCell(x,y,DEFAULT_CELL_VALUE);
    }
}

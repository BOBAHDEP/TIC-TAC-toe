package com.game;
import java.util.Scanner;

public class GetData {

    private static final char X_COORDINATE_NAME = 'x';

    private static final char Y_COORDINATE_NAME = 'y';

    public static int GetXCoordinateOfCell(Field field){
        return getCoordinateOfCell(field, X_COORDINATE_NAME);
    }

    public static int GetYCoordinateOfCell(Field field){
        return getCoordinateOfCell(field, Y_COORDINATE_NAME);
    }

    private static int getCoordinateOfCell(Field field, char coordinateName){
        Scanner scanner = new Scanner(System.in);
        int coordinate = 0;
        System.out.println("Please enter cell " + coordinateName + " coordinate:");
        while(true){
            if(scanner.hasNextInt()) {
                coordinate = scanner.nextInt();
                if(coordinate >= field.getMinCoordinateValue() && coordinate < field.getFieldSize() ){
                    return coordinate;
                } else {
                    coordinate = 0;
                    System.out.println("This number is not valid. Please try again");
                }
            } else {
                System.out.println("This number is not integer. Please try again");
            }
        }
    }

    public static boolean getYesOrNoAnswer(){
        Scanner scanner = new Scanner(System.in);
        String answer;
        while (true){
            answer = scanner.nextLine();
            if (answer.equals("yes")){
                return true;
            }
            if (answer.equals("no")){
                return false;
            } else {
                System.out.println("Wrong answer, please enter it again");
            }
        }

    }

    public static int getRegimeOfGame(){
        Scanner scanner = new Scanner(System.in);
        int regimeOfGame = 0;
        System.out.println("Please choose regime of game:");
        System.out.println("1 - Two players on one computer");
        System.out.println("2 - Player vs computer");
        System.out.println("3 - Two players using internet");
        while (true){
            if(scanner.hasNextInt()) {
                regimeOfGame = scanner.nextInt();
                if(regimeOfGame == Game.getOnePlayerGameRegime() || regimeOfGame == Game.getTwoPlayersGameRegime() || regimeOfGame == Game.getTwoPlayersOnlineGameRegime()){
                    return regimeOfGame;
                } else {
                    System.out.println("Wrong data, please try again");
                }

        }
    }
    }
}

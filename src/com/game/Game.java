package com.game;

public class Game {

    private static final int TWO_PLAYERS_GAME_REGIME = 1;

    private static final int ONE_PLAYER_GAME_REGIME = 2;

    private static final int TWO_PLAYERS_ONLINE_GAME_REGIME = 3;

    private int gameRegime = TWO_PLAYERS_GAME_REGIME;

    private Field field;

    public static int getTwoPlayersGameRegime(){
        return TWO_PLAYERS_GAME_REGIME;
    }

    public static int getOnePlayerGameRegime(){
        return ONE_PLAYER_GAME_REGIME;
    }

    public static int getTwoPlayersOnlineGameRegime(){
        return TWO_PLAYERS_ONLINE_GAME_REGIME;
    };

    public Game(){
        field = new Field();
        field.araseField();
    }

    private void firstPlayerMove(){
        boolean setCell = false;
        if(gameRegime == TWO_PLAYERS_GAME_REGIME || gameRegime == TWO_PLAYERS_ONLINE_GAME_REGIME){
            System.out.println("First Player,");
        } else{
            System.out.println("Player,");
        }
        while (!setCell)  {
            setCell = field.setCellFirstPlayer(GetData.GetXCoordinateOfCell(field),GetData.GetYCoordinateOfCell(field));
        }
    }

    private void secondPlayerMove(){
        boolean setCell = false;
        System.out.println("Second Player,");
        while (!setCell)  {
            setCell = field.setCellSecondPlayer(GetData.GetXCoordinateOfCell(field),GetData.GetYCoordinateOfCell(field));
        }
    }

    public void playGame(){
        field.araseField();
        gameRegime = GetData.getRegimeOfGame();
        if(gameRegime == TWO_PLAYERS_GAME_REGIME || gameRegime == ONE_PLAYER_GAME_REGIME){
            playTwoPlayersGame();
        }

    }

    private void restart(){
        System.out.println("Want to play again?(yes/no)");
        if(GetData.getYesOrNoAnswer()){
            playGame();
        } else {
            System.exit(0);
        }
    }

    private void playTwoPlayersGame(){
        field.araseField();
        while (true){
            field.showField();
            firstPlayerMove();
            field.showField();

            if(field.checkFirstPlayerWinn()){
                System.out.println("First player winn");
                restart();
            }
            if(field.isFull()){
                System.out.println("Draw game");
                restart();
            }
            if (gameRegime == TWO_PLAYERS_GAME_REGIME){
                secondPlayerMove();
            } else {
                Bot.botMove(field);
            }
            if(field.checkSecondPlayerWinn()){
                if (gameRegime == TWO_PLAYERS_GAME_REGIME){
                    System.out.println("Second player winn");
                } else {
                    System.out.println("Computer winn");
                }
                restart();
            }
            if(field.isFull()){
                System.out.println("Draw game");
                restart();
            }

        }
    }

}

package com.game;

public class Game {

    private static final int TWO_PLAYERS_GAME_REGIME = 1;

    private static final int ONE_PLAYER_GAME_REGIME = 2;

    private static final int TWO_PLAYERS_ONLINE_GAME_REGIME = 3;

    private static final int FIRST_PLAYER_NUMBER = 1;

    private static final int SECOND_PLAYER_NUMBER = 2;

    private int gameRegime = TWO_PLAYERS_GAME_REGIME;

    private Field field;

    private GameHistory gameHistory;

    private GameInternet gameInternet = new GameInternet(null);  //TODO IP

    public static int getTwoPlayersGameRegime(){
        return TWO_PLAYERS_GAME_REGIME;
    }

    public static int getOnePlayerGameRegime(){
        return ONE_PLAYER_GAME_REGIME;
    }

    public static int getTwoPlayersOnlineGameRegime(){
        return TWO_PLAYERS_ONLINE_GAME_REGIME;
    }

    public Game(){
        field = new Field();
        field.emptyField();
        gameHistory = new GameHistory();
    }

    private void humanMove(int playerNumber){
        boolean setCell = false;
        while (true){
            int x = 0,y = 0;
            while (!setCell)  {
                x = GetData.GetXCoordinateOfCell(field);
                y = GetData.GetYCoordinateOfCell(field);
                if (playerNumber == FIRST_PLAYER_NUMBER){
                    setCell = field.setCellFirstPlayer(x,y);
                }else if (playerNumber == SECOND_PLAYER_NUMBER){
                    setCell = field.setCellSecondPlayer(x,y);
                }
            }
            System.out.println("Do you want to change your choose?");
            if(GetData.getYesOrNoAnswer()){
                field.setDefaultCellValue(x,y);
                setCell = false;
            } else {
                if(gameRegime == TWO_PLAYERS_ONLINE_GAME_REGIME){
                    gameInternet.sentMove(x,y);
                }
                return;
            }
        }
    }
    private void firstPlayerMove(){

        if(gameRegime == TWO_PLAYERS_GAME_REGIME || gameRegime == TWO_PLAYERS_ONLINE_GAME_REGIME){
            System.out.println("First Player,");
        } else{
            System.out.println("Player,");
        }
        humanMove(FIRST_PLAYER_NUMBER);

    }

    private void secondPlayerMove(){
        System.out.println("Second Player,");
        humanMove(SECOND_PLAYER_NUMBER);

    }

    public void playGame(){
        gameHistory.emptyHistory();
        field.emptyField();
        gameRegime = GetData.getRegimeOfGame();

        if(gameRegime == TWO_PLAYERS_GAME_REGIME || gameRegime == ONE_PLAYER_GAME_REGIME || gameRegime == TWO_PLAYERS_ONLINE_GAME_REGIME){
            playTwoPlayersGame();
        }

    }

    private void restart(){
        System.out.println("Want to see history of steps?(yes/no)");
        if(GetData.getYesOrNoAnswer()){
            gameHistory.showHistory();
        }
        System.out.println("Want to play again?(yes/no)");
        if(GetData.getYesOrNoAnswer()){
            playGame();
        } else {
            System.exit(0);
        }
    }

    private boolean isFirst(){                                                  //todo 2  regime
        if (gameRegime != TWO_PLAYERS_ONLINE_GAME_REGIME){
            return true;
        } else {
            System.out.println("Are you first to play?");
                                                                                    //2 потока - сообщение, ввод с клавы
        } return true;
    }

    private void playTwoPlayersGame(){
        field.emptyField();

        boolean isFirst = isFirst();

        while (true){

            if(isFirst){
                field.showField();

                gameHistory.addStep(field);
                firstPlayerMove();

                gameHistory.addStep(field);

                field.showField();

                if(field.checkFirstPlayerWinn()){
                    System.out.println("First player winn");
                    restart();
                }
                if(field.isFull()){
                    System.out.println("Draw game");
                    restart();
                }
            }
            if (gameRegime == TWO_PLAYERS_GAME_REGIME){
                secondPlayerMove();
            } else if(gameRegime == ONE_PLAYER_GAME_REGIME){
                Bot.botMove(field);
            } else if(gameRegime == TWO_PLAYERS_ONLINE_GAME_REGIME){
                gameInternet.secondPlayerOnlineMove(field);
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

package com.game;


public class GameInternet {


    private Server server = new Server();
    private Client client = new Client();

    public GameInternet(String hostname){
        server.createServer(0);
        client.createClient(hostname, 0);
    }

    public void sentMove(int xCoordinate, int yCoordinate){
        client.sentInteger(xCoordinate);
        client.sentInteger(yCoordinate);
    }

    public int getXCoordinate(){
        return server.getInteger();
    }
    public int getYCoordinate(){
        return server.getInteger();
    }

    public void secondPlayerOnlineMove(Field field){
            int xCoordinate = getXCoordinate();
            int yCoordinate = getYCoordinate();
            field.setCellSecondPlayer(xCoordinate,yCoordinate);
    }

    public void sentIAmFirst(){

    }
    }


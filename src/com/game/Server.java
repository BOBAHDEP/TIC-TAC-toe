package com.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    private static final int DEFAULT_PORT_NUMBER = 9999;
    private static final String I_AM_SECOND = "Choose who is first";
    private int portNumber = DEFAULT_PORT_NUMBER;
    private ServerSocket serverSocket = null;

    public void createServer(int inPortNumber){

        if(inPortNumber <= 0){
            inPortNumber = DEFAULT_PORT_NUMBER;
        }

        portNumber = inPortNumber;

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println("Порт занят: " + portNumber);
            System.exit(-1);
        }

    }

    public int getInteger(){
        return Integer.parseInt(getString());
    }

    public boolean iAmSecond(){
        String answer;
        while(true){
            answer = getString();
            if(answer.equals(I_AM_SECOND)) return true;
        }
    }

    private String getString(){
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Ошибка при подключении к порту: " + portNumber);
            System.exit(-1);
        }

        InputStream in = null;
        try {
            in = clientSocket.getInputStream();
        } catch (IOException e) {
            System.out.println("Не удалось получить поток ввода.");
            System.exit(-1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String inLine = null;

        try {
            if ((inLine = reader.readLine()) != null) {
                return inLine;
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении сообщения.");
            System.exit(-1);
        }
        return " ";
    }

}

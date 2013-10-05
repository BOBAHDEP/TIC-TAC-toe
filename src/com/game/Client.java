package com.game;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final String DEFAULT_HOST = "localhost";
    private static final String I_AM_FIRST = "Choose who is first";
    private static final int DEFAULT_PORT = 9999;

    private Socket socket = null;

    public void createClient(String hostName, int portNumber) {

        if(hostName == null){
            hostName = DEFAULT_HOST;
        }
        if(portNumber <= 0){
            portNumber = DEFAULT_PORT;
        }

        try {
            socket = new Socket(hostName, portNumber);
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост: " + hostName);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода при создании сокета " + hostName + ":" + portNumber);
            System.exit(-1);
        }
    }

    public void sentInteger(int inNumber){
        sentString(inNumber + "");
    }

    public void sentIAmFirst(){
        sentString(I_AM_FIRST);
    }

    private void sentString(String sentData){
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Не удалось получить поток вывода.");
            System.exit(-1);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        try {
            writer.write(sentData + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи сообщения.");
            System.exit(-1);
        }
    }

}
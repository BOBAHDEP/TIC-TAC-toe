package com.game;
import java.lang.Math;

public class Bot {

    public static void botMove(Field field){
        boolean setRandomCell = false;
        while (!setRandomCell)  {
            int randomXCoordinate = (int)(Math.random()*field.getFieldSize());
            int randomYCoordinate = (int)(Math.random()*field.getFieldSize());
            if(randomXCoordinate < field.getFieldSize() && randomYCoordinate < field.getFieldSize()){
                setRandomCell = field.setCellSecondPlayer(randomXCoordinate,randomYCoordinate);
            }
        }

    }
}

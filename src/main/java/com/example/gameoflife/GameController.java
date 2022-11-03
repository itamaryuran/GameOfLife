package com.example.gameoflife;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameController {

    @FXML
    private Canvas canv;

    private GraphicsContext gc;
    private boolean mat[][];
    final int cellSize = 2;
    final int matSize = 600;

    public void initialize() {
        gc = canv.getGraphicsContext2D();
        mat = getmat();
        drawmat(mat);
    }
    public boolean[][] getmat(){
        boolean mat[][]= new boolean[matSize][matSize];
        Random r = new Random();
        for (int i =0;i<matSize;i++){
            for (int j =0;j<matSize;j++){
                mat[i][j] = r.nextBoolean();
            }
        }
        return mat;
    }

    public void drawmat(boolean mat[][]){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canv.getWidth(), canv.getHeight());

        for (int i =0;i<matSize;i++){
            for (int j =0;j<matSize;j++){
               if (mat[i][j]== false)
               {
                   gc.setFill(Color.NAVY);
                   gc.fillRect((i * cellSize), (j * cellSize), cellSize, cellSize);
               }
               else
               {
                   gc.setFill(Color.LAVENDER);
                   gc.fillRect((i * cellSize), (j * cellSize), cellSize, cellSize);
               }
            }
        }
    }


    public boolean[][] newGen(boolean mat[][]){
        boolean[][] temp = new boolean[matSize][matSize];
        for (int i =0;i<matSize;i++) {
            for (int j = 0; j < matSize; j++) {
                if ((mat[i][j] == false) && liveNeighbors(mat, i, j)==3){
                    temp[i][j]=true;
                }
                else if ((mat[i][j] == true) && (liveNeighbors(mat, i, j)<2||liveNeighbors(mat, i, j)>=4)){
                    temp[i][j]=false;
                }
                else if ((mat[i][j] == true) && (liveNeighbors(mat, i, j)==2||liveNeighbors(mat, i, j)==3)){
                    temp[i][j]=true;
                }
                else{
                    temp[i][j] = false;
                }

            }
        }
        return temp;


    }

    public int liveNeighbors(boolean mat[][], int i, int j) {
        int count = 0;
        try{if (mat[i-1][j-1]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i][j-1]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i+1][j-1]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i-1][j]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i+1][j]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i-1][j+1]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i][j+1]==true) {count++;}} catch (Exception e) {}
        try{if (mat[i+1][j+1]==true) {count++;}} catch (Exception e) {}
        return count;
    }
    @FXML
    void click(KeyEvent event) {
        mat = newGen(mat);
        drawmat(mat);

    }
    @FXML
    void clickM(MouseEvent event) {
        mat = newGen(mat);
        drawmat(mat);

    }


}

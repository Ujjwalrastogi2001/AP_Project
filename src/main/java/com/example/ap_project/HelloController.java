package com.example.ap_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class HelloController {
    @FXML
    private ImageView bluepawn;

    @FXML
    private ImageView boardImage;

    @FXML
    private ImageView dice1;

    @FXML
    private ImageView dice2;

    @FXML
    private ImageView dice3;

    @FXML
    private ImageView dice4;

    @FXML
    private ImageView dice5;

    @FXML
    private ImageView dice6;

    @FXML
    private ImageView downarrow;

    @FXML
    private ImageView greenpawn;

    @FXML
    private ImageView player1turn;

    @FXML
    private ImageView player2turn;
    @FXML
    private ImageView uparrow;
    public player blue;
    public player green;

    public String turnTracker;
    @FXML
    void roll(MouseEvent event) {
        if(turnTracker=="blue") {
            player2turn.setVisible(true);
            player1turn.setVisible(false);
            blue.move(rollDice());
            System.out.println("Position of blue: "+blue.getPosition());
            turnTracker = "green";
        }
        else if(turnTracker=="green") {
            player1turn.setVisible(true);
            player2turn.setVisible(false);
            green.move(rollDice());
            System.out.println("Position of green: "+green.getPosition());
            turnTracker = "blue";

        }

    }
    public void initialize(){
        turnTracker="blue";
        blue=new player(bluepawn,"blue",-2,538);
        green = new player(greenpawn,"green",10,538);
    }
    int rollDice(){
        int dice;
        double a = Math.random()*(6)+1;
        dice=(int)a;
        switch(dice){
            case 1:
                dice1.setVisible(true);
                dice2.setVisible(false);
                dice3.setVisible(false);
                dice4.setVisible(false);
                dice5.setVisible(false);
                dice6.setVisible(false);
                break;
            case 2:
                dice2.setVisible(true);
                dice1.setVisible(false);
                dice3.setVisible(false);
                dice4.setVisible(false);
                dice5.setVisible(false);
                dice6.setVisible(false);
                break;
            case 3:
                dice3.setVisible(true);
                dice2.setVisible(false);
                dice1.setVisible(false);
                dice4.setVisible(false);
                dice5.setVisible(false);
                dice6.setVisible(false);
                break;
            case 4:
                dice4.setVisible(true);
                dice2.setVisible(false);
                dice3.setVisible(false);
                dice1.setVisible(false);
                dice5.setVisible(false);
                dice6.setVisible(false);
                break;
            case 5:
                dice5.setVisible(true);
                dice2.setVisible(false);
                dice3.setVisible(false);
                dice1.setVisible(false);
                dice4.setVisible(false);
                dice6.setVisible(false);
                break;
            case 6:
                dice6.setVisible(true);
                dice2.setVisible(false);
                dice3.setVisible(false);
                dice1.setVisible(false);
                dice5.setVisible(false);
                dice4.setVisible(false);
                break;
        }
        return dice;
    }
}
class player{
    int floor=1;
    final int startX;
    final int startY;
    int currx;
    int curry;
    public static final int onestepx=60;
    public static final int onestepy=58;
    public static int endX=0;
    public static int endY=0;
    boolean opened;
    ImageView token;
    String color;
    player(ImageView token, String color, int startX , int startY){
        this.startX=startX;
        this.startY=startY;
        currx=startX;
        curry=startY;
        opened=false;
        this.token=token;
        this.color=color;
        endX=startX+10*onestepx;
        endY=startY+10*onestepy;
    };
    void move(int dice){
        if(!opened && dice==1){
            token.setLayoutY(startY);
            token.setLayoutX(startX);
            opened=true;
        }
        else{
            if(opened) {
                System.out.println("condition1");
                if(floor%2==0) currx=currx-dice*(onestepx-1);
                else
                    currx = currx + dice * (onestepx - 1);
                if(currx>endX || currx<startX){
                    System.out.println("condition1");
                    floor++;
                    if(floor%2==0)
                        currx=endX-(currx-endX)+onestepx;
                    else
                        currx=2*startX-(currx)-onestepx;
                }
                token.setLayoutY(curry+onestepy);
            }
        }

    }
    int getPosition(){
        int num;
        int Xposition=(int)(token.getLayoutX())/58+1;
        System.out.println("currx: "+token.getLayoutX());
        int Yposition=11-((int)(token.getLayoutY())/55+1);
        if(Yposition%2==0)
            num=(Yposition-1)*10+(11-Xposition);
        else num=(Yposition-1)*10+Xposition;
        return num;
    }

}
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
            turnTracker = "green";
        }
        else if(turnTracker=="green") {
            player1turn.setVisible(true);
            player2turn.setVisible(false);
            green.move(rollDice());
            turnTracker = "blue";
        }

    }
    public void initialize(){
        turnTracker="blue";
        blue=new player(bluepawn,"blue");
        green = new player(greenpawn,"green");
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
    public static final int startX=12;
    public static final int startY=547;
    public static final int onestepx=58;
    public static final int onestepy=60;
    boolean opened;
    ImageView token;
    String color;
    player(ImageView token, String color){
        opened=false;
        this.token=token;
        this.color=color;
    };
    void move(int dice){
        if(!opened && dice==1){
            token.setLayoutY(startY);
            token.setLayoutX(startX);
            opened=true;
        }

    }

}
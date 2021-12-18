package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    void frontToBoard(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("board.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void boardToFront(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("front.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void roll(MouseEvent event) {
        if(turnTracker=="blue") {
            player2turn.setVisible(true);   player1turn.setVisible(false);
            blue.move(rollDice());
            System.out.println("Position of blue: "+blue.getPosition());
            turnTracker = "green";
        }
        else if(turnTracker=="green") {
            player1turn.setVisible(true);   player2turn.setVisible(false);
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
    public void diceVisibility(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f){
        dice1.setVisible(a);dice2.setVisible(b);dice3.setVisible(c);dice4.setVisible(d);dice5.setVisible(e);dice6.setVisible(f);
    }
    int rollDice(){
        int dice;
        double a = Math.random()*(6)+1;
        dice=(int)a;
        switch(dice){
            case 1:
                diceVisibility(true,false,false,false,false,false); break;
            case 2:
                diceVisibility(false,true,false,false,false,false); break;
            case 3:
                diceVisibility(false,false,true,false,false,false); break;
            case 4:
                diceVisibility(false,false,false,true,false,false); break;
            case 5:
                diceVisibility(false,false,false,false,true,false); break;
            case 6:
                diceVisibility(false,false,false,false,false,true); break;
        }
        return dice;
    }
}
class player{

    int floor=1;    final int startX;   final int startY;   int currx;  int curry;  public static final int onestepx=60;
    public static final int onestepy=58;   public static int endX=0;    public static int endY=0;   boolean opened;
    ImageView token;    String color;

    player(ImageView token, String color, int startX , int startY){
                this.startX=startX;this.startY=startY;currx=startX;curry=startY;
                opened=false;this.token=token;this.color=color;
                endX=startX+10*onestepx;endY=startY+10*onestepy;
    }
    void move(int dice){
        if(!opened && dice==1) {moveByOne(); return;}
        else if(!opened && dice!=1) {return;}
        for(int i=0;i<dice;i++) moveByOne();
        System.out.println("position "+color+" " +getPosition());
    }
    void moveByOne(){
        if(!opened){
            token.setLayoutY(startY);   token.setLayoutX(startX);   opened=true;
        }
        else{
            int block=getPosition();
            if(block%10==0 && floor!=10){curry-=onestepy;   floor++;}
            else {
                if(floor%2==0) currx-=onestepx;
                else currx+=onestepx;
            }

            if(getPosition()==100)  {
                //TODO the player wins;
            }


            token.setLayoutX(currx);
            token.setLayoutY(curry);

        }

    }
    int getPosition(){
        int num;
        int Xposition=(int)(token.getLayoutX())/58+1;
//        System.out.println("currx: "+token.getLayoutX());
        int Yposition=11-((int)(token.getLayoutY())/55+1);
        System.out.println("pos " +Yposition+ "  "+Xposition);
        if(Yposition%2==0)
            num=(Yposition-1)*10+(11-Xposition);
        else num=(Yposition-1)*10+Xposition;
        return num;
    }
}
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
    public static final int startX=-2;
    public static final int startY=538;
    public static final int onestepx=60;
    public static final int onestepy=55;
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
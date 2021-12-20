package com.example.ap_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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

    ArrayList<snake> snakes=new ArrayList<>();
    ArrayList<ladder> ladders=new ArrayList<>();

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
        if (turnTracker == "blue") {
            int dice = rollDice();
            if (blue.move(dice)) {
                Timeline t1 = new Timeline((new KeyFrame(Duration.millis(500 * dice + 1), e -> {
                    player2turn.setVisible(true);
                    player1turn.setVisible(false);
                    System.out.println("----FINAL POSITION: "+blue.getPosition());
                })));
                t1.play();
            } else {
                player2turn.setVisible(true);
                player1turn.setVisible(false);
            }
            System.out.println("Position of blue: " + blue.getPosition());
            turnTracker = "green";
        } else if (turnTracker == "green") {

            int dice = rollDice();
            if (green.move(dice)) {
                Timeline t1 = new Timeline((new KeyFrame(Duration.millis(500 * dice + 1), e -> {
                    player1turn.setVisible(true);
                    player2turn.setVisible(false);
                })));
                t1.play();
            } else {
                player1turn.setVisible(true);
                player2turn.setVisible(false);
            }
            System.out.println("Position of green: " + green.getPosition());
            turnTracker = "blue";
        }
    }
    public void initialize(){
        turnTracker="blue";
        blue=new player(bluepawn,"blue",-2,538);
        green = new player(greenpawn,"green",10,538);
        snakes.add(new snake(47,5,358,306,238,538));
        snakes.add(new snake(29,9,478,422,478,538));
        snakes.add(new snake(38,15,118,364,298,480));
        snakes.add(new snake(97,25,178,16,238,422));
        snakes.add(new snake(53,33,418,248,418,364));
        snakes.add(new snake(62,37,58,190,178,364));
        snakes.add(new snake(86,54,298,74,358,248));
        snakes.add(new snake(92,70,478,16,538,190));
        blue.setSnakes(snakes);green.setSnakes(snakes);
        ladders.add(new ladder(2,23,58,538,118,422));
        ladders.add(new ladder(8,34,418,538,358,364));
        ladders.add(new ladder(20,77,-2,480,178,132));
        ladders.add(new ladder(32,68,478,364,418,190));
        ladders.add(new ladder(41,79,-2,306,58,132));
        ladders.add(new ladder(74,88,358,132,418,74));
        ladders.add(new ladder(82,100,58,74,-2,16));
        ladders.add(new ladder(85,95,238,74,298,16));
        blue.setLadders(ladders);green.setLadders(ladders);

        for(int i=0;i<ladders.size();i++){
            System.out.println(ladders.get(i).getStart()+" "+ladders.get(i).getEnd()+" "+ladders.get(i));
        }
        System.out.println();
        for(int i=0;i<snakes.size();i++){
           // System.out.print(snakes.get(i).getStart()+" ");
        }



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
    ArrayList<snake> snakes=new ArrayList<>();
    ArrayList<ladder> ladders=new ArrayList<>();

    int floor=1;    final int startX;   final int startY;   int currx;  int curry;  public static final int onestepx=60;
    public static final int onestepy=58;   public static int endX=0;    public static int endY=0;   boolean opened;
    ImageView token;    String color;

    player(ImageView token, String color, int startX , int startY){
                this.startX=startX;this.startY=startY;currx=startX;curry=startY;
                opened=false;this.token=token;this.color=color;
                endX=startX+10*onestepx;endY=startY+10*onestepy;
    }
    boolean move(int dice){
        if(!opened && dice==1) {moveByOne(dice); return false;}
        else if(!opened && dice!=1) {return false;}
        if(getPosition()+dice>100) return true;
//        ArrayList<TranslateTransition> t = new ArrayList<>();
//        for(int i=0;i<dice;i++){
//            System.out.println("calling move by one");
//            t.add(moveByOne(dice));
//        }
//        for(int i=0;i<dice;i++) {
//            System.out.println(t.get(i)); }
//        AtomicInteger j = new AtomicInteger();
        Timeline t1=new Timeline((new KeyFrame(Duration.millis(500),e->{
            moveByOne(dice).play();
        })));
        //transition play
        t1.setCycleCount(dice);
        t1.play();

        int posn=getPosition();

        for(int i=0;i<ladders.size();i++){
            ladder l=ladders.get(i);
            if(posn==l.getStart()){token.setLayoutX(l.getEndX());token.setLayoutY(l.getEndY());floor=(l.getEnd()-1)/10+1;currx=l.getEndX();curry=l.getEndY();break;}
        }
        for(int i=0;i<snakes.size();i++){
            snake s=snakes.get(i);
            if(posn==s.getStart()){token.setLayoutX(s.getEndX());token.setLayoutY(s.getEndY());floor=(s.getEnd()-1)/10+1;currx=s.getEndX();curry=s.getEndY();break;}
        }

        //System.out.println("position "+color+" " +getPosition());
        return true;

    }
    TranslateTransition moveByOne(int dice){
        System.out.println("cury "+currx+"  "+curry);
        TranslateTransition transition;
        int prevX, prevY;
        prevX = currx;
        prevY = curry;
        if(!opened){
            token.setLayoutY(startY);   token.setLayoutX(startX);
            opened=true;
            transition= new TranslateTransition();
////            transition.setFromX(prevX);
//            transition.setByY(onestepy);
//            transition.setCycleCount(1);
//            transition.setAutoReverse(false);
//            curry = curry+onestepy;
        }
        else{
            int block=getPosition();
            System.out.println("-----------BLOCK: "+block);
            if(block%10==0 && floor!=10){
                System.out.println("-----TIME TO GO UP-----");
                //time to go up
                curry-=onestepy;
                floor++;
                transition= new TranslateTransition(Duration.millis(500),token);
                transition.setToY(-onestepy*(floor-1));
            }
            else {
                if(floor%2==0) currx-=onestepx;
                else currx+=onestepx;
                transition= new TranslateTransition(Duration.millis(500),token);
//              transition.setFromX(prevX);
                transition.setToX(currx);
            }
            transition.setCycleCount(1);
            transition.setAutoReverse(false);

            if(getPosition()==100)  {
                //TODO new scene for winning player.
                System.out.println("Player "+ color+" wins the game");
            }


//            token.setLayoutX(currx);
//            token.setLayoutY(curry);

////         token.setLayoutY(curry);
//            transition.setOnFinished(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
////                    token.setLayoutX(currx);
////                    token.setLayoutY(curry);
////                    System.out.println("currx :  "+currx+" cuury:"+curry);
//                    return;
//                }
//            });
        }
        return transition;
    }
    int getPosition(){
        int num;
        int Xposition=currx/58+1;
//        System.out.println("currx: "+token.getLayoutX());
        int Yposition=11-(curry/55+1);
        System.out.println("pos " +Yposition+ "  "+Xposition);
        if(Yposition%2==0)
            num=(Yposition-1)*10+(11-Xposition);
        else num=(Yposition-1)*10+Xposition;
        return num;
    }
    void setLadders(ArrayList<ladder> l){ladders.addAll(l);}
    void setSnakes(ArrayList<snake> s){snakes.addAll(s);}
}

class ladder{
    private int start,end,startX,startY,endX,endY;
    ladder(int start,int end,int startX,int startY,int endX,int endY){
        this.start=start;this.end=end;this.startX=startX;this.startY=startY;this.endX=endX;this.endY=endY;
    }
    public int getStart(){return start;}    public int getEnd(){return end;}
    public int getStartX(){return startX;}  public int getStartY(){return startY;}
    public int getEndX(){return endX;}  public int getEndY(){return endY;}
}
class snake{
    private int start,end,startX,startY,endX,endY;
    snake(int start,int end,int startX,int startY,int endX,int endY){
        this.start=start;this.end=end;this.startX=startX;this.startY=startY;this.endX=endX;this.endY=endY;
    }
    public int getStart(){return start;}    public int getEnd(){return end;}
    public int getStartX(){return startX;}  public int getStartY(){return startY;}
    public int getEndX(){return endX;}  public int getEndY(){return endY;}
}
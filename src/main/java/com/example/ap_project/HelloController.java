package com.example.ap_project;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.shape.*;
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
    @FXML
    private static ImageView p1win;
    @FXML
    private static ImageView p2win;
    @FXML
    private Line ladder2;

    @FXML
    private Line ladder20;

    @FXML
    private Line ladder32;

    @FXML
    private Line ladder41;

    @FXML
    private Line ladder74;

    @FXML
    private Line ladder8;
    public  player blue;
    public player green;
    public static int[] testDice=new int[]{1,1,1};
    public static int counter=0;

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
        ladders.add(new ladder(2,23,58,538,118,422,ladder2));
        ladders.add(new ladder(8,34,418,538,358,364,ladder8));
        ladders.add(new ladder(20,77,-2,480,178,132,ladder20));
        ladders.add(new ladder(32,68,478,364,418,190,ladder32));
        ladders.add(new ladder(41,79,-2,306,58,132,ladder41));
        ladders.add(new ladder(74,88,358,132,418,74,ladder74));
        ladders.add(new ladder(82,100,58,74,-2,16,null));
        ladders.add(new ladder(85,95,238,74,298,16,null));
        blue.setLadders(ladders);green.setLadders(ladders);

        for(int i=0;i<ladders.size();i++){
            System.out.println(ladders.get(i).getStart()+" "+ladders.get(i).getEnd()+" "+ladders.get(i));
        }
        System.out.println();
        for(int i=0;i<snakes.size();i++){
           // System.out.print(snakes.get(i).getStart()+" ");
        }



    }
    public static void win(String s){
        if(s=="blue"){p1win.setVisible(true);p2win.setVisible(false);}
        if(s=="green"){p1win.setVisible(false);p2win.setVisible(false);}
    }
    public void diceVisibility(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f){
        dice1.setVisible(a);dice2.setVisible(b);dice3.setVisible(c);dice4.setVisible(d);dice5.setVisible(e);dice6.setVisible(f);
    }
    int rollDice(){
        int dice=0;
        if(counter<3) {
//        double a = Math.random()*(6)+1;
//        dice=(int)a;
            dice = testDice[counter];
            switch (dice) {
                case 1:
                    diceVisibility(true, false, false, false, false, false);
                    break;
                case 2:
                    diceVisibility(false, true, false, false, false, false);
                    break;
                case 3:
                    diceVisibility(false, false, true, false, false, false);
                    break;
                case 4:
                    diceVisibility(false, false, false, true, false, false);
                    break;
                case 5:
                    diceVisibility(false, false, false, false, true, false);
                    break;
                case 6:
                    diceVisibility(false, false, false, false, false, true);
                    break;
            }
        }
        counter++;
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
        if(getPosition()+dice>100) return false;

        Timeline t1=new Timeline((new KeyFrame(Duration.millis(500),e->{
            moveByOne(dice).play();
        })));
        //Transition play
        t1.setCycleCount(dice);
        t1.play();
        Timeline t2=new Timeline((new KeyFrame(Duration.millis(500*(dice+2)),e->{
            int posn=getPosition();
            System.out.println("curr in move: "+currx+" "+curry);
            System.out.println("posn:-----------"+posn);

            for(int i=0;i<ladders.size();i++){
                ladder l=ladders.get(i);
                if(posn==l.getStart()){
                    Timeline ladderMotion= new Timeline((new KeyFrame(Duration.millis(500),f->{
                        Path path = new Path();
                        LineTo lineTo= new LineTo();
                        lineTo.setX(120);
                        lineTo.setY(-100);
                        path.getElements().add(new MoveTo(80,20));
                        path.getElements().add(lineTo);
                        PathTransition pathTransition = new PathTransition();
                        pathTransition.setDuration(Duration.millis(500));
                        pathTransition.setPath(path);
                        pathTransition.setNode(token);

                        pathTransition.setCycleCount(1);

                        pathTransition.play();
                    })));
                    ladderMotion.play();

                    System.out.println("ladder chadh gai");break;}
            }
            for(int i=0;i<snakes.size();i++){
                snake s=snakes.get(i);
                if(posn==s.getStart()){token.setLayoutX(s.getEndX());token.setLayoutY(s.getEndY());floor=(s.getEnd()-1)/10+1;currx=s.getEndX();curry=s.getEndY();
                    System.out.println("saanp kaat gaya");break;}
            }
            System.out.println("block:+"+getPosition());
            //System.out.println("position "+color+" " +getPosition());
            System.out.println("----------------------------------------------------------------------");
        })));
        t2.play();
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
        }
        else{
            transition= new TranslateTransition(Duration.millis(500),token);
            int block=getPosition();
            System.out.println("block:+"+block);
            if(block%10==0 && floor!=10){curry-=onestepy;   floor++;transition.setToY(-onestepy*(floor-1));}
            else {
                if(floor%2==0){ currx-=onestepx;transition.setToX(currx);}
                else{ currx+=onestepx;transition.setToX(currx);}
            }

            if(getPosition()==100)  {
                System.out.println("Player "+ color+" wins the game");
                HelloController.win(color);
            }
            System.out.println("block:+"+getPosition());
            System.out.println("Transition playing.....");


            //transition.setToX(currx);
            transition.setCycleCount(1);
            transition.setAutoReverse(false);
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
        int Xposition=(int)(currx)/58+1;
//        System.out.println("currx: "+token.getLayoutX());
        int Yposition=11-((int)(curry/55+1));
        //System.out.println("pos " +Yposition+ "  "+Xposition);
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
    public Line motionLine;
    ladder(int start,int end,int startX,int startY,int endX,int endY, Line line){
        this.start=start;this.end=end;this.startX=startX;this.startY=startY;this.endX=endX;this.endY=endY;
        this.motionLine=line;
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
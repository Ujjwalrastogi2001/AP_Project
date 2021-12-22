package com.example.ap_project;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private Polyline polyLine;
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
    private ImageView p1win;
    @FXML
    private CubicCurve snake29part1;

    @FXML
    private CubicCurve snake29part2;

    @FXML
    private CubicCurve snake38;

    @FXML
    private CubicCurve snake47part1;

    @FXML
    private CubicCurve snake47part2;

    @FXML
    private CubicCurve snake53part1;

    @FXML
    private CubicCurve snake62part1;

    @FXML
    private CubicCurve snake62part2;

    @FXML
    private CubicCurve snake86part1;

    @FXML
    private CubicCurve snake86part2;

    @FXML
    private CubicCurve snake92part1;

    @FXML
    private CubicCurve snake92part2;

    @FXML
    private CubicCurve snake97part1;

    @FXML
    private CubicCurve snake97part2;

    @FXML
    private CubicCurve snake97part3;
    @FXML
    private ImageView p2win;
    private   player blue;
    private  player green;

    ArrayList<snake> snakes=new ArrayList<>();
    ArrayList<ladder> ladders=new ArrayList<>();
    public int diceList[]=new int[]{1,6,6,6,6,5};
    public static int count=0;
    public String turnTracker;
    public TranslateTransition moveDownArrow;


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
        moveDownArrow.pause();
        setDiceClickable(false);
        if (turnTracker == "blue") {
            int dice = rollDice();
            if (blue.move(dice)) {
                Timeline t1 = new Timeline((new KeyFrame(Duration.millis(500 * dice + 1), e -> {
                    player2turn.setVisible(true);
                    player1turn.setVisible(false);
                    setDiceClickable(true);
                    moveDownArrow.play();
                    System.out.println("----FINAL POSITION: "+blue.getPosition());
                })));
                t1.play();
            } else {
                player2turn.setVisible(true);
                player1turn.setVisible(false);
                setDiceClickable(true);
                moveDownArrow.play();
            }
            System.out.println("Position of blue: " + blue.getPosition());
            turnTracker = "green";
        } else if (turnTracker == "green") {

            int dice = rollDice();
            if (green.move(dice)) {
                Timeline t1 = new Timeline((new KeyFrame(Duration.millis(500 * dice + 2), e -> {
                    player1turn.setVisible(true);
                    player2turn.setVisible(false);
                    setDiceClickable(true);
                    moveDownArrow.play();
                })));
                t1.play();
            } else {
                player1turn.setVisible(true);
                player2turn.setVisible(false);
                moveDownArrow.play();
                setDiceClickable(true);
            }
            System.out.println("Position of green: " + green.getPosition());
            turnTracker = "blue";

        }


    }
    @FXML
    void findLoc(MouseEvent event){
        System.out.println("X Coordinate: "+ event.getSceneX()+ " Y Coordinate: "+ event.getSceneY() );
    }
    public void initialize(){
        turnTracker="blue";
        blue=new player(bluepawn,"blue",-2,538);
        green = new player(greenpawn,"green",10,538);
        ArrayList<CubicCurve> pathList= new ArrayList<>();
        pathList.add(snake47part1);  pathList.add(snake47part2);

        snakes.add(new snake(47,5,120,236,238,538,pathList));
        pathList.clear();
        pathList.add(snake29part1);pathList.add(snake29part2);
        snakes.add(new snake(29,9,0,118,478,538,pathList));
        pathList.clear();
        pathList.add(snake38);

        snakes.add(new snake(38,15,-180,118,298,480,pathList));
        pathList.clear();
        pathList.add(snake97part1);pathList.add(snake97part2);pathList.add(snake97part3);
        snakes.add(new snake(97,25,-60,413,238,422,pathList));
        pathList.clear();
        pathList.add(snake53part1);
        snakes.add(new snake(53,33,0,118,418,364,pathList));
        pathList.clear();
        pathList.add(snake62part1);pathList.add(snake62part2);
        snakes.add(new snake(62,37,-120,178,178,364,pathList));
        pathList.clear();
        pathList.add(snake86part1);pathList.add(snake86part2);
        snakes.add(new snake(86,54,-60,178,358,248,pathList));
        pathList.clear();
        pathList.add(snake92part1);pathList.add(snake92part2);
        snakes.add(new snake(92,70,-60,178,538,190,pathList));
        pathList.clear();
        blue.setSnakes(snakes);green.setSnakes(snakes);
        ladders.add(new ladder(2,23,60,118,118,422));
        ladders.add(new ladder(8,34,-60,178,358,364));
        ladders.add(new ladder(20,77,180,354,178,132));
        ladders.add(new ladder(32,68,-60,178,418,190));
        ladders.add(new ladder(41,79,60,178,58,132));
        ladders.add(new ladder(74,88,60,59,418,74));
        ladders.add(new ladder(82,100,-60,59,-2,16));
        ladders.add(new ladder(85,95,60,59,298,16));
        blue.setLadders(ladders);green.setLadders(ladders);

        moveDownArrow= new TranslateTransition(Duration.millis(800),downarrow);
        moveDownArrow.setByY(-30);
        moveDownArrow.setCycleCount(TranslateTransition.INDEFINITE);
        moveDownArrow.setAutoReverse(true);
        moveDownArrow.play();

        Timeline statusChecker = new Timeline((new KeyFrame(Duration.millis(500), e -> {
            if(blue.win){
                win(blue.getColor());
            }
            else if(green.win){
                win(green.getColor());
            }
        })));
        statusChecker.setCycleCount(Timeline.INDEFINITE);
        statusChecker.play();
    }
    public void win(String s){
        System.out.println("---------------player win: "+ s);
        if(s=="blue"){p1win.setVisible(true);p2win.setVisible(false);}
        if(s=="green"){p1win.setVisible(false);p2win.setVisible(true);}
    }
    public void diceVisibility(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f){
        dice1.setVisible(a);dice2.setVisible(b);dice3.setVisible(c);dice4.setVisible(d);dice5.setVisible(e);dice6.setVisible(f);
    }
    int rollDice(){
        int dice;
        double a = Math.random()*(3)+1;
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
    void setDiceClickable(boolean clickable){
        if(clickable){
            dice1.setOnMouseClicked(this::roll);
            dice2.setOnMouseClicked(this::roll);
            dice3.setOnMouseClicked(this::roll);
            dice4.setOnMouseClicked(this::roll);
            dice5.setOnMouseClicked(this::roll);
            dice6.setOnMouseClicked(this::roll);
        }
        else {
            dice1.setOnMouseClicked(null);
            dice2.setOnMouseClicked(null);
            dice3.setOnMouseClicked(null);
            dice4.setOnMouseClicked(null);
            dice5.setOnMouseClicked(null);
            dice6.setOnMouseClicked(null);
        }
    }
}
class player{
    private ArrayList<snake> snakes=new ArrayList<>();
    private ArrayList<ladder> ladders=new ArrayList<>();

    private int floor=1;    private final int startX;   private final int startY;   private int currx;  private int curry;  private  static final int onestepx=60;
    private  static final int onestepy=58;   private  static int endX=0;    private  static int endY=0;   private boolean opened;
    private ImageView token;    private String color; boolean win;

    player(ImageView token, String color, int startX , int startY){
        this.startX=startX;this.startY=startY;currx=startX;curry=startY;
        opened=false;this.token=token;this.color=color;
        endX=startX+10*onestepx;endY=startY+10*onestepy;
        win=false;
    }
    String getColor(){
        return this.color;
    }
    ImageView getToken(){return this.token;}
    boolean move(int dice){
//        TranslateTransition moveDownArrow;
//        ImageView downArrow=HelloController.getDownarrow();
//        moveDownArrow= new TranslateTransition(Duration.millis(10),downArrow);
//        moveDownArrow.setByY(-30);
//        moveDownArrow.setCycleCount(1);
//        moveDownArrow.setAutoReverse(false);
//        moveDownArrow.play();
        System.out.println("dice: "+dice);
        if(!opened && dice==1) {moveByOne(); return false;}
        else if(!opened && dice!=1) {return false;}
        if(getPosition()+dice>100) return false;
        AtomicInteger n= new AtomicInteger();
        AtomicInteger count= new AtomicInteger(-1);
        Thread thread = new Thread(){
            public void run() {
                System.out.println("===========TOKEN2: "+token);
                for(int i=0;i<dice;i++){
                    System.out.println("in timeline");
                    if (n.get() < dice) {
                        System.out.println("layout x before transtiotn :" + token.getLayoutX() + " " + token.getLayoutY());
                        moveByOne().play();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("layout x after transtiotn :" + token.getLayoutX() + " " + token.getLayoutY());

                    }
                    count.getAndIncrement();
                    n.getAndIncrement();
                }

                int posn=getPosition();
                System.out.println("curr in move: "+currx+" "+curry);
                System.out.println("posn:-----------"+posn);

                for(int i=0;i<ladders.size();i++){
                    ladder l=ladders.get(i);
                    if(posn==l.getStart()){
                        TranslateTransition transition= new TranslateTransition();
                        transition.setNode(token);
                        transition.setByX(l.getTx());
                        transition.setByY(-l.getTy());
                        transition.setDuration(Duration.millis(500));
                        transition.setCycleCount(1);
                        transition.play();
                        floor=(l.getEnd()-1)/10+1;currx=l.getEndX();curry=l.getEndY();
                        System.out.println("ladder chadh gai"+currx+" "+curry);
                        System.out.println("ladder chadh gai "+ token.getLayoutX()+" "+token.getLayoutY());break;}
                }
                for(int i=0;i<snakes.size();i++){
                    snake s=snakes.get(i);
                    if(posn==s.getStart()){
                        AtomicInteger count= new AtomicInteger(0);
                        ArrayList<CubicCurve> pathList= s.pathList;
                        System.out.println("snakeee----------------------------------"+ s.pathList);
                        Timeline animate = new Timeline((new KeyFrame(Duration.millis(300), z -> {
                            System.out.println("---------animate Cycle count: "+count.get());
                            System.out.println("-------------------------------"+ pathList);
                            PathTransition transition= new PathTransition();
                            transition.setNode(token);
                            token.setX(token.getLayoutX());
                            token.setY(token.getLayoutY());
                            token.setLayoutX(0);
                            token.setLayoutY(0);
                            transition.setPath(s.pathList.get(count.get()));
                            transition.setDuration(Duration.millis(1000));
                            transition.setCycleCount(1);
                            transition.play();
                            count.getAndIncrement();
                        })));
                        animate.setCycleCount(s.pathList.size());
                        animate.play();
                        floor=(s.getEnd()-1)/10+1;currx=s.getEndX();curry=s.getEndY();

//                        token.setLayoutX(s.getEndX());
//                        token.setLayoutY(-s.getEndY());
//                        token.setX(s.getEndX());
//                        token.setY(-s.getEndY());
                        System.out.println("saanp kaat gaya"+token.getLayoutX()+" "+token.getLayoutY());
                        System.out.println("find at floor : "+floor);
                        break;}
                    System.out.println("===========TOKEN1: "+token);
                }
                System.out.println("block:+"+getPosition());
                System.out.println("-------------------------------FINISH---------------------------------------");
            }

        };
//        thread.dice = dice;
        thread.start();
//        moveDownArrow.setAutoReverse(true);
//        moveDownArrow.play();
        return true;

    }
    TranslateTransition moveByOne(){
        System.out.println("cury "+currx+"  "+curry);
        TranslateTransition transition;
        if(!opened){
            token.setLayoutY(startY);   token.setLayoutX(startX);
            opened=true;
            transition= new TranslateTransition();
        }
        else{

            transition= new TranslateTransition(Duration.millis(100),token);
            int block=getPosition();
            System.out.println("block:+"+block);
            if(block%10==0 && floor!=10){curry-=onestepy;   floor++;transition.setToY(-onestepy*(floor-1));
                }
            else {
                if(floor%2==0){ currx-=onestepx;transition.setToX(currx);}
                else{ currx+=onestepx;transition.setToX(currx);}
            }
            if(getPosition()==100)  {
                System.out.println("Player "+ color+" wins the game");
                this.win=true;
            }
            System.out.println("block:+"+getPosition());
            transition.setCycleCount(1);
            transition.setAutoReverse(false);
            transition.play();
        }
        return transition;
    }
    int getPosition(){
        int num;
        int Xposition=(int)(currx)/58+1;
        int Yposition=11-((int)(curry/55+1));
        if(Yposition%2==0)
            num=(Yposition-1)*10+(11-Xposition);
        else num=(Yposition-1)*10+Xposition;
        return num;
    }
    void setLadders(ArrayList<ladder> l){ladders.addAll(l);}
    void setSnakes(ArrayList<snake> s){snakes.addAll(s);}
}

class ladder{
    private int start,end,tx,ty,endX,endY;
    ladder(int start,int end,int tx,int ty,int endX,int endY){
        this.start=start;this.end=end;this.tx=tx;this.ty=ty;this.endX=endX;this.endY=endY;
    }
    public int getStart(){return start;}    public int getEnd(){return end;}
    public int getTx(){return tx;}  public int getTy(){return ty;}
    public int getEndX(){return endX;}  public int getEndY(){return endY;}
}
class snake{
    private int start,end,tx,ty,endX,endY;
    public ArrayList<CubicCurve> pathList=new ArrayList<>();
    snake(int start,int end,int tx,int ty,int endX,int endY,ArrayList<CubicCurve> path){
        this.start=start;this.end=end;this.tx=tx;this.ty=ty;this.endX=endX;this.endY=endY;
        for(int i=0;i<path.size();i++){
            pathList.add(path.get(i));
        }
        System.out.println("snakeee----------------------------------"+ this.pathList+"   "+pathList);
    }
    public int getStart(){return start;}    public int getEnd(){return end;}
    public int getTx(){return tx;}  public int getTy(){return ty;}
    public int getEndX(){return endX;}  public int getEndY(){return endY;}
}
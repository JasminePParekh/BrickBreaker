import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.effect.ColorAdjust; 
import javafx.scene.image.ImageView; 
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.util.*;
import javafx.scene.text.Text;

public class Example extends Application implements EventHandler<InputEvent>{
Stage stage;
Group root;
GraphicsContext gc;
GraphicsContext gcTwo;
String name = "Jasmine";
String str = " WELCOME TO BRICK BREAKER! Created by Jazz\n Help the ball the destroy the bricks by clicking arrow\n keys or using the trackpad to move the paddle. \n But be careful you only have three lives and the\n ball gets faster as you go.\n GOODLUCK! Press the SPACE BAR to begin...";
Text t;
AudioClip clip;
Image fire;
Image ball;
Image heart;
Image win;
Image slide;
Image lose;
AnimateObjects animate;
Canvas canvas;
Canvas canvasTwo;
Brick b;
Image brick;
int minScore;
boolean lost = false;
boolean won = false;
boolean start = true;
int gameProgress;
Rectangle2D rect1;
Rectangle2D rect2;
Rectangle2D sliderRect;
Rectangle2D sliderRect2;
Rectangle2D sliderRect3;
Rectangle2D sliderRect4;
String bXDirection = "left";
String bYDirection = "up";
int sliderSlope = 1;
boolean hitCheck = false;
boolean justLostLife = false;
int x = 300;
int y = 300;
int brickPoints = 0;
int rand;
int sliderX=500;
int sliderY=500;
double increment = 5;
int lives = 3;
int sIncrement = 46;
ArrayList<Brick> arr = new ArrayList<Brick>();

public void handle(final InputEvent event){ 
	if(event instanceof KeyEvent){
		if (((KeyEvent)event).getCode() == KeyCode.LEFT ){
		
			if(sliderRect.getMinX() < 0.0 ) 
         		sliderX = -60; 
    		else 
    			sliderX-=sIncrement;
		} 
		if (((KeyEvent)event).getCode() == KeyCode.RIGHT ){
			    if( sliderRect.getMaxX() > 760)
			    	sliderX = 720;
    			else
    				sliderX+=sIncrement;
		}
		if(((KeyEvent)event).getCode() == KeyCode.ENTER){
		if(lost == true){
      	System.out.println( "Restarting app!" );
      	stage.hide();
      	restart();
        }
        }
        
        if(((KeyEvent)event).getCode() == KeyCode.SPACE){
        URL resource = getClass().getResource("start.mp3");
			clip = new AudioClip(resource.toString());
			clip.play();
        start = false;
        restart();
        
        }
		}	
    
	if(event instanceof MouseEvent){
	sliderX = (int)((MouseEvent)event).getX();
	}	 
}

public class AnimateObjects extends AnimationTimer{

	public void handle(long now){
		gc.clearRect(0, 0, canvas.getWidth(),canvas.getHeight());
		gcTwo.clearRect(0, 0, canvasTwo.getWidth(),canvasTwo.getHeight());
		word();
		move();
		printBricks();
		drawLives();
		sliderMove();
		hit();
		levelUp();
		bounceCanvas();
		checkWin();
		
	}
		
}	
	
public static void main(final String [] args){
launch();
}
public void start(Stage stage)
{
this.stage = stage;
stage.setTitle("Brick Breaker - Jasmine Parekh");
root = new Group();
canvas = new Canvas(800, 600);
canvasTwo = new Canvas(800,800);
root.getChildren().add(canvas);
root.getChildren().add(canvasTwo);
Scene scene = new Scene(root,800,800, Color.BLACK);
stage.setScene(scene);
scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
scene.addEventHandler(MouseEvent.MOUSE_MOVED,this);
gc = canvas.getGraphicsContext2D();
gcTwo = canvasTwo.getGraphicsContext2D();
ball = new Image("ball.jpg");
brick = new Image("download3.png");
fire = new Image("fire.gif");
slide = new Image("slider.png");
heart = new Image("heart.png");
lose = new Image("lose.jpg");
win = new Image("won.png");
makeBricks();
animate = new AnimateObjects();
animate.start();

stage.show();

    }

public void move(){
	if(bXDirection.equals("right")){
		x+=increment;
	}
	if(bXDirection.equals("left")){
		x-=increment;
	}
	if(bYDirection.equals("up")){
	if(justLostLife){
			try {
		   		Thread.sleep(2000);
			}
			catch(Exception e){}
			justLostLife = false;
		}
		if(sliderSlope == 1 || sliderSlope == 4){
		y-=3+increment;
		}
		if(sliderSlope ==2 || sliderSlope == 3){
		y-=increment;
		}
	}
	if(bYDirection.equals("down")){
		y+=increment;
	} 
	if(start == true){
	gc.setFill( Color.RED); //Fills the text in yellow
			gc.setStroke( Color.BLACK ); //Changes the outline the black
			gc.setLineWidth(1); //How big the black lines will be
			Font font = Font.font( "Arial", FontWeight.NORMAL, 32);
			gc.setFont( font );
	gc.fillText( str, 30, 250); //draws the yellow part of the text
	gc.strokeText( str, 30, 250 );
	x = 0;
	y = 230;
	}
	if (lost == false && won == false){
	gc.drawImage(ball,x,y);
	rect2 = new Rectangle2D(x,y,ball.getWidth(),ball.getHeight());
	}
	else{
	x=300;
	y=300;
	}

}
public void levelUp(){
if(brickPoints<=20){
increment += 0.0001;
}
else if (brickPoints>20 && brickPoints<=45) {
increment += 0.001;
}
else if (brickPoints>45 && brickPoints<=70){
increment += 0.005;
}
else if (brickPoints>70){
increment += 0.008;
}
}
public void sliderMove(){

gc.drawImage(slide,sliderX, sliderY);
sliderRect = new Rectangle2D(sliderX, sliderY,35,slide.getHeight());
sliderRect2 = new Rectangle2D(sliderX+35, sliderY,36,slide.getHeight());
sliderRect3 = new Rectangle2D(sliderX+71, sliderY,36,slide.getHeight());
sliderRect4 = new Rectangle2D(sliderX+106, sliderY,35,slide.getHeight()); 
}
public void makeBricks(){
for( int i = 0; i < 5; i++ ){

	for(int j = 0; j<20; j++){
		b = new Brick(4+j*40, i*20);
		arr.add(b);
	}
	
} 
}




public void printBricks(){
	for( int i = 0; i < arr.size(); i++) {
		   b = arr.get(i);
		   gc.drawImage(b.brick, b.getMinX(), b.getMinY());
		}
		gc.drawImage(fire,580, 520); gc.drawImage(fire,480, 520); gc.drawImage(fire,380, 520); 
		gc.drawImage(fire,280, 520); gc.drawImage(fire,180, 520); gc.drawImage(fire,0, 520); 
}

public void checkWin(){
		if(arr.size() == 0){
			gc.drawImage(win,0,0);	
			won = true;	
		}
		else if(lives<0){
			gc.setFill( Color.YELLOW); //Fills the text in yellow
			gc.setStroke( Color.BLACK ); //Changes the outline the black
			gc.setLineWidth(1); //How big the black lines will be
			Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
			gc.setFont( font );
				if(lives == -1){
			URL resource = getClass().getResource("gameover.mp3");
			clip = new AudioClip(resource.toString());
			clip.play();
			}
			lives--;
			gc.fillText( "                 Game Over:\nPress the ENTER to Restart!", 50, 300); //draws the yellow part of the text
			gc.strokeText( "                 Game Over\nPress the ENTER to Restart!", 50, 300 );
			lost = true;
		}
}
public void drawLives(){
if (lives == 3){
gcTwo.drawImage(heart,20,650); gcTwo.drawImage(heart,80,650); gcTwo.drawImage(heart,140,650);
}
if(lives == 2){
 gcTwo.drawImage(heart,80,650); gcTwo.drawImage(heart,140,650);
}
if(lives == 1){
  gcTwo.drawImage(heart,140,650);
}
}








public void bounceCanvas(){
	if(rect2.getMinX() <= 0.0){
		bXDirection = "right";
		x = 0;
	}
	if(rect2.getMaxX() >= canvas.getWidth()){
		bXDirection = "left";
		x = 760;
	}
	if(hitCheck){
		bYDirection = "down";	
		hitCheck = false;
	}
	if(rect2.getMaxY() >= canvas.getHeight()){		
		bYDirection = "up";	
		y = (int)sliderRect.getMinY()-60;
		x = (int)sliderRect.getMaxX()-60;
		if (lost == false && won == false){
		URL resource = getClass().getResource("fire.mp3");
		clip = new AudioClip(resource.toString());
		clip.play();
		}
		lives--;
		justLostLife = true;
	}
	if(rect2.getMinY()<=0.0){
		bYDirection = "down";
		y=0;
	}
	if(rect2.intersects(sliderRect)){
	URL resource = getClass().getResource("bop.mp3");
	clip = new AudioClip(resource.toString());
	clip.play();
	bYDirection="up";
	sliderSlope = 1;
	y = (int)sliderRect.getMinY()-60;
	}
	if(rect2.intersects(sliderRect2)){
	URL resource = getClass().getResource("bop.mp3");
	clip = new AudioClip(resource.toString());
	clip.play();
	bYDirection="up";
	sliderSlope = 2;
	y = (int)sliderRect2.getMinY()-60;
	}
	if(rect2.intersects(sliderRect3)){
	URL resource = getClass().getResource("bop.mp3");
	clip = new AudioClip(resource.toString());
	clip.play();
	bYDirection="up";
	sliderSlope = 3;
	y = (int)sliderRect3.getMinY()-60;
	}
	if(rect2.intersects(sliderRect4)){
	URL resource = getClass().getResource("bop.mp3");
	clip = new AudioClip(resource.toString());
	clip.play();
	bYDirection="up";
	sliderSlope = 4;
	y = (int)sliderRect4.getMinY()-60;
	}
	
	
}

public void word(){
	gcTwo.setFont(new Font("Gabriola", 100.0));
	gcTwo.setStroke(Color.RED);
	gcTwo.strokeText("Brick Breaker",250,700,400);
	gcTwo.setStroke(Color.YELLOW);
	gcTwo.strokeText(Integer.toString(brickPoints),700,700,100);
}
public void restart(){
System.out.println("Restarting Start");
sliderSlope = 1;
hitCheck = false;
justLostLife = false;
lost = false;
won = false;
int minScore;
boolean lost = false;
boolean won = false;
boolean leaderboard = false;
x = 300;
y = 300;
brickPoints = 0;
sliderX=500;
sliderY=500;
increment = 5;
lives = 3;
sIncrement = 46;
arr.clear();
makeBricks();
System.out.println("Restarting End");
stage.show();
}

	


public void hit(){

	for(int i = 0; i<arr.size(); i++){
		if(arr.get(i).intersects(rect2)){
			//System.out.println("HIT");
			URL resource = getClass().getResource("pop.mp3");
			clip = new AudioClip(resource.toString());
			clip.play();
			hitCheck = true;
			brickPoints++;

			if(hitCheck){
				y = (int)arr.get(i).getMaxY();
			}
			if(arr.get(i).counter <= 0){
			arr.remove(i);
			}
			else{
			arr.get(i).counter--;
			}
			break;
		}	
}

}
}
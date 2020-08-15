import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.ColorAdjust; 
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import javafx.scene.image.ImageView;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.util.*;

public class Brick extends Rectangle2D{

public Image brick;
public int counter;
Graphics2D g2;
int rand;
int randTwo;

	public Brick(double minX, double minY){
		super(minX, minY, 40, 20);
		rand = (int)(Math.random()*7)+1;
		
	
		if(rand == 1){
		brick = new Image("download.png");
		}
		else if(rand == 2){
		brick = new Image("download1.png");
		}
		else if (rand == 3){
		brick = new Image("download2.png");
		}
		else if (rand == 4){
		brick = new Image("download3.png");
		}
		else if (rand == 5){
		brick = new Image("download4.png");
		}
		else if (rand == 6){
		brick = new Image("download5.png");
		}
		else if (rand == 7){
		brick = new Image("download6.png");
		}
		}
		}

		


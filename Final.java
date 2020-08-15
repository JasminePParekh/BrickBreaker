import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;


public class Final extends Application{
GraphicsContext gc;
Image trooper;
AnimateObjects animate;
public static void main(String [] args){
launch();
}
public void start(Stage stage)
{
stage.setTitle("Brick Breaker - Jasmine Parekh");
Group root = new Group();
Canvas canvas = new Canvas(800, 400);
root.getChildren().add(canvas);
Scene scene = new Scene(root);
stage.setScene(scene);
gc = canvas.getGraphicsContext2D();
trooper = new Image("trooper.jpg");
gc.drawImage(trooper, 180,100);
animate = new AnimateObjects();
animate.start();
stage.show();
}

}
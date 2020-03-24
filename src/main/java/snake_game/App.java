package snake_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import snake_game.model.Directions;
import snake_game.model.Grid;
import snake_game.view.GameScene;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    //game starting static variables;
    int x = 20;
    int y = 20;
    int snakeLength = 7;
    boolean borders = true;
    Directions dir = Directions.EAST;
    int windowLength = 800;

    //game objects
    private static GameScene gamescene;
    private static Controller controller;
    private static Grid gamegrid;

    //animation objects
    public final int FRAMES_PER_SECOND = 3;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Timeline myAnimationPlay;

    @Override
    public void start(Stage stage) throws IOException {
        controller = new Controller(x,y,snakeLength,borders,dir,windowLength);
        gamescene = controller.getMyGameScene();
        gamegrid = controller.getMyGameGrid();
        stage.setScene(gamescene.getMyScene());
        stage.show();

        //game animation
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                step(SECOND_DELAY, stage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        myAnimationPlay = new Timeline();
        myAnimationPlay.setCycleCount(Timeline.INDEFINITE);
        myAnimationPlay.getKeyFrames().add(frame);
        myAnimationPlay.play();

        gamescene.getMyScene().setOnKeyPressed(e -> controller.userInputUpdate(e.getCode()));
    }

    private void step (double elapsedTime,Stage stage) throws IOException {
        if (!gamegrid.isAlive()) {
            System.exit(0);
        }
        controller.stepGame();


    }

    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
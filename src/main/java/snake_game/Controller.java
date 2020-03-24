package snake_game;

import javafx.scene.input.KeyCode;
import snake_game.model.Directions;
import snake_game.model.Grid;
import snake_game.view.GameScene;

public class Controller {
    Grid myGameGrid;
    GameScene myGameScene;

    public Controller(int x, int y, int snakeLength, boolean borders, Directions dir, int windowLength) {
        myGameGrid = new Grid(x,y,snakeLength, borders, dir);
        myGameScene = new GameScene(myGameGrid, windowLength, windowLength/x);
    }

    public void stepGame() {
        myGameGrid.moveSnake();
        myGameScene.updateGame();
    }

    public Grid getMyGameGrid() {
        return myGameGrid;
    }

    public GameScene getMyGameScene() {
        return myGameScene;
    }

    public void userInputUpdate(KeyCode code) {
        if (code == KeyCode.UP && myGameGrid.getDir()!= Directions.SOUTH) {
            myGameGrid.setDir(Directions.NORTH);
        } else if (code == KeyCode.DOWN && myGameGrid.getDir()!= Directions.NORTH) {
            myGameGrid.setDir(Directions.SOUTH);
        } else if (code == KeyCode.LEFT && myGameGrid.getDir()!= Directions.EAST) {
            myGameGrid.setDir(Directions.WEST);
        } else if (code == KeyCode.RIGHT && myGameGrid.getDir()!= Directions.WEST) {
            myGameGrid.setDir(Directions.EAST);
        }
    }


}

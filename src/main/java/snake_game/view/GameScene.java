package snake_game.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import snake_game.model.Cell;
import snake_game.model.Grid;

import java.util.HashMap;

public class GameScene {
    HashMap<Cell, Rectangle> myMap;
    Scene myScene;
    Group root;
    private int indexSize;
    private int squareSize;

    public GameScene(Grid myGrid, int windowLength, int s) {
        Cell[][] thisGrid = myGrid.getMyGrid();
        myMap = new HashMap<>();
        this.indexSize = windowLength/thisGrid[0].length;
        this.squareSize = s;

        root = new Group();

        //set up all the squares of the grid
        //creates hashMap
        for (int i=0; i< thisGrid.length ;i++) {
            for (int j=0; j<thisGrid[0].length;j++) {
                Rectangle rect = new Rectangle(i*indexSize, j*indexSize,squareSize, squareSize);
                rect.setFill(Color.WHITE);
                myMap.put(thisGrid[i][j], rect);
                root.getChildren().add(rect);
            }
        }

        //sets up the scene
        myScene = new Scene(root, windowLength, windowLength);
    }

    public HashMap<Cell, Rectangle> getMyMap() {
        return myMap;
    }

    public Scene getMyScene() {
        return myScene;
    }

    public void updateGame() {
        for (Cell each: myMap.keySet()){
            if (each.isFood()) {
                myMap.get(each).setFill(Color.BLUE);
            } else if (each.getLives() > 0) {
                myMap.get(each).setFill(Color.BLACK);
            } else {
                myMap.get(each).setFill(Color.WHITE);
            }
        }
    }
}

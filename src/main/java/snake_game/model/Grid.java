package snake_game.model;

import java.util.Random;

public class Grid {
    Cell[][] myGrid;
    int x;
    int y;
    int snakeX;
    int snakeY;
    int snakeLength;
    boolean borders;
    Directions dir;
    boolean alive;

    public Grid(int x, int y, int snakeLength, boolean borders, Directions dir) {
        this.x = x;
        this.y = y;
        this.borders = borders;
        this.snakeLength = snakeLength;
        this.dir = dir;
        snakeX = x/2;
        snakeY = y/2;
        myGrid = new Cell[x][y];
        makeGrid();
        createSnake(snakeX,snakeY);
        alive = true;
        makeNewFood();
    }

    public void createSnake(int x, int y) {
        for (int z = 0; z < snakeLength;z++) {
            myGrid[x-z][y].lives = snakeLength-z;
        }
    }

    public void makeNewFood() {
        Random rand = new Random();
        int rand_x = rand.nextInt(x);
        int rand_y = rand.nextInt(y);

        // finds the next food location
        while (myGrid[rand_x][rand_y].food || myGrid[rand_x][rand_y].lives != 0) {
            rand_x = rand.nextInt(x);
            rand_y = rand.nextInt(y);
        }

        //sets the new food location;
        myGrid[rand_x][rand_y].food = true;
    }

    public int[] getNewLocation() {
        int[] ans = new int[2];
        if (dir == Directions.NORTH) {
            ans[0] = snakeX;
            ans[1] = snakeY - 1;
        } else if (dir == Directions.SOUTH) {
            ans[0] = snakeX;
            ans[1] = snakeY + 1;
        } else if (dir == Directions.EAST) {
            ans[0] = snakeX + 1;
            ans[1] = snakeY;
        } else if (dir == Directions.WEST) {
            ans[0] = snakeX -1;
            ans[1] = snakeY;
        }
        return ans;
    }

    public void moveSnake() {
        int[] newSnakePos = getNewLocation();
        //new position is food
        System.out.println(newSnakePos[0]);
        System.out.println(newSnakePos[1]);

        //if new position is out of bounds
        if (newSnakePos[0] < 0 || newSnakePos[0] >=x ||
                newSnakePos[1] < 0 || newSnakePos[1] >=y) {
            alive = false;

        // if new pos is food
        } else if (myGrid[newSnakePos[0]][newSnakePos[1]].food) {
            myGrid[newSnakePos[0]][newSnakePos[1]].food = false;
            snakeLength += 1;
            myGrid[newSnakePos[0]][newSnakePos[1]].lives = snakeLength;
            snakeX = newSnakePos[0];
            snakeY = newSnakePos[1];
            makeNewFood();

        //new position is snake body
        } else if (myGrid[newSnakePos[0]][newSnakePos[1]].lives > 0) {
            alive = false;

        // new position is not border or snake body
        } else {
            reduceAllLives();
            myGrid[newSnakePos[0]][newSnakePos[1]].lives = snakeLength;
            snakeX = newSnakePos[0];
            snakeY = newSnakePos[1];
        }
    }

    public void reduceAllLives() {
        for (int i=0;i<myGrid.length;i++) {
            for (int j=0;j<myGrid.length;j++) {
                if (myGrid[i][j].lives > 0) {
                    myGrid[i][j].lives -= 1;
                }
            }
        }
    }

    public void makeGrid() {
        for (int i=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                myGrid[i][j] = new Cell(false,0);
            }
        }
    }

    public Cell[][] getMyGrid() {
        return myGrid;
    }

    public Directions getDir() {
        return dir;
    }

    public void setDir(Directions newDir) {
        dir = newDir;
    }

    public boolean isAlive() {
        return alive;
    }
}

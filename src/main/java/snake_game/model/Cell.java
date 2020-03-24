package snake_game.model;

public class Cell {
    boolean food;
    int lives;

    public Cell(boolean food, int lives) {
        this.food = food;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public boolean isFood() {
        return food;
    }
}

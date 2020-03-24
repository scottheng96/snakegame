module snake_game {
    requires javafx.controls;
    requires javafx.fxml;

    opens snake_game to javafx.fxml;
    exports snake_game;
}
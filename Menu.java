import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

public class Menu extends VBox {
    Menu(Stage stage){
        setAlignment(Pos.CENTER);
        setSpacing(3);
        setBackground(new Background(new BackgroundImage(
                        new Image("1.jpg"),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT))
        );

        Button play = new Button("Play");
        Button highScores = new Button("High Score");
        Button exit = new Button("Exit");

        play.setFont(new Font(30));
        highScores.setFont(new Font(30));
        exit.setFont(new Font(30));

        play.setOnAction(event ->stage.setScene(new Scene(new BeforeGame(stage),1200,980)));
        highScores.setOnAction(event ->stage.setScene(new Scene(new HighScores(stage),1200,980)));
        exit.setOnAction(event ->exit());

        getChildren().addAll(play, highScores, exit);
    }
}

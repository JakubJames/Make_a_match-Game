import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HighScores extends FlowPane {

    HighScores(Stage stage){
        setOrientation(Orientation.VERTICAL);
        setAlignment(Pos.CENTER);

        ObservableList<Score> scores = FXCollections.observableArrayList();
        ScrollPane scrollPane = new ScrollPane();
        ListView<Score> scoreList = new ListView<>();
        StringBuilder winnerList = new StringBuilder();
        Label header = new Label("HIGH SCORES");
        Button aReturn = new Button("Return");

        scrollPane.prefWidthProperty().bind(scoreList.widthProperty());
        scrollPane.prefHeightProperty().bind(scoreList.heightProperty());
        header.setFont(new Font(60));
        scrollPane.setContent(scoreList);

        //pobieranie wyników z pliku
        try {
            FileInputStream fis = new FileInputStream("src/highScores.txt");
            while(fis.available()>0){
                winnerList.append((char)fis.read());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku src/highScores.txt");
        }catch (IOException e2){
            System.out.println(e2);
        }

        //dzielenie na linie
        String lines[] = winnerList.toString().split("\n");
        for(String s:lines){
            String[] tmp=s.split("\\.");
            scores.add(new Score(tmp[0],tmp[1],tmp[2]));
        }
        scoreList.setItems(scores.sorted((o1,o2)->{
            int score1=((o1.pole*100)/o1.czas);
            int score2=((o2.pole*100)/o2.czas);
            System.out.println("SORTOWANIE: "+score1+" "+score2);
            return score2-score1;
        }));

        aReturn.setOnAction(e->{
            stage.setScene(new Scene(new Menu(stage),1200,980));
        });

        getChildren().addAll(header,scrollPane,aReturn);
    }
}

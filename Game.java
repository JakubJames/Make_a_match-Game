import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends BorderPane {

    static final int ILOSC_ZDJEC=18;
    ArrayList<Integer> poleGry;
    Card para;
    IntegerProperty licznikdoWygranej;
    AnimationTimer timer;

    Game(Stage stage, int liczbaKart) {
        Label label = new Label();
        label.setFont(new Font(25));

        //zmodyfikowana wersja licznika/stopera: https://stackoverflow.com/questions/40821849/creating-simple-stopwatch-javafx
        timer = new AnimationTimer() {
            private long timestamp;
            private long time = 0;
            private long fraction = 0;

            @Override
            public void start() {
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                fraction = System.currentTimeMillis() - timestamp;
                stage.setScene(new Scene(new Menu(stage), 1200, 980));
                WinnerWindow ww = new WinnerWindow(liczbaKart,time);
                ww.setX(stage.getX()+500);
                ww.setY(stage.getY()+450);
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 1000 <= newTime) {
                    long deltaT = (newTime - timestamp) / 1000;
                    time += deltaT;
                    timestamp += 1000 * deltaT;
                    if(time<100) {
                        if(time<10){
                            label.setText("00"+(time));
                        }else {
                            label.setText("0"+(time));
                        }
                    }else{
                        label.setText(Long.toString(time));
                    }
                }
            }
        };
        ////////////////////////////////////////////
        timer.start();
        setRight(label);


        VBox mainBox= new VBox();
        mainBox.setSpacing(30);
        mainBox.setAlignment(Pos.CENTER);

        poleGry = new ArrayList<>();
        licznikdoWygranej= new SimpleIntegerProperty((liczbaKart*liczbaKart)/2);

        Button aReturn = new Button("Return");

        int k=0;
        int l=1;

        //tworzenie intów i następne ich mieszanie, by karty był losowo rozmieszczone
        while (k<liczbaKart*liczbaKart){
            poleGry.add(k,l);
            if(k<poleGry.size()) {
                poleGry.add(k+1,l);
            }
            k+=2;
            l++;
        }
        Collections.shuffle(poleGry);////////////////////////

        //ustawianie kart
        k=0;
        for (int i = 0; i < liczbaKart; i++) {
            HBox hBox = new HBox();

            hBox.setSpacing(30);
            hBox.setAlignment(Pos.CENTER);

            for (int j = 0; j <  liczbaKart; j++) {
                hBox.getChildren().add(new Card(this, poleGry.get(k)));
                k++;
            }
            mainBox.getChildren().add(hBox);
        }

        aReturn.setOnAction(e->{
            stage.setScene(new Scene(new Menu(stage),1200,980));
        });
        mainBox.getChildren().add(aReturn);

        setCenter(mainBox);
    }
}

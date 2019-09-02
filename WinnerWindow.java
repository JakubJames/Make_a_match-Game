import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;

public class WinnerWindow extends Stage {
    WinnerWindow(int liczbakart,long czas){
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);

        Label label = new Label();
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(20));
        label.setText("Wygrałeś!\nTwój czas to "+czas+"\nChcesz zapisać wynik?\nWpisz swoją nazwę:");
        pane.getChildren().add(label);

        TextField nazwa = new TextField();
        pane.getChildren().add(nazwa);

        Button zapisz = new Button("Zapisz wynik");
        zapisz.setOnAction(event -> {
            if(nazwa.getText()!=null){
                try {
                    StringBuilder wynik = new StringBuilder();
                    FileInputStream fis = new FileInputStream("src/highScores.txt");
                    while(fis.available()>0){
                        wynik.append((char)fis.read());
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/highScores.txt"));

                    wynik.append(nazwa.getText()+"."+liczbakart+"."+czas+"\n");
                    writer.append(wynik);
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Brak pliku src/highScores.txt");
                }catch (IOException e2){
                    System.out.println("Problem z IO");
                }
                close();
            }
        });
        pane.getChildren().add(zapisz);

        Scene scene = new Scene(pane, 230, 300);
        setScene(scene);
        setTitle("Wygrałeś!");
        show();
    }
}

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BeforeGame  extends VBox {
    BeforeGame(Stage stage){
        setAlignment(Pos.CENTER);
        setSpacing(30);

        Label label = new Label("Liczba rzędów/wierszy (2, 4 lub 6):");
        TextField liczbaKart = new TextField();
        Button play = new Button("Play");
        Label error = new Label();
        Button aReturn = new Button("Return");

        label.setFont(new Font(40));
        liczbaKart.setAlignment(Pos.CENTER);
        liczbaKart.setMaxWidth(500);
        play.setMaxWidth(250);
        error.setFont(new Font(20));

        play.setOnAction(event -> {
            try {
                int liczbaKartval = Integer.parseInt(liczbaKart.getText());
                if((liczbaKartval*liczbaKartval)/2>Game.ILOSC_ZDJEC||liczbaKartval<=1||(liczbaKartval*liczbaKartval)%2!=0){
                    error.setText("BŁĘDNA WARTOŚĆ");
                }else {
                    try {
                        stage.setScene(new Scene(new Game(stage, liczbaKartval), 1200, 980));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }catch (NumberFormatException e){
                error.setText("BŁĘDNA WARTOŚĆ");
            }

        });

        aReturn.setOnAction(e->{
            stage.setScene(new Scene(new Menu(stage),1200,980));
        });

        getChildren().addAll(label,liczbaKart, play,error,aReturn);
    }
}

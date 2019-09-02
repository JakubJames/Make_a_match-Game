import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Card extends ImageView{
    final Image reverse;
    final Image img;
    Game game;
    Boolean odsloniete;

    public Card(Game game,int nrZdjecia){
        if(nrZdjecia<10) img = new Image("img/cat0"+nrZdjecia+".jpg");
        else  img = new Image("img/cat"+nrZdjecia+".jpg");
        reverse = new Image("img/reverse.jpg");
        this.game = game;
        odsloniete = false;

        setImage(reverse);

        setPickOnBounds(true);

        //całą mechanika klikania
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            //sprawdza czy karta nie jest juz odkryta
            if(!odsloniete) {
                odsloniete = true;
                setImage(img);
                //sprawdza czy to pierwsza czy druga odkryta karta
                if (game.para == null) {
                    game.para = this;
                } else {
                    //sprawdza dopasowanie
                    if (game.para.img.getUrl().equals(img.getUrl())) {
                        game.para = null;
                        game.licznikdoWygranej.setValue(game.licznikdoWygranej.getValue() - 1);
                        //sprawdza czy to koniec gry (wygrana)
                        if (game.licznikdoWygranej.getValue() == 0) {
                            game.timer.stop();
                        }
                    }else {
                        //pozostawienie odkrytej karty na 0.6 sekundy
                        new Timeline(new KeyFrame(
                            Duration.millis(600),
                            event -> {
                                setImage(reverse);
                                odsloniete = false;

                                game.para.setImage(reverse);
                                game.para.odsloniete = false;

                                game.para = null;
                            }
                        )).play();
                    }
                }
            }
        });
    }
}
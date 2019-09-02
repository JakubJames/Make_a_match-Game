public class Score{
    String name;
    int pole;
    int czas;

    Score(String name, String pole, String czas) {
        this.name = name;
        this.pole = Integer.parseInt(pole);
        //nie wiem skąd ta spacja po #czas
        czas=czas.replaceAll("\\D","");
        this.czas = Integer.parseInt(czas);
    }

    public String toString(){
        return name+" Pole: "+pole+"x"+pole+" Czas: "+czas;
    }
}

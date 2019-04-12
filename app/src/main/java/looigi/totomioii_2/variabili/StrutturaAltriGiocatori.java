package looigi.totomioii_2.variabili;

import java.util.ArrayList;
import java.util.List;

public class StrutturaAltriGiocatori {
    //-------- Singleton ----------//
    private static StrutturaAltriGiocatori instance = null;

    private StrutturaAltriGiocatori() {
    }

    public static StrutturaAltriGiocatori getInstance() {
        if (instance == null) {
            instance = new StrutturaAltriGiocatori();
        }

        return instance;
    }
    private List<Integer> idGiocatore;
    private List<String> Giocatore;
    private List<String> Motto;

    public void AggiungeGiocatore(int idGioc, String Gioc, String sMotto) {
        if (Giocatore==null) {
            idGiocatore=new ArrayList<>();
            Giocatore=new ArrayList<>();
            Motto=new ArrayList<>();
        }

        idGiocatore.add(idGioc);
        Giocatore.add(Gioc);
        Motto.add(sMotto);
    }

    public String RitornaGiocatore(String Chi) {
        if (Giocatore==null) {
            return "";
        }

        for (int i=0;i<Giocatore.size();i++) {
            if (Giocatore.get(i).trim().toUpperCase().equals(Chi.trim().toUpperCase())) {
                return Integer.toString(idGiocatore.get(i)) +";"+ Giocatore.get(i)+";"+Motto.get(i)+";";
            }
        }

        return "";
    }

    public String RitornaGiocatore(int Quale) {
        if (Giocatore==null) {
            return "";
        }

        for (int i=0;i<Giocatore.size();i++) {
            if (idGiocatore.get(i)==Quale) {
                return idGiocatore +";"+ Giocatore+";"+Motto+";";
            }
        }

        return "";
    }
}

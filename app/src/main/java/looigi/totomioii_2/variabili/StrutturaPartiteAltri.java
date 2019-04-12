package looigi.totomioii_2.variabili;

import java.util.ArrayList;
import java.util.List;

public class StrutturaPartiteAltri {
    //-------- Singleton ----------//
    private static StrutturaPartiteAltri instance = null;

    private StrutturaPartiteAltri() {
    }

    public static StrutturaPartiteAltri getInstance() {
        if (instance == null) {
            instance = new StrutturaPartiteAltri();
        }

        return instance;
    }

    private List<Integer> Partita;
    private List<String> Segni;
    private List<String> Risultato;
    private List<String> Casa;
    private List<String> Fuori;
    private int PartitaJolly;
    private int FilRouge;

    public void PulisceCampi() {
        Partita=new ArrayList<>();
        Segni=new ArrayList<>();
        Risultato=new ArrayList<>();
        Casa=new ArrayList<>();
        Fuori=new ArrayList<>();
        PartitaJolly=-1;
        FilRouge=-1;
    }

    public int getFilRouge() {
        return FilRouge;
    }

    public void setFilRouge(int FilRouge) {
        this.FilRouge = FilRouge;
    }

    public int getPartitaJolly() {
        return PartitaJolly;
    }

    public void setPartitaJolly(int partitaJolly) {
        this.PartitaJolly = partitaJolly;
    }

    public void AggiungePartita(int Partita) {
        this.Partita.add(Partita);
    }

    public void AggiungeSegni(String Segni) {
        this.Segni.add(Segni);
    }

    public void AggiungeRisultato(String Risultato) {
        this.Risultato.add(Risultato);
    }

    public void AggiungeCasa(String Casa) {
        this.Casa.add(Casa);
    }

    public void AggiungeFuori(String Fuori) {
        this.Fuori.add(Fuori);
    }

    public Integer RitornaPartita(int Quale) {
        return Partita.get(Quale);
    }

    public String RitornaRisultato(int Quale) {
        return Risultato.get(Quale);
    }

    public String RitornaSegni(int Quale) {
        return Segni.get(Quale);
    }

    public String RitornaCasa(int Quale) {
        return Casa.get(Quale);
    }

    public String RitornaFuori(int Quale) {
        return Fuori.get(Quale);
    }

    public void ModificaSegni(int Quale, String Segni) {
        this.Segni.set(Quale, Segni);
    }

    public void ModificaRisultato(int Quale, String Risultato) {
        this.Risultato.set(Quale, Risultato);
    }
}

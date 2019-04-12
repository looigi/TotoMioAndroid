package looigi.totomioii_2.variabili;

import java.util.Date;

public class StrutturaDatiDiGioco {
    //-------- Singleton ----------//
    private static StrutturaDatiDiGioco instance = null;

    private StrutturaDatiDiGioco() {
    }

    public static StrutturaDatiDiGioco getInstance() {
        if (instance == null) {
            instance = new StrutturaDatiDiGioco();
        }

        return instance;
    }

    private int Giornata;
    private int StatoConcorso;
    private int GiornataIT;
    private int GiornataEL;
    private int GiornataCL;
    private int GiornataDer;
    private int GiornataCI;
    private int GiornataSpeciale;
    private int Anno;
    private String descAnno;
    private Date dataChiusura;
    private boolean Speciale;

    public boolean isSpeciale() {
        return Speciale;
    }

    public void setSpeciale(boolean speciale) {
        Speciale = speciale;
    }

    public Date getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(Date dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public String getDescAnno() {
        return descAnno;
    }

    public void setDescAnno(String descAnno) {
        this.descAnno = descAnno;
    }

    public int getAnno() {
        return Anno;
    }

    public void setAnno(int anno) {
        Anno = anno;
    }

    public int getGiornata() {
        return Giornata;
    }

    public void setGiornata(int giornata) {
        Giornata = giornata;
    }

    public int getStatoConcorso() {
        return StatoConcorso;
    }

    public void setStatoConcorso(int statoConcorso) {
        StatoConcorso = statoConcorso;
    }

    public int getGiornataIT() {
        return GiornataIT;
    }

    public void setGiornataIT(int giornataIT) {
        GiornataIT = giornataIT;
    }

    public int getGiornataEL() {
        return GiornataEL;
    }

    public void setGiornataEL(int giornataEL) {
        GiornataEL = giornataEL;
    }

    public int getGiornataCL() {
        return GiornataCL;
    }

    public void setGiornataCL(int giornataCL) {
        GiornataCL = giornataCL;
    }

    public int getGiornataDer() {
        return GiornataDer;
    }

    public void setGiornataDer(int giornataDer) {
        GiornataDer = giornataDer;
    }

    public int getGiornataCI() {
        return GiornataCI;
    }

    public void setGiornataCI(int giornataCI) {
        GiornataCI = giornataCI;
    }

    public int getGiornataSpeciale() {
        return GiornataSpeciale;
    }

    public void setGiornataSpeciale(int giornataSpeciale) {
        GiornataSpeciale = giornataSpeciale;
    }
}

package looigi.totomioii_2.variabili;

import java.util.ArrayList;
import java.util.List;

public class StrutturaPartite {
    //-------- Singleton ----------//
    private static StrutturaPartite instance = null;

    private StrutturaPartite() {
    }

    public static StrutturaPartite getInstance() {
        if (instance == null) {
            instance = new StrutturaPartite();
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

    private List<Integer> posSquadraCasa;
    private List<Integer> punSquadraCasa;
    private List<Integer> vinSquadraCasa;
    private List<Integer> parSquadraCasa;
    private List<Integer> perSquadraCasa;
    private List<Integer> gfaSquadraCasa;
    private List<Integer> gsuSquadraCasa;

    private List<Integer> posSquadraFuori;
    private List<Integer> punSquadraFuori;
    private List<Integer> vinSquadraFuori;
    private List<Integer> parSquadraFuori;
    private List<Integer> perSquadraFuori;
    private List<Integer> gfaSquadraFuori;
    private List<Integer> gsuSquadraFuori;

    private List<Float> r0_0;
    private List<Float> r0_1;
    private List<Float> r0_2;
    private List<Float> r0_3;
    private List<Float> r0_4;
    private List<Float> r1_0;
    private List<Float> r1_1;
    private List<Float> r1_2;
    private List<Float> r1_3;
    private List<Float> r1_4;
    private List<Float> r2_0;
    private List<Float> r2_1;
    private List<Float> r2_2;
    private List<Float> r2_3;
    private List<Float> r2_4;
    private List<Float> r3_0;
    private List<Float> r3_1;
    private List<Float> r3_2;
    private List<Float> r3_3;
    private List<Float> r3_4;
    private List<Float> r4_0;
    private List<Float> r4_1;
    private List<Float> r4_2;
    private List<Float> r4_3;
    private List<Float> r4_4;
    private List<Float> rAltro;

    private List<Float> r1;
    private List<Float> rX;
    private List<Float> r2;
    private List<Float> r1X;
    private List<Float> r12;
    private List<Float> rX2;

    public void PulisceCampi() {
        Partita=new ArrayList<>();
        Segni=new ArrayList<>();
        Risultato=new ArrayList<>();
        Casa=new ArrayList<>();
        Fuori=new ArrayList<>();
        PartitaJolly=-1;
        FilRouge=-1;

        posSquadraCasa = new ArrayList<>();
        punSquadraCasa = new ArrayList<>();
        vinSquadraCasa = new ArrayList<>();
        parSquadraCasa = new ArrayList<>();
        perSquadraCasa = new ArrayList<>();
        gfaSquadraCasa = new ArrayList<>();
        gsuSquadraCasa = new ArrayList<>();

        posSquadraFuori = new ArrayList<>();
        punSquadraFuori = new ArrayList<>();
        vinSquadraFuori = new ArrayList<>();
        parSquadraFuori = new ArrayList<>();
        perSquadraFuori = new ArrayList<>();
        gfaSquadraFuori = new ArrayList<>();
        gsuSquadraFuori = new ArrayList<>();

        r0_0 = new ArrayList<>();
        r0_1 = new ArrayList<>();
        r0_2 = new ArrayList<>();
        r0_3 = new ArrayList<>();
        r0_4 = new ArrayList<>();
        r1_0 = new ArrayList<>();
        r1_1 = new ArrayList<>();
        r1_2 = new ArrayList<>();
        r1_3 = new ArrayList<>();
        r1_4 = new ArrayList<>();
        r2_0 = new ArrayList<>();
        r2_1 = new ArrayList<>();
        r2_2 = new ArrayList<>();
        r2_3 = new ArrayList<>();
        r2_4 = new ArrayList<>();
        r3_0 = new ArrayList<>();
        r3_1 = new ArrayList<>();
        r3_2 = new ArrayList<>();
        r3_3 = new ArrayList<>();
        r3_4 = new ArrayList<>();
        r4_0 = new ArrayList<>();
        r4_1 = new ArrayList<>();
        r4_2 = new ArrayList<>();
        r4_3 = new ArrayList<>();
        r4_4 = new ArrayList<>();
        rAltro = new ArrayList<>();

        r1 = new ArrayList<>();
        rX = new ArrayList<>();
        r2 = new ArrayList<>();
        r1X = new ArrayList<>();
        r12 = new ArrayList<>();
        rX2 = new ArrayList<>();
    }

    public String RitornaQuoteSegno(int Quale, String Segno, Boolean fr, Boolean jolly) {
        String Ritorno = "";

        switch (Segno) {
            case "1":
                Ritorno=Float.toString(r1.get(Quale));
                break;
            case "X":
                Ritorno=Float.toString(rX.get(Quale));
                break;
            case "2":
                Ritorno=Float.toString(r2.get(Quale));
                break;
            case "1X":
                Ritorno=Float.toString(r1X.get(Quale));
                break;
            case "12":
                Ritorno=Float.toString(r12.get(Quale));
                break;
            case "X2":
                Ritorno=Float.toString(rX2.get(Quale));
                break;
        }

        if (fr) {
            if (!Ritorno.isEmpty()) {
                Float r = Float.parseFloat(Ritorno);
                r *= 2;
                Ritorno = Float.toString(r);
            }
        }

        if (jolly) {
            if (!Ritorno.isEmpty()) {
                Float r = Float.parseFloat(Ritorno);
                r *= 2;
                Ritorno = Float.toString(r);
            }
        }

        return "Quota: "+Ritorno;
    }

    public String RitornaQuoteRisultato(int Quale, String risCasa, String risFuori, Boolean fr, Boolean jolly) {
        String Ritorno="";
        String ris=risCasa+"_"+risFuori;

        switch(ris) {
            case "0_0":
                Ritorno=Float.toString(r0_0.get(Quale));
                break;
            case "0_1":
                Ritorno=Float.toString(r0_1.get(Quale));
                break;
            case "0_2":
                Ritorno=Float.toString(r0_2.get(Quale));
                break;
            case "0_3":
                Ritorno=Float.toString(r0_3.get(Quale));
                break;
            case "0_4":
                Ritorno=Float.toString(r0_4.get(Quale));
                break;

            case "1_0":
                Ritorno=Float.toString(r1_0.get(Quale));
                break;
            case "1_1":
                Ritorno=Float.toString(r1_1.get(Quale));
                break;
            case "1_2":
                Ritorno=Float.toString(r1_2.get(Quale));
                break;
            case "1_3":
                Ritorno=Float.toString(r1_3.get(Quale));
                break;
            case "1_4":
                Ritorno=Float.toString(r1_4.get(Quale));
                break;

            case "2_0":
                Ritorno=Float.toString(r2_0.get(Quale));
                break;
            case "2_1":
                Ritorno=Float.toString(r2_1.get(Quale));
                break;
            case "2_2":
                Ritorno=Float.toString(r2_2.get(Quale));
                break;
            case "2_3":
                Ritorno=Float.toString(r2_3.get(Quale));
                break;
            case "2_4":
                Ritorno=Float.toString(r2_4.get(Quale));
                break;

            case "3_0":
                Ritorno=Float.toString(r3_0.get(Quale));
                break;
            case "3_1":
                Ritorno=Float.toString(r3_1.get(Quale));
                break;
            case "3_2":
                Ritorno=Float.toString(r3_2.get(Quale));
                break;
            case "3_3":
                Ritorno=Float.toString(r3_3.get(Quale));
                break;
            case "3_4":
                Ritorno=Float.toString(r3_4.get(Quale));
                break;

            case "4_0":
                Ritorno=Float.toString(r4_0.get(Quale));
                break;
            case "4_1":
                Ritorno=Float.toString(r4_1.get(Quale));
                break;
            case "4_2":
                Ritorno=Float.toString(r4_2.get(Quale));
                break;
            case "4_3":
                Ritorno=Float.toString(r4_3.get(Quale));
                break;
            case "4_4":
                Ritorno=Float.toString(r4_4.get(Quale));
                break;

            default:
                Ritorno=Float.toString(rAltro.get(Quale));
                break;
        }

        if (jolly) {
            if (!Ritorno.isEmpty()) {
                Float r = Float.parseFloat(Ritorno);
                r *= 2;
                Ritorno = Float.toString(r);
            }
        }

        if (fr) {
            if (!Ritorno.isEmpty()) {
                Float r = Float.parseFloat(Ritorno);
                r *= 2;
                Ritorno = Float.toString(r);
            }
        }

        return "Quota: "+Ritorno;
    }

    public String RitornaStatisticheSquadraCasa(int Quale) {
        String Ritorno="";

        Ritorno="Pos. "+Integer.toString(posSquadraCasa.get(Quale));
        Ritorno+=" Pt. "+Integer.toString(punSquadraCasa.get(Quale));
        Ritorno+=" V.: "+Integer.toString(vinSquadraCasa.get(Quale));
        Ritorno+=" N.: "+Integer.toString(parSquadraCasa.get(Quale));
        Ritorno+=" P.: "+Integer.toString(perSquadraCasa.get(Quale));
        Ritorno+=" GF: "+Integer.toString(gfaSquadraCasa.get(Quale));
        Ritorno+=" GS: "+Integer.toString(gsuSquadraCasa.get(Quale));

        return Ritorno;
    }

    public String RitornaStatisticheSquadraFuori(int Quale) {
        String Ritorno="";

        Ritorno="Pos. "+Integer.toString(posSquadraFuori.get(Quale));
        Ritorno+=" Pt. "+Integer.toString(punSquadraFuori.get(Quale));
        Ritorno+=" V.: "+Integer.toString(vinSquadraFuori.get(Quale));
        Ritorno+=" N.: "+Integer.toString(parSquadraFuori.get(Quale));
        Ritorno+=" P.: "+Integer.toString(perSquadraFuori.get(Quale));
        Ritorno+=" GF: "+Integer.toString(gfaSquadraFuori.get(Quale));
        Ritorno+=" GS: "+Integer.toString(gsuSquadraFuori.get(Quale));

        return Ritorno;
    }

    public void AggiungePosSquadraCasa(int Posizione) {
        this.posSquadraCasa.add(Posizione);
    }
    public void AggiungePunSquadraCasa(int Punti) {
        this.punSquadraCasa.add(Punti);
    }
    public void AggiungevinSquadraCasa(int Valore) {
        this.vinSquadraCasa.add(Valore);
    }
    public void AggiungeparSquadraCasa(int Valore) {
        this.parSquadraCasa.add(Valore);
    }
    public void AggiungeperSquadraCasa(int Valore) {
        this.perSquadraCasa.add(Valore);
    }
    public void AggiungegfaSquadraCasa(int Valore) {
        this.gfaSquadraCasa.add(Valore);
    }
    public void AggiungegsuSquadraCasa(int Valore) {
        this.gsuSquadraCasa.add(Valore);
    }

    public void AggiungePosSquadraFuori(int Posizione) {
        this.posSquadraFuori.add(Posizione);
    }
    public void AggiungePunSquadraFuori(int Punti) {
        this.punSquadraFuori.add(Punti);
    }
    public void AggiungevinSquadraFuori(int Valore) {
        this.vinSquadraFuori.add(Valore);
    }
    public void AggiungeparSquadraFuori(int Valore) {
        this.parSquadraFuori.add(Valore);
    }
    public void AggiungeperSquadraFuori(int Valore) {
        this.perSquadraFuori.add(Valore);
    }
    public void AggiungegfaSquadraFuori(int Valore) {
        this.gfaSquadraFuori.add(Valore);
    }
    public void AggiungegsuSquadraFuori(int Valore) {
        this.gsuSquadraFuori.add(Valore);
    }

    public void AggiungeR0_0(Float Valore) {
        this.r0_0.add(Valore);
    }
    public void AggiungeR0_1(Float Valore) {
        this.r0_1.add(Valore);
    }
    public void AggiungeR0_2(Float Valore) {
        this.r0_2.add(Valore);
    }
    public void AggiungeR0_3(Float Valore) {
        this.r0_3.add(Valore);
    }
    public void AggiungeR0_4(Float Valore) {
        this.r0_4.add(Valore);
    }

    public void AggiungeR1_0(Float Valore) {
        this.r1_0.add(Valore);
    }
    public void AggiungeR1_1(Float Valore) {
        this.r1_1.add(Valore);
    }
    public void AggiungeR1_2(Float Valore) {
        this.r1_2.add(Valore);
    }
    public void AggiungeR1_3(Float Valore) {
        this.r1_3.add(Valore);
    }
    public void AggiungeR1_4(Float Valore) {
        this.r1_4.add(Valore);
    }
    public void AggiungeR2_0(Float Valore) {
        this.r2_0.add(Valore);
    }
    public void AggiungeR2_1(Float Valore) {
        this.r2_1.add(Valore);
    }
    public void AggiungeR2_2(Float Valore) {
        this.r2_2.add(Valore);
    }
    public void AggiungeR2_3(Float Valore) {
        this.r2_3.add(Valore);
    }
    public void AggiungeR2_4(Float Valore) {
        this.r2_4.add(Valore);
    }

    public void AggiungeR3_0(Float Valore) {
        this.r3_0.add(Valore);
    }
    public void AggiungeR3_1(Float Valore) {
        this.r3_1.add(Valore);
    }
    public void AggiungeR3_2(Float Valore) {
        this.r3_2.add(Valore);
    }
    public void AggiungeR3_3(Float Valore) {
        this.r3_3.add(Valore);
    }
    public void AggiungeR3_4(Float Valore) {
        this.r3_4.add(Valore);
    }
    public void AggiungeR4_0(Float Valore) {
        this.r4_0.add(Valore);
    }
    public void AggiungeR4_1(Float Valore) {
        this.r4_1.add(Valore);
    }
    public void AggiungeR4_2(Float Valore) {
        this.r4_2.add(Valore);
    }
    public void AggiungeR4_3(Float Valore) {
        this.r4_3.add(Valore);
    }
    public void AggiungeR4_4(Float Valore) {
        this.r4_4.add(Valore);
    }
    public void AggiungeRAltro(Float Valore) {
        this.rAltro.add(Valore);
    }
    public void AggiungeR1(Float Valore) {
        this.r1.add(Valore);
    }
    public void AggiungeRX(Float Valore) {
        this.rX.add(Valore);
    }
    public void AggiungeR2(Float Valore) {
        this.r2.add(Valore);
    }
    public void AggiungeR1X(Float Valore) {
        this.r1X.add(Valore);
    }
    public void AggiungeR12(Float Valore) {
        this.r12.add(Valore);
    }
    public void AggiungeRX2(Float Valore) {
        this.rX2.add(Valore);
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

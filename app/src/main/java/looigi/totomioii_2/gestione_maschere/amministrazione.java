package looigi.totomioii_2.gestione_maschere;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class amministrazione extends Activity {
    //-------- Singleton ----------//
    private static amministrazione instance = null;

    private amministrazione() {
    }

    public static amministrazione getInstance() {
        if (instance == null) {
            instance = new amministrazione();
        }

        return instance;
    }

    private Context context;

    public void VisualizzaSchermata(final Context context) {
        this.context = context;

        int statoConcorso = StrutturaDatiDiGioco.getInstance().getStatoConcorso();
        if (statoConcorso == 1) {
            VariabiliStatiche.getInstance().getCmdChiudeConcorso().setEnabled(true);
            VariabiliStatiche.getInstance().getCmdChiudeConcorso().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DBRemoto dbr = new DBRemoto();
                    dbr.ChiudeConcorso(context);
                }
            });

            VariabiliStatiche.getInstance().getCmdAvvisaSoft().setEnabled(true);
            VariabiliStatiche.getInstance().getCmdAvvisaSoft().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String Messaggio = "";
                    String Altro="";
                    if (StrutturaDatiDiGioco.getInstance().isSpeciale()) {
                        Altro="Speciale";
                    }
                    Messaggio+= ApreTesto() + "E' in essere il concorso " + Altro + "TotoMIO numero " + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) + ".<br />";
                    Messaggio+="Chi non ha compilato la schedina e' pregato di effettuare l'operazione prima della scadenza del concorso (" + StrutturaDatiDiGioco.getInstance().getDataChiusura().toString() + ") per evitare spiacevoli problemi di 'tappaggio'.";
                    DBRemoto dbr = new DBRemoto();
                    dbr.Avvisa(context, Messaggio);
                }
            });

            VariabiliStatiche.getInstance().getCmdAvvisaHard().setEnabled(true);
            VariabiliStatiche.getInstance().getCmdAvvisaHard().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String Messaggio = "";
                    String Altro="";
                    if (StrutturaDatiDiGioco.getInstance().isSpeciale()) {
                        Altro="Speciale";
                    }
                    Messaggio += ApreTesto() + "Si ricorda che è in essere il concorso " + Altro + "TotoMIO numero " + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) + " ed è vicina la data di chiusura.<br />";
                    Messaggio += "Chi non ha compilato la schedina e' pregato di effettuare l'operazione prima della scadenza del concorso (" + StrutturaDatiDiGioco.getInstance().getDataChiusura().toString() + ") per evitare spiacevoli problemi di 'tappaggio'." + ChiudeTesto() + "<br />";
                    Messaggio += "<br />" + ApreTestoGrande() + "Si ricorda che anche una eventuale riapertura del concorso e una successiva compilazione della colonna propria, NON toglierà eventuali tappi immessi in modo automatico dopo la chiusura." + ChiudeTesto() + "<br /><br />";
                    DBRemoto dbr = new DBRemoto();
                    dbr.Avvisa(context, Messaggio);
                }
            });

            VariabiliStatiche.getInstance().getCmdAvvisaLast().setEnabled(true);
            VariabiliStatiche.getInstance().getCmdAvvisaLast().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String Messaggio = "";
                    String Altro="";
                    if (StrutturaDatiDiGioco.getInstance().isSpeciale()) {
                        Altro="Speciale";
                    }
                    Messaggio = ApreTesto() + "Fra pochi minuti verrà chiuso il concorso " + Altro + "TotoMIO numero " + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) + ".<br />";
                    Messaggio += "Chi non ha compilato la schedina e' pregato di effettuare l'operazione prima della scadenza del concorso (" + StrutturaDatiDiGioco.getInstance().getDataChiusura().toString() + ") per evitare spiacevoli problemi di 'tappaggio'." + ChiudeTesto() + "<br />";
                    Messaggio += "<br />" + ApreTestoGrande() + "Si ricorda che anche una eventuale riapertura del concorso e una successiva compilazione della colonna propria, NON toglierà eventuali tappi immessi in modo automatico dopo la chiusura." + ChiudeTesto() + "<br /><br />";
                    DBRemoto dbr = new DBRemoto();
                    dbr.Avvisa(context, Messaggio);
                }
            });
        } else {
            VariabiliStatiche.getInstance().getCmdChiudeConcorso().setEnabled(false);
            VariabiliStatiche.getInstance().getCmdAvvisaHard().setEnabled(false);
            VariabiliStatiche.getInstance().getCmdAvvisaLast().setEnabled(false);
            VariabiliStatiche.getInstance().getCmdAvvisaSoft().setEnabled(false);
        }
    }

    private String ChiudeTesto() {
        return "</span>";
    }

    private String ApreTestoGrande() {
        return "<span style=\"font-family:Tahoma; font-size: 16px; color: #AA0000; font-weight:bold;\">";
    }

    private String ApreTesto() {
        return "<span style=\"font-family:Tahoma; font-size: 13px; color: #000000; font-weight:bold;\">";
    }
}

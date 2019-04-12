package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaSuddenDeath;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class dettaglio_concorso {
    //-------- Singleton ----------//
    private static dettaglio_concorso instance = null;

    private dettaglio_concorso() {
    }

    public static dettaglio_concorso getInstance() {
        if (instance == null) {
            instance = new dettaglio_concorso();
        }

        return instance;
    }

    private Context context;

    public void VisualizzaSD(Context context) {
        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaSuddenDeath(context);

        //String Appoggio="LOOIGI;GENOA;§DDTUNITED;TORINO;§TITU;OLBIA;§GIGINHO72;FIORENTINA;§GHOSTDOG;VICENZA;§ERICO;SASSUOLO;§OTTIMISTA;ATALANTA;§PIEROGIAN;FANO ALMA J.;§RONALDO11;CHIEVO;§MATTEO;FERMANA;§TRICOLINO;BOLOGNA;§";
        //VisualizzaSuddenDeath(Appoggio);
    }

    public void VisualizzaSuddenDeath(String Appoggio) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Appoggio.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaSuddenDeath arrayAdapter = new AdapterClassificaSuddenDeath(
                context,
                R.layout.lst_sd,
                lista);

        VariabiliStatiche.getInstance().getLvSD().setAdapter(arrayAdapter);
    }
}

package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterMessaggi;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class messaggi {
    //-------- Singleton ----------//
    private static messaggi instance = null;

    private messaggi() {
    }

    public static messaggi getInstance() {
        if (instance == null) {
            instance = new messaggi();
        }

        return instance;
    }

    public void RitornaMessaggi(Context context) {
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaMessaggi(context);
    }

    public void VisualizzaMessaggi(Context context, String Appoggio) {
        List<String> listaDaLeggere = new ArrayList<>();
        List<String> listaLetti = new ArrayList<>();
        String Campi[]=Appoggio.split("§");

        for (String riga : Campi) {
            String c[]=riga.split(";");
            if (c[4].equals("N")) {
                listaDaLeggere.add(riga);
            } else {
                listaLetti.add(riga);
            }
        }

        AdapterMessaggi arrayAdapter1 = new AdapterMessaggi(
                context,
                R.layout.lst_messaggi,
                listaLetti);

        AdapterMessaggi arrayAdapter2 = new AdapterMessaggi(
                context,
                R.layout.lst_messaggi,
                listaDaLeggere);

        VariabiliStatiche.getInstance().getLstLetti().setAdapter(arrayAdapter1);
        VariabiliStatiche.getInstance().getLstDaLeggere().setAdapter(arrayAdapter2);
    }
}

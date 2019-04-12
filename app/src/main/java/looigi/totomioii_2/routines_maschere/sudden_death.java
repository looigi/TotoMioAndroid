package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaSuddenDeathEsclusi;
import looigi.totomioii_2.adapter.AdapterClassificaSuddenDeathInGioco;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class sudden_death {
    //-------- Singleton ----------//
    private static sudden_death instance = null;

    private sudden_death() {
    }

    public static sudden_death getInstance() {
        if (instance == null) {
            instance = new sudden_death();
        }

        return instance;
    }

    public void VisualizzaClassifica(Context context) {
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaClassificaSuddenDeath(context);
    }

    public void VisualizzaClassificaSuddenDeath(Context context, String Appoggio) {
        List<String> listaEsclusi = new ArrayList<>();
        List<String> listaInGioco = new ArrayList<>();
        String Campi[]=Appoggio.split("§");

        for (String riga : Campi) {
            if (riga.contains("Esclusi")) {
                listaEsclusi.add(riga.replace("Esclusi;",""));
            } else {
                String c[]=riga.split(";");
                String p=c[4];
                if (p.length()==1) p="0"+p;
                listaInGioco.add(p+";"+c[1]+";"+c[2]+";"+c[3]+";"+c[4]+";");
            }
        }
        Collections.sort(listaEsclusi ,Collections.reverseOrder());
        Collections.sort(listaInGioco ,Collections.reverseOrder());

        AdapterClassificaSuddenDeathInGioco arrayAdapter1 = new AdapterClassificaSuddenDeathInGioco(
                context,
                R.layout.lst_sudden_death,
                listaInGioco);

        AdapterClassificaSuddenDeathEsclusi arrayAdapter2 = new AdapterClassificaSuddenDeathEsclusi(
                context,
                R.layout.lst_sudden_death,
                listaEsclusi);

        VariabiliStatiche.getInstance().getLvInGioco().setAdapter(arrayAdapter1);
        VariabiliStatiche.getInstance().getLvEsclusi().setAdapter(arrayAdapter2);
    }
}

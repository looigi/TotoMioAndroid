package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaRisultati;
import looigi.totomioii_2.adapter.AdapterClassificaSpeciale;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class classifica_speciale {
    //-------- Singleton ----------//
    private static classifica_speciale instance = null;
    private Context context;

    private classifica_speciale() {
    }

    public static classifica_speciale getInstance() {
        if (instance == null) {
            instance = new classifica_speciale();
        }

        return instance;
    }

    public void VisualizzaClassifica(Context context) {
        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaClassificaSpeciale(context);
    }

    public void VisualizzaClassificaSpeciale(Context context, String Classifica) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaSpeciale arrayAdapter = new AdapterClassificaSpeciale(
                context,
                R.layout.lst_classifica,
                lista);

        VariabiliStatiche.getInstance().getLstSpeciale().setAdapter(arrayAdapter);
    }
}

package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaRisultati;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class classifica_risultati {
    //-------- Singleton ----------//
    private static classifica_risultati instance = null;

    private classifica_risultati() {
    }

    public static classifica_risultati getInstance() {
        if (instance == null) {
            instance = new classifica_risultati();
        }

        return instance;
    }

    private Context context;

    public void VisualizzaClassifica(Context context) {
        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaClassificaRisultati(context);
    }

    public void VisualizzaClassificaRisultati(Context context, String Classifica) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaRisultati arrayAdapter = new AdapterClassificaRisultati(
                context,
                R.layout.lst_classifica,
                lista);

        VariabiliStatiche.getInstance().getLvClassificaRisultati().setAdapter(arrayAdapter);
    }
}

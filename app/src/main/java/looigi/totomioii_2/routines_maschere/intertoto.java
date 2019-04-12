package looigi.totomioii_2.routines_maschere;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterCoppaItalia;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class intertoto {
    //-------- Singleton ----------//
    private static intertoto instance = null;
    private List<String> Partite;
    private int turnoAttuale;
    private int turnoMinimo=3;

    private intertoto() {
    }

    public static intertoto getInstance() {
        if (instance == null) {
            instance = new intertoto();
        }

        return instance;
    }

    private Context context;

    public void RichiamaIntertoto(Context context) {
        VariabiliStatiche.getInstance().getBtnIndietro().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (turnoAttuale>turnoMinimo) {
                    turnoAttuale-=2;
                    VisualizzaValori(turnoAttuale);
                }
            }
        });

        VariabiliStatiche.getInstance().getBtnAvanti().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (turnoAttuale<9) {
                    turnoAttuale+=2;
                    VisualizzaValori(turnoAttuale);
                }
            }
        });

        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaIntertoto(context);
    }

    public void VisualizzaIntertoto(String Classifica) {
        Partite = new ArrayList<>();

        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            Partite.add(riga);
        }

        Partite=Utility.getInstance().SistemaAndataRitorno(Partite);

        turnoAttuale=turnoMinimo;
        VisualizzaValori(turnoAttuale);
    }

    private void VisualizzaValori(int Turno) {
        List<String> lista = new ArrayList<>();
        String t="";

        lista=Partite;
        VariabiliStatiche.getInstance().getTxtTurno().setText(t);

        if (lista!=null && lista.size()>0) {
            AdapterCoppaItalia arrayAdapter = new AdapterCoppaItalia(
                    context,
                    R.layout.lst_intertoto,
                    lista);

            VariabiliStatiche.getInstance().getLstIntertoto().setAdapter(arrayAdapter);
        } else {
            VariabiliStatiche.getInstance().getLstIntertoto().setAdapter(null);
        }
    }

}

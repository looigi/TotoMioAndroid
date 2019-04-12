package looigi.totomioii_2.routines_maschere;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaCampionato;
import looigi.totomioii_2.adapter.AdapterCoppaItalia;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class coppa_italia {
    //-------- Singleton ----------//
    private static coppa_italia instance = null;
    private List<String> Ottavi;
    private List<String> Quarti;
    private List<String> Semifinale;
    private List<String> Finale;
    private int turnoAttuale;
    private int turnoMinimo=3;

    private coppa_italia() {
    }

    public static coppa_italia getInstance() {
        if (instance == null) {
            instance = new coppa_italia();
        }

        return instance;
    }

    private Context context;

    public void RichiamaCoppaItalia(Context context) {
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
        dbr.RitornaCoppaItalia(context);
    }

    public void VisualizzaCoppaItalia(String Classifica) {
        Ottavi = new ArrayList<>();
        Quarti = new ArrayList<>();
        Semifinale = new ArrayList<>();
        Finale = new ArrayList<>();

        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            String c[]=riga.split(";");

            switch (c[0]) {
                case "3":
                case "4":
                    Ottavi.add(riga);
                    break;
                case "5":
                case "6":
                    Quarti.add(riga);
                    break;
                case "7":
                case "8":
                    Semifinale.add(riga);
                    break;
                case "9":
                case "10":
                    Finale.add(riga);
                    break;
            }
        }

        Ottavi=Utility.getInstance().SistemaAndataRitorno(Ottavi);
        Quarti=Utility.getInstance().SistemaAndataRitorno(Quarti);
        Semifinale=Utility.getInstance().SistemaAndataRitorno(Semifinale);
        Finale=Utility.getInstance().SistemaAndataRitorno(Finale);

        turnoAttuale=turnoMinimo;
        VisualizzaValori(turnoAttuale);
    }

    private void VisualizzaValori(int Turno) {
        List<String> lista = new ArrayList<>();
        String t="";

        switch (Turno) {
            case 3:
                lista=Ottavi;
                t="Ottavi";
                break;
            case 5:
                lista=Quarti;
                t="Quarti";
                break;
            case 7:
                lista=Semifinale;
                t="Semifinali";
                break;
            case 9:
                lista=Finale;
                t="Finale";
                break;
        }
        VariabiliStatiche.getInstance().getTxtTurno().setText(t);

        if (lista!=null && lista.size()>0) {
            AdapterCoppaItalia arrayAdapter = new AdapterCoppaItalia(
                    context,
                    R.layout.lst_coppaitalia,
                    lista);

            VariabiliStatiche.getInstance().getLstCoppaItalia().setAdapter(arrayAdapter);
        } else {
            VariabiliStatiche.getInstance().getLstCoppaItalia().setAdapter(null);
        }
    }

}

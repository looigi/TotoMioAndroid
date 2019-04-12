package looigi.totomioii_2.routines_maschere;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterEuropaLeague;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class europa_league {
    //-------- Singleton ----------//
    private static europa_league instance = null;
    private List<String> FaseGironi;
    private List<String> Quarti;
    private List<String> Semifinale;
    private List<String> Finale;
    private int turnoAttuale;
    private int turnoMinimo=7;
    private int partitaAttuale;
    private int partitaMinima=1;
    private int partitaMassima=-1;
    private String Modalita;

    private europa_league() {
    }

    public static europa_league getInstance() {
        if (instance == null) {
            instance = new europa_league();
        }

        return instance;
    }

    private Context context;

    public void RichiamaEuropaLeague(Context context) {
        Modalita="Gironi";

        VariabiliStatiche.getInstance().getBtnFaseGironi().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Modalita="Gironi";
                VisualizzaValori(partitaAttuale);
            }
        });

        VariabiliStatiche.getInstance().getBtnFaseFinale().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Modalita="FaseFinale";
                VisualizzaValori(turnoAttuale);
            }
        });

        VariabiliStatiche.getInstance().getBtnIndietro().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Modalita.equals("Gironi")) {
                    if (turnoAttuale > turnoMinimo) {
                        turnoAttuale -= 2;
                        VisualizzaValori(turnoAttuale);
                    }
                } else {
                    if (partitaAttuale > partitaMinima) {
                        partitaAttuale --;
                        VisualizzaValori(partitaAttuale);
                    }
                }
            }
        });

        VariabiliStatiche.getInstance().getBtnAvanti().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!Modalita.equals("Gironi")) {
                    if (turnoAttuale < 11) {
                        turnoAttuale += 2;
                        VisualizzaValori(turnoAttuale);
                    }
                } else {
                    if (partitaAttuale < partitaMassima) {
                        partitaAttuale ++;
                        VisualizzaValori(partitaAttuale);
                    }
                }
            }
        });

        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaEuropaLeague(context);
    }

    public void VisualizzaEuropaLeague(String Classifica) {
        FaseGironi = new ArrayList<>();
        Quarti = new ArrayList<>();
        Semifinale = new ArrayList<>();
        Finale = new ArrayList<>();

        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            String c[]=riga.split(";");

            if (Integer.parseInt(c[0])>100) {
                int p = Integer.parseInt(c[0]);
                p-=100;
                if (partitaMassima<p) {
                    partitaMassima = p;
                }

                FaseGironi.add(riga);
            } else {
                switch (c[0]) {
                    case "7":
                    case "8":
                        Quarti.add(riga);
                        break;
                    case "9":
                    case "10":
                        Semifinale.add(riga);
                        break;
                    case "11":
                    case "12":
                        Finale.add(riga);
                        break;
                }
            }
        }

        Quarti=Utility.getInstance().SistemaAndataRitorno(Quarti);
        Semifinale=Utility.getInstance().SistemaAndataRitorno(Semifinale);
        Finale=Utility.getInstance().SistemaAndataRitorno(Finale);

        turnoAttuale=turnoMinimo;
        partitaAttuale=partitaMinima;

        if (Modalita.equals("Gironi")) {
            VisualizzaValori(partitaAttuale);
        } else {
            VisualizzaValori(turnoAttuale);
        }
    }

    private void VisualizzaValori(int Turno) {
        List<String> lista = new ArrayList<>();

        VariabiliStatiche.getInstance().getTxtTurno().setText("");

        if (Modalita.equals("Gironi")) {
            VariabiliStatiche.getInstance().getLayGironi().setVisibility(LinearLayout.VISIBLE);
            VariabiliStatiche.getInstance().getLayFinale().setVisibility(LinearLayout.GONE);

            VariabiliStatiche.getInstance().getTxtTurno().setText("Partita: "+Turno);

            String t = Integer.toString(Turno+100);

            if (FaseGironi!=null && FaseGironi.size()>0) {
                for (String s : FaseGironi) {
                    String c[] = s.split(";");
                    if (c[0].equals(t)) {
                        lista.add(s);
                    }
                }

                AdapterEuropaLeague arrayAdapter = new AdapterEuropaLeague(
                        context,
                        R.layout.lst_europaleague,
                        lista);

                VariabiliStatiche.getInstance().getLstEuropaLeagueGironi().setAdapter(arrayAdapter);
            } else {
                VariabiliStatiche.getInstance().getLstEuropaLeague().setAdapter(null);
            }
        } else {
            VariabiliStatiche.getInstance().getLayGironi().setVisibility(LinearLayout.GONE);
            VariabiliStatiche.getInstance().getLayFinale().setVisibility(LinearLayout.VISIBLE);

            String t = "";

            switch (Turno) {
                case 7:
                    lista = Quarti;
                    t = "Quarti";
                    break;
                case 9:
                    lista = Semifinale;
                    t = "Semifinali";
                    break;
                case 11:
                    lista = Finale;
                    t = "Finale";
                    break;
            }
            VariabiliStatiche.getInstance().getTxtTurno().setText(t);

            if (lista!=null && lista.size()>0) {
                AdapterEuropaLeague arrayAdapter = new AdapterEuropaLeague(
                        context,
                        R.layout.lst_europaleague,
                        lista);

                VariabiliStatiche.getInstance().getLstEuropaLeague().setAdapter(arrayAdapter);
            } else {
                VariabiliStatiche.getInstance().getLstEuropaLeague().setAdapter(null);
            }
        }
    }

}

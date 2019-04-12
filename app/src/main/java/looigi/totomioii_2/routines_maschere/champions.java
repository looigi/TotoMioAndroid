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

public class champions {
    //-------- Singleton ----------//
    private static champions instance = null;
    private List<String> FaseGironiA;
    private List<String> FaseGironiB;
    private List<String> Semifinale;
    private List<String> Finale;
    private int turnoAttuale;
    private int turnoMinimo=3;
    private int partitaAttualeA;
    private int partitaMinimaA=1;
    private int partitaMassimaA=-1;
    private int partitaAttualeB;
    private int partitaMinimaB=1;
    private int partitaMassimaB=-1;
    private String Modalita;

    private champions() {
    }

    public static champions getInstance() {
        if (instance == null) {
            instance = new champions();
        }

        return instance;
    }

    private Context context;

    public void RichiamaChampions(Context context) {
        Modalita="GironeA";

        VariabiliStatiche.getInstance().getBtnFaseGironeA().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Modalita="GironeA";
                VisualizzaValori(partitaAttualeA);
            }
        });

        VariabiliStatiche.getInstance().getBtnFaseGironeB().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Modalita="GironeB";
                VisualizzaValori(partitaAttualeB);
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
                if (Modalita.equals("GironeA")) {
                    if (partitaAttualeA > partitaMinimaA) {
                        partitaAttualeA --;
                        VisualizzaValori(partitaAttualeA);
                    }
                } else {
                    if (Modalita.equals("GironeB")) {
                        if (partitaAttualeB > partitaMinimaB) {
                            partitaAttualeB --;
                            VisualizzaValori(partitaAttualeB);
                        }
                    } else {
                        if (turnoAttuale > turnoMinimo) {
                            turnoAttuale -= 2;
                            VisualizzaValori(turnoAttuale);
                        }
                    }
                }
            }
        });

        VariabiliStatiche.getInstance().getBtnAvanti().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Modalita.equals("GironeA")) {
                    if (partitaAttualeA < partitaMassimaA) {
                        partitaAttualeA ++;
                        VisualizzaValori(partitaAttualeA);
                    }
                } else {
                    if (Modalita.equals("GironeB")) {
                        if (partitaAttualeB < partitaMassimaB) {
                            partitaAttualeB ++;
                            VisualizzaValori(partitaAttualeB);
                        }
                    } else {
                        if (turnoAttuale < 5) {
                            turnoAttuale += 2;
                            VisualizzaValori(turnoAttuale);
                        }
                    }
                }
            }
        });

        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaChampionsLeague(context);
    }

    public void VisualizzaChampionsLeague(String Classifica) {
        FaseGironiA = new ArrayList<>();
        FaseGironiB = new ArrayList<>();
        Semifinale = new ArrayList<>();
        Finale = new ArrayList<>();

        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            String c[]=riga.split(";");

            if (Integer.parseInt(c[0])>100 && Integer.parseInt(c[0])<200) {
                int p = Integer.parseInt(c[0]);
                p-=100;
                if (partitaMassimaA<p) {
                    partitaMassimaA = p;
                }

                FaseGironiA.add(riga);
            } else {
                if (Integer.parseInt(c[0])>200) {
                    int p = Integer.parseInt(c[0]);
                    p -= 200;
                    if (partitaMassimaB < p) {
                        partitaMassimaB = p;
                    }

                    FaseGironiB.add(riga);
                } else {
                    switch (c[0]) {
                        case "3":
                        case "4":
                            Semifinale.add(riga);
                            break;
                        case "5":
                        case "6":
                            Finale.add(riga);
                            break;
                    }
                }
            }
        }

        Semifinale=Utility.getInstance().SistemaAndataRitorno(Semifinale);
        Finale=Utility.getInstance().SistemaAndataRitorno(Finale);

        turnoAttuale=turnoMinimo;
        partitaAttualeA=partitaMinimaA;
        partitaAttualeB=partitaMinimaB;

        if (Modalita.equals("GironeA")) {
            VisualizzaValori(partitaAttualeA);
        } else {
            if (Modalita.equals("GironeB")) {
                VisualizzaValori(partitaAttualeB);
            } else {
                VisualizzaValori(turnoAttuale);
            }
        }
    }

    private void VisualizzaValori(int Turno) {
        List<String> lista = new ArrayList<>();

        VariabiliStatiche.getInstance().getTxtTurno().setText("");

        if (Modalita.equals("GironeA")) {
            VariabiliStatiche.getInstance().getLayGironeA().setVisibility(LinearLayout.VISIBLE);
            VariabiliStatiche.getInstance().getLayGironeB().setVisibility(LinearLayout.GONE);
            VariabiliStatiche.getInstance().getLayFinale().setVisibility(LinearLayout.GONE);

            VariabiliStatiche.getInstance().getTxtTurno().setText("Gir. A - Partita: "+Turno);

            String t = Integer.toString(Turno+100);

            if (FaseGironiA!=null && FaseGironiA.size()>0) {
                for (String s : FaseGironiA) {
                    String c[] = s.split(";");
                    if (c[0].equals(t)) {
                        lista.add(s);
                    }
                }

                AdapterEuropaLeague arrayAdapter = new AdapterEuropaLeague(
                        context,
                        R.layout.lst_champions,
                        lista);

                VariabiliStatiche.getInstance().getLstChampionsGironeA().setAdapter(arrayAdapter);
            } else {
                VariabiliStatiche.getInstance().getLstChampions().setAdapter(null);
            }
        } else {
            if (Modalita.equals("GironeB")) {
                VariabiliStatiche.getInstance().getLayGironeA().setVisibility(LinearLayout.GONE);
                VariabiliStatiche.getInstance().getLayGironeB().setVisibility(LinearLayout.VISIBLE);
                VariabiliStatiche.getInstance().getLayFinale().setVisibility(LinearLayout.GONE);

                VariabiliStatiche.getInstance().getTxtTurno().setText("Gir. B - Partita: "+Turno);

                String t = Integer.toString(Turno+200);

                if (FaseGironiB!=null && FaseGironiB.size()>0) {
                    for (String s : FaseGironiB) {
                        String c[] = s.split(";");
                        if (c[0].equals(t)) {
                            lista.add(s);
                        }
                    }

                    AdapterEuropaLeague arrayAdapter = new AdapterEuropaLeague(
                            context,
                            R.layout.lst_champions,
                            lista);

                    VariabiliStatiche.getInstance().getLstChampionsGironeB().setAdapter(arrayAdapter);
                } else {
                    VariabiliStatiche.getInstance().getLstChampions().setAdapter(null);
                }
            } else {
                VariabiliStatiche.getInstance().getLayGironeA().setVisibility(LinearLayout.GONE);
                VariabiliStatiche.getInstance().getLayGironeB().setVisibility(LinearLayout.GONE);
                VariabiliStatiche.getInstance().getLayFinale().setVisibility(LinearLayout.VISIBLE);

                String t = "";

                switch (Turno) {
                    case 3:
                        lista = Semifinale;
                        t = "Semifinali";
                        break;
                    case 5:
                        lista = Finale;
                        t = "Finale";
                        break;
                }
                VariabiliStatiche.getInstance().getTxtTurno().setText(t);

                if (lista!=null && lista.size()>0) {
                    AdapterEuropaLeague arrayAdapter = new AdapterEuropaLeague(
                            context,
                            R.layout.lst_champions,
                            lista);

                    VariabiliStatiche.getInstance().getLstChampions().setAdapter(arrayAdapter);
                } else {
                    VariabiliStatiche.getInstance().getLstChampions().setAdapter(null);
                }
            }
        }
    }

}

package looigi.totomioii_2.gestione_maschere;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaPartite;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class colonna_propria {
    private boolean NonGestireRoutine;
    private Context context;

    public colonna_propria(Context context) {
        this.context = context;

        if (StrutturaDatiDiGioco.getInstance().getDataChiusura()!=null) {
            long diff = StrutturaDatiDiGioco.getInstance().getDataChiusura().getTime() - System.currentTimeMillis();
            if (diff < 0) {
                Utility.getInstance().VisualizzaPOPUP(context, "Orario di chiusura superato...", false, 0, false);
                VariabiliStatiche.getInstance().getCmdSalvaTutto().setEnabled(false);
            } else {
                VariabiliStatiche.getInstance().getCmdSalvaTutto().setEnabled(true);
            }

            VisualizzaDatiSchedina(0);
        } else  {
            VariabiliStatiche.getInstance().getCmdSalvaTutto().setEnabled(false);
        }
    }

    private void VisualizzaDatiSchedina(final int Quale) {
        VariabiliStatiche.getInstance().getCmdIndietro().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Quale>0) {
                    VisualizzaDatiSchedina(Quale - 1);
                }
            }
        });

        VariabiliStatiche.getInstance().getCmdAvanti().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Quale<13) {
                    VisualizzaDatiSchedina(Quale + 1);
                }
            }
        });

        VariabiliStatiche.getInstance().getCmdSalvaTutto().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ControllaEsattezza()) {
                    String Schedina="";

                    Schedina+= Integer.toString(StrutturaPartite.getInstance().getFilRouge())+"§";
                    for (int i=0;i<14;i++) {
                        Schedina+= Integer.toString(i)+";";
                        Schedina+=StrutturaPartite.getInstance().RitornaSegni(i)+";";
                        Schedina+=StrutturaPartite.getInstance().RitornaRisultato(i)+";§";
                    }

                    DBRemoto dbr=new DBRemoto();
                    dbr.SalvaDatiSchedina(context, Schedina);
                }
            }
        });

        VariabiliStatiche.getInstance().gettNumeroPartita().setText("Partita " + Integer.toString(Quale+1) + "/14");
        VariabiliStatiche.getInstance().getTxtDettaglio().setText("Doppie: " + Integer.toString(Utility.getInstance().ContaDoppie()));

        if (Quale+1==StrutturaPartite.getInstance().getPartitaJolly()) {
            VariabiliStatiche.getInstance().getImgJolly().setVisibility(LinearLayout.VISIBLE);
        } else {
            VariabiliStatiche.getInstance().getImgJolly().setVisibility(LinearLayout.GONE);
        }

        NonGestireRoutine=true;

        // Fil rouge
        if (Quale+1==StrutturaPartite.getInstance().getFilRouge()) {
            VariabiliStatiche.getInstance().getCheckFR().setChecked(true);
        } else {
            VariabiliStatiche.getInstance().getCheckFR().setChecked(false);
        }

        VariabiliStatiche.getInstance().getCheckFR().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked) {
                       StrutturaPartite.getInstance().setFilRouge(Quale + 1);
                   }

                   String risCasa=VariabiliStatiche.getInstance().gettRisCasa().getText().toString();
                   String risFuori=VariabiliStatiche.getInstance().gettRisFuori().getText().toString();

                   Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
                   Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);
                   VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));

                   String Segno="";

                   if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("1")) {
                       Segno+="1";
                   }
                   if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("X")) {
                       Segno+="X";
                   }
                   if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("2")) {
                       Segno+="2";
                   }
                   VariabiliStatiche.getInstance().getTxtQuoteSegno().setText(StrutturaPartite.getInstance().RitornaQuoteSegno(Quale, Segno,fr ,jolly));
               }
           }
        );
        // Fil rouge

        VariabiliStatiche.getInstance().getTxtCasa().setText(StrutturaPartite.getInstance().RitornaCasa(Quale));
        VariabiliStatiche.getInstance().getTxtFuori().setText(StrutturaPartite.getInstance().RitornaFuori(Quale));

        // Segni
        String Segno="";
        if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("1")) {
            VariabiliStatiche.getInstance().getCheck1().setChecked(true);
            Segno+="1";
        } else {
            VariabiliStatiche.getInstance().getCheck1().setChecked(false);
        }

        if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("X")) {
            VariabiliStatiche.getInstance().getCheckX().setChecked(true);
            Segno+="X";
        } else {
            VariabiliStatiche.getInstance().getCheckX().setChecked(false);
        }

        if (StrutturaPartite.getInstance().RitornaSegni(Quale).contains("2")) {
            VariabiliStatiche.getInstance().getCheck2().setChecked(true);
            Segno+="2";
        } else {
            VariabiliStatiche.getInstance().getCheck2().setChecked(false);
        }

        NonGestireRoutine=false;

        VariabiliStatiche.getInstance().getCheck1().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   AggiornaSegni(Quale, 1);
               }
           }
        );

        VariabiliStatiche.getInstance().getCheckX().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   AggiornaSegni(Quale, 2);
               }
           }
        );

        VariabiliStatiche.getInstance().getCheck2().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   AggiornaSegni(Quale, 3);
               }
           }
        );
        // Segni

        // Risultato
        String risCasa="";
        String risFuori="";

        if (StrutturaPartite.getInstance().RitornaRisultato(Quale).contains("-")) {
            String Campi[]=StrutturaPartite.getInstance().RitornaRisultato(Quale).split("-");

            VariabiliStatiche.getInstance().gettRisCasa().setText(Campi[0]);
            VariabiliStatiche.getInstance().gettRisFuori().setText(Campi[1]);

            risCasa=Campi[0];
            risFuori=Campi[1];
        } else {
            VariabiliStatiche.getInstance().gettRisCasa().setText("0");
            VariabiliStatiche.getInstance().gettRisFuori().setText("0");

            risCasa="0";
            risFuori="0";
        }

        VariabiliStatiche.getInstance().getTxtCasaDett().setText(StrutturaPartite.getInstance().RitornaCasa(Quale));
        VariabiliStatiche.getInstance().getTxtFuoriDett().setText(StrutturaPartite.getInstance().RitornaFuori(Quale));

        VariabiliStatiche.getInstance().getTxtCasaStat().setText(StrutturaPartite.getInstance().RitornaStatisticheSquadraCasa(Quale));
        VariabiliStatiche.getInstance().getTxtFuoriStat().setText(StrutturaPartite.getInstance().RitornaStatisticheSquadraFuori(Quale));

        VariabiliStatiche.getInstance().getCmdRisCasaMeno().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ris= Integer.parseInt(VariabiliStatiche.getInstance().gettRisCasa().getText().toString());
                if (ris>0) {
                    ris--;
                    VariabiliStatiche.getInstance().gettRisCasa().setText(Integer.toString(ris));
                    String sris=VariabiliStatiche.getInstance().gettRisCasa().getText()+"-"+VariabiliStatiche.getInstance().gettRisFuori().getText();
                    StrutturaPartite.getInstance().ModificaRisultato(Quale, sris);
                }

                String risCasa=VariabiliStatiche.getInstance().gettRisCasa().getText().toString();
                String risFuori=VariabiliStatiche.getInstance().gettRisFuori().getText().toString();

                Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
                Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);
                VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));
            }
        });

        VariabiliStatiche.getInstance().getCmdRisCasaPiu().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ris= Integer.parseInt(VariabiliStatiche.getInstance().gettRisCasa().getText().toString());
                ris++;
                VariabiliStatiche.getInstance().gettRisCasa().setText(Integer.toString(ris));
                String sris=VariabiliStatiche.getInstance().gettRisCasa().getText()+"-"+VariabiliStatiche.getInstance().gettRisFuori().getText();
                StrutturaPartite.getInstance().ModificaRisultato(Quale, sris);

                String risCasa=VariabiliStatiche.getInstance().gettRisCasa().getText().toString();
                String risFuori=VariabiliStatiche.getInstance().gettRisFuori().getText().toString();

                Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
                Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);

                VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));
            }
        });

        VariabiliStatiche.getInstance().getCmdRisFuoriMeno().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ris= Integer.parseInt(VariabiliStatiche.getInstance().gettRisFuori().getText().toString());
                if (ris>0) {
                    ris--;
                    VariabiliStatiche.getInstance().gettRisFuori().setText(Integer.toString(ris));
                    String sris=VariabiliStatiche.getInstance().gettRisCasa().getText()+"-"+VariabiliStatiche.getInstance().gettRisFuori().getText();
                    StrutturaPartite.getInstance().ModificaRisultato(Quale, sris);
                }

                String risCasa=VariabiliStatiche.getInstance().gettRisCasa().getText().toString();
                String risFuori=VariabiliStatiche.getInstance().gettRisFuori().getText().toString();

                Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
                Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);

                VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));
            }
        });

        VariabiliStatiche.getInstance().getCmdRisFuoriPiu().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ris= Integer.parseInt(VariabiliStatiche.getInstance().gettRisFuori().getText().toString());
                ris++;
                VariabiliStatiche.getInstance().gettRisFuori().setText(Integer.toString(ris));
                String sris=VariabiliStatiche.getInstance().gettRisCasa().getText()+"-"+VariabiliStatiche.getInstance().gettRisFuori().getText();
                StrutturaPartite.getInstance().ModificaRisultato(Quale, sris);

                String risCasa=VariabiliStatiche.getInstance().gettRisCasa().getText().toString();
                String risFuori=VariabiliStatiche.getInstance().gettRisFuori().getText().toString();

                Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
                Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);

                VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));
            }
        });
        // Risultato

        Utility.getInstance().ImpostaImmagineSquadra(context,
                StrutturaPartite.getInstance().RitornaCasa(Quale)+"_Tonda.jpg",
                VariabiliStatiche.getInstance().getImgCasa());

        Utility.getInstance().ImpostaImmagineSquadra(context,
                StrutturaPartite.getInstance().RitornaFuori(Quale)+"_Tonda.jpg",
                VariabiliStatiche.getInstance().getImgFuori());

        Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
        Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);

        VariabiliStatiche.getInstance().getTxtQuoteSegno().setText(StrutturaPartite.getInstance().RitornaQuoteSegno(Quale, Segno, fr, jolly));
        VariabiliStatiche.getInstance().getTxtQuoteRisultato().setText(StrutturaPartite.getInstance().RitornaQuoteRisultato(Quale, risCasa, risFuori, fr, jolly));
    }

    private boolean ControllaEsattezza() {
        boolean Ok=true;

        if (StrutturaPartite.getInstance().getFilRouge()==-1) {
            Utility.getInstance().VisualizzaPOPUP(context, "Selezionare un Fil Rouge", false, 0, false);
            Ok=false;
        } else {
            for (int i=0;i<14;i++) {
                if (StrutturaPartite.getInstance().RitornaSegni(i).isEmpty()) {
                    Utility.getInstance().VisualizzaPOPUP(context, "Selezionare almeno un segno per la partita "+ Integer.toString(i+1), false, 0, false);
                    Ok=false;
                    break;
                //} else {
                //    if (StrutturaPartite.getInstance().RitornaRisultato(i).isEmpty()) {
                //        Utility.getInstance().VisualizzaPOPUP(context, "Inserire un risultato per la partita "+ Integer.toString(i+1), false, 0, false);
                //        Ok=false;
                //        break;
                //    }
                }
            }
        }

        return Ok;
    }

    private void AggiornaSegni(int Quale, int Chiamante) {
        if (!NonGestireRoutine) {
            String Segni = "";

            if (VariabiliStatiche.getInstance().getCheck1().isChecked()) {
                Segni += "1";
            }
            if (VariabiliStatiche.getInstance().getCheckX().isChecked()) {
                Segni += "X";
            }
            if (VariabiliStatiche.getInstance().getCheck2().isChecked()) {
                Segni += "2";
            }

            boolean Ok = true;

            if (Segni.length() == 2) {
                int Doppie = Utility.getInstance().ContaDoppie();
                if (Doppie > 6) {
                    Utility.getInstance().VisualizzaPOPUP(context, "Troppe doppie: " + Doppie, false, 0, false);
                    Ok = false;
                }
            }

            if (Ok) {
                if (Segni.equals("1X2")) {
                    switch (Chiamante) {
                        case 1:
                            VariabiliStatiche.getInstance().getCheck1().setChecked(false);
                            break;
                        case 2:
                            VariabiliStatiche.getInstance().getCheckX().setChecked(false);
                            break;
                        case 3:
                            VariabiliStatiche.getInstance().getCheck2().setChecked(false);
                            break;
                    }
                    Utility.getInstance().VisualizzaPOPUP(context, "Troppi segni per la partita.\nTriple non ammesse", false, 0, false);
                } else {
                    if (Segni.isEmpty()) {
                        Utility.getInstance().VisualizzaPOPUP(context, "Inserire almeno un segno", false, 0, false);
                    } else {
                        StrutturaPartite.getInstance().ModificaSegni(Quale, Segni);
                    }
                }
            }

            Boolean fr=VariabiliStatiche.getInstance().getCheckFR().isChecked();
            Boolean jolly=(StrutturaPartite.getInstance().getPartitaJolly()==Quale-1);
            VariabiliStatiche.getInstance().getTxtQuoteSegno().setText(StrutturaPartite.getInstance().RitornaQuoteSegno(Quale, Segni, fr, jolly));
        }
    }
}

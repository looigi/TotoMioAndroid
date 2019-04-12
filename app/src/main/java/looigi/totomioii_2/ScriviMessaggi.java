package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.adapter.AdapterListaGiocatori;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ScriviMessaggi extends AppCompatActivity {
    private Context context;
    private Activity act;

    private static List<String> listaGiocatori;
    private static List<String> listaDestinatari;
    private static int Progressivo;
    private static String Mittente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrivi_messaggi);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context=this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity) context).getWindow().getDecorView(), this);

        Bundle b = getIntent().getExtras();
        String messaggio="";
        String sMittente="";
        int iProgressivo=-1;
        if(b != null) {
            messaggio = b.getString("messaggio");
            iProgressivo = b.getInt("progressivo");
            sMittente = b.getString("mittente");
        }

        Progressivo=iProgressivo;
        Mittente=sMittente;

        final ListView lvGiocatori = findViewById(R.id.lstGiocatori);
        final ListView lvDestinatari = findViewById(R.id.lstDestinatari);

        if (Progressivo!=-1) {
            lvGiocatori.setEnabled(false);
            lvDestinatari.setEnabled(false);
        }

        lvGiocatori.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Nome=listaGiocatori.get(position);

                listaDestinatari.add(Nome);
                listaGiocatori.remove(position);

                AggiornaListe(context);
            }
        });
        lvDestinatari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Nome=listaDestinatari.get(position);

                listaGiocatori.add(Nome);
                listaDestinatari.remove(position);

                AggiornaListe(context);
            }
        });

        VariabiliStatiche.getInstance().setLvGiocatori(lvGiocatori);
        VariabiliStatiche.getInstance().setLvDestinatari(lvDestinatari);

        listaGiocatori = new ArrayList<>();
        listaDestinatari = new ArrayList<>();

        ImageView i = findViewById(R.id.imgInviaMessaggio);
        i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText tM = findViewById(R.id.txtMessaggio);
                if (tM.getText().toString().trim().isEmpty()) {
                    Utility.getInstance().VisualizzaPOPUP(context, "Digitare un messaggio", false, 0, false);
                } else {
                    if (listaDestinatari.size()==0) {
                        Utility.getInstance().VisualizzaPOPUP(context, "Selezionare almeno un destinatario", false, 0, false);
                    } else {
                        String dest="";

                        for (String d : listaDestinatari) {
                            dest += d.substring(d.indexOf("-")+1,d.length()) +";";
                        }

                        VariabiliStatiche.getInstance().setActPassaggio(act);

                        DBRemoto dbr= new DBRemoto();
                        dbr.ScriviMessaggio(context, dest, tM.getText().toString());
                    }
                }
            }
        });

        ImpostaSchermata(context);
    }

    public void ImpostaSchermata(Context context) {
        DBRemoto d = new DBRemoto();
        d.RitornaTuttiIGiocatori(context);
    }

    private static void AggiornaListe(Context context) {
        AdapterListaGiocatori arrayAdapter1 = new AdapterListaGiocatori(
                context,
                R.layout.lst_mancanti,
                listaGiocatori);

        VariabiliStatiche.getInstance().getLvGiocatori().setAdapter(arrayAdapter1);

        AdapterListaGiocatori arrayAdapter2 = new AdapterListaGiocatori(
                context,
                R.layout.lst_mancanti,
                listaDestinatari);

        VariabiliStatiche.getInstance().getLvGiocatori().setAdapter(arrayAdapter1);
        VariabiliStatiche.getInstance().getLvDestinatari().setAdapter(arrayAdapter2);
    }

    public static void RiempieListaGiocatori(Context context, String Giocatori) {
        String Campi[]=Giocatori.split(";");

        for (String riga : Campi) {
            if (!riga.toUpperCase().contains(StrutturaDatiGiocatore.getInstance().getNick().toUpperCase().trim())) {
                listaGiocatori.add(riga);
            }
        }

        if (Progressivo!=-1) {
            for (int i=0; i<listaGiocatori.size(); i++) {
                if (listaGiocatori.get(i).toUpperCase().contains(Mittente.toUpperCase())) {
                    listaDestinatari.add(listaGiocatori.get(i));
                    listaGiocatori.remove(i);
                    break;
                }
            }
        }

        AggiornaListe(context);
    }
}

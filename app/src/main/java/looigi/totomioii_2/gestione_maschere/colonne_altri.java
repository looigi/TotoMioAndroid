package looigi.totomioii_2.gestione_maschere;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterColonna;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaAltriGiocatori;
import looigi.totomioii_2.variabili.StrutturaPartiteAltri;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class colonne_altri extends Activity {
    //-------- Singleton ----------//
    private static colonne_altri instance = null;

    private colonne_altri() {
    }

    public static colonne_altri getInstance() {
        if (instance == null) {
            instance = new colonne_altri();
        }

        return instance;
    }

    private Context context;
    private ArrayAdapter<String> adapter;
    private String NomeGiocatoreSelezionato;
    private String idGiocatoreSelezionato;

    public void CaricaValori(Context context, String Chi) {
        this.context=context;

        NomeGiocatoreSelezionato=Chi;

        RiempieDropDownGiocatori();
    }

    public void RiempieCampiGiocatore(String DatiGioc) {
        if (!DatiGioc.isEmpty()) {
            String Campi[] = DatiGioc.split(";");

            VariabiliStatiche.getInstance().getTxtColonneUser().setText(Campi[1].toUpperCase());
            if (Campi.length > 2) {
                VariabiliStatiche.getInstance().getTxtColonneMotto().setText(Campi[2].replace(":", ""));
            } else {
                VariabiliStatiche.getInstance().getTxtColonneMotto().setText("");
            }
            Utility.getInstance().ImpostaImmagineGiocatore(context,
                    Campi[1] + ".jpg",
                    VariabiliStatiche.getInstance().getImgColonneUser()
            );

            DBRemoto dbr = new DBRemoto();
            dbr.RitornaDatiSchedinaAltri(context, Campi[0]);
        }
    }

    private void RiempieDropDownGiocatori() {
        if (adapter==null) {
            DBRemoto dbr=new DBRemoto();
            dbr.RitornaGiocatori(context);
        } else {
            RiempieCampiDiBase();
        }
    }

    private void RiempieCampiDiBase() {
        String DatiGioc=StrutturaAltriGiocatori.getInstance().RitornaGiocatore(NomeGiocatoreSelezionato);
        if (!DatiGioc.isEmpty()) {
            RiempieCampiGiocatore(DatiGioc);
        } else {
            DBRemoto dbr=new DBRemoto();
            dbr.RitornaDatiGiocatoreAltri(context, idGiocatoreSelezionato);
        }
    }

    public void RiempieDropDownGiocatoriDopoLettura(String[] items) {
        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item,
                items);
        VariabiliStatiche.getInstance().getSpnGiocatori().setAdapter(adapter);
        VariabiliStatiche.getInstance().getSpnGiocatori().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String Gioc=(String) parent.getItemAtPosition(position);
                String Campi[]=Gioc.split("-");
                if (!Campi[1].toUpperCase().equals(NomeGiocatoreSelezionato.toUpperCase())) {
                    idGiocatoreSelezionato = Campi[0];
                    NomeGiocatoreSelezionato = Campi[1];
                    RiempieCampiDiBase();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        RiempieCampiDiBase();
    }

    public void RiempieLista() {
        List<String> lista = new ArrayList<>();

        int Jolly= StrutturaPartiteAltri.getInstance().getPartitaJolly();
        int FilRouge=StrutturaPartiteAltri.getInstance().getFilRouge();

        for (int i=0; i<14; i++) {
            String Partita= Integer.toString(i);
            String Casa=StrutturaPartiteAltri.getInstance().RitornaCasa(i);
            String Fuori=StrutturaPartiteAltri.getInstance().RitornaFuori(i);
            String Segno=StrutturaPartiteAltri.getInstance().RitornaSegni(i);
            String Risultato=StrutturaPartiteAltri.getInstance().RitornaRisultato(i);
            String J="0";
            if (i+1==Jolly) J="1";
            String F="0";
            if (i+1==FilRouge) F="1";
            String Riga=Partita+";"+Casa+";"+Fuori+";"+Segno+";"+Risultato+";"+J+";"+F+";";

            lista.add(Riga);
        }

        AdapterColonna arrayAdapter = new AdapterColonna (
            context,
            R.layout.lst_colonna,
            lista);

        VariabiliStatiche.getInstance().getLvColonnaPropria().setAdapter(arrayAdapter);
    }
}

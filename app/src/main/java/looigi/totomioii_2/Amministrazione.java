package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.adapter.AdapterMancanti;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.gestione_maschere.amministrazione;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Amministrazione extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amministrazione);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        Button b1 = (Button) findViewById(R.id.cmdChiudeConcorso);
        Button b2 = (Button) findViewById(R.id.cmdAvvisaSoft);
        Button b3 = (Button) findViewById(R.id.cmdAvvisaHard);
        Button b4 = (Button) findViewById(R.id.cmdAvvisaLast);
        ListView lv = findViewById(R.id.lstMancanti);

        VariabiliStatiche.getInstance().setCmdChiudeConcorso(b1);
        VariabiliStatiche.getInstance().setCmdAvvisaSoft(b2);
        VariabiliStatiche.getInstance().setCmdAvvisaHard(b3);
        VariabiliStatiche.getInstance().setCmdAvvisaLast(b4);
        VariabiliStatiche.getInstance().setLstMancanti(lv);

        CaricaMancanti(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void CaricaMancanti(Context context) {
        //String Appoggio="LOOIGI;Credi che chi c’ha loro sia ‘n signore. L’oro pe’ me nun conta conta er core;§";
        //ImpostaSchermata(context, Appoggio);
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaMancanti(context);
    }

    public static void ImpostaSchermata(Context context, String Ritorno) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Ritorno.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterMancanti arrayAdapter = new AdapterMancanti(
                context,
                R.layout.lst_mancanti,
                lista);

        VariabiliStatiche.getInstance().getLstMancanti().setAdapter(arrayAdapter);

        amministrazione.getInstance().VisualizzaSchermata(context);
    }
}

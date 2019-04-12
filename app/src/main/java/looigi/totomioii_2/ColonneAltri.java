package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import looigi.totomioii_2.gestione_maschere.colonne_altri;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ColonneAltri extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colonna_altri);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        ListView lv = (ListView) findViewById(R.id.lstColonnaPropria);

        Spinner dropdown = (Spinner) findViewById(R.id.spnGiocatori);
        TextView txtGiocatore=(TextView) findViewById(R.id.txtGiocatore);
        TextView txtMottoGioc=(TextView) findViewById(R.id.txtMottoGioc);
        ImageView imgGioc=(ImageView) findViewById(R.id.imgGiocatore);

        VariabiliStatiche.getInstance().setLvColonnaPropria(lv);
        VariabiliStatiche.getInstance().setSpnGiocatori(dropdown);
        VariabiliStatiche.getInstance().setTxtColonneUser(txtGiocatore);
        VariabiliStatiche.getInstance().setTxtColonneMotto(txtMottoGioc);
        VariabiliStatiche.getInstance().setImgColonneUser(imgGioc);

        colonne_altri.getInstance().CaricaValori(context, StrutturaDatiGiocatore.getInstance().getNick());
    }
}

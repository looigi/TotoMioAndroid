package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.classifica_campionato;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ClassificaCampionato extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifica_campionato);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCC = (ListView) findViewById(R.id.lstCampionato);

        ImpostaSchermata(context, lvCC);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context, ListView lvCC) {
        VariabiliStatiche.getInstance().setLvClassificaCampionato(lvCC);

        classifica_campionato.getInstance().VisualizzaClassifica(context);
    }
}

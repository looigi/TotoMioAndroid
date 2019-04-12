package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.classifica_risultati;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ClassificaRisultati extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifica_risultati);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCR = (ListView) findViewById(R.id.lstRisultati);

        ImpostaSchermata(context, lvCR);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context, ListView lvCR) {
        VariabiliStatiche.getInstance().setLvClassificaRisultati(lvCR);

        classifica_risultati.getInstance().VisualizzaClassifica(context);
    }
}

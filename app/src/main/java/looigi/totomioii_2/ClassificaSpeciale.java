package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.classifica_speciale;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ClassificaSpeciale extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifica_speciale);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCR = (ListView) findViewById(R.id.lstSpeciale);

        VariabiliStatiche.getInstance().setLstSpeciale(lvCR);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        classifica_speciale.getInstance().VisualizzaClassifica(context);
    }
}

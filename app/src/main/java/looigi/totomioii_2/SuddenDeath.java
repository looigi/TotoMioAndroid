package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.sudden_death;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class SuddenDeath extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudden_death);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        ListView lvInGioco=findViewById(R.id.lstInGioco);
        ListView lvEsclusi=findViewById(R.id.lstEsclusi);

        VariabiliStatiche.getInstance().setLvInGioco(lvInGioco);
        VariabiliStatiche.getInstance().setLvEsclusi(lvEsclusi);

        sudden_death.getInstance().VisualizzaClassifica(context);
    }
}

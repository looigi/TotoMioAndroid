package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.dettaglio_concorso;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class DettaglioConcorso extends Activity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dettaglio_concorso);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(final Context context) {
        ListView lvCC = (ListView) findViewById(R.id.lstSD);
        VariabiliStatiche.getInstance().setLvSD(lvCC);

        dettaglio_concorso.getInstance().VisualizzaSD(context);
    }
}

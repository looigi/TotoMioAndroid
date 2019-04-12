package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import looigi.totomioii_2.routines_maschere.champions;
import looigi.totomioii_2.routines_maschere.europa_league;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Champions extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champions);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCC = findViewById(R.id.lstChampions);
        ListView lvCCA = findViewById(R.id.lstChampionsA);
        ListView lvCCB = findViewById(R.id.lstChampionsB);

        Button btnIndietro=findViewById(R.id.btnIndietro);
        Button btnAvanti=findViewById(R.id.btnAvanti);
        Button btnFaseFinale=findViewById(R.id.btnFinale);
        Button btnGironeA=findViewById(R.id.btnGironeA);
        Button btnGironeB=findViewById(R.id.btnGironeB);
        TextView txtTurno=findViewById(R.id.txtTurno);
        LinearLayout layGironeA=findViewById(R.id.layChampionsA);
        LinearLayout layGironeB=findViewById(R.id.layChampionsB);
        LinearLayout layFinale=findViewById(R.id.layFaseFinale);

        VariabiliStatiche.getInstance().setBtnAvanti(btnAvanti);
        VariabiliStatiche.getInstance().setBtnIndietro(btnIndietro);
        VariabiliStatiche.getInstance().setTxtTurno(txtTurno);
        VariabiliStatiche.getInstance().setBtnFaseFinale(btnFaseFinale);
        VariabiliStatiche.getInstance().setBtnFaseGironeA(btnGironeA);
        VariabiliStatiche.getInstance().setBtnFaseGironeB(btnGironeB);
        VariabiliStatiche.getInstance().setLstChampions(lvCC);
        VariabiliStatiche.getInstance().setLstChampionsGironeA(lvCCA);
        VariabiliStatiche.getInstance().setLstChampionsGironeB(lvCCB);
        VariabiliStatiche.getInstance().setLayGironeA(layGironeA);
        VariabiliStatiche.getInstance().setLayGironeB(layGironeB);
        VariabiliStatiche.getInstance().setLayFinale(layFinale);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        champions.getInstance().RichiamaChampions(context);
    }
}

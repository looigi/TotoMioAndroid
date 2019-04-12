package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import looigi.totomioii_2.routines_maschere.coppa_italia;
import looigi.totomioii_2.routines_maschere.europa_league;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class EuropaLeague extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.europa_league);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCC = findViewById(R.id.lstEuropaLeague);
        ListView lvCCG = findViewById(R.id.lstEuropaLeagueGironi);

        Button btnIndietro=findViewById(R.id.btnIndietro);
        Button btnAvanti=findViewById(R.id.btnAvanti);
        Button btnFaseFinale=findViewById(R.id.btnFinale);
        Button btnGironi=findViewById(R.id.btnGironi);
        TextView txtTurno=findViewById(R.id.txtTurno);
        LinearLayout layGironi=findViewById(R.id.layGironi);
        LinearLayout layFinale=findViewById(R.id.layFaseFinale);

        VariabiliStatiche.getInstance().setBtnAvanti(btnAvanti);
        VariabiliStatiche.getInstance().setBtnIndietro(btnIndietro);
        VariabiliStatiche.getInstance().setTxtTurno(txtTurno);
        VariabiliStatiche.getInstance().setBtnFaseFinale(btnFaseFinale);
        VariabiliStatiche.getInstance().setBtnFaseGironi(btnGironi);
        VariabiliStatiche.getInstance().setLstEuropaLeague(lvCC);
        VariabiliStatiche.getInstance().setLstEuropaLeagueGironi(lvCCG);
        VariabiliStatiche.getInstance().setLayGironi(layGironi);
        VariabiliStatiche.getInstance().setLayFinale(layFinale);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        europa_league.getInstance().RichiamaEuropaLeague(context);
    }
}

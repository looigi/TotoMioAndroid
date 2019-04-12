package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import looigi.totomioii_2.routines_maschere.coppa_italia;
import looigi.totomioii_2.routines_maschere.intertoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Intertoto extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intertoto);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ListView lvCC = (ListView) findViewById(R.id.lstIntertoto);

        Button btnIndietro=findViewById(R.id.btnIndietro);
        Button btnAvanti=findViewById(R.id.btnAvanti);
        TextView txtTurno=findViewById(R.id.txtTurno);

        VariabiliStatiche.getInstance().setBtnAvanti(btnAvanti);
        VariabiliStatiche.getInstance().setBtnIndietro(btnIndietro);
        VariabiliStatiche.getInstance().setTxtTurno(txtTurno);

        VariabiliStatiche.getInstance().setLstIntertoto(lvCC);

        ImpostaSchermata(context, lvCC);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context, ListView lvCC) {
        intertoto.getInstance().RichiamaIntertoto(context);
    }
}

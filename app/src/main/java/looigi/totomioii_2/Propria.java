package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import looigi.totomioii_2.gestione_maschere.colonna_propria;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Propria extends AppCompatActivity {
    private Context context;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colonna_propria);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }
    
    public void ImpostaSchermata(Context context) {
        Button cmdIndietro=(Button) findViewById(R.id.cmdIndietro);
        Button cmdAvanti=(Button) findViewById(R.id.cmdAvanti);
        TextView tNumeroPartita=(TextView) findViewById(R.id.txtNumeroPartita);
        TextView txtCasa=(TextView) findViewById(R.id.txtCasa);
        TextView txtFuori=(TextView) findViewById(R.id.txtFuori);
        ImageView imgCasa=(ImageView) findViewById(R.id.imgCasa);
        ImageView imgFuori=(ImageView) findViewById(R.id.imgFuori);
        CheckBox check1=(CheckBox) findViewById(R.id.check1);
        CheckBox checkX=(CheckBox) findViewById(R.id.checkX);
        CheckBox check2=(CheckBox) findViewById(R.id.check2);
        TextView txtDettaglio=(TextView) findViewById(R.id.txtDettaglio);
        ImageView imgJolly=(ImageView) findViewById(R.id.imgJolly);
        CheckBox checkFR=(CheckBox) findViewById(R.id.checkFR);
        Button cmdSalvaTutto=(Button) findViewById(R.id.cmdSalvaTutto);

        Button cmdRisCasaMeno=(Button) findViewById(R.id.cmdRisCasaMeno);
        Button cmdRisCasaPiu=(Button) findViewById(R.id.cmdRisCasaPiu);
        Button cmdRisFuoriMeno=(Button) findViewById(R.id.cmdRisFuoriMeno);
        Button cmdRisFuoriPiu=(Button) findViewById(R.id.cmdRisFuoriPiu);
        TextView txtRisCasa=(TextView) findViewById(R.id.txtRisCasa);
        TextView txtRisFuori=(TextView) findViewById(R.id.txtRisFuori);

        TextView txtCasaDett=(TextView) findViewById(R.id.txtCasaDett);
        TextView txtCasaStat=(TextView) findViewById(R.id.txtCasaStat);
        TextView txtFuoriDett=(TextView) findViewById(R.id.txtFuoriDett);
        TextView txtFuoriStat=(TextView) findViewById(R.id.txtFuoriStat);

        TextView txtQuoteSegno=(TextView) findViewById(R.id.txtQuoteSegno);
        TextView txtQuoteRisultato=(TextView) findViewById(R.id.txtQuoteRisultato);

        VariabiliStatiche.getInstance().setCmdIndietro(cmdIndietro);
        VariabiliStatiche.getInstance().setCmdAvanti(cmdAvanti);
        VariabiliStatiche.getInstance().settNumeroPartita(tNumeroPartita);
        VariabiliStatiche.getInstance().setTxtCasa(txtCasa);
        VariabiliStatiche.getInstance().setTxtFuori(txtFuori);
        VariabiliStatiche.getInstance().setImgCasa(imgCasa);
        VariabiliStatiche.getInstance().setImgFuori(imgFuori);
        VariabiliStatiche.getInstance().setCheck1(check1);
        VariabiliStatiche.getInstance().setCheckX(checkX);
        VariabiliStatiche.getInstance().setCheck2(check2);
        VariabiliStatiche.getInstance().setTxtDettaglio(txtDettaglio);
        VariabiliStatiche.getInstance().setCheckFR(checkFR);
        VariabiliStatiche.getInstance().setImgJolly(imgJolly);
        VariabiliStatiche.getInstance().setCmdSalvaTutto(cmdSalvaTutto);
        VariabiliStatiche.getInstance().setCmdRisCasaMeno(cmdRisCasaMeno);
        VariabiliStatiche.getInstance().setCmdRisCasaPiu(cmdRisCasaPiu);
        VariabiliStatiche.getInstance().setCmdRisFuoriMeno(cmdRisFuoriMeno);
        VariabiliStatiche.getInstance().setCmdRisFuoriPiu(cmdRisFuoriPiu);
        VariabiliStatiche.getInstance().settRisCasa(txtRisCasa);
        VariabiliStatiche.getInstance().settRisFuori(txtRisFuori);

        VariabiliStatiche.getInstance().setTxtCasaDett(txtCasaDett);
        VariabiliStatiche.getInstance().setTxtCasaStat(txtCasaStat);
        VariabiliStatiche.getInstance().setTxtFuoriDett(txtFuoriDett);
        VariabiliStatiche.getInstance().setTxtFuoriStat(txtFuoriStat);

        VariabiliStatiche.getInstance().setTxtQuoteSegno(txtQuoteSegno);
        VariabiliStatiche.getInstance().setTxtQuoteRisultato(txtQuoteRisultato);

        new colonna_propria(context);
    }
}

package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Classifiche extends AppCompatActivity {
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifiche);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(final Context context) {
        Button btnGenerale = findViewById(R.id.btnGenerale);
        btnGenerale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, ClassificaGenerale.class);
                startActivity(intent);
            }
        });

        Button btnRisultati = findViewById(R.id.btnRisultati);
        btnRisultati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, ClassificaRisultati.class);
                startActivity(intent);
            }
        });

        Button btnCampionato = findViewById(R.id.btnCampionato);
        btnCampionato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, ClassificaCampionato.class);
                startActivity(intent);
            }
        });

        Button btnSD = findViewById(R.id.btnSD);
        btnSD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, SuddenDeath.class);
                startActivity(intent);
            }
        });

        Button btnCS = findViewById(R.id.btnCS);
        btnCS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, ClassificaSpeciale.class);
                startActivity(intent);
            }
        });
    }
}

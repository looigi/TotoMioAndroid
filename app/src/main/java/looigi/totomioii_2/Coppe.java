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

public class Coppe extends AppCompatActivity {
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coppe);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(final Context context) {
        Button btnCoppaItalia = findViewById(R.id.btnCoppaItalia);
        btnCoppaItalia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, CoppaItalia.class);
                startActivity(intent);
            }
        });

        Button btnEuropaLeague = findViewById(R.id.btnEuropaLeague);
        btnEuropaLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, EuropaLeague.class);
                startActivity(intent);
            }
        });

        Button btnChampions = findViewById(R.id.btnChampions);
        btnChampions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, Champions.class);
                startActivity(intent);
            }
        });

        Button btnIntertoto = findViewById(R.id.btnInterToto);
        btnIntertoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(act);

                Intent intent = new Intent(context, Intertoto.class);
                startActivity(intent);
            }
        });
    }
}

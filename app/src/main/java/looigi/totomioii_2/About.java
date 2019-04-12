package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class About extends AppCompatActivity {
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity) context).getWindow().getDecorView(), this);

        TextView t = findViewById(R.id.txtVersione);
        t.setText("Versione "+Utility.getInstance().getVersion(context));

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }
}

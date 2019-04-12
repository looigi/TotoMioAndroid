package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import looigi.totomioii_2.routines_maschere.messaggi;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Messaggi extends AppCompatActivity {
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messaggi);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context=this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity) context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        ImageView btnScrivi=findViewById(R.id.imgScriviMessaggio);
        btnScrivi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                act.finish();
                Intent intent = new Intent(context, ScriviMessaggi.class);
                context.startActivity(intent);
            }
        });

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }

    public void ImpostaSchermata(Context context) {
        ListView lvLetti=findViewById(R.id.lstLetti);
        ListView lvDaLeggere=findViewById(R.id.lstDaLeggere);

        VariabiliStatiche.getInstance().setLstLetti(lvLetti);
        VariabiliStatiche.getInstance().setLstDaLeggere(lvDaLeggere);

        messaggi.getInstance().RitornaMessaggi(context);
    }
}

package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import looigi.totomioii_2.gestione_maschere.resoconto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Resoconto extends AppCompatActivity {
    private Context context;
    private static Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resoconto);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;
        act=this;

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity)context).getWindow().getDecorView(), this);

        ImpostaSchermata(context);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }


    public void ImpostaSchermata(Context context) {
        WebView webview = (WebView) findViewById(R.id.webViewResoconto);
        webview.getSettings().setJavaScriptEnabled(true);
        VariabiliStatiche.getInstance().setWebViewResoconto(webview);

        resoconto.getInstance().VisualizzaSchermata(context);
    }
}

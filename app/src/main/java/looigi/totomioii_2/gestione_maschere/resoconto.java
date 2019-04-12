package looigi.totomioii_2.gestione_maschere;

import android.app.Activity;
import android.content.Context;

import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class resoconto extends Activity {
    //-------- Singleton ----------//
    private static resoconto instance = null;

    private resoconto() {
    }

    public static resoconto getInstance() {
        if (instance == null) {
            instance = new resoconto();
        }

        return instance;
    }

    private Context context;

    public void VisualizzaSchermata(Context context) {
        this.context=context;

        String Indirizzo=VariabiliStatiche.RadiceSito +"/InvioMail/"+
                StrutturaDatiDiGioco.getInstance().getAnno()+"/"+
                StrutturaDatiDiGioco.getInstance().getGiornata()+".htm";

        if (Utility.getInstance().doesURLExist(Indirizzo)) {
            VariabiliStatiche.getInstance().getWebViewResoconto().loadUrl(Indirizzo);
        }
    }
}

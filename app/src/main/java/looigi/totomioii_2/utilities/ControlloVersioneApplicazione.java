package looigi.totomioii_2.utilities;

import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ControlloVersioneApplicazione {
    private static String verAttuale = "";

    public void ControllaVersione() {
        verAttuale = Utility.getInstance().getVersion(VariabiliStatiche.getInstance().getMainContext());

        DBRemoto dbr = new DBRemoto();
        dbr.RitornaVersioneApplicazione(VariabiliStatiche.getInstance().getMainContext(),"");
    }

    public static void ControlloVersione(String NuovaVersione) {
        if (!NuovaVersione.equals(verAttuale) && !NuovaVersione.isEmpty()) {
            String path = VariabiliStatiche.RadiceWS+"/NuoveVersioni/TotoMIO.apk";

            UpdateApp atualizaApp = new UpdateApp();
            atualizaApp.setContext(VariabiliStatiche.getInstance().getMainContext());
            atualizaApp.execute(path);
        } else {
            MainActivity.PrendeDatiStatistici(VariabiliStatiche.getInstance().getMainContext());
        }
    }
}

package looigi.totomioii_2.gestione_maschere;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class loginUtente {
	//-------- Singleton ----------//
	private static loginUtente instance = null;

	private loginUtente() {
	}

	public static loginUtente getInstance() {
		if (instance == null) {
			instance = new loginUtente();
		}

		return instance;
	}

	private String InizialiMaschera="LU_";
	private static Context context;
	
	//private static TextView layErrore;
	public static String Password1;
	//private static EditText txtNick;
	//private String Nick;
		
	//private static RitornoDaGestioneWS rws;

	/* @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {    
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			return false;
		}    
		
		return super.onKeyDown(keyCode, event);
	} */

	public void SalvaDati() {
		StrutturaDatiGiocatore.getInstance().setNick(VariabiliStatiche.getInstance().getTxtNick().getText().toString().trim());

		DBRemoto dbr=new DBRemoto();
		dbr.RitornaIdUtenteLogin(context, StrutturaDatiGiocatore.getInstance().getNick());
	}

	public void ErroreVisualizzato(boolean Acceso, String Messaggio) {
		if (!Acceso) {
			VariabiliStatiche.getInstance().getLayErrore().setVisibility(LinearLayout.GONE);
			VariabiliStatiche.getInstance().getLayErrore().setText("");
		} else {
			VariabiliStatiche.getInstance().getLayErrore().setVisibility(LinearLayout.VISIBLE);
			VariabiliStatiche.getInstance().getLayErrore().setText(Messaggio);
		}
	}

	public void ControllaDati(Context context, EditText txtNick, EditText txtPassword1) {
		boolean Ritorno=true;

		ErroreVisualizzato(false, "");
		
		//EditText txtNick=(EditText) findViewById(R.id.txtNickLogin);
		String Nick=txtNick.getText().toString().trim();

		if (Nick.length()==0) {
			ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "NickNonInserito"));
			Ritorno=false;
		}
		if (Nick.length()>50) {
			ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "NickTroppoLungo"));
			Ritorno=false;
		}
		
		Password1=txtPassword1.getText().toString().trim();
		if (Ritorno) {
			if (Password1.length()==0) {
				ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "PassNonInserita"));
				Ritorno=false;
			}
			if (Password1.length()>50) {
				ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "PassTroppoLunga"));
				Ritorno=false;
			}
		}
		
		DBRemoto dbr =new DBRemoto();
		dbr.RitornaPassword(context, Nick);
	}
}

package looigi.totomioii_2.db;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import looigi.totomioii_2.soap.GestioneWEBServiceSOAP;
import looigi.totomioii_2.soap.HttpFileUpload;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class DBRemoto {
	private String Radice= VariabiliStatiche.RadiceWS +"/service1.asmx/";

	public void UploadFile(Context context, String PercorsoDIR, String nomeFile){
		try {
			FileInputStream fstrm = new FileInputStream(PercorsoDIR+"/"+nomeFile);
			HttpFileUpload hfu = new HttpFileUpload("http://looigi.no-ip.biz:12345/goTM/default.aspx", PercorsoDIR,
					Integer.toString(StrutturaDatiDiGioco.getInstance().getAnno()), StrutturaDatiGiocatore.getInstance().getNick()+".jpg", "Upload immagine");
			hfu.Send_Now(context, fstrm);
		} catch (FileNotFoundException ignored) {
		}
	}

    public void RitornaIdUtenteLogin(Context context, String Nick) {
		String Urletto=Radice + "RitornaIDUtente?Nick=" + Nick + "&Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaIdUtenteLogin");
    }

    public void RitornaPassword(Context context, String Nick) {
    	String Urletto=Radice;
		Urletto+= "RitornaPassword?Nick=" + Nick + "&Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();
		new GestioneWEBServiceSOAP(context,Urletto,"RitornaPassword");
    }

	public void RitornaDatiDiGioco(Context context) {
		String Urletto=Radice + "RitornaDatiDiGioco?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaDatiDiGioco");
	}

	public void RitornaDatiStatisticiGiocatore(Context context) {
        String Urletto=Radice + "RitornaDatiStatisticiGiocatore?id=" + StrutturaDatiGiocatore.getInstance().getIdUtente()
                + "&Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() + "&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata();

        new GestioneWEBServiceSOAP(context, Urletto, "RitornaDatiStatisticiGiocatore");
    }

	public void RitornaDatiGiocatoreAltri(Context context, String Chi) {
		String Urletto=Radice + "RitornaDatiGiocatoreAltri?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Chi=" + Chi;

		new GestioneWEBServiceSOAP(context, Urletto, "RitornaDatiGiocatoreAltri");
	}

	public void RitornaDatiSchedina(Context context) {
		String Urletto=Radice + "RitornaDatiSchedina?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno()+
				"&Giornata=" + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) +
				"&CodGiocatore="  + Integer.toString(StrutturaDatiGiocatore.getInstance().getIdUtente());
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaDatiSchedina");
	}

	public void RitornaDatiSchedinaAltri(Context context, String CodGiocatore) {
		String Urletto=Radice + "RitornaDatiSchedina?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno()+
				"&Giornata=" + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) +
				"&CodGiocatore="  + CodGiocatore;
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaDatiSchedinaAltri");
	}

	public void SalvaDatiSchedina(Context context, String Schedina) {
		String Urletto=Radice + "SalvaDatiSchedina?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno()+
				"&Giornata=" + Integer.toString(StrutturaDatiDiGioco.getInstance().getGiornata()) +
				"&CodGiocatore="  + Integer.toString(StrutturaDatiGiocatore.getInstance().getIdUtente()) +
				"&Schedina=" + Schedina +
				"&Speciale=0";
		new GestioneWEBServiceSOAP(context,Urletto, "SalvaDatiSchedina");
	}

	public void RitornaGiocatori(Context context) {
		String Urletto=Radice + "RitornaGiocatori?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaGiocatori");
	}

	public void RitornaTuttiIGiocatori(Context context) {
		String Urletto=Radice + "RitornaGiocatori?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();
		new GestioneWEBServiceSOAP(context,Urletto, "RitornaTuttiIGiocatori");
	}

	public void RitornaClassificaGenerale(Context context) {
		String Urletto=Radice + "RitornaClassificaGenerale?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaClassificaGenerale");
	}

	public void RitornaClassificaRisultati(Context context) {
		String Urletto=Radice + "RitornaClassificaRisultati?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaClassificaRisultati");
	}

	public void RitornaClassificaCampionato(Context context) {
		String Urletto=Radice + "RitornaClassificaCampionato?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaClassificaCampionato");
	}

	public void RitornaSuddenDeath(Context context) {
		String Urletto=Radice + "RitornaSuddenDeath?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaSuddenDeath");
	}

	public void RitornaMessaggi(Context context) {
		String Urletto=Radice + "RitornaMessaggi?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaMessaggi");
	}

	public void MarcaMessaggioComeLetto(Context context, String Progressivo) {
		String Urletto=Radice + "RitornoMarcaMessaggioComeLetto?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente() +
				"&Progressivo=" + Progressivo;

		new GestioneWEBServiceSOAP(context,Urletto, "RitornoMarcaMessaggioComeLetto");
	}

	public void RitornaMancanti(Context context) {
		String Urletto=Radice + "RitornaMancanti?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaMancanti");
	}

	public void RitornaClassificaSuddenDeath(Context context) {
		String Urletto=Radice + "RitornaClassificaSuddenDeath?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaClassificaSuddenDeath");
	}

	public void RitornaClassificaSpeciale(Context context) {
		String Urletto=Radice + "RitornaClassificaSpeciale?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaClassificaSpeciale");
	}

	public void RitornaCoppaItalia(Context context) {
		String Urletto=Radice + "RitornaCoppaItalia?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaCoppaItalia");
	}

	public void RitornaIntertoto(Context context) {
		String Urletto=Radice + "RitornaInterToto?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaInterToto");
	}

	public void RitornaEuropaLeague(Context context) {
		String Urletto=Radice + "RitornaEuropaLeague?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaEuropaLeague");
	}

	public void RitornaChampionsLeague(Context context) {
		String Urletto=Radice + "RitornaChampionsLeague?Anno=" + StrutturaDatiDiGioco.getInstance().getAnno();

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaChampionsLeague");
	}

	public void EliminaMessaggio(Context context, String Progressivo) {
		String Urletto=Radice + "EliminaMessaggio?" +
				"Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&Giocatore="+ StrutturaDatiGiocatore.getInstance().getIdUtente() +
				"&Progressivo=" + Progressivo;

		new GestioneWEBServiceSOAP(context,Urletto, "EliminaMessaggio");
	}

	public void ChiudeConcorso(Context context) {
		String Urletto=Radice + "ChiudeConcorso?" +
				"Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +"&"+
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata() +
				"&Giocatore="+ StrutturaDatiGiocatore.getInstance().getNick();
		new GestioneWEBServiceSOAP(context,Urletto, "ChiudeConcorso");
	}

	public void Avvisa(Context context, String Messaggio) {
		String sMessaggio=Messaggio;
		sMessaggio=sMessaggio.replace("<","***MINORE***");
		sMessaggio=sMessaggio.replace(">","***MAGGIORE***");
		sMessaggio=sMessaggio.replace("/","***BARRA***");
		sMessaggio=sMessaggio.replace(" ","_");

		String Urletto=Radice + "Avvisa?" +
				"Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +"&"+
				"&Giornata=" + StrutturaDatiDiGioco.getInstance().getGiornata() +
				"&Giocatore="+ StrutturaDatiGiocatore.getInstance().getNick() +
				"&Messaggio="+sMessaggio;
		new GestioneWEBServiceSOAP(context,Urletto, "Avvisa");
	}

	public void SalvaDatiGiocatore(Context context, String Nick , String Motto, String Password, String EMail, String Cognome, String Nome) {
		String Urletto=Radice + "AggiornaProfiloGiocatore?" +
				"Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente() +
				"&Nick=" + Nick +
				"&Motto=" + Motto +
				"&Password=" + Password +
				"&EMail=" + EMail +
				"&Cognome=" + Cognome +
				"&Nome=" + Nome;

		new GestioneWEBServiceSOAP(context,Urletto, "SalvaDatiGiocatore");
	}

	public void ScriviMessaggio(Context context, String Destinatari, String Messaggio) {
		String Urletto=Radice + "InviaMessaggio?" +
				"Anno=" + StrutturaDatiDiGioco.getInstance().getAnno() +
				"&id=" + StrutturaDatiGiocatore.getInstance().getIdUtente() +
				"&Destinatari=" + Destinatari +
				"&Messaggio=" + Messaggio;

		new GestioneWEBServiceSOAP(context,Urletto, "ScriviMessaggio");
	}

	public void RitornaVersioneApplicazione(Context context, String Maschera) {
		String Urletto=Radice + "RitornaVersioneApplicazione";

		new GestioneWEBServiceSOAP(context,Urletto, "RitornaVersioneApplicazione");
	}
}

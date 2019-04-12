package looigi.totomioii_2.soap;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import looigi.totomioii_2.Amministrazione;
import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.Messaggi;
import looigi.totomioii_2.ScriviMessaggi;
import looigi.totomioii_2.db.DBLocale;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.gestione_maschere.colonne_altri;
import looigi.totomioii_2.gestione_maschere.loginUtente;
import looigi.totomioii_2.routines_maschere.champions;
import looigi.totomioii_2.routines_maschere.classifica_campionato;
import looigi.totomioii_2.routines_maschere.classifica_generale;
import looigi.totomioii_2.routines_maschere.classifica_risultati;
import looigi.totomioii_2.routines_maschere.classifica_speciale;
import looigi.totomioii_2.routines_maschere.coppa_italia;
import looigi.totomioii_2.routines_maschere.dettaglio_concorso;
import looigi.totomioii_2.routines_maschere.europa_league;
import looigi.totomioii_2.routines_maschere.intertoto;
import looigi.totomioii_2.routines_maschere.messaggi;
import looigi.totomioii_2.routines_maschere.sudden_death;
import looigi.totomioii_2.thread.MessageThread;
import looigi.totomioii_2.utilities.ControlloVersioneApplicazione;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.StrutturaPartite;
import looigi.totomioii_2.variabili.StrutturaPartiteAltri;
import looigi.totomioii_2.variabili.VariabiliStatiche;

//import looigi.totomioii_2.LoginUtente;

public class RitornoDaGestioneWS {
	public void RitornaVersioneApplicazione(String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			//DialogMessaggio.getInstance().show(VariabiliStaticheGlobali.getInstance().getContext(), Appoggio, true, "Cv Calcio");
		} else {
			ControlloVersioneApplicazione.ControlloVersione(Appoggio);
		}
	}

	public void RitornoUploadImmagine(Context context) {
		String PercorsoDIR = VariabiliStatiche.PercorsoDIR+"/Immagini";
		String NomeFile = StrutturaDatiGiocatore.getInstance().getNick()+".jpg";
		File daEliminare = new File(PercorsoDIR, NomeFile);
		if(daEliminare.exists())
		{
			daEliminare.delete();
		}

		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}

	private String ToglieTag(String Cosa) {
		return Cosa;
	}

	public void RitornoLetturaIdUtenteLogin(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);
		
		if (!Appoggio.toUpperCase().contains("ERROR:")) {
			if (!Appoggio.trim().isEmpty()) {
				Boolean Ok = true;

				if (!MainActivity.AccountPresente) {
					StrutturaDatiGiocatore.getInstance().setIdUtente(Integer.parseInt(Appoggio));

					String Datella = Utility.getInstance().PrendeDataAttuale();

					DBLocale dbl = new DBLocale();
					dbl.SalvaNuovoUtente(context, StrutturaDatiGiocatore.getInstance().getIdUtente(),
							StrutturaDatiGiocatore.getInstance().getNick(), Datella);

					MainActivity.SettaMascheraGiaLoggato();
				} else {
					loginUtente.getInstance().ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "LoginNonValido"));
				}
			} else {
				loginUtente.getInstance().ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "LoginNonValido"));
			}
		}
	}

	public void RitornoPassword(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			if (!loginUtente.Password1.equals(Appoggio)) {
				loginUtente.getInstance().ErroreVisualizzato(true, Utility.getInstance().ControllaLingua(context, "PasswordNonValida"));
			} else {
				loginUtente.getInstance().SalvaDati();
			}
		}
	}

	public void RitornoDatiDiGioco(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			String Campi[]=Ritorno.split(";");

			StrutturaDatiDiGioco.getInstance().setGiornata(Integer.parseInt(Campi[0]));
			StrutturaDatiDiGioco.getInstance().setStatoConcorso(Integer.parseInt(Campi[1]));
			StrutturaDatiDiGioco.getInstance().setGiornataIT(Integer.parseInt(Campi[2]));
			StrutturaDatiDiGioco.getInstance().setGiornataEL(Integer.parseInt(Campi[3]));
			StrutturaDatiDiGioco.getInstance().setGiornataCL(Integer.parseInt(Campi[4]));
			StrutturaDatiDiGioco.getInstance().setGiornataDer(Integer.parseInt(Campi[5]));
			StrutturaDatiDiGioco.getInstance().setGiornataCI(Integer.parseInt(Campi[6]));
			StrutturaDatiDiGioco.getInstance().setGiornataSpeciale(Integer.parseInt(Campi[7]));
			StrutturaDatiDiGioco.getInstance().setDescAnno(Campi[8]);

			Boolean Speciale=false;
			if (Integer.parseInt(Campi[0])>100) {
				Speciale=true;
			}
			StrutturaDatiDiGioco.getInstance().setSpeciale(Speciale);

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ITALY);
			String string = "";
			try {
				string = ""+Campi[9];
			} catch (Exception ignored) {

			}
			if (!string.isEmpty()) {
				try {
					Date date = format.parse(string);
					StrutturaDatiDiGioco.getInstance().setDataChiusura(date);
				} catch (ParseException ignored) {
				}
			}

			MainActivity.PrendeDatiGiocatore(context);
		}
	}

	public void RitornoDatiStatisticiGiocatore(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			String Campi[]=Appoggio.split(";");

			for (int i=2; i<15; i++) {
				if (Campi[i].isEmpty()) Campi[i] = "0";
			}

			StrutturaDatiGiocatore.getInstance().setTesto(Campi[0]);
			StrutturaDatiGiocatore.getInstance().setTipologia(Campi[1]);
			StrutturaDatiGiocatore.getInstance().setPosClassifica(Integer.parseInt(Campi[2]));
			StrutturaDatiGiocatore.getInstance().setPosCampionato(Integer.parseInt(Campi[3]));
			StrutturaDatiGiocatore.getInstance().setPosRisultati(Integer.parseInt(Campi[4]));
			StrutturaDatiGiocatore.getInstance().setTotVersamento(Float.parseFloat(Campi[5].replace(",",".")));
			StrutturaDatiGiocatore.getInstance().setVinti(Float.parseFloat(Campi[6].replace(",",".")));
			StrutturaDatiGiocatore.getInstance().setReali(Float.parseFloat(Campi[7].replace(",",".")));
			StrutturaDatiGiocatore.getInstance().setPresi(Float.parseFloat(Campi[8].replace(",",".")));
			StrutturaDatiGiocatore.getInstance().setAmici(Float.parseFloat(Campi[9].replace(",",".")));
			StrutturaDatiGiocatore.getInstance().setNumeroTappi(Integer.parseInt(Campi[10]));
			StrutturaDatiGiocatore.getInstance().setVittorie(Integer.parseInt(Campi[11]));
			StrutturaDatiGiocatore.getInstance().setUltimiPosti(Integer.parseInt(Campi[12]));
			StrutturaDatiGiocatore.getInstance().setSecondiPosti(Integer.parseInt(Campi[13]));
			StrutturaDatiGiocatore.getInstance().setMessaggi(Integer.parseInt(Campi[14]));
			StrutturaDatiGiocatore.getInstance().setPassword(Campi[15]);
			StrutturaDatiGiocatore.getInstance().setCognome(Campi[16]);
			StrutturaDatiGiocatore.getInstance().setNome(Campi[17]);
			StrutturaDatiGiocatore.getInstance().setEMail(Campi[18]);

			MainActivity.PrendeDatiSchedina(context);
		}
	}

	public void RitornaCoppaItalia(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			coppa_italia.getInstance().VisualizzaCoppaItalia(Ritorno);
		}
	}

	public void RitornaEuropaLeague(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			europa_league.getInstance().VisualizzaEuropaLeague(Ritorno);
		}
	}

	public void RitornaChampionsLeague(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			champions.getInstance().VisualizzaChampionsLeague(Ritorno);
		}
	}

	public void RitornoDatiGiocatoreAltri(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			loginUtente.getInstance().ErroreVisualizzato(true, Appoggio);
		} else {
			colonne_altri.getInstance().RiempieCampiGiocatore(Ritorno);
		}
	}

	public void RitornoSalvaDatiSchedina(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Colonna NON salvata:\n"+Appoggio, false, 0, false);
		} else {
			MessageThread.getInstance().setBloccaMessage(false);
			Utility.getInstance().VisualizzaPOPUP(context, "Colonna salvata", false, 0, false);
		}
	}

	public void RitornoDatiSchedina(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			String[] Campi=Appoggio.split("§");

			StrutturaPartite.getInstance().PulisceCampi();

			for (String Riga : Campi) {
				String[] Campi2=Riga.split(";");

				StrutturaPartite.getInstance().AggiungePartita(Integer.parseInt(Campi2[0]));
				StrutturaPartite.getInstance().AggiungeSegni(""+Campi2[1]);
				StrutturaPartite.getInstance().AggiungeRisultato(""+Campi2[2]);
				StrutturaPartite.getInstance().AggiungeCasa(Campi2[3]);
				StrutturaPartite.getInstance().AggiungeFuori(Campi2[4]);
				if (StrutturaPartite.getInstance().getPartitaJolly()==-1) {
					StrutturaPartite.getInstance().setPartitaJolly(Integer.parseInt(Campi2[5]));
				}
				if (StrutturaPartite.getInstance().getFilRouge()==-1) {
					if (!Campi2[6].isEmpty()) {
						StrutturaPartite.getInstance().setFilRouge(Integer.parseInt(Campi2[6]));
					}
				}

				StrutturaPartite.getInstance().AggiungePosSquadraCasa(Integer.parseInt(Campi2[7]));
				StrutturaPartite.getInstance().AggiungevinSquadraCasa(Integer.parseInt(Campi2[8]));
				StrutturaPartite.getInstance().AggiungeparSquadraCasa(Integer.parseInt(Campi2[9]));
				StrutturaPartite.getInstance().AggiungeperSquadraCasa(Integer.parseInt(Campi2[10]));
				StrutturaPartite.getInstance().AggiungegfaSquadraCasa(Integer.parseInt(Campi2[11]));
				StrutturaPartite.getInstance().AggiungegsuSquadraCasa(Integer.parseInt(Campi2[12]));
				StrutturaPartite.getInstance().AggiungePunSquadraCasa(Integer.parseInt(Campi2[13]));

				StrutturaPartite.getInstance().AggiungePosSquadraFuori(Integer.parseInt(Campi2[14]));
				StrutturaPartite.getInstance().AggiungevinSquadraFuori(Integer.parseInt(Campi2[15]));
				StrutturaPartite.getInstance().AggiungeparSquadraFuori(Integer.parseInt(Campi2[16]));
				StrutturaPartite.getInstance().AggiungeperSquadraFuori(Integer.parseInt(Campi2[17]));
				StrutturaPartite.getInstance().AggiungegfaSquadraFuori(Integer.parseInt(Campi2[18]));
				StrutturaPartite.getInstance().AggiungegsuSquadraFuori(Integer.parseInt(Campi2[19]));
				StrutturaPartite.getInstance().AggiungePunSquadraFuori(Integer.parseInt(Campi2[20]));

				StrutturaPartite.getInstance().AggiungeR0_0(Float.parseFloat(Campi2[21]));
				StrutturaPartite.getInstance().AggiungeR0_1(Float.parseFloat(Campi2[22]));
				StrutturaPartite.getInstance().AggiungeR0_2(Float.parseFloat(Campi2[23]));
				StrutturaPartite.getInstance().AggiungeR0_3(Float.parseFloat(Campi2[24]));
				StrutturaPartite.getInstance().AggiungeR0_4(Float.parseFloat(Campi2[25]));

				StrutturaPartite.getInstance().AggiungeR1_0(Float.parseFloat(Campi2[26]));
				StrutturaPartite.getInstance().AggiungeR1_1(Float.parseFloat(Campi2[27]));
				StrutturaPartite.getInstance().AggiungeR1_2(Float.parseFloat(Campi2[28]));
				StrutturaPartite.getInstance().AggiungeR1_3(Float.parseFloat(Campi2[29]));
				StrutturaPartite.getInstance().AggiungeR1_4(Float.parseFloat(Campi2[30]));

				StrutturaPartite.getInstance().AggiungeR2_0(Float.parseFloat(Campi2[31]));
				StrutturaPartite.getInstance().AggiungeR2_1(Float.parseFloat(Campi2[32]));
				StrutturaPartite.getInstance().AggiungeR2_2(Float.parseFloat(Campi2[33]));
				StrutturaPartite.getInstance().AggiungeR2_3(Float.parseFloat(Campi2[34]));
				StrutturaPartite.getInstance().AggiungeR2_4(Float.parseFloat(Campi2[35]));

				StrutturaPartite.getInstance().AggiungeR3_0(Float.parseFloat(Campi2[36]));
				StrutturaPartite.getInstance().AggiungeR3_1(Float.parseFloat(Campi2[37]));
				StrutturaPartite.getInstance().AggiungeR3_2(Float.parseFloat(Campi2[38]));
				StrutturaPartite.getInstance().AggiungeR3_3(Float.parseFloat(Campi2[39]));
				StrutturaPartite.getInstance().AggiungeR3_4(Float.parseFloat(Campi2[40]));

				StrutturaPartite.getInstance().AggiungeR4_0(Float.parseFloat(Campi2[41]));
				StrutturaPartite.getInstance().AggiungeR4_1(Float.parseFloat(Campi2[42]));
				StrutturaPartite.getInstance().AggiungeR4_2(Float.parseFloat(Campi2[43]));
				StrutturaPartite.getInstance().AggiungeR4_3(Float.parseFloat(Campi2[44]));
				StrutturaPartite.getInstance().AggiungeR4_4(Float.parseFloat(Campi2[45]));

				StrutturaPartite.getInstance().AggiungeRAltro(Float.parseFloat(Campi2[46]));

				StrutturaPartite.getInstance().AggiungeR1(Float.parseFloat(Campi2[47]));
				StrutturaPartite.getInstance().AggiungeRX(Float.parseFloat(Campi2[48]));
				StrutturaPartite.getInstance().AggiungeR2(Float.parseFloat(Campi2[49]));

				StrutturaPartite.getInstance().AggiungeR1X(Float.parseFloat(Campi2[50]));
				StrutturaPartite.getInstance().AggiungeR12(Float.parseFloat(Campi2[51]));
				StrutturaPartite.getInstance().AggiungeRX2(Float.parseFloat(Campi2[52]));
			}

			MainActivity.ScriveDatiStrutture();
		}
	}

	public void RitornoDatiSchedinaAltri(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			String[] Campi=Ritorno.split("§");

			StrutturaPartiteAltri.getInstance().PulisceCampi();

			for (String Riga : Campi) {
				String[] Campi2=Riga.split(";");

				StrutturaPartiteAltri.getInstance().AggiungePartita(Integer.parseInt(Campi2[0]));
				StrutturaPartiteAltri.getInstance().AggiungeSegni(""+Campi2[1]);
				StrutturaPartiteAltri.getInstance().AggiungeRisultato(""+Campi2[2]);
				StrutturaPartiteAltri.getInstance().AggiungeCasa(Campi2[3]);
				StrutturaPartiteAltri.getInstance().AggiungeFuori(Campi2[4]);
				if (StrutturaPartiteAltri.getInstance().getPartitaJolly()==-1) {
					StrutturaPartiteAltri.getInstance().setPartitaJolly(Integer.parseInt(Campi2[5]));
				}
				if (StrutturaPartiteAltri.getInstance().getFilRouge()==-1) {
					if (!Campi2[6].isEmpty()) {
						StrutturaPartiteAltri.getInstance().setFilRouge(Integer.parseInt(Campi2[6]));
					}
				}
			}

			colonne_altri.getInstance().RiempieLista();
		}
	}

	public void RitornoGiocatori(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			String[] Campi=Ritorno.split(";");
			colonne_altri.getInstance().RiempieDropDownGiocatoriDopoLettura(Campi);
		}
	}

	public void RitornaTuttiIGiocatori(Context context, String Ritorno) {
		String Appoggio=ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			ScriviMessaggi.RiempieListaGiocatori(context, Appoggio);
		}
	}

	public void RitornoClassificaGenerale(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			classifica_generale.getInstance().VisualizzaClassificaGenerale(context, Ritorno);
		}
	}

	public void RitornaIntertoto(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			intertoto.getInstance().VisualizzaIntertoto(Ritorno);
		}
	}

	public void RitornaClassificaSpeciale(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			classifica_speciale.getInstance().VisualizzaClassificaSpeciale(context, Ritorno);
		}
	}

	public void RitornoMessaggi(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			messaggi.getInstance().VisualizzaMessaggi(context, Appoggio);
		}
	}

	public void RitornoMarcaMessaggioComeLetto(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			DBRemoto dbr=new DBRemoto();
			dbr.RitornaMessaggi(context);
		}
	}

	public void RitornoClassificaSuddendeath(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			sudden_death.getInstance().VisualizzaClassificaSuddenDeath(context, Appoggio);
		}
	}

	public void RitornoSuddendeath(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			dettaglio_concorso.getInstance().VisualizzaSuddenDeath(Appoggio);
		}
	}

	public void RitornoMancanti(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);

		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			Amministrazione.ImpostaSchermata(context, Appoggio);
		}
	}

	public void RitornoClassificaRisultati(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			classifica_risultati.getInstance().VisualizzaClassificaRisultati(context, Ritorno);
		}
	}

	public void RitornoClassificaCampionato(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			classifica_campionato.getInstance().VisualizzaClassificaCampionato(Ritorno);
		}
	}

	public void EliminaMessaggio(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			DBRemoto dbr=new DBRemoto();
			dbr.RitornaMessaggi(context);
		}
	}

	public void SalvaGiocatore(final Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			// String Campi[]=Appoggio.split(";");
			// Nick & ";" & Password & ";" & Motto & ";" & EMail & ";" & Cognome & ";" & Nome & ";"

			Utility.getInstance().VisualizzaPOPUP(context, "Valori modificati.\nSi prega di rieffettuare il login", false, 0, false);

			final Handler hSelezionaRiga;
			Runnable runRigaTC;

			hSelezionaRiga = new Handler(Looper.getMainLooper());
			hSelezionaRiga.postDelayed(runRigaTC=new Runnable() {
				@Override
				public void run() {
					VariabiliStatiche.getInstance().getActPassaggio().finish();

					MainActivity.AccountPresente = false;

					DBLocale dbl = new DBLocale();
					dbl.PulisceDB(context);

					System.exit(0);
				}
			}, 1000);

		}
	}

	public void ChiudeConcorso(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			Utility.getInstance().VisualizzaPOPUP(context, "Concorso chiuso", false, 0, false);
			VariabiliStatiche.getInstance().getCmdChiudeConcorso().setEnabled(false);
			VariabiliStatiche.getInstance().getCmdAvvisaHard().setEnabled(false);
			VariabiliStatiche.getInstance().getCmdAvvisaLast().setEnabled(false);
			VariabiliStatiche.getInstance().getCmdAvvisaSoft().setEnabled(false);
		}
	}

	public void ScriviMessaggio(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			Intent intent = new Intent(context, Messaggi.class);
			context.startActivity(intent);
		}
	}

	public void Avvisa(Context context, String Ritorno) {
		String Appoggio = ToglieTag(Ritorno);
		if (Appoggio.toUpperCase().contains("ERROR:")) {
			Utility.getInstance().VisualizzaPOPUP(context, "Errore:\n\n"+Appoggio, false, 0, false);
		} else {
			Utility.getInstance().VisualizzaPOPUP(context, "Messaggio inviato", false, 0, false);
		}
	}

}
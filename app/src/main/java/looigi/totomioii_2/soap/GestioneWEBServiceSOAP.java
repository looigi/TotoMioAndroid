package looigi.totomioii_2.soap;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.utilities.Utility;

public class GestioneWEBServiceSOAP extends ListActivity {
	static ProgressDialog progressDialog;
	String tOperazione;
	Boolean Errore;
  	Handler handlerF;
  	Runnable rTimerF;
  	Context conx;
  	String Urletto;
  	int SecondiPassati;
  	String messErrore;
  	
    String NAMESPACE = "http://totoMIOWS.it/";
    String METHOD_NAME = "";
    String SOAP_ACTION = "http://totoMIOWS.it/";
    String sURL = "";
    String Parametri[];
    String result="";
 	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	}
	
	public GestioneWEBServiceSOAP(Context context, String urletto, String TipoOperazione) {
		tOperazione=TipoOperazione;
		conx=context;

		ChiudeDialog();

		ApriDialog();

		MainActivity.OperazioneInCorso=tOperazione;
		
		Urletto=urletto;
		
		SecondiPassati = 0;
		SplittaCampiUrletto(Urletto);
		
		Errore=false;

		Esegue(conx, Urletto);
	}

	private void SplittaCampiUrletto(String Cosa) {
		String Perc=Cosa;
		int pos=-1;
		String Indirizzo="";
		String Variabili[];
		String Funzione="";
		
		pos=Perc.indexOf("?");
		if (pos>-1) {
			Indirizzo=Perc.substring(0, pos);
			for (int i=Indirizzo.length()-1;i>0;i--) {
				if (Indirizzo.substring(i, i+1).equals("/")) {
					Funzione=Indirizzo.substring(i+1, Indirizzo.length());
					Indirizzo=Indirizzo.substring(0, i);
					break;
				}
			}
			sURL=Indirizzo;
			METHOD_NAME = Funzione;
			SOAP_ACTION = NAMESPACE + Funzione;
			Perc=Perc.substring(pos+1, Perc.length());
			pos=Perc.indexOf("&");
			if (pos>-1) {
				Variabili=Perc.split("&");
			} else {
				Variabili=new String[1];
				Variabili[0]=Perc;
			}
			Parametri=Variabili;
		} else {
			Indirizzo=Perc;
			for (int i=Indirizzo.length()-1;i>0;i--) {
				if (Indirizzo.substring(i, i+1).equals("/")) {
					Funzione=Indirizzo.substring(i+1, Indirizzo.length());
					Indirizzo=Indirizzo.substring(0, i);
					break;
				}
			}
			sURL=Indirizzo;
			METHOD_NAME = Funzione;
			SOAP_ACTION = NAMESPACE + Funzione;
		}
	}
	
	private void Esegue(final Context context, final String Urletto) {
    	new BackgroundAsyncTask(context).execute(Urletto);
	}
	
	public void ChiudeDialog() {
        try {
        	progressDialog.dismiss();
        } catch (Exception ignored) {

        }

        MainActivity.OperazioneInCorso="";
	}
	
	private void ApriDialog() {
		try {
			progressDialog = new ProgressDialog(conx);
			progressDialog.setMessage(Utility.getInstance().ControllaLingua(conx, "AttenderePrego"));
			progressDialog.setCancelable(false);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.show();
		} catch (Exception ignored) {
		   
		}
	}
	
	private class BackgroundAsyncTask extends AsyncTask<String, Integer, String> {
		private Context context;
		
	    private BackgroundAsyncTask(Context cxt) {
	        context = cxt;
	    }
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(String p) {
			super.onPostExecute(p);
			
			ControllaFineCiclo();
		}

	    @Override
	    protected String doInBackground(String... sUrl) {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            String Parametro="";
            String Valore="";
            
            if (Parametri!=null) {
	            for (int i=0;i<Parametri.length;i++) {
	            	int pos=Parametri[i].indexOf("=");
	            	if (pos>-1) {
	            		Parametro=Parametri[i].substring(0, pos);
	            		Valore=Parametri[i].substring(pos+1,Parametri[i].length());
	            	}
	            	Request.addProperty(Parametro, Valore);
	            }
            }

            SoapSerializationEnvelope soapEnvelope = null;
            HttpTransportSE aht = null;
            try {
                soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    			soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                aht = new HttpTransportSE(sURL, 30000);
                aht.call(SOAP_ACTION, soapEnvelope);
            } catch (SocketTimeoutException e) {
            	Errore=true;
            	messErrore=e.getMessage();
            	if (messErrore!=null) {
            		messErrore=messErrore.toUpperCase().replace("WWW.LOOIGI.IT","Web Service");
            	} else {
            		messErrore="Unknown";
            	}
            	result="ERROR: "+messErrore;
				Utility.getInstance().VisualizzaPOPUP(context, "Errore di socket sul DB:\n" + messErrore, false, 0, false);
			} catch (IOException e) {
            	Errore=true;
            	messErrore=e.getMessage();
            	if (messErrore!=null)
            		messErrore=messErrore.toUpperCase().replace("WWW.LOOIGI.IT","Web Service");
            	result="ERROR: "+messErrore;
				Utility.getInstance().VisualizzaPOPUP(context, "Errore di I/O dal DB:\n" + messErrore, false, 0, false);
            } catch (XmlPullParserException e) {
            	Errore=true;
            	messErrore=e.getMessage();
            	if (messErrore!=null) {
            		messErrore=messErrore.toUpperCase().replace("WWW.LOOIGI.IT","Web Service");
            	} else {
            		messErrore="Unknown";
            	}
            	result="ERRORE: "+messErrore;
				Utility.getInstance().VisualizzaPOPUP(context, "Errore di parsing XML:\n" + messErrore, false, 0, false);
            } catch (Exception e) {
            	Errore=true;
            	messErrore=e.getMessage();
            	if (messErrore!=null)
            		messErrore=messErrore.toUpperCase().replace("WWW.LOOIGI.IT","Web Service");
            	result="ERROR: "+messErrore;
				Utility.getInstance().VisualizzaPOPUP(context, "Errore generico di lettura sul DB:\n" + messErrore, false, 0, false);
            }
            if (!Errore) {
	            try {
	                result = ""+soapEnvelope.getResponse();
	            } catch (SoapFault e) {
	            	Errore=true;
	            	messErrore=e.getMessage();
	            	if (messErrore!=null) {
	            		messErrore=messErrore.toUpperCase().replace("WWW.LOOIGI.IT","Web Service");
	            	} else {
	            		messErrore="Unknown";
	            	}
	            	result="ERRORE: "+messErrore;
					Utility.getInstance().VisualizzaPOPUP(context, "Errore Soap:\n" + messErrore, false, 0, false);
	            }
            }
            if (aht!=null) {
            	aht=null;
            }
            if (soapEnvelope!=null) {
            	soapEnvelope=null;
            }

			return null;
	    }
	 	
	    private void ControllaFineCiclo() {
			String Ritorno=result;

			if (!Errore) {
				RitornoDaGestioneWS r=new RitornoDaGestioneWS();

				ChiudeDialog();

				Boolean Ancora=true;
				while (Ancora) {
					switch (tOperazione) {
						case "RitornaPassword":
							r.RitornoPassword(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaIdUtenteLogin":
							r.RitornoLetturaIdUtenteLogin(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaDatiDiGioco":
							r.RitornoDatiDiGioco(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaDatiStatisticiGiocatore":
							r.RitornoDatiStatisticiGiocatore(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaDatiSchedina":
							r.RitornoDatiSchedina(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaDatiSchedinaAltri":
							r.RitornoDatiSchedinaAltri(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaGiocatori":
							ChiudeDialog();
							r.RitornoGiocatori(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaTuttiIGiocatori":
							r.RitornaTuttiIGiocatori(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaDatiGiocatoreAltri":
							ChiudeDialog();
							r.RitornoDatiGiocatoreAltri(context, Ritorno);
							Ancora=false;
							break;
						case "SalvaDatiSchedina":
							r.RitornoSalvaDatiSchedina(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaClassificaGenerale":
							r.RitornoClassificaGenerale(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaMessaggi":
							r.RitornoMessaggi(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaClassificaSuddenDeath":
							r.RitornoClassificaSuddendeath(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaSuddenDeath":
							r.RitornoSuddendeath(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaMancanti":
							r.RitornoMancanti(context, Ritorno);
							Ancora=false;
							break;
						case "RitornoMarcaMessaggioComeLetto":
							r.RitornoMarcaMessaggioComeLetto(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaClassificaRisultati":
							r.RitornoClassificaRisultati(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaClassificaCampionato":
							r.RitornoClassificaCampionato(context, Ritorno);
							Ancora=false;
							break;
						case "EliminaMessaggio":
							r.EliminaMessaggio(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaCoppaItalia":
							r.RitornaCoppaItalia(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaEuropaLeague":
							r.RitornaEuropaLeague(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaInterToto":
							r.RitornaIntertoto(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaChampionsLeague":
							r.RitornaChampionsLeague(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaClassificaSpeciale":
							r.RitornaClassificaSpeciale(context, Ritorno);
							Ancora=false;
							break;
						case "SalvaDatiGiocatore":
							r.SalvaGiocatore(context, Ritorno);
							Ancora=false;
							break;
						case "ScriviMessaggio":
							r.ScriviMessaggio(context, Ritorno);
							Ancora=false;
							break;
						case "ChiudeConcorso":
							r.ChiudeConcorso(context, Ritorno);
							Ancora=false;
							break;
						case "Avvisa":
							r.Avvisa(context, Ritorno);
							Ancora=false;
							break;
						case "RitornaVersioneApplicazione":
							r.RitornaVersioneApplicazione(Ritorno);
							Ancora=false;
							break;
					}
				}
			}
	    }
	    
	    @Override
	    protected void onProgressUpdate(Integer... progress) {
	        super.onProgressUpdate(progress);
	        
	    }

		@Override
		protected void onCancelled(){
		}
   
	}

	public void cancel(boolean b) {
		if (b) {
		}
	}
}

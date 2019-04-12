package looigi.totomioii_2.utilities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import looigi.totomioii_2.About;
import looigi.totomioii_2.Amministrazione;
import looigi.totomioii_2.Classifiche;
import looigi.totomioii_2.ColonneAltri;
import looigi.totomioii_2.Coppe;
import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.Messaggi;
import looigi.totomioii_2.Profilo;
import looigi.totomioii_2.Propria;
import looigi.totomioii_2.R;
import looigi.totomioii_2.Resoconto;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.StrutturaPartite;
import looigi.totomioii_2.variabili.VariabiliStatiche;

//import looigi.totomioii_2.Messaggi;
//import looigi.totomioii_2.autostart.service;
//import looigi.totomioii_2.thread.MessageThread;

@SuppressLint("SimpleDateFormat")
public class Utility {
	//-------- Singleton ----------//
	private static Utility instance = null;

	private Utility() {
	}

	public static Utility getInstance() {
		if (instance == null) {
			instance = new Utility();
		}

		return instance;
	}


	public String RitornaNazione(Context context) {
		String country = context.getApplicationContext().getResources().getConfiguration().locale.getDisplayCountry();
		
		return country;
	}
	
	public Bitmap PrendeImmagineDaAssets(Context context, String NomeImmagine) {
		InputStream ims = null;
		try {
			ims = context.getAssets().open(NomeImmagine);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    Drawable d = Drawable.createFromStream(ims, null);
	    Bitmap b=ConverteDrawableToBitmap(d);
	    
	    return b;
	}
	
	private static Bitmap ConverteDrawableToBitmap(Drawable drawable) {
	    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(bitmap);
	    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
	    drawable.draw(canvas);

	    return bitmap;
	}
	
	public String PrendeNickPerDir(String Nick) {
		String Ritorno=Nick;
		
		Ritorno=Ritorno.replace(" ", "_");
		Ritorno=Ritorno.replace(">", "_");
		Ritorno=Ritorno.replace("<", "_");
		Ritorno=Ritorno.replace("|", "_");
		Ritorno=Ritorno.replace("+", "_");
		Ritorno=Ritorno.replace("&", "_");
		Ritorno=Ritorno.replace("?", "_");
		Ritorno=Ritorno.replace("*", "_");
		Ritorno=Ritorno.replace("/", "_");
		Ritorno=Ritorno.replace("\\", "_");
		Ritorno=Ritorno.replace(".", "_");
		Ritorno=Ritorno.replace(",", "_");

		return Ritorno;
	}
	
	public String ControllaLingua(Context context, String Stringa) {
		String Ritorno="";
		String NomeStringa=Stringa;
		
		if (MainActivity.Linguaggio==null) {
			MainActivity.Linguaggio="INGLESE";
		}
		
		if (MainActivity.Linguaggio.equals("INGLESE")) {
			NomeStringa+="_EN";
		} else {
			NomeStringa+="_IT";
		}
		
		int NumStringa=getStringResource(context, NomeStringa);
				
		if (NumStringa==0) {
			Ritorno="***"+NomeStringa+"***";
		} else {
			Ritorno=context.getString(NumStringa);
		}
		
		return Ritorno;
	}
	
	private int getStringResource(Context context, String name) {
		int id = 0;
		
		if (context!=null) {
			try {
				if (name!=null) {
					id=context.getResources().getIdentifier(name, "string", context.getPackageName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	            
        return id;
	}

	public String PrendeDataAttuale() {
		String Ritorno="";
		
		Calendar Oggi = Calendar.getInstance();
        int Giorno=Oggi.get(Calendar.DAY_OF_MONTH);
        int Mese=Oggi.get(Calendar.MONTH)+1;
        int Anno=Oggi.get(Calendar.YEAR);
        String sGiorno= Integer.toString(Giorno).trim();
        String sMese= Integer.toString(Mese).trim();
        String sAnno= Integer.toString(Anno).trim();
        if (sGiorno.length()==1) {
        	sGiorno="0"+sGiorno;
        }
        if (sMese.length()==1) {
        	sMese="0"+sMese;
        }
        Ritorno=sGiorno+"/"+sMese+"/"+sAnno;
        
        return Ritorno;
	}

	AlertDialog.Builder builder;
	
	public void ChiudePOPUP() {
		builder=null;	
	}

	public boolean ePari(int numero) {
		if ((numero % 2) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void VisualizzaPOPUP(final Context context, final String Messaggio, final Boolean Tasti, final int QualeOperazione, final boolean withCancel) {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				String sMessaggio=Messaggio;
				sMessaggio=sMessaggio.replace("***ACAPO***","\n");
				sMessaggio=sMessaggio.replace("§","\n---------------------------\n");

				builder = new AlertDialog.Builder(context);
				builder.setTitle("TotoMIO 4 Android");
				builder.setMessage(sMessaggio);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

						if (Tasti) {
							switch (QualeOperazione) {
								case 1:
									break;
							}
						}
					}
				});

				if (withCancel) {
					if (Tasti) {
						builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
					}
				}

				@SuppressWarnings("unused")
                AlertDialog dialog = builder.show();
			}
		});
	}

	public void CreaCartella(String PercorsoDIR) {
		File dDirectory = new File(PercorsoDIR);
		try {
			dDirectory.mkdir();
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}

		File file = new File(PercorsoDIR+"/.nomedia");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ignored) {

			}
		}
	}

	public boolean fileExistsInSD(String sFileName, String Percorso){
		String sFile=Percorso+"/"+sFileName;
		File file = new File(sFile);

		return file.exists();
	}

	public void ImpostaPermessi(Context context, Activity act) {
		int permissionRequestCode1 = 1193;

		String[] PERMISSIONS = new String[]{
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.INTERNET,
				Manifest.permission.ACCESS_NETWORK_STATE,
				Manifest.permission.RECEIVE_BOOT_COMPLETED,
				Manifest.permission.READ_EXTERNAL_STORAGE
		};

		if (!hasPermissions(context, PERMISSIONS)) {
			ActivityCompat.requestPermissions(act, PERMISSIONS, permissionRequestCode1);
		}
	}

	private boolean hasPermissions(Context context, String... permissions) {
		for (String permission : permissions) {
			if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}

		return true;
	}

	public String TornaStatoConcorso(int iStato) {
		String Stato="";

		switch (iStato) {
			case 0:
				Stato="Nessuno";
				break;
			case 1:
				Stato="Aperto";
				break;
			case 2:
				Stato="Da controllare";
				break;
			case 3:
				Stato="Chiuso";
				break;
			case 4:
				Stato="Anno chiuso";
				break;
		}

		return Stato;
	}

	public int ContaDoppie() {
		int quante=0;

		for (int i=0;i<14;i++) {
			if (StrutturaPartite.getInstance().RitornaSegni(i).length()==2) {
				quante++;
			}
		}

		return quante;
	}

	public boolean isNumeric(String str)
	{
		for (char c : str.toCharArray())
		{
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}

	private void ControllaSeEsisteNoMedia(String PercorsoDIR) {
		File file = new File(PercorsoDIR+"/.nomedia");
		if(!file.exists()) {
			File gpxfile = new File(PercorsoDIR, ".nomedia");
			try {
				FileWriter writer = new FileWriter(gpxfile);
				writer.append("");
				writer.flush();
				writer.close();
			} catch (IOException ignored) {
			}
		}
	}

	public void ImpostaImmagineGiocatore(Context context, String NomeFile, ImageView v) {
		String PercorsoDIR=VariabiliStatiche.PercorsoDIR+"/Immagini";
		Utility.getInstance().CreaCartella(PercorsoDIR);

		ControllaSeEsisteNoMedia(PercorsoDIR);

		boolean Esiste=true;

		if (!Utility.getInstance().fileExistsInSD(NomeFile, PercorsoDIR)) {
			Esiste=false;
		}

		if (!Esiste) {
			if (context!=null) {
				String url = VariabiliStatiche.RadiceSito +"/App_Themes/Standard/Images/Giocatori/" + Integer.toString(StrutturaDatiDiGioco.getInstance().getAnno()).trim() + "/" + NomeFile;

				ScaricaImmagini.getInstance().AggiungeImmagineDaScaricare(
						v,
						url,
						PercorsoDIR,
						NomeFile);

				//DownloadPic a = new DownloadPic();
				//a.setDirectory(PercorsoDIR);
				//a.setFiletto(NomeFile);
				//a.setIdSconosciuto(id);
				//a.setFileDaDown("http://looigi.no-ip.biz:12345/TotoMIOII/App_Themes/Standard/Images/Giocatori/" + Integer.toString(StrutturaDatiDiGioco.getInstance().getAnno()).trim() + "/" + NomeFile);
				//a.setImmagine(v);
				//a.startDownload();
			}
		} else {
			v.setImageBitmap(BitmapFactory.decodeFile(PercorsoDIR+"/"+NomeFile));
		}
	}

	public List<String> SistemaAndataRitorno(List<String> Lista) {
		List<String> appo=new ArrayList<>();

		for (String r : Lista){
			String c[]=r.split(";");

			if (!Utility.getInstance().ePari(Integer.parseInt(c[0]))) {
				Boolean Ok=false;
				String s = c[3]+";"+c[2];
				for (String rr : Lista) {
					String cc[]=rr.split(";");

					if (Utility.getInstance().ePari(Integer.parseInt(cc[0]))) {
						if (rr.contains(s)) {
							String riga=c[0]+";"+c[1]+";"+s+";"+c[5]+";"+cc[5]+";"+cc[6]+";";

							appo.add(riga);
							Ok=true;
							break;
						}
					}
				}

				if (!Ok) {
					String riga=c[0]+";"+c[1]+";"+s+";"+c[5]+";-;-;";

					appo.add(riga);
				}
			}
		}

		return appo;
	}

	public void ImpostaImmagineSquadra(Context context, String NomeFile, ImageView v) {
		String PercorsoDIR=VariabiliStatiche.PercorsoDIR+"/Immagini/Stemmi";
		Utility.getInstance().CreaCartella(PercorsoDIR);

		ControllaSeEsisteNoMedia(PercorsoDIR);

		boolean Esiste=true;
		int id = context.getResources().getIdentifier("looigi.totomioii_2:drawable/niente", null, null);

		if (!Utility.getInstance().fileExistsInSD(NomeFile, PercorsoDIR)) {
			Esiste=false;
		}

		if (!Esiste) {
			String url = VariabiliStatiche.RadiceSito+"/App_Themes/Standard/Images/Stemmi/" + NomeFile;

			ScaricaImmagini.getInstance().AggiungeImmagineDaScaricare(
					v,
					url,
					PercorsoDIR,
					NomeFile);

			//DownloadPic a = new DownloadPic();
			//a.setDirectory(PercorsoDIR);
			//a.setFiletto(NomeFile);
			//a.setIdSconosciuto(id);
			//a.setFileDaDown("http://looigi.no-ip.biz:12345/TotoMIOII/App_Themes/Standard/Images/Stemmi/" + NomeFile);
			//a.setImmagine(v);
			//a.startDownload();
		} else {
			v.setImageBitmap(BitmapFactory.decodeFile(PercorsoDIR+"/"+NomeFile));
		}
	}

	/* private String RitornoControlloURL;

	public boolean doesURLExist(String sUrl)
	{
		RitornoControlloURL="";

		ControllaSeEsisteURL c=new ControllaSeEsisteURL();
		c.execute(sUrl);

		while (RitornoControlloURL.isEmpty()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ignored) {

			}
		}

		if (RitornoControlloURL.equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

	private class ControllaSeEsisteURL extends AsyncTask<String, Integer, String> {
		private boolean Ritorno;

		@Override
		protected String doInBackground(String... sUrl) {
			int responseCode = -1;

			Ritorno=false;

			try {
				URL url = new URL(sUrl[0]);
				HttpURLConnection.setFollowRedirects(false);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setReadTimeout(30000);
				httpURLConnection.setRequestMethod("HEAD");
				//httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
				responseCode = httpURLConnection.getResponseCode();
				httpURLConnection.disconnect();
			} catch (IOException e) {
				responseCode=-1;
			}
			if (responseCode == HttpURLConnection.HTTP_OK)
				Ritorno=true;

			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);

			if (Ritorno) {
				RitornoControlloURL = "OK";
			} else {
				RitornoControlloURL = "KO";
			}
		}
	} */

	public boolean doesURLExist(String targetUrl) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		HttpURLConnection httpUrlConn;
		try {
			httpUrlConn = (HttpURLConnection) new URL(targetUrl)
					.openConnection();

			// A HEAD request is just like a GET request, except that it asks
			// the server to return the response headers only, and not the
			// actual resource (i.e. no message body).
			// This is useful to check characteristics of a resource without
			// actually downloading it,thus saving bandwidth. Use HEAD when
			// you don't actually need a file's contents.
			httpUrlConn.setRequestMethod("HEAD");

			// Set timeouts in milliseconds
			httpUrlConn.setConnectTimeout(3000);
			httpUrlConn.setReadTimeout(3000);

			// Print HTTP status code/message for your information.
			//System.out.println("Response Code: "
			//		+ httpUrlConn.getResponseCode());
			//System.out.println("Response Message: "
			//		+ httpUrlConn.getResponseMessage());

			return (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			//System.out.println("Error: " + e.getMessage());
			return false;
		}
	}

	public String LeggePaginaWEB(String Indirizzo) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		String Ritorno="";

		try {
			url = new URL(Indirizzo);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				Ritorno+=line;
			}

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ignored) {
			ignored.printStackTrace();
		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}

		return Ritorno;
	}

	public void PlayNotificationSound(Context context) {
		try {
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
			r.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ControllaStato(int Maschera) {
		String ritorno="";
		Boolean Test=false;
		int statoConcorso = StrutturaDatiDiGioco.getInstance().getStatoConcorso();

		// 0 - Nessuno
		// 1 - Aperto
		// 2 - Da Controllare
		// 3 - Chiuso
		// 4 - Anno chiuso

		if (Test) {
			statoConcorso = 1;
			Date d;
			if (Maschera == 3) {
				d = new Date(System.currentTimeMillis() - 10000);
			} else {
				d = new Date(System.currentTimeMillis() + 1000);
			}
			StrutturaDatiDiGioco.getInstance().setDataChiusura(d);
			StrutturaDatiDiGioco.getInstance().setGiornata(21);
		}

		switch (Maschera) {
			case 1:
				break;
			case 2:
				switch (statoConcorso) {
					case 0:
						ritorno="Nessun concorso presente";
						break;
					case 1:
						long diff = StrutturaDatiDiGioco.getInstance().getDataChiusura().getTime() - System.currentTimeMillis();
						if (diff > 0) {
							ritorno="Concorso aperto";
						}
						break;
					case 3:
						ritorno="Concorso chiuso";
						break;
					case 4:
						ritorno="Anno chiuso";
						break;
				}
				break;
			case 3:
				switch (statoConcorso) {
					case 0:
						ritorno="Nessun concorso presente";
						break;
					case 1:
						long diff = StrutturaDatiDiGioco.getInstance().getDataChiusura().getTime() - System.currentTimeMillis();
						if (diff <= 0) {
							ritorno="Concorso chiuso";
						}
						break;
					case 2:
						ritorno="Concorso da controllare";
						break;
					case 3:
						ritorno="Concorso chiuso";
						break;
					case 4:
						ritorno="Anno chiuso";
						break;
				}

				break;
			case 4:
				break;
		}

		return ritorno;
	}

	public void ImpostaBottoniMaschera(final Context context, View view, final Activity a) {
		if (MainActivity.AccountPresente!=null && MainActivity.AccountPresente) {
			ImageButton imgHome = view.findViewById(R.id.imgHome);
			imgHome.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, MainActivity.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgPropria = view.findViewById(R.id.imgPropria);
			imgPropria.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String Ritorno = "";

					Ritorno = Utility.getInstance().ControllaStato(3);
					if (!Ritorno.isEmpty()) {
						Utility.getInstance().VisualizzaPOPUP(context, Ritorno, false, 0, false);
					} else {
						VariabiliStatiche.getInstance().setActPassaggio(a);
						Intent intent = new Intent(context, Propria.class);
						context.startActivity(intent);
					}
				}
			});

			ImageButton imgAltri = view.findViewById(R.id.imgAltri);
			imgAltri.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String Ritorno = "";

					Ritorno = Utility.getInstance().ControllaStato(2);
					if (!Ritorno.isEmpty()) {
						Utility.getInstance().VisualizzaPOPUP(context, Ritorno, false, 0, false);
					} else {
						VariabiliStatiche.getInstance().setActPassaggio(a);
						Intent intent = new Intent(context, ColonneAltri.class);
						context.startActivity(intent);
					}
				}
			});

			ImageButton imgClassifiche = view.findViewById(R.id.imgClassifiche);
			imgClassifiche.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, Classifiche.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgCoppe = view.findViewById(R.id.imgCoppe);
			imgCoppe.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, Coppe.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgMessaggi = view.findViewById(R.id.imgMessaggi);
			imgMessaggi.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, Messaggi.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgResoconto = view.findViewById(R.id.imgResoconto);
			imgResoconto.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, Resoconto.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgProfilo = view.findViewById(R.id.imgProfilo);
			imgProfilo.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, Profilo.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgAbout = view.findViewById(R.id.imgAbout);
			imgAbout.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					VariabiliStatiche.getInstance().setActPassaggio(a);
					Intent intent = new Intent(context, About.class);
					context.startActivity(intent);
				}
			});

			ImageButton imgAmministrazione = view.findViewById(R.id.imgAmministrazione);
			imgAmministrazione.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (StrutturaDatiGiocatore.getInstance().getTipologia()!=null) {
						if (StrutturaDatiGiocatore.getInstance().getTipologia().toUpperCase().equals("AMMINISTRATORE")) {
							VariabiliStatiche.getInstance().setActPassaggio(a);
							Intent intent = new Intent(context, Amministrazione.class);
							context.startActivity(intent);
						} else {
							Utility.getInstance().VisualizzaPOPUP(context, "Non si hanno i permessi\nper visualizzare la maschera", false, 0, false);
						}
					}
				}
			});

			ImageButton imgUscita = view.findViewById(R.id.imgUscita);
			imgUscita.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					System.exit(0);
				}
			});
		}
	}

	public String getVersion(Context context) {
		String version = "";

		try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return version;
	}
}

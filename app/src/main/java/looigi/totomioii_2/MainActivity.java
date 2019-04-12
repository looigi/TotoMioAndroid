package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import looigi.totomioii_2.db.DBLocale;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.gestione_maschere.loginUtente;
import looigi.totomioii_2.utilities.ControlloVersioneApplicazione;
import looigi.totomioii_2.utilities.Permessi;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaAltriGiocatori;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.StrutturaPartite;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class MainActivity extends Activity {
    public static Boolean AccountPresente;
    public static String OperazioneInCorso;
    public static String Linguaggio="ITALIANO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VariabiliStatiche.getInstance().setMainContext(this);
        VariabiliStatiche.getInstance().setMainActivity(this);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        Permessi p=new Permessi();
        p.ControllaPermessi(this);

        StrutturaDatiDiGioco.getInstance().setAnno(5);

        Utility.getInstance().ImpostaPermessi(this, this);
        Utility.getInstance().CreaCartella(Environment.getExternalStorageDirectory().getPath() + "/LooigiSoft");
        Utility.getInstance().CreaCartella(Environment.getExternalStorageDirectory().getPath() + "/LooigiSoft/TotoMIO");

        TextView layErrore = (TextView) findViewById(R.id.txtErroreLogin);
        LinearLayout layLogin = (LinearLayout) findViewById(R.id.layLogin);
        EditText txtNick = (EditText) findViewById(R.id.txtNickLogin);
        LinearLayout layLoggato = (LinearLayout) findViewById(R.id.layLoggato);
        ImageView imgView = (ImageView) findViewById(R.id.imgUser);
        TextView tNomeUtente = (TextView) findViewById(R.id.txtUser);
        TextView tMotto = (TextView) findViewById(R.id.txtMotto);
        TextView tAnno = (TextView) findViewById(R.id.txtAnno);
        TextView tGiornata = (TextView) findViewById(R.id.txtGiornata);
        TextView tStato = (TextView) findViewById(R.id.txtStatoConcorso);

        TextView tPosClass = (TextView) findViewById(R.id.txtPosClassifica);
        TextView tPosCamp = (TextView) findViewById(R.id.txtPosCampionato);
        TextView tPosRis = (TextView) findViewById(R.id.txtPosRisultati);
        TextView tTotVers = (TextView) findViewById(R.id.txtTotVersamento);
        TextView tVinti = (TextView) findViewById(R.id.txtVinti);
        TextView tReali = (TextView) findViewById(R.id.txtReali);
        TextView tPresi = (TextView) findViewById(R.id.txtPresi);
        TextView tAmici = (TextView) findViewById(R.id.txtAmici);
        TextView tTappi = (TextView) findViewById(R.id.txtNumeroTappi);
        TextView tVittorie = (TextView) findViewById(R.id.txtVittorie);
        TextView tUltimi = (TextView) findViewById(R.id.txtUltimi);
        TextView tSecondi = (TextView) findViewById(R.id.txtSecondiPosti);
        TextView tChiusura = (TextView) findViewById(R.id.txtChiusura);
        TextView tColonna = (TextView) findViewById(R.id.txtColonna);
        TextView tMessaggi = (TextView) findViewById(R.id.txtMessaggi);

        LinearLayout lMessaggi = findViewById(R.id.layMessaggi);

        Button btnDettaglioConcorso = findViewById(R.id.btnDettaglioConcorso);
        btnDettaglioConcorso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int statoConcorso = StrutturaDatiDiGioco.getInstance().getStatoConcorso();
                String ritorno = "";

                switch (statoConcorso) {
                    case 0:
                        ritorno = "Nessun concorso presente";
                        break;
                    case 1:
                        long diff = StrutturaDatiDiGioco.getInstance().getDataChiusura().getTime() - System.currentTimeMillis();
                        if (diff > 0) {
                            ritorno = "Concorso aperto";
                        }
                        break;
                    case 3:
                        ritorno = "Concorso chiuso";
                        break;
                    case 4:
                        ritorno = "Anno chiuso";
                        break;
                }

                if (!ritorno.isEmpty()) {
                    Utility.getInstance().VisualizzaPOPUP(VariabiliStatiche.getInstance().getMainContext(), ritorno, false, 0, false);
                } else {
                    VariabiliStatiche.getInstance().setActPassaggio(VariabiliStatiche.getInstance().getMainActivity());
                    Intent intent = new Intent(VariabiliStatiche.getInstance().getMainContext(), DettaglioConcorso.class);
                    startActivity(intent);
                }
            }
        });

        Button btnMessaggi = findViewById(R.id.btnMessaggi);
        btnMessaggi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VariabiliStatiche.getInstance().setActPassaggio(VariabiliStatiche.getInstance().getMainActivity());
                Intent intent = new Intent(VariabiliStatiche.getInstance().getMainContext(), Messaggi.class);
                startActivity(intent);
            }
        });

        DBLocale dbl = new DBLocale();
        dbl.CreaDB(this);
        AccountPresente = dbl.PrendeDatiUtente(this);

        VariabiliStatiche.getInstance().setLayLogin(layLogin);
        VariabiliStatiche.getInstance().setLayLoggato(layLoggato);
        VariabiliStatiche.getInstance().setImgView(imgView);
        VariabiliStatiche.getInstance().settNomeUtente(tNomeUtente);
        VariabiliStatiche.getInstance().setLayErrore(layErrore);
        VariabiliStatiche.getInstance().setTxtNick(txtNick);
        VariabiliStatiche.getInstance().settMotto(tMotto);
        VariabiliStatiche.getInstance().settAnno(tAnno);
        VariabiliStatiche.getInstance().settGiornata(tGiornata);
        VariabiliStatiche.getInstance().settStatoConcorso(tStato);
        VariabiliStatiche.getInstance().settPosClass(tPosClass);
        VariabiliStatiche.getInstance().settPosCamp(tPosCamp);
        VariabiliStatiche.getInstance().settPosRis(tPosRis);
        VariabiliStatiche.getInstance().settTotVers(tTotVers);
        VariabiliStatiche.getInstance().settVinti(tVinti);
        VariabiliStatiche.getInstance().settReali(tReali);
        VariabiliStatiche.getInstance().settPresi(tPresi);
        VariabiliStatiche.getInstance().settAmici(tAmici);
        VariabiliStatiche.getInstance().settTappi(tTappi);
        VariabiliStatiche.getInstance().settVittorie(tVittorie);
        VariabiliStatiche.getInstance().settUltimi(tUltimi);
        VariabiliStatiche.getInstance().settSecondi(tSecondi);
        VariabiliStatiche.getInstance().settChiusura(tChiusura);
        VariabiliStatiche.getInstance().setTxtColonna(tColonna);
        VariabiliStatiche.getInstance().setTxtMessaggi(tMessaggi);
        VariabiliStatiche.getInstance().setLayMessaggi(lMessaggi);

        imgView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });

        if (!MainActivity.AccountPresente) {
            SettaMascheraPerLogin(VariabiliStatiche.getInstance().getMainContext());
        } else {
            SettaMascheraGiaLoggato();

            Utility.getInstance().ImpostaBottoniMaschera(VariabiliStatiche.getInstance().getMainContext(),
                    ((Activity) VariabiliStatiche.getInstance().getMainContext()).getWindow().getDecorView(), this);
        }

        if (VariabiliStatiche.getInstance().getActPassaggio()!=null) {
            VariabiliStatiche.getInstance().getActPassaggio().finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            //image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            cursor.close();

            //String PercorsoDIR = Environment.getExternalStorageDirectory().getPath()+"/TotoMIO/Immagini";
            String P[] = imagePath.split("/");
            String PercorsoDIR="";
            for (int i=0; i<P.length-1;i++) {
                PercorsoDIR+=P[i]+"/";
            }
            DBRemoto dbr = new DBRemoto();
            dbr.UploadFile(VariabiliStatiche.getInstance().getMainContext(), PercorsoDIR,P[P.length-1]);
        }
    }

    public static void SettaMascheraGiaLoggato() {
        VariabiliStatiche.getInstance().getLayLogin().setVisibility(LinearLayout.GONE);
        VariabiliStatiche.getInstance().getLayLoggato().setVisibility(LinearLayout.VISIBLE);

        MainActivity.AccountPresente=true;

        Utility.getInstance().ImpostaBottoniMaschera(VariabiliStatiche.getInstance().getMainContext(), ((Activity) VariabiliStatiche.getInstance().getMainContext()).getWindow().getDecorView(), VariabiliStatiche.getInstance().getMainActivity());

        Utility.getInstance().ImpostaImmagineGiocatore(VariabiliStatiche.getInstance().getMainContext(),
                StrutturaDatiGiocatore.getInstance().getNick()+".jpg",
                VariabiliStatiche.getInstance().getImgView());


        if (!VariabiliStatiche.haControllatoLaVersione) {
            VariabiliStatiche.haControllatoLaVersione=true;

            ControlloVersioneApplicazione c = new ControlloVersioneApplicazione();
            c.ControllaVersione();
        }

        if (!VariabiliStatiche.haControllatoLaVersione) {
            VariabiliStatiche.haControllatoLaVersione=true;

            ControlloVersioneApplicazione c = new ControlloVersioneApplicazione();
            c.ControllaVersione();
        } else {
            PrendeDatiStatistici(VariabiliStatiche.getInstance().getMainContext());
        }
    }

    private void SettaMascheraPerLogin(final Context context) {
        VariabiliStatiche.getInstance().getLayLogin().setVisibility(LinearLayout.VISIBLE);
        VariabiliStatiche.getInstance().getLayLoggato().setVisibility(LinearLayout.GONE);

        final EditText txtPassword1 = (EditText) findViewById(R.id.txtPasswordLogin);

        if (MainActivity.AccountPresente) {
            VariabiliStatiche.getInstance().getTxtNick().setText(StrutturaDatiGiocatore.getInstance().getNick());
            VariabiliStatiche.getInstance().getTxtNick().setEnabled(false);
        } else {
            VariabiliStatiche.getInstance().getTxtNick().setText("");
            VariabiliStatiche.getInstance().getTxtNick().setEnabled(true);
        }

        loginUtente.getInstance().ErroreVisualizzato(false, "");

        TextView txtTitolo = (TextView) findViewById(R.id.txtTitolone);
        txtTitolo.setText(Utility.getInstance().ControllaLingua(context, "LU_Titolo"));

        Button BottoneAnnulla = (Button) findViewById(R.id.cmdAnnullaL);
        BottoneAnnulla.setText(Utility.getInstance().ControllaLingua(context, "AU_Annulla"));
        BottoneAnnulla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.ChiudeTutto();
            }
        });

        Button BottoneOK = (Button) findViewById(R.id.cmdOKL);
        BottoneOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginUtente.getInstance().ControllaDati(context,
                        VariabiliStatiche.getInstance().getTxtNick(),
                        txtPassword1);
            }
        });

        //getWindow().setSoftInputMode(
        //        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void PrendeDatiStatistici(Context context) {
        //if (VariabiliStatiche.getInstance().gettGiornata().getText().toString().isEmpty()) {
            DBRemoto dbr = new DBRemoto();
            dbr.RitornaDatiDiGioco(context);
        //} else {
        //    ScriveDatiStrutture();
        //}
    }

    public static void PrendeDatiSchedina(final Context context) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DBRemoto dbr=new DBRemoto();
                dbr.RitornaDatiSchedina(context);
            }
        }, 300);
    }

    public static void PrendeDatiGiocatore(final Context context) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DBRemoto dbr=new DBRemoto();
                dbr.RitornaDatiStatisticiGiocatore(context);
            }
        }, 300);
    }

    public static void ScriveDatiStrutture() {
        VariabiliStatiche.getInstance().gettNomeUtente().setText(StrutturaDatiGiocatore.getInstance().getNick().toUpperCase());
        VariabiliStatiche.getInstance().gettMotto().setText(StrutturaDatiGiocatore.getInstance().getTesto());

        String Stato=Utility.getInstance().TornaStatoConcorso(StrutturaDatiDiGioco.getInstance().getStatoConcorso());

        VariabiliStatiche.getInstance().gettAnno().setText(StrutturaDatiDiGioco.getInstance().getDescAnno());

        int g=StrutturaDatiDiGioco.getInstance().getGiornata();
        String Speciale="";
        if (g>100) {
            Speciale="Speciale ";
            g-=100;
        }
        VariabiliStatiche.getInstance().gettGiornata().setText(Speciale+Integer.toString(g));
        VariabiliStatiche.getInstance().gettStatoConcorso().setText(Stato);

        Float daPagare=StrutturaDatiGiocatore.getInstance().getTotVersamento();
        daPagare -= StrutturaDatiGiocatore.getInstance().getVinti();
        daPagare -= StrutturaDatiGiocatore.getInstance().getReali();
        daPagare -= StrutturaDatiGiocatore.getInstance().getAmici();

        String dp = String.format("%.2f", daPagare);

        VariabiliStatiche.getInstance().gettPosClass().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getPosClassifica()));
        VariabiliStatiche.getInstance().gettPosCamp().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getPosCampionato()));
        VariabiliStatiche.getInstance().gettPosRis().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getPosRisultati()));
        VariabiliStatiche.getInstance().gettTotVers().setText("€ "+dp + " / € " + Float.toString(StrutturaDatiGiocatore.getInstance().getTotVersamento()));
        VariabiliStatiche.getInstance().gettVinti().setText("€ "+Float.toString(StrutturaDatiGiocatore.getInstance().getVinti()));
        VariabiliStatiche.getInstance().gettReali().setText("€ "+Float.toString(StrutturaDatiGiocatore.getInstance().getReali()));
        VariabiliStatiche.getInstance().gettPresi().setText("€ "+Float.toString(StrutturaDatiGiocatore.getInstance().getPresi()));
        VariabiliStatiche.getInstance().gettAmici().setText("€ "+Float.toString(StrutturaDatiGiocatore.getInstance().getAmici()));
        VariabiliStatiche.getInstance().gettTappi().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getNumeroTappi()));
        VariabiliStatiche.getInstance().gettVittorie().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getVittorie()));
        VariabiliStatiche.getInstance().gettUltimi().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getUltimiPosti()));
        VariabiliStatiche.getInstance().gettSecondi().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getSecondiPosti()));
        VariabiliStatiche.getInstance().getTxtMessaggi().setText(Integer.toString(StrutturaDatiGiocatore.getInstance().getMessaggi()));

        if (StrutturaDatiGiocatore.getInstance().getMessaggi()>0) {
            VariabiliStatiche.getInstance().getLayMessaggi().setVisibility(LinearLayout.VISIBLE);
        } else {
            VariabiliStatiche.getInstance().getLayMessaggi().setVisibility(LinearLayout.GONE);
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (StrutturaDatiDiGioco.getInstance().getDataChiusura()!=null) {
            String dataChiusura = df.format(StrutturaDatiDiGioco.getInstance().getDataChiusura());
            VariabiliStatiche.getInstance().gettChiusura().setText(dataChiusura);
        } else {
            VariabiliStatiche.getInstance().gettChiusura().setText("");
        }

        if (StrutturaDatiDiGioco.getInstance().getStatoConcorso()==1) {
            if (StrutturaPartite.getInstance().getFilRouge() > -1) {
                VariabiliStatiche.getInstance().getTxtColonna().setVisibility(LinearLayout.VISIBLE);
                VariabiliStatiche.getInstance().getTxtColonna().setText("Colonna propria già compilata");
                VariabiliStatiche.getInstance().getTxtColonna().setTextColor(Color.GREEN);
            } else {
                VariabiliStatiche.getInstance().getTxtColonna().setVisibility(LinearLayout.VISIBLE);
                VariabiliStatiche.getInstance().getTxtColonna().setText("Colonna propria ancora da compilare");
                VariabiliStatiche.getInstance().getTxtColonna().setTextColor(Color.RED);
            }
        } else {
            VariabiliStatiche.getInstance().getTxtColonna().setVisibility(LinearLayout.GONE);
        }

        if (VariabiliStatiche.getInstance().gettGiornata().getText().toString().isEmpty()) {
            // Non ha caricato i dati
            //Utility.getInstance().VisualizzaTabs(true);
        } else {
            if (StrutturaAltriGiocatori.getInstance().RitornaGiocatore(StrutturaDatiGiocatore.getInstance().getNick()).isEmpty()) {
                StrutturaAltriGiocatori.getInstance().AggiungeGiocatore(
                        StrutturaDatiGiocatore.getInstance().getIdUtente(),
                        StrutturaDatiGiocatore.getInstance().getNick(),
                        StrutturaDatiGiocatore.getInstance().getTesto()
                );
            }

            //Utility.getInstance().VisualizzaTabs(false);
        }
    }

    public static void ChiudeTutto() {
        System.exit(0);
    }
}

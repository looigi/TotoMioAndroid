package looigi.totomioii_2.variabili;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class VariabiliStatiche {
    //-------- Singleton ----------//
    private static VariabiliStatiche instance = null;

    private VariabiliStatiche() {
    }

    public static VariabiliStatiche getInstance() {
        if (instance == null) {
            instance = new VariabiliStatiche();
        }

        return instance;
    }

    private TextView layErrore;
    private EditText txtNick;
    private LinearLayout layLogin;
    private LinearLayout layLoggato;
    private ImageView imgView;
    private TextView tNomeUtente;
    private TextView tMotto;
    private TextView tAnno;
    private TextView tGiornata;
    private TextView tStatoConcorso;
    private TextView tPosClass;
    private TextView tPosCamp;
    private TextView tPosRis;
    private TextView tTotVers;
    private TextView tVinti;
    private TextView tReali;
    private TextView tPresi;
    private TextView tAmici;
    private TextView tTappi;
    private TextView tVittorie;
    private TextView tUltimi;
    private TextView tSecondi;
    private TextView tChiusura;

    private Button cmdIndietro;
    private Button cmdAvanti;
    private TextView tNumeroPartita;
    private TextView txtCasa;
    private TextView txtFuori;
    private ImageView imgCasa;
    private ImageView imgFuori;
    private CheckBox check1;
    private CheckBox checkX;
    private CheckBox check2;
    private TextView txtDettaglio;
    private CheckBox checkFR;
    private ImageView imgJolly;
    private Button cmdSalvaTutto;
    private TabHost tabHost;
    private TextView txtColonna;

    private TextView tRisCasa;
    private TextView tRisFuori;
    private Button cmdRisCasaMeno;
    private Button cmdRisCasaPiu;
    private Button cmdRisFuoriMeno;
    private Button cmdRisFuoriPiu;
    private ListView lvColonnaPropria;

    private ListView lvClassificaGenerale;
    private ListView lvClassificaRisultati;
    private ListView lvClassificaCampionato;

    private Spinner spnGiocatori;
    private TextView txtColonneUser;
    private TextView txtColonneMotto;
    private ImageView imgColonneUser;

    private TabHost tabHostClassifiche;
    private int QuantiTabsClassifica;

    private WebView webViewResoconto;

    private Button cmdChiudeConcorso;
    private Button cmdAvvisaSoft;
    private Button cmdAvvisaHard;
    private Button cmdAvvisaLast;

    private TextView txtCasaDett;
    private TextView txtCasaStat;
    private TextView txtFuoriDett;
    private TextView txtFuoriStat;

    private TextView txtQuoteSegno;
    private TextView txtQuoteRisultato;

    private ListView lvInGioco;
    private ListView lvEsclusi;

    private ListView lvSD;

    private ListView lstMancanti;

    private TextView txtMessaggi;
    private LinearLayout layMessaggi;
    private ListView lstLetti;
    private ListView lstDaLeggere;

    private ListView lvGiocatori;
    private ListView lvDestinatari;

    private Activity mainActivity;
    private Context mainContext;
    private Activity actPassaggio;

    private Button btnIndietro;
    private Button btnAvanti;
    private TextView txtTurno;
    private ListView lstCoppaItalia;

    private ListView lstEuropaLeague;
    private ListView lstEuropaLeagueGironi;
    private Button btnFaseGironi;
    private Button btnFaseFinale;
    private LinearLayout layGironi;
    private LinearLayout layFinale;

    private LinearLayout layGironeA;
    private LinearLayout layGironeB;
    private Button btnFaseGironeA;
    private Button btnFaseGironeB;
    private ListView lstChampions;
    private ListView lstChampionsGironeA;
    private ListView lstChampionsGironeB;
    public static String RadiceWS = "http://looigi.no-ip.biz:12345/totoMIOWS";
    public static String RadiceSito = "http://looigi.no-ip.biz:12345/TotoMIOII";
    public static String PercorsoDIR= Environment.getExternalStorageDirectory().getPath()+"/LooigiSoft/TotoMio";
    public static Boolean StaAggiornandoLaVersione=false;
    public static Boolean haControllatoLaVersione=false;
    private ListView lstSpeciale;

    private ListView lstIntertoto;

    public ListView getLstIntertoto() {
        return lstIntertoto;
    }

    public void setLstIntertoto(ListView lstIntertoto) {
        this.lstIntertoto = lstIntertoto;
    }

    public ListView getLstSpeciale() {
        return lstSpeciale;
    }

    public void setLstSpeciale(ListView lstSpeciale) {
        this.lstSpeciale = lstSpeciale;
    }

    public ListView getLstChampions() {
        return lstChampions;
    }

    public void setLstChampions(ListView lstChampions) {
        this.lstChampions = lstChampions;
    }

    public ListView getLstChampionsGironeA() {
        return lstChampionsGironeA;
    }

    public void setLstChampionsGironeA(ListView lstChampionsGironeA) {
        this.lstChampionsGironeA = lstChampionsGironeA;
    }

    public ListView getLstChampionsGironeB() {
        return lstChampionsGironeB;
    }

    public void setLstChampionsGironeB(ListView lstChampionsGironeB) {
        this.lstChampionsGironeB = lstChampionsGironeB;
    }

    public Button getBtnFaseGironeA() {
        return btnFaseGironeA;
    }

    public void setBtnFaseGironeA(Button btnFaseGironeA) {
        this.btnFaseGironeA = btnFaseGironeA;
    }

    public Button getBtnFaseGironeB() {
        return btnFaseGironeB;
    }

    public void setBtnFaseGironeB(Button btnFaseGironeB) {
        this.btnFaseGironeB = btnFaseGironeB;
    }

    public LinearLayout getLayGironeA() {
        return layGironeA;
    }

    public void setLayGironeA(LinearLayout layGironeA) {
        this.layGironeA = layGironeA;
    }

    public LinearLayout getLayGironeB() {
        return layGironeB;
    }

    public void setLayGironeB(LinearLayout layGironeB) {
        this.layGironeB = layGironeB;
    }

    public LinearLayout getLayGironi() {
        return layGironi;
    }

    public void setLayGironi(LinearLayout layGironi) {
        this.layGironi = layGironi;
    }

    public LinearLayout getLayFinale() {
        return layFinale;
    }

    public void setLayFinale(LinearLayout layFinale) {
        this.layFinale = layFinale;
    }

    public Button getBtnFaseGironi() {
        return btnFaseGironi;
    }

    public void setBtnFaseGironi(Button btnFaseGironi) {
        this.btnFaseGironi = btnFaseGironi;
    }

    public Button getBtnFaseFinale() {
        return btnFaseFinale;
    }

    public void setBtnFaseFinale(Button btnFaseFinale) {
        this.btnFaseFinale = btnFaseFinale;
    }

    public ListView getLstEuropaLeague() {
        return lstEuropaLeague;
    }

    public void setLstEuropaLeague(ListView lstEuropaLeague) {
        this.lstEuropaLeague = lstEuropaLeague;
    }

    public ListView getLstEuropaLeagueGironi() {
        return lstEuropaLeagueGironi;
    }

    public void setLstEuropaLeagueGironi(ListView lstEuropaLeagueGironi) {
        this.lstEuropaLeagueGironi = lstEuropaLeagueGironi;
    }

    public ListView getLstCoppaItalia() {
        return lstCoppaItalia;
    }

    public void setLstCoppaItalia(ListView lstCoppaItalia) {
        this.lstCoppaItalia = lstCoppaItalia;
    }

    public TextView getTxtTurno() {
        return txtTurno;
    }

    public void setTxtTurno(TextView txtTurno) {
        this.txtTurno = txtTurno;
    }

    public Button getBtnIndietro() {
        return btnIndietro;
    }

    public void setBtnIndietro(Button btnIndietro) {
        this.btnIndietro = btnIndietro;
    }

    public Button getBtnAvanti() {
        return btnAvanti;
    }

    public void setBtnAvanti(Button btnAvanti) {
        this.btnAvanti = btnAvanti;
    }

    public Activity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public Context getMainContext() {
        return mainContext;
    }

    public void setMainContext(Context mainContext) {
        this.mainContext = mainContext;
    }

    public Activity getActPassaggio() {
        return actPassaggio;
    }

    public void setActPassaggio(Activity actPassaggio) {
        this.actPassaggio = actPassaggio;
    }

    public ListView getLvGiocatori() {
        return lvGiocatori;
    }

    public void setLvGiocatori(ListView lvGiocatori) {
        this.lvGiocatori = lvGiocatori;
    }

    public ListView getLvDestinatari() {
        return lvDestinatari;
    }

    public void setLvDestinatari(ListView lvDestinatari) {
        this.lvDestinatari = lvDestinatari;
    }

    public ListView getLstLetti() {
        return lstLetti;
    }

    public void setLstLetti(ListView lstLetti) {
        this.lstLetti = lstLetti;
    }

    public ListView getLstDaLeggere() {
        return lstDaLeggere;
    }

    public void setLstDaLeggere(ListView lstDaLeggere) {
        this.lstDaLeggere = lstDaLeggere;
    }

    public TextView getTxtMessaggi() {
        return txtMessaggi;
    }

    public void setTxtMessaggi(TextView txtMessaggi) {
        this.txtMessaggi = txtMessaggi;
    }

    public LinearLayout getLayMessaggi() {
        return layMessaggi;
    }

    public void setLayMessaggi(LinearLayout layMessaggi) {
        this.layMessaggi = layMessaggi;
    }

    public ListView getLstMancanti() {
        return lstMancanti;
    }

    public void setLstMancanti(ListView lstMancanti) {
        this.lstMancanti = lstMancanti;
    }

    public ListView getLvSD() {
        return lvSD;
    }

    public void setLvSD(ListView lvSD) {
        this.lvSD = lvSD;
    }

    public ListView getLvInGioco() {
        return lvInGioco;
    }

    public void setLvInGioco(ListView lvInGioco) {
        this.lvInGioco = lvInGioco;
    }

    public ListView getLvEsclusi() {
        return lvEsclusi;
    }

    public void setLvEsclusi(ListView lvEsclusi) {
        this.lvEsclusi = lvEsclusi;
    }

    public TextView getTxtQuoteRisultato() {
        return txtQuoteRisultato;
    }

    public void setTxtQuoteRisultato(TextView txtQuoteRisultato) {
        this.txtQuoteRisultato = txtQuoteRisultato;
    }

    public TextView getTxtQuoteSegno() {
        return txtQuoteSegno;
    }

    public void setTxtQuoteSegno(TextView txtQuoteSegno) {
        this.txtQuoteSegno = txtQuoteSegno;
    }

    public TextView getTxtCasaDett() {
        return txtCasaDett;
    }

    public void setTxtCasaDett(TextView txtCasaDett) {
        this.txtCasaDett = txtCasaDett;
    }

    public TextView getTxtCasaStat() {
        return txtCasaStat;
    }

    public void setTxtCasaStat(TextView txtCasaStat) {
        this.txtCasaStat = txtCasaStat;
    }

    public TextView getTxtFuoriDett() {
        return txtFuoriDett;
    }

    public void setTxtFuoriDett(TextView txtFuoriDett) {
        this.txtFuoriDett = txtFuoriDett;
    }

    public TextView getTxtFuoriStat() {
        return txtFuoriStat;
    }

    public void setTxtFuoriStat(TextView txtFuoriStat) {
        this.txtFuoriStat = txtFuoriStat;
    }

    public Button getCmdAvvisaSoft() {
        return cmdAvvisaSoft;
    }

    public void setCmdAvvisaSoft(Button cmdAvvisaSoft) {
        this.cmdAvvisaSoft = cmdAvvisaSoft;
    }

    public Button getCmdAvvisaHard() {
        return cmdAvvisaHard;
    }

    public void setCmdAvvisaHard(Button cmdAvvisaHard) {
        this.cmdAvvisaHard = cmdAvvisaHard;
    }

    public Button getCmdAvvisaLast() {
        return cmdAvvisaLast;
    }

    public void setCmdAvvisaLast(Button cmdAvvisaLast) {
        this.cmdAvvisaLast = cmdAvvisaLast;
    }

    public Button getCmdChiudeConcorso() {
        return cmdChiudeConcorso;
    }

    public void setCmdChiudeConcorso(Button cmdChiudeConcorso) {
        this.cmdChiudeConcorso = cmdChiudeConcorso;
    }

    public WebView getWebViewResoconto() {
        return webViewResoconto;
    }

    public void setWebViewResoconto(WebView webViewResoconto) {
        this.webViewResoconto = webViewResoconto;
    }

    public ListView getLvClassificaGenerale() {
        return lvClassificaGenerale;
    }

    public void setLvClassificaGenerale(ListView lvClassificaGenerale) {
        this.lvClassificaGenerale = lvClassificaGenerale;
    }

    public ListView getLvClassificaRisultati() {
        return lvClassificaRisultati;
    }

    public void setLvClassificaRisultati(ListView lvClassificaRisultati) {
        this.lvClassificaRisultati = lvClassificaRisultati;
    }

    public ListView getLvClassificaCampionato() {
        return lvClassificaCampionato;
    }

    public void setLvClassificaCampionato(ListView lvClassificaCampionato) {
        this.lvClassificaCampionato = lvClassificaCampionato;
    }

    public int getQuantiTabsClassifica() {
        return QuantiTabsClassifica;
    }

    public void setQuantiTabsClassifica(int quantiTabsClassifica) {
        QuantiTabsClassifica = quantiTabsClassifica;
    }

    public TabHost getTabHostClassifiche() {
        return tabHostClassifiche;
    }

    public void setTabHostClassifiche(TabHost tabHostClassifiche) {
        this.tabHostClassifiche = tabHostClassifiche;
    }

    public TextView getTxtColonneUser() {
        return txtColonneUser;
    }

    public void setTxtColonneUser(TextView txtColonneUser) {
        this.txtColonneUser = txtColonneUser;
    }

    public TextView getTxtColonneMotto() {
        return txtColonneMotto;
    }

    public void setTxtColonneMotto(TextView txtColonneMotto) {
        this.txtColonneMotto = txtColonneMotto;
    }

    public ImageView getImgColonneUser() {
        return imgColonneUser;
    }

    public void setImgColonneUser(ImageView imgColonneUser) {
        this.imgColonneUser = imgColonneUser;
    }

    public Spinner getSpnGiocatori() {
        return spnGiocatori;
    }

    public void setSpnGiocatori(Spinner spnGiocatori) {
        this.spnGiocatori = spnGiocatori;
    }

    public ListView getLvColonnaPropria() {
        return lvColonnaPropria;
    }

    public void setLvColonnaPropria(ListView lvColonnaPropria) {
        this.lvColonnaPropria = lvColonnaPropria;
    }

    public TextView gettRisCasa() {
        return tRisCasa;
    }

    public void settRisCasa(TextView tRisCasa) {
        this.tRisCasa = tRisCasa;
    }

    public TextView gettRisFuori() {
        return tRisFuori;
    }

    public void settRisFuori(TextView tRisFuori) {
        this.tRisFuori = tRisFuori;
    }

    public Button getCmdRisCasaMeno() {
        return cmdRisCasaMeno;
    }

    public void setCmdRisCasaMeno(Button cmdRisCasaMeno) {
        this.cmdRisCasaMeno = cmdRisCasaMeno;
    }

    public Button getCmdRisCasaPiu() {
        return cmdRisCasaPiu;
    }

    public void setCmdRisCasaPiu(Button cmdRisCasaPiu) {
        this.cmdRisCasaPiu = cmdRisCasaPiu;
    }

    public Button getCmdRisFuoriMeno() {
        return cmdRisFuoriMeno;
    }

    public void setCmdRisFuoriMeno(Button cmdRisFuoriMeno) {
        this.cmdRisFuoriMeno = cmdRisFuoriMeno;
    }

    public Button getCmdRisFuoriPiu() {
        return cmdRisFuoriPiu;
    }

    public void setCmdRisFuoriPiu(Button cmdRisFuoriPiu) {
        this.cmdRisFuoriPiu = cmdRisFuoriPiu;
    }

    public TextView getTxtColonna() {
        return txtColonna;
    }

    public void setTxtColonna(TextView txtColonna) {
        this.txtColonna = txtColonna;
    }

    public TabHost getTabHost() {
        return tabHost;
    }

    public void setTabHost(TabHost tabHost) {
        this.tabHost = tabHost;
    }

    public Button getCmdSalvaTutto() {
        return cmdSalvaTutto;
    }

    public void setCmdSalvaTutto(Button cmdSalvaTutto) {
        this.cmdSalvaTutto = cmdSalvaTutto;
    }

    public CheckBox getCheckFR() {
        return checkFR;
    }

    public void setCheckFR(CheckBox checkFR) {
        this.checkFR = checkFR;
    }

    public ImageView getImgJolly() {
        return imgJolly;
    }

    public void setImgJolly(ImageView imgJolly) {
        this.imgJolly = imgJolly;
    }

    public TextView getTxtDettaglio() {
        return txtDettaglio;
    }

    public void setTxtDettaglio(TextView txtDettaglio) {
        this.txtDettaglio = txtDettaglio;
    }

    public Button getCmdIndietro() {
        return cmdIndietro;
    }

    public void setCmdIndietro(Button cmdIndietro) {
        this.cmdIndietro = cmdIndietro;
    }

    public Button getCmdAvanti() {
        return cmdAvanti;
    }

    public void setCmdAvanti(Button cmdAvanti) {
        this.cmdAvanti = cmdAvanti;
    }

    public TextView gettNumeroPartita() {
        return tNumeroPartita;
    }

    public void settNumeroPartita(TextView tNumeroPartita) {
        this.tNumeroPartita = tNumeroPartita;
    }

    public TextView getTxtCasa() {
        return txtCasa;
    }

    public void setTxtCasa(TextView txtCasa) {
        this.txtCasa = txtCasa;
    }

    public TextView getTxtFuori() {
        return txtFuori;
    }

    public void setTxtFuori(TextView txtFuori) {
        this.txtFuori = txtFuori;
    }

    public ImageView getImgCasa() {
        return imgCasa;
    }

    public void setImgCasa(ImageView imgCasa) {
        this.imgCasa = imgCasa;
    }

    public ImageView getImgFuori() {
        return imgFuori;
    }

    public void setImgFuori(ImageView imgFuori) {
        this.imgFuori = imgFuori;
    }

    public CheckBox getCheck1() {
        return check1;
    }

    public void setCheck1(CheckBox check1) {
        this.check1 = check1;
    }

    public CheckBox getCheckX() {
        return checkX;
    }

    public void setCheckX(CheckBox checkX) {
        this.checkX = checkX;
    }

    public CheckBox getCheck2() {
        return check2;
    }

    public void setCheck2(CheckBox check2) {
        this.check2 = check2;
    }

    public TextView gettChiusura() {
        return tChiusura;
    }

    public void settChiusura(TextView tChiusura) {
        this.tChiusura = tChiusura;
    }

    public TextView gettPosClass() {
        return tPosClass;
    }

    public void settPosClass(TextView tPosClass) {
        this.tPosClass = tPosClass;
    }

    public TextView gettPosCamp() {
        return tPosCamp;
    }

    public void settPosCamp(TextView tPosCamp) {
        this.tPosCamp = tPosCamp;
    }

    public TextView gettPosRis() {
        return tPosRis;
    }

    public void settPosRis(TextView tPosRis) {
        this.tPosRis = tPosRis;
    }

    public TextView gettTotVers() {
        return tTotVers;
    }

    public void settTotVers(TextView tTotVers) {
        this.tTotVers = tTotVers;
    }

    public TextView gettVinti() {
        return tVinti;
    }

    public void settVinti(TextView tVinti) {
        this.tVinti = tVinti;
    }

    public TextView gettReali() {
        return tReali;
    }

    public void settReali(TextView tReali) {
        this.tReali = tReali;
    }

    public TextView gettPresi() {
        return tPresi;
    }

    public void settPresi(TextView tPresi) {
        this.tPresi = tPresi;
    }

    public TextView gettAmici() {
        return tAmici;
    }

    public void settAmici(TextView tAmici) {
        this.tAmici = tAmici;
    }

    public TextView gettTappi() {
        return tTappi;
    }

    public void settTappi(TextView tTappi) {
        this.tTappi = tTappi;
    }

    public TextView gettVittorie() {
        return tVittorie;
    }

    public void settVittorie(TextView tVittorie) {
        this.tVittorie = tVittorie;
    }

    public TextView gettUltimi() {
        return tUltimi;
    }

    public void settUltimi(TextView tUltimi) {
        this.tUltimi = tUltimi;
    }

    public TextView gettSecondi() {
        return tSecondi;
    }

    public void settSecondi(TextView tSecondi) {
        this.tSecondi = tSecondi;
    }

    public TextView gettAnno() {
        return tAnno;
    }

    public void settAnno(TextView tAnno) {
        this.tAnno = tAnno;
    }

    public TextView gettGiornata() {
        return tGiornata;
    }

    public void settGiornata(TextView tGiornata) {
        this.tGiornata = tGiornata;
    }

    public TextView gettStatoConcorso() {
        return tStatoConcorso;
    }

    public void settStatoConcorso(TextView tStatoConcorso) {
        this.tStatoConcorso = tStatoConcorso;
    }

    public TextView gettMotto() {
        return tMotto;
    }

    public void settMotto(TextView tMotto) {
        this.tMotto = tMotto;
    }

    public TextView getLayErrore() {
        return layErrore;
    }

    public void setLayErrore(TextView layErrore) {
        this.layErrore = layErrore;
    }

    public EditText getTxtNick() {
        return txtNick;
    }

    public void setTxtNick(EditText txtNick) {
        this.txtNick = txtNick;
    }

    public LinearLayout getLayLogin() {
        return layLogin;
    }

    public void setLayLogin(LinearLayout layLogin) {
        this.layLogin = layLogin;
    }

    public LinearLayout getLayLoggato() {
        return layLoggato;
    }

    public void setLayLoggato(LinearLayout layLoggato) {
        this.layLoggato = layLoggato;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public TextView gettNomeUtente() {
        return tNomeUtente;
    }

    public void settNomeUtente(TextView tNomeUtente) {
        this.tNomeUtente = tNomeUtente;
    }
}

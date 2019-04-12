package looigi.totomioii_2.variabili;

public class StrutturaDatiGiocatore {
    //-------- Singleton ----------//
    private static StrutturaDatiGiocatore instance = null;

    private StrutturaDatiGiocatore() {
    }

    public static StrutturaDatiGiocatore getInstance() {
        if (instance == null) {
            instance = new StrutturaDatiGiocatore();
        }

        return instance;
    }

    private String Nick="";
    private String Password="";
    private Integer idUtente;
    private String Testo;
    private String Tipologia;
    private int PosClassifica;
    private int PosCampionato;
    private int PosRisultati;
    private float TotVersamento;
    private float Vinti;
    private float Reali;
    private float Presi;
    private float Amici;
    private int NumeroTappi;
    private int Vittorie;
    private int UltimiPosti;
    private int SecondiPosti;
    private int Messaggi;
    private String Cognome="";
    private String Nome="";
    private String EMail="";

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getMessaggi() {
        return Messaggi;
    }

    public void setMessaggi(int messaggi) {
        Messaggi = messaggi;
    }

    public String getTesto() {
        return Testo;
    }

    public void setTesto(String testo) {
        Testo = testo;
    }

    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }

    public int getPosClassifica() {
        return PosClassifica;
    }

    public void setPosClassifica(int posClassifica) {
        PosClassifica = posClassifica;
    }

    public int getPosCampionato() {
        return PosCampionato;
    }

    public void setPosCampionato(int posCampionato) {
        PosCampionato = posCampionato;
    }

    public int getPosRisultati() {
        return PosRisultati;
    }

    public void setPosRisultati(int posRisultati) {
        PosRisultati = posRisultati;
    }

    public float getTotVersamento() {
        return TotVersamento;
    }

    public void setTotVersamento(float totVersamento) {
        TotVersamento = totVersamento;
    }

    public float getVinti() {
        return Vinti;
    }

    public void setVinti(float vinti) {
        Vinti = vinti;
    }

    public float getReali() {
        return Reali;
    }

    public void setReali(float reali) {
        Reali = reali;
    }

    public float getPresi() {
        return Presi;
    }

    public void setPresi(float presi) {
        Presi = presi;
    }

    public float getAmici() {
        return Amici;
    }

    public void setAmici(float amici) {
        Amici = amici;
    }

    public int getNumeroTappi() {
        return NumeroTappi;
    }

    public void setNumeroTappi(int numeroTappi) {
        NumeroTappi = numeroTappi;
    }

    public int getVittorie() {
        return Vittorie;
    }

    public void setVittorie(int vittorie) {
        Vittorie = vittorie;
    }

    public int getUltimiPosti() {
        return UltimiPosti;
    }

    public void setUltimiPosti(int ultimiPosti) {
        UltimiPosti = ultimiPosti;
    }

    public int getSecondiPosti() {
        return SecondiPosti;
    }

    public void setSecondiPosti(int secondiPosti) {
        SecondiPosti = secondiPosti;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }
}

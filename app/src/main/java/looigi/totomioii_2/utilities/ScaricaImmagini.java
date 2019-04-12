package looigi.totomioii_2.utilities;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.variabili.VariabiliStatiche;

public class ScaricaImmagini {
    private static ScaricaImmagini instance = null;

    private ScaricaImmagini() {
    }

    public static ScaricaImmagini getInstance() {
        if (instance == null) {
            instance = new ScaricaImmagini();
        }

        return instance;
    }

    private List<String> UrlDaScaricare;
    private List<String> CartellaDestinazione;
    private List<String> nomeFileDestinazione;
    private List<ImageView> Immagini;
    private Thread tScoda=null;
    private String RitornoDownload;

    public void setRitornoDownload(String ritornoDaWS) {
        RitornoDownload = ritornoDaWS;
    }

    public void AggiungeImmagineDaScaricare(ImageView v, String url, String sCartella, String sNomeFile) {
        if (UrlDaScaricare ==null) {
            UrlDaScaricare =new ArrayList<>();
        }
        if (Immagini==null) {
            Immagini=new ArrayList<>();
        }
        if (CartellaDestinazione==null) {
            CartellaDestinazione=new ArrayList<>();
        }
        if (nomeFileDestinazione==null) {
            nomeFileDestinazione=new ArrayList<>();
        }

        UrlDaScaricare.add(url);
        Immagini.add(v);
        CartellaDestinazione.add(sCartella);
        nomeFileDestinazione.add(sNomeFile);

        if (tScoda==null || tScoda.isInterrupted()) {
            tScoda = new EffettuaDownload();
            tScoda.setPriority(Thread.MIN_PRIORITY);
            tScoda.start();
        }
    }

    private class EffettuaDownload extends Thread {
        @Override
        public void run() {
            Ciclo();
        }
    }

    private void Ciclo() {
        Boolean Cont=true;

        while (Cont) {
            String s;

            while (!UrlDaScaricare.isEmpty()) {
                s = UrlDaScaricare.get(0);

                if (s != null) {
                    String ritorno= EsegueDownload(s, Immagini.get(0), CartellaDestinazione.get(0), nomeFileDestinazione.get(0));

                    if (!ritorno.equals("*")) {
                    }

                    UrlDaScaricare.remove(0);
                    Immagini.remove(0);
                    CartellaDestinazione.remove(0);
                    nomeFileDestinazione.remove(0);
                } else {
                    Cont=false;
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            if (UrlDaScaricare.isEmpty()) {
                Cont=false;
            }
        }

        if (tScoda!=null) {
            tScoda.interrupt();
            tScoda=null;
        }
    }

    private String EsegueDownload(String urlDaScaricare, ImageView v, String sCartella, String sFile) {
        int id = VariabiliStatiche.getInstance().getMainContext().getResources().getIdentifier("looigi.totomioii_2:drawable/sconosciuto", null, null);

        RitornoDownload="";

        DownloadPic a = new DownloadPic();
        a.setFiletto(sFile);
        a.setDirectory(sCartella);
        a.setFileDaDown(urlDaScaricare);
        a.setIdSconosciuto(id);
        a.setImmagine(v);
        a.startDownload();

        while(RitornoDownload.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        return RitornoDownload;
    }

}

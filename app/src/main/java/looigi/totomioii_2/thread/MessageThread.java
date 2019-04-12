package looigi.totomioii_2.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import looigi.totomioii_2.Messaggi;
import looigi.totomioii_2.utilities.FilesMethods;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class MessageThread {
    private boolean BloccaMessage;
    private Timer tTmrMessage;
    private String Indirizzo;
    private int Contatore;

    private static MessageThread instance = null;

    private MessageThread() {
        // Exists only to defeat instantiation.
        //this.context = context;
    }

    public static MessageThread getInstance() {
        if (instance == null) {
            instance = new MessageThread();
        }

        return instance;
    }

    public boolean isBloccaMessage() {
        return BloccaMessage;
    }

    public void setBloccaMessage(boolean bloccaMessage) {
        BloccaMessage = bloccaMessage;
    }

    public void start(Context context) {
        BloccaMessage =false;
        Contatore=0;

        InternalThread(context);
    }

    private void InternalThread(final Context context) {
        final String PercorsoDIR= Environment.getExternalStorageDirectory().getPath()+"/TotoMIO";
        final String NomeFileLog="Log.txt";

        if (tTmrMessage == null) {
            tTmrMessage = new Timer();
            tTmrMessage.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!BloccaMessage) {
                        if (StrutturaDatiGiocatore.getInstance().getIdUtente()>-1) {
                            Indirizzo= VariabiliStatiche.RadiceWS +"/MessaggiApp/"+
                                    Integer.toString(StrutturaDatiDiGioco.getInstance().getAnno())+"/"+
                                    Integer.toString(StrutturaDatiGiocatore.getInstance().getIdUtente())+".txt";

                            Contatore++;
                            Date currentTime = Calendar.getInstance().getTime();
                            FilesMethods.getInstance().WriteTextFile(PercorsoDIR,NomeFileLog,currentTime.toString()+":" + Integer.toString(Contatore));

                            if (Utility.getInstance().doesURLExist(Indirizzo)) {
                                String Ritorno = Utility.getInstance().LeggePaginaWEB(Indirizzo);

                                Utility.getInstance().PlayNotificationSound(context);

                                Intent intent = new Intent(context, Messaggi.class);
                                intent.putExtra("messaggio", Ritorno);
                                context.startActivity(intent);

                                BloccaMessage=true;
                            }
                        }
                    }
                }
            }, 0, 60000);
        }
    }

    public void StopMessageThread() {
        if (!BloccaMessage || tTmrMessage !=null) {
            BloccaMessage = true;

            if (tTmrMessage != null) {
                tTmrMessage.cancel();
                tTmrMessage.purge();
                tTmrMessage = null;
            }
        }
    }
}

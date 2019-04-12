package looigi.totomioii_2.autostart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class crea_notifica {
    private NotificationManager notificationManager;
    private Runnable runRiga;
    private int id;

    public void CreaNotifica(Context context) {
        NotificationCompat.Builder notificationBuilder;
        RemoteViews contentView;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationBuilder = new NotificationCompat.Builder(context);
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher);
            notificationBuilder.setOngoing(true);

            contentView = new RemoteViews(context.getPackageName(), R.layout.barra_notifica);
            setListenersTasti(context, contentView);

            notificationBuilder.setContent(contentView);
            notificationBuilder.setAutoCancel(true);

            notificationManager.notify(1, notificationBuilder.build());
            id=1;
        } else {
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, 0);

            Notification notification =  new NotificationCompat.Builder(context).setAutoCancel(true)
                    .setContentTitle("TotoMIO II 4 Android")
                    .setContentText("TotoMIO II 4 Android")
                    .setContentIntent(pi)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("TotoMIO II 4 Android")
                    .build();

            notificationManager =
                    (NotificationManager) context.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
            id=0;
        }

        AttendeEChiudeNotifica();
    }

    private void AttendeEChiudeNotifica() {
        final Handler hSelezionaRiga;

        hSelezionaRiga = new Handler();
        hSelezionaRiga.postDelayed(runRiga=new Runnable() {
            @Override
            public void run() {
                notificationManager.cancel(id);

                hSelezionaRiga.removeCallbacks(runRiga);
            }
        }, 10000);

    }
    private void setListenersTasti(Context context, RemoteViews view){
        view.setTextViewText(R.id.TextView01, "TotoMIO II 4 Android");

        Intent apre=new Intent(context, MainActivity.class);
        apre.putExtra("DO", "apre");
        PendingIntent pApre= PendingIntent.getActivity(context, 1, apre, 0);
        view.setOnClickPendingIntent(R.id.ImgApreNB, pApre);
    }
}

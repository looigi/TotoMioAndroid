package looigi.totomioii_2.utilities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import looigi.totomioii_2.BuildConfig;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class UpdateApp extends AsyncTask<String,Void,Void> {
    private ProgressDialog progressDialog;
    private String messErrore="";
    private Context context;

    public void setContext(Context contextf) {
        context = contextf;
    }

    private void ChiudeDialog() {
        try {
            progressDialog.dismiss();
        } catch (Exception ignored) {
        }
//
        if (!messErrore.isEmpty()) {
            VariabiliStatiche.getInstance().getMainActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Utility.getInstance().VisualizzaPOPUP(VariabiliStatiche.getInstance().getMainContext(),
                            "ERRORE: " + messErrore, false, 0, false);
                }
            });
        }
    }

    private void ApriDialog() {
        VariabiliStatiche.getInstance().getMainActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("Attendere Prego...\nControllo versione in corso...");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                } catch (Exception ignored) {
////
                }
            }
        });
    }

    @Override
    protected Void doInBackground(String... arg0) {
        ApriDialog();

        VariabiliStatiche.StaAggiornandoLaVersione=true;

        try {
            URL url = new URL(arg0[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.connect();

            String pathDestinazione = VariabiliStatiche.getInstance().PercorsoDIR;
            File file = new File(pathDestinazione);
            file.mkdirs();
            File outputFile = new File(file, "update.apk");
            if(outputFile.exists()){
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
//
            InputStream is = c.getInputStream();
//
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            Uri contentUri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    new File(VariabiliStatiche.getInstance().PercorsoDIR+"/update.apk"));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            VariabiliStatiche.getInstance().getMainContext().startActivity(intent);
            messErrore="";
        } catch (Exception ignored) {
            messErrore=ignored.getMessage();
        }

        ChiudeDialog();

        return null;
    }
}

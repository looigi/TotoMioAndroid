package looigi.totomioii_2.utilities;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FilesMethods {
    private String TAG="UTILITIES";
    private boolean isRestart = false;

    private static FilesMethods instance = null;

    private FilesMethods() {
    }

    public static FilesMethods getInstance() {
        if(instance == null) {
            instance = new FilesMethods();
        }

        return instance;
    }

    public boolean deleteFiles(File path)
    {
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret=this.deleteFiles(f);
            }
        }
        ret = path.delete();
        return ret;
    }

    public boolean ExistsFile(String PathDIR, String FileName) {
        File file = new File(PathDIR+"/"+FileName);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void AppendData(String PathDIR, String FileName, String data) {
        File file = new File(PathDIR+"/"+FileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        String contenuto=ReadTextFile(PathDIR, FileName);
        contenuto+=data;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.append(contenuto);
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteFiles(String PathDIR, String FileName) {
        File file = new File(PathDIR+"/"+FileName);
        if (file.exists()) {
            file.delete();
        } else {
        }
    }

    public void WriteTextFile(String PercorsoDIR, String NomeFile, String Stringa) {
        DeleteFiles(PercorsoDIR, NomeFile);

        File gpxfile = new File(PercorsoDIR, NomeFile);
        FileWriter writer;
        try {
            writer = new FileWriter(gpxfile,true);
            writer.append(Stringa);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadTextFile(String sDir, String sFile) {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File(sDir+"/"+sFile);

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    public void CopyFileFromAssets(Context context, String Origine, String PathDest, String FileName) {
        try {
            InputStream myInput = context.getAssets().open(Origine);
            String outFileName =PathDest + File.separator + FileName;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {

        }
    }
}

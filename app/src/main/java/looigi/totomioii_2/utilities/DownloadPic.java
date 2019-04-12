package looigi.totomioii_2.utilities;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPic {
	private String Directory = "";
	private String NomeFiletto="";
	private String FileDaDownloadare="";
	private ImageView imgView;
	private int idSconosciuto;

    public void startDownload() {
		DownloadFile downloadFile = new DownloadFile();
 		downloadFile.execute(FileDaDownloadare);
    }

	public void setImmagine(ImageView NomeImm) {
		imgView=NomeImm;
	}

	public void setIdSconosciuto(int id) { this.idSconosciuto=id; }

	public void setDirectory(String NomeDir) {
		Directory = NomeDir;
	}
	
	public void setFileDaDown(String NomeFileURL) {
		FileDaDownloadare = NomeFileURL;
	}
	
	public void setFiletto(String NomeFile) {
		NomeFiletto = NomeFile;
	}
	
	private class DownloadFile extends AsyncTask<String, Integer, String> {
	    @Override
	    protected String doInBackground(String... sUrl) {
			try {
				URL url = new URL(sUrl[0]);
				URLConnection connection = url.openConnection();
				connection.connect();
				int fileLength = connection.getContentLength();

				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(Directory+"/"+NomeFiletto);

				byte data[] = new byte[1024];
				long total = 0;
				int count;
				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress((int) (total * 100 / fileLength));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();

				ScaricaImmagini.getInstance().setRitornoDownload("ERROR: "+e.getMessage());
			}

	        return null;
	    }

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);

			if (Utility.getInstance().fileExistsInSD(NomeFiletto, Directory)) {
				imgView.setImageBitmap(BitmapFactory.decodeFile(Directory+"/"+NomeFiletto));
			} else {
				imgView.setImageResource(idSconosciuto);
			}

			ScaricaImmagini.getInstance().setRitornoDownload("*");
		}
	
	    /* @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        mProgressDialog.show();
	    } */
	
	    @Override
	    protected void onProgressUpdate(Integer... progress) {
	        super.onProgressUpdate(progress);
	    }
	}
    
}

package looigi.totomioii_2.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.ScriviMessaggi;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;

public class AdapterMessaggi extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterMessaggi(Context context, int textViewResourceId, List<String> objects)
	{	
		super(context, textViewResourceId, objects);
		
		this.context = context;
		this.lista=objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//if(convertView == null)
		//{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.lst_messaggi, null);
			if (Utility.getInstance().ePari(position)) {
				convertView.setBackgroundColor(Color.argb(255,228,228,228));
			} else {
				convertView.setBackgroundColor(Color.argb(255,255,255,255));
			}
		//}

		String riga = lista.get(position);
		final String Campi[]=riga.split(";");

		TextView Giocatore = (TextView) convertView.findViewById(R.id.txtGiocatore);
		ImageView imgG = (ImageView) convertView.findViewById(R.id.imgGioc);
		TextView Messaggio = (TextView) convertView.findViewById(R.id.txtMessaggio);
		TextView DataInvio = (TextView) convertView.findViewById(R.id.txtDataInvio);
		TextView Progressivo = (TextView) convertView.findViewById(R.id.txtProgressivo);

		convertView.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
				if (Campi[4].equals("N")) {
					DBRemoto dbr=new DBRemoto();
					dbr.MarcaMessaggioComeLetto(context, Campi[0]);
				}

				final Dialog d = new Dialog(context, R.style.Theme_AppCompat_Dialog);
				d.setContentView(R.layout.dialog_messaggi);

				TextView di = d.findViewById(R.id.txtDataInvio);
				di.setText(Campi[3]);

				TextView m = d.findViewById(R.id.txtMittente);
				m.setText(Campi[1]);

				TextView t = d.findViewById(R.id.txtTestoMessaggio);
				t.setText(Campi[2]);

				TextView p = d.findViewById(R.id.txtProgressivo);
				p.setText(Campi[0]);

				ImageView i = (ImageView) d.findViewById(R.id.imgMittente);
				Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[1]+".jpg", i);

				ImageView btnElimina=d.findViewById(R.id.imgEliminaMessaggio);
				btnElimina.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						AlertDialog.Builder builder = new AlertDialog.Builder(context);
						builder.setIcon(R.drawable.warning_dialog);
						builder.setTitle("Vuoi rimuovere il messaggio ?");
						builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								DBRemoto dbr=new DBRemoto();
								dbr.EliminaMessaggio(context, Campi[0]);

								d.cancel();
							}
						});

						builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								arg0.cancel();
							}
						});

						AlertDialog alert = builder.create();
						alert.show();
					}
				});

				ImageView btnRispondi=d.findViewById(R.id.imgRispondiMessaggio);
				btnRispondi.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						d.cancel();

						Intent intent = new Intent(context, ScriviMessaggi.class);
						Bundle b = new Bundle();
						b.putString("mittente", Campi[1]);
						b.putString("messaggio", Campi[2]);
						b.putInt("progressivo", Integer.parseInt(Campi[0]));
						intent.putExtras(b);
						context.startActivity(intent);
					}
				});

				d.show();
		   }
	   });

		String testo=Campi[2];
		if (testo.length()>30) {
			testo=testo.substring(0,27)+"...";
		}

		Giocatore.setText(Campi[1]);
		Messaggio.setText(testo);
		DataInvio.setText(Campi[3]);
		Progressivo.setText(Campi[0]);

		Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[1]+".jpg", imgG);

		return convertView;
	}
}

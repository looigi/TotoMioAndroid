package looigi.totomioii_2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.utilities.Utility;

public class AdapterEuropaLeague extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterEuropaLeague(Context context, int textViewResourceId, List<String> objects)
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
			convertView = inflater.inflate(R.layout.lst_europaleague, null);
			if (Utility.getInstance().ePari(position)) {
				convertView.setBackgroundColor(Color.argb(255,228,228,228));
			} else {
				convertView.setBackgroundColor(Color.argb(255,255,255,255));
			}
		//}

		String riga = lista.get(position);
		String Campi[]=riga.split(";");

		TextView Casa = (TextView) convertView.findViewById(R.id.txtCasa);
		ImageView imgCasa = (ImageView) convertView.findViewById(R.id.imgGiocCasa);
		TextView Fuori = (TextView) convertView.findViewById(R.id.txtFuori);
		ImageView imgFuori = (ImageView) convertView.findViewById(R.id.imgGiocFuori);
		TextView RisAndata = (TextView) convertView.findViewById(R.id.txtRisAndata);
		TextView RisRitorno= (TextView) convertView.findViewById(R.id.txtRisRitorno);
		TextView Tipologia = (TextView) convertView.findViewById(R.id.txtTipo);
		TextView Vincitore = (TextView) convertView.findViewById(R.id.txtVincitore);

		int turno = Integer.parseInt(Campi[0]);

		//if (!Utility.getInstance().ePari(turno)) {
			Tipologia.setText("Partita "+Campi[1]);
			Casa.setText(Campi[2]);
			Fuori.setText(Campi[3]);
			RisAndata.setText(Campi[4]);
			RisRitorno.setText(Campi[5]);
			Vincitore.setText(Campi[6]);
		//} else {
		//	Tipologia.setText("Ritorno");
		//	Casa.setText(Campi[2]);
		//	Fuori.setText(Campi[3]);
		//	RisAndata.setText(Campi[5]);
			//RisRitorno.setText(Campi[5]);
		//	Vincitore.setText(Campi[6]);
		//}

		if (!Campi[2].equals("-")) {
			Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[2] + ".jpg", imgCasa);
		}
		if (!Campi[3].equals("-")) {
			Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[3] + ".jpg", imgFuori);
		}

		return convertView;
	}
}

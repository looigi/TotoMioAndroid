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

public class AdapterClassificaSuddenDeath extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterClassificaSuddenDeath(Context context, int textViewResourceId, List<String> objects)
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
			convertView = inflater.inflate(R.layout.lst_sd, null);
			if (Utility.getInstance().ePari(position)) {
				convertView.setBackgroundColor(Color.argb(255,228,228,228));
			} else {
				convertView.setBackgroundColor(Color.argb(255,255,255,255));
			}
		//}

		String riga = lista.get(position);
		String Campi[]=riga.split(";");

		TextView Giocatore = (TextView) convertView.findViewById(R.id.txtGiocatore);
		ImageView imgG = (ImageView) convertView.findViewById(R.id.imgGioc);
		//imgG.setImageResource(0);

		TextView Squadra = (TextView) convertView.findViewById(R.id.txtSquadra);
		ImageView imgS = (ImageView) convertView.findViewById(R.id.imgSquadra);
		//imgS.setImageResource(0);

		Giocatore.setText(Campi[0]);
		Squadra.setText(Campi[1]);

		//imgG.setImageResource(R.drawable.ic_launcher);

		Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[0]+".jpg", imgG);
		Utility.getInstance().ImpostaImmagineSquadra(context, Campi[1]+"_Tonda.jpg", imgS);

		return convertView;
	}
}

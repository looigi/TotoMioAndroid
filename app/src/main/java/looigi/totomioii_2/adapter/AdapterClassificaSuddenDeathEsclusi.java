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

public class AdapterClassificaSuddenDeathEsclusi extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterClassificaSuddenDeathEsclusi(Context context, int textViewResourceId, List<String> objects)
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
			convertView = inflater.inflate(R.layout.lst_sudden_death, null);
			if (Utility.getInstance().ePari(position)) {
				convertView.setBackgroundColor(Color.argb(255,228,228,228));
			} else {
				convertView.setBackgroundColor(Color.argb(255,255,255,255));
			}
		//}

		String riga = lista.get(position);
		String Campi[]=riga.split(";");

		TextView Posizione = (TextView) convertView.findViewById(R.id.txtUscito);
		TextView Giocatore = (TextView) convertView.findViewById(R.id.txtGiocatoreClass);
		ImageView img = (ImageView) convertView.findViewById(R.id.imgGiocClass);
		TextView Motto = (TextView) convertView.findViewById(R.id.txtMottoClass);
		TextView Punti = (TextView) convertView.findViewById(R.id.txtPunti);

		Posizione.setText(Campi[0]);
		Giocatore.setText(Campi[1]);
		try {
			Motto.setText(Campi[2]);
		} catch (Exception ignored) {
			Motto.setText("");
		}
		Punti.setText(Campi[3]);

		Utility.getInstance().ImpostaImmagineGiocatore(context, Campi[1]+".jpg", img);

		return convertView;
	}
}

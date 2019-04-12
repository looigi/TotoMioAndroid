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

public class AdapterListaGiocatori extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterListaGiocatori(Context context, int textViewResourceId, List<String> objects)
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
			convertView = inflater.inflate(R.layout.lst_mancanti, null);
			if (Utility.getInstance().ePari(position)) {
				convertView.setBackgroundColor(Color.argb(255,228,228,228));
			} else {
				convertView.setBackgroundColor(Color.argb(255,255,255,255));
			}
		//}

		String riga = lista.get(position);
		String Campi[]=riga.split(";");

		TextView Giocatore = (TextView) convertView.findViewById(R.id.txtGiocatoreClass);
		ImageView img = (ImageView) convertView.findViewById(R.id.imgGiocClass);
		TextView Motto = (TextView) convertView.findViewById(R.id.txtMottoClass);

		String Nome=Campi[0];
		if (Nome.contains("-")) {
			Nome=Nome.substring(Nome.indexOf("-")+1,Nome.length());
		}
		Giocatore.setText(Nome);
		Motto.setText("");

		Utility.getInstance().ImpostaImmagineGiocatore(context, Nome+".jpg", img);

		return convertView;
	}
}

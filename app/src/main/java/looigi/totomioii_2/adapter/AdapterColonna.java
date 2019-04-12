package looigi.totomioii_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.utilities.Utility;

public class AdapterColonna extends ArrayAdapter
{
	private Context context;
	private List<String> lista;

	public AdapterColonna(Context context, int textViewResourceId, List<String> objects)
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
			convertView = inflater.inflate(R.layout.lst_colonna, null);
		//}

		String riga = lista.get(position);

		TextView Partita = (TextView) convertView.findViewById(R.id.txtPartita);
		TextView Casa = (TextView) convertView.findViewById(R.id.txtCasa);
		TextView Fuori = (TextView) convertView.findViewById(R.id.txtFuori);
		TextView Segno = (TextView) convertView.findViewById(R.id.txtSegno);
		TextView Risultato = (TextView) convertView.findViewById(R.id.txtRisultato);

		ImageView imgCasa = (ImageView) convertView.findViewById(R.id.imgCasa);
		ImageView imgFuori = (ImageView) convertView.findViewById(R.id.imgFuori);

		ImageView imgJolly = (ImageView) convertView.findViewById(R.id.imgJolly);
		ImageView imgFR = (ImageView) convertView.findViewById(R.id.imgFR);

		String Campi[]=riga.split(";");
		Partita.setText(Integer.toString(Integer.parseInt(Campi[0])+1));
		Casa.setText(Campi[1]);
		Fuori.setText(Campi[2]);
		Segno.setText(Campi[3]);
		Risultato.setText(Campi[4]);

		if (Campi[5].equals("1")) {
			imgJolly.setVisibility(LinearLayout.VISIBLE);
		} else {
			imgJolly.setVisibility(LinearLayout.GONE);
		}

		if (Campi[6].equals("1")) {
			imgFR.setVisibility(LinearLayout.VISIBLE);
		} else {
			imgFR.setVisibility(LinearLayout.GONE);
		}

		String sCasa=Casa.getText()+"_Tonda.jpg";
		String sFuori=Fuori.getText()+"_Tonda.jpg";

		Utility.getInstance().ImpostaImmagineSquadra(context, sCasa, imgCasa);
		Utility.getInstance().ImpostaImmagineSquadra(context, sFuori, imgFuori);

		return convertView;
	}
}

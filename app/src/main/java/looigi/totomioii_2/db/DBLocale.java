package looigi.totomioii_2.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import looigi.totomioii_2.MainActivity;
import looigi.totomioii_2.variabili.StrutturaDatiDiGioco;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

import static android.content.Context.MODE_PRIVATE;

public class DBLocale {
	
	public void CreaDB(Context context) {
    	SQLiteDatabase myDB= null;

    	try {
			myDB = context.openOrCreateDatabase("DatiLocali"+ StrutturaDatiDiGioco.getInstance().getAnno(), MODE_PRIVATE, null);
			myDB.execSQL("CREATE TABLE IF NOT EXISTS Dati (idUtente int(5), Nick text, Attivo text, Datella text);");
			myDB.execSQL("CREATE TABLE IF NOT EXISTS Opzioni (Suono Text, Linguaggio Text);");
			myDB.close();
    	} catch(Exception ignored) {

		} finally {
		   if (myDB != null){
			   myDB.close();
		   }
		}
	}
		
    public void PulisceDB(Context context) {
		@SuppressWarnings("unused")
        SQLiteDatabase myDB= null;

    	try {
			myDB = context.openOrCreateDatabase("DatiLocali"+ StrutturaDatiDiGioco.getInstance().getAnno(), MODE_PRIVATE, null);
			String Sql="Delete From Dati;";
			myDB.execSQL(Sql);
    	} catch (Exception ignored) {
    		
    	}
    }

    public void SalvaNuovoUtente(Context context, int idUtente, String Nick, String Datella) {
		Context c=context;
		if (c==null) {
			c=VariabiliStatiche.getInstance().getMainContext();
		}

		SQLiteDatabase myDB = null;
		myDB = c.openOrCreateDatabase("DatiLocali"+ StrutturaDatiDiGioco.getInstance().getAnno(), MODE_PRIVATE, null);
	   	String Sql="";
	   	
	   	Sql="Delete From Dati;";
	   	myDB.execSQL(Sql);

	   	Sql="Insert Into Dati Values (" + idUtente +", '"+ Nick +"', 'S', '" +Datella+"');";
	   	myDB.execSQL(Sql);
	   	
	   	myDB.close();
    }
    
    public void SalvaOpzioni(Context context) {
		SQLiteDatabase myDB= null;
		myDB = context.openOrCreateDatabase("DatiLocali"+ StrutturaDatiDiGioco.getInstance().getAnno(), MODE_PRIVATE, null);
	   	String Sql="";
	   	String Suono="";
	   	
	   	Sql="Delete From Opzioni;";
	   	myDB.execSQL(Sql);

	   	Sql="Insert Into Opzioni Values ('"+Suono+"','"+ MainActivity.Linguaggio+"');";
	   	myDB.execSQL(Sql);
	   	
	   	myDB.close();
    }
    
	public boolean PrendeDatiUtente(Context context) {
		int NumeroUtente=-1;
		String Nick="";
		Boolean Ok=false;
		
    	SQLiteDatabase myDB= null;
		myDB = context.openOrCreateDatabase("DatiLocali"+ StrutturaDatiDiGioco.getInstance().getAnno(), MODE_PRIVATE, null);
	   	String Sql="SELECT idUtente, Nick, Attivo FROM Dati;";
		Cursor c = myDB.rawQuery(Sql , null);
		c.moveToFirst();
		try {
			NumeroUtente=c.getInt(0);
			Nick=c.getString(1);
			Ok=true;
		} catch (Exception e) {
			NumeroUtente=-1;
			Nick="";
			Ok=false;
		}
		c.close();

		/* Utility.getInstance().VisualizzaPOPUP(VariabiliStatiche.getInstance().getMainContext(),
				"Utente: "+Nick+" - Numero: "+NumeroUtente+" - Anno: "+
						StrutturaDatiDiGioco.getInstance().getAnno(), false,0,
				false); */

		String Suono="S";
		String Linguaggio="ITALIANO";
		Boolean Ok2;

	   	Sql="SELECT * FROM Opzioni;";
		c = myDB.rawQuery(Sql , null);
		c.moveToFirst();
		try {
			Suono=c.getString(0);
			Linguaggio=c.getString(1);
			Ok2=true;
		} catch (Exception e) {
			Ok2=false;
		}
		c.close();

		if (!Ok2) {
			Sql="Insert Into Opzioni Values ('"+Suono+"', '"+Linguaggio+"')";
		   	myDB.execSQL(Sql);
		}
		
		myDB.close();
		
		StrutturaDatiGiocatore.getInstance().setIdUtente(NumeroUtente);
		StrutturaDatiGiocatore.getInstance().setNick(Nick);
		
		MainActivity.Linguaggio=Linguaggio;
		
		return Ok;
	}
}

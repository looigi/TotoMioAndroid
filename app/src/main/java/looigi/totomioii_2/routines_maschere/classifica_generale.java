package looigi.totomioii_2.routines_maschere;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import looigi.totomioii_2.R;
import looigi.totomioii_2.adapter.AdapterClassificaGenerale;
import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class classifica_generale {
    //-------- Singleton ----------//
    private static classifica_generale instance = null;

    private classifica_generale() {
    }

    public static classifica_generale getInstance() {
        if (instance == null) {
            instance = new classifica_generale();
        }

        return instance;
    }

    private Context context;

    public void VisualizzaClassifica(Context context) {
        this.context=context;
        DBRemoto dbr=new DBRemoto();
        dbr.RitornaClassificaGenerale(context);
    }

    /*public void CreaTabs(Context context) {
        this.context=context;

        if (VariabiliStatiche.getInstance().getQuantiTabsClassifica()==-1) {
            List<String> titoli = new ArrayList<>();
            titoli.add("Generale");
            titoli.add("Risultati");
            titoli.add("Campionato");

            VariabiliStatiche.getInstance().getTabHostClassifiche().setup();
            VariabiliStatiche.getInstance().getTabHostClassifiche().addTab(VariabiliStatiche.getInstance().getTabHostClassifiche().newTabSpec("tabview1").setContent(R.id.tabC1).setIndicator(titoli.get(0)));
            VariabiliStatiche.getInstance().getTabHostClassifiche().addTab(VariabiliStatiche.getInstance().getTabHostClassifiche().newTabSpec("tabview2").setContent(R.id.tabC2).setIndicator(titoli.get(1)));
            VariabiliStatiche.getInstance().getTabHostClassifiche().addTab(VariabiliStatiche.getInstance().getTabHostClassifiche().newTabSpec("tabview3").setContent(R.id.tabC3).setIndicator(titoli.get(2)));
            VariabiliStatiche.getInstance().setQuantiTabsClassifica(3);

            VariabiliStatiche.getInstance().getTabHostClassifiche().setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String arg0) {
                    setTabColor(VariabiliStatiche.getInstance().getTabHostClassifiche());

                    EsegueRoutineSuTab(VariabiliStatiche.getInstance().getTabHostClassifiche());
                }
            });

            TabWidget tw = (TabWidget) VariabiliStatiche.getInstance().getTabHostClassifiche().findViewById(android.R.id.tabs);

            for (int i = 0; i < VariabiliStatiche.getInstance().getQuantiTabsClassifica(); i++) {
                View tabView = tw.getChildTabViewAt(i);
                tabView.getLayoutParams().width = 480;
                tabView.setBackgroundColor(Color.argb(200, 20, 20, 150));

                TextView tv = (TextView) tabView.findViewById(android.R.id.title);
                tv.setText(titoli.get(i));
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(15);
            }

            DBRemoto dbr=new DBRemoto();
            dbr.RitornaClassificaGenerale(context);

            setTabColor(VariabiliStatiche.getInstance().getTabHostClassifiche());
        }
    }

    private static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.argb(200,20,20,150));
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(15);
        }

        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.WHITE);
        TextView tv = (TextView) tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).findViewById(android.R.id.title);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(15);
    }

    private void EsegueRoutineSuTab(TabHost tabhost) {
        int numeroTab=tabhost.getCurrentTab();
        DBRemoto dbr=new DBRemoto();

        switch(numeroTab) {
            case 0:
                dbr.RitornaClassificaGenerale(context);
                break;
            case 1:
                dbr.RitornaClassificaRisultati(context);
                break;
            case 2:
                dbr.RitornaClassificaCampionato(context);
                break;
        }
    } */

    public void VisualizzaClassificaGenerale(Context context, String Classifica) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaGenerale arrayAdapter = new AdapterClassificaGenerale(
                context,
                R.layout.lst_classifica,
                lista);

        VariabiliStatiche.getInstance().getLvClassificaGenerale().setAdapter(arrayAdapter);
    }

    /* public void VisualizzaClassificaRisultati(String Classifica) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaRisultati arrayAdapter = new AdapterClassificaRisultati(
                context,
                R.layout.lst_classifica,
                lista);

        VariabiliStatiche.getInstance().getLvClassificaRisultati().setAdapter(arrayAdapter);
    }

    public void VisualizzaCoppaItalia(String Classifica) {
        List<String> lista = new ArrayList<>();
        String Campi[]=Classifica.split("§");

        for (String riga : Campi) {
            lista.add(riga);
        }

        AdapterClassificaCampionato arrayAdapter = new AdapterClassificaCampionato(
                context,
                R.layout.lst_classifica,
                lista);

        VariabiliStatiche.getInstance().getLvClassificaCampionato().setAdapter(arrayAdapter);
    } */
}

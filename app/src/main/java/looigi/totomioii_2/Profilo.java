package looigi.totomioii_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import looigi.totomioii_2.db.DBRemoto;
import looigi.totomioii_2.utilities.Utility;
import looigi.totomioii_2.variabili.StrutturaDatiGiocatore;
import looigi.totomioii_2.variabili.VariabiliStatiche;

public class Profilo extends AppCompatActivity {
    private Context context;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilo);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        context = this;
        act=this;

        ImageView imgUser = findViewById(R.id.imgUser);
        //imgUser.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //        startActivityForResult(i, 1);
        //    }
        //});

        Utility.getInstance().ImpostaImmagineGiocatore(context, StrutturaDatiGiocatore.getInstance().getNick()+".jpg", imgUser);

        final EditText t = findViewById(R.id.txtUser);
        t.setText(StrutturaDatiGiocatore.getInstance().getNick());

        final EditText c = findViewById(R.id.txtCognome);
        c.setText(StrutturaDatiGiocatore.getInstance().getCognome());

        final EditText n = findViewById(R.id.txtNome);
        n.setText(StrutturaDatiGiocatore.getInstance().getNome());

        final EditText e = findViewById(R.id.txtEMail);
        e.setText(StrutturaDatiGiocatore.getInstance().getEMail());

        final EditText m = findViewById(R.id.txtMotto);
        m.setText(StrutturaDatiGiocatore.getInstance().getTesto());

        final EditText p = findViewById(R.id.txtPassword);
        p.setText(StrutturaDatiGiocatore.getInstance().getPassword());

        ImageButton imgSalva = findViewById(R.id.imgSalva);
        imgSalva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (t.getText().toString().trim().isEmpty()) {
                    Utility.getInstance().VisualizzaPOPUP(context, "Inserire il nick name", false, -1, false);
                } else {
                    if (c.getText().toString().trim().isEmpty()) {
                        Utility.getInstance().VisualizzaPOPUP(context, "Inserire il cognome", false, -1, false);
                    } else {
                        if (n.getText()  .toString().trim().isEmpty()) {
                            Utility.getInstance().VisualizzaPOPUP(context, "Inserire il nome", false, -1, false);
                        } else {
                            if (e.getText().toString().trim().isEmpty()) {
                                Utility.getInstance().VisualizzaPOPUP(context, "Inserire l'email", false, -1, false);
                            } else {
                                if (!e.getText().toString().contains(".") || !e.getText().toString().contains("@") || e.getText().toString().trim().length()<5) {
                                    Utility.getInstance().VisualizzaPOPUP(context, "EMail non valida", false, -1, false);
                                } else {
                                    if (p.getText().toString().trim().isEmpty()) {
                                        Utility.getInstance().VisualizzaPOPUP(context, "Inserire la password", false, -1, false);
                                    } else {
                                        VariabiliStatiche.getInstance().setActPassaggio(act);

                                        String Nick = t.getText().toString().trim();
                                        String Motto = m.getText().toString().trim();
                                        String Password = p.getText().toString().trim();
                                        String EMail = e.getText().toString().trim();
                                        String Cognome = c.getText().toString().trim();
                                        String Nome = n.getText().toString().trim();

                                        DBRemoto dbr= new DBRemoto();
                                        dbr.SalvaDatiGiocatore(context, Nick, Motto, Password, EMail, Cognome, Nome);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        Utility.getInstance().ImpostaBottoniMaschera(context, ((Activity) context).getWindow().getDecorView(), this);

        VariabiliStatiche.getInstance().getActPassaggio().finish();
    }
}

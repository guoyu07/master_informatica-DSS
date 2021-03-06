package com.practicas.dss.bolivarfrancisco_p5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;


public class ResultsActivity extends Activity {

    private Button btn;
    private BaseAdapter adaptador;
    private DBHelperResults db = DBHelperResults.getInstance(this); // Base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);
        adaptador = new AdaptadorResults(this);
        ListView listView = (ListView) findViewById(R.id.listPuntuaciones);
        listView.setAdapter(adaptador);

        btn = (Button) findViewById(R.id.deleteResults);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarResultados();
            }
        });

        //lista = (LinearLayout) findViewById(R.id.listaPuntuaciones);
        //actualizarLista();
    }

    private void eliminarResultados() {
        new MaterialDialog.Builder(this)
                .title(R.string.delete_results)
                .content(R.string.delete_results_message) // Mensaje con los aciertos y fallos que se llevan
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        db.clear(); // Se borran las puntuaciones
                        finish(); // Se vuelve al menú principal
                    }
                })
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .cancelable(false)
                .show();
    }

}

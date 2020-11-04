package com.example.estereograma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    ArrayList<HashMap<String, String>> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String endpoint = "http://www.marcosdiasvendramini.com.br/Get/Estereogramas.aspx";
            listaDados = new BuscarDadosWeb().execute(endpoint).get();
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, listaDados, R.layout.modelo_item,
                    new String[] {"nome", "codigo"},
                    new int[] {R.id.lblNome, R.id.lblCodigo});
            setListAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        HashMap<String, String> item = listaDados.get(position);
        Intent tela = new Intent(MainActivity.this, DetalhesActivity.class);
        Bundle parametros = new Bundle();
        parametros.putString("nome", item.get("nome"));
        parametros.putString("imagem", item.get("imagem"));
        tela.putExtras(parametros);
        startActivity(tela);
    }
}
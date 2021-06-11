package com.example.macacosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Macacos_Activity extends AppCompatActivity {

    List<Macacos> macacosList;
    MacacoAdapter macacoAdapter;
    SQLiteDatabase meuBancoDeDados;
    ListView listViewMacacos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macaco_layout);

        listViewMacacos = findViewById(R.id.listarMacacosView);

        macacosList = new ArrayList<>();

        meuBancoDeDados = openOrCreateDatabase(MainActivity.NOME_BANCO_DE_DADOS, MODE_PRIVATE, null);

        visualizarMacacosDatabase();
    }

    private void visualizarMacacosDatabase() {

        Cursor cursorMacacos = meuBancoDeDados.rawQuery("SELECT * FROM macacos", null);

        if (cursorMacacos.moveToFirst()) {
            do {
                macacosList.add(new Macacos(
                        cursorMacacos.getInt(0),
                        cursorMacacos.getString(1),
                        cursorMacacos.getString(2),
                        cursorMacacos.getString(3),
                        cursorMacacos.getInt(4)
                ));
            } while (cursorMacacos.moveToNext());
        }
        cursorMacacos.close();

        macacoAdapter = new MacacoAdapter(this, R.layout.list_view_macaco, macacosList, meuBancoDeDados);

        listViewMacacos.setAdapter(macacoAdapter);
    }
}
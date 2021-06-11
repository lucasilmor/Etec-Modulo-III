package com.example.macacosapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MacacoAdapter extends ArrayAdapter<Macacos> {

    //Variaveis globais
    Context mCtx;
    int listaLayoutRes;
    List<Macacos> listaMacacos;
    SQLiteDatabase meuBancoDeDados;

    //Construtor da classe
    public MacacoAdapter(Context mCtx, int listaLayoutRes, List<Macacos> listaMacacos, SQLiteDatabase meuBancoDeDados) {
        super(mCtx, listaLayoutRes, listaMacacos);

        this.mCtx = mCtx;
        this.listaLayoutRes = listaLayoutRes;
        this.listaMacacos = listaMacacos;
        this.meuBancoDeDados = meuBancoDeDados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listaLayoutRes, null);

        final Macacos macacos = listaMacacos.get(position);

        TextView txtViewNome, txtViewEspecie, txtViewIdade, txtViewDataEntrada;

        txtViewNome = view.findViewById(R.id.txtNomeViewMacaco);
        txtViewEspecie = view.findViewById(R.id.txtEspecieViewMacaco);
        txtViewIdade = view.findViewById(R.id.txtIdadeViewMacaco);
        txtViewDataEntrada = view.findViewById(R.id.txtEntradaviewMacaco);

        txtViewNome.setText(macacos.getNome());
        txtViewEspecie.setText(macacos.getEspecie());
        txtViewIdade.setText(String.valueOf(macacos.getIdade()));
        txtViewDataEntrada.setText(macacos.getDataEntrada());

        Button btnExcluir, btnEditar;

        btnExcluir = view.findViewById(R.id.btnExcluirViewMacaco);
        btnEditar = view.findViewById(R.id.btnEditarViewMacaco);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarMacaco(macacos);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Deseja excluir o registro?");
                builder.setIcon(R.drawable.outline_cancel);
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String sql = "DELETE FROM macacos WHERE id = ?";

                        meuBancoDeDados.execSQL(sql, new Integer[]{macacos.getId()});
                        recarregarMacacosDB();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //somente vai voltar para tela.
                        recarregarMacacosDB();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;


    }

    public void alterarMacaco(final Macacos macacos) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.alterar_macaco, null);
        builder.setView(view);

        final EditText txtEditarMacaco = view.findViewById(R.id.txtEditarMacaco);
        final EditText txtEditarIdade = view.findViewById(R.id.txtEditarIdade);
        final Spinner spnEspecies = view.findViewById(R.id.spnEspecies);

        txtEditarMacaco.setText(macacos.getNome());
        txtEditarIdade.setText(String.valueOf(macacos.getIdade()));

        //Criando o janela de diálogo
        final AlertDialog dialog = builder.create();
        //Mostrando a janela de diálogo
        dialog.show();

        view.findViewById(R.id.btnAlterarMacaco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtEditarMacaco.getText().toString().trim();
                String idade = txtEditarIdade.getText().toString().trim();
                String especies = spnEspecies.getSelectedItem().toString().trim();

                if (nome.isEmpty()) {
                    txtEditarMacaco.setError("Preencha o campo nome");
                    txtEditarMacaco.requestFocus();
                    return;
                }
                if (idade.isEmpty()) {
                    txtEditarIdade.setError("Preencha o campo idade");
                    txtEditarIdade.requestFocus();
                    return;
                }

                String sql = "UPDATE macacos SET nome = ?, especie = ?, idade = ? WHERE id = ?";
                meuBancoDeDados.execSQL(sql,
                        new String[]{nome, especies, idade, String.valueOf(macacos.getId())});
                Toast.makeText(mCtx, "O Macaco foi alterado com sucesso!!!", Toast.LENGTH_LONG).show();

                recarregarMacacosDB();

                dialog.dismiss();
            }
        });

    }

    //Realizar um select na tabela
    public void recarregarMacacosDB() {
        Cursor cursorMacacos = meuBancoDeDados.rawQuery("SELECT * FROM macacos", null);
        if (cursorMacacos.moveToFirst()) {
            listaMacacos.clear();
            do {
                listaMacacos.add(new Macacos(
                        cursorMacacos.getInt(0),
                        cursorMacacos.getString(1),
                        cursorMacacos.getString(2),
                        cursorMacacos.getString(3),
                        cursorMacacos.getInt(4)
                ));
            } while (cursorMacacos.moveToNext());
        }
        cursorMacacos.close();
        notifyDataSetChanged();
    }


}


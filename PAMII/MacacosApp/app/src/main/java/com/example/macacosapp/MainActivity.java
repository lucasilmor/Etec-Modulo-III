package com.example.macacosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NOME_BANCO_DE_DADOS = "macaco.db";

    TextView lblMacacos;
    EditText txtNomeMacaco, txtIdadeMacaco;
    Spinner spnEspecies;

    Button btnAdicionaMacaco;

    SQLiteDatabase meuBancoDeDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblMacacos = findViewById(R.id.lblVisualizaMacaco);
        txtNomeMacaco = findViewById(R.id.txtNomeNovoMacaco);
        txtIdadeMacaco = findViewById(R.id.txtNovaIdadeDoMacaco);
        spnEspecies = findViewById(R.id.spnEspecies);

        btnAdicionaMacaco = findViewById(R.id.btnAdicionarMacaco);

        btnAdicionaMacaco.setOnClickListener(this);
        lblMacacos.setOnClickListener(this);

        meuBancoDeDados = openOrCreateDatabase(NOME_BANCO_DE_DADOS, MODE_PRIVATE, null);

        criarTabelaEmpregado();


        Spinner spinnerMacacos = findViewById(R.id.spnEspecies);


        spinnerMacacos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private boolean verificarEntrada(String nome, String salario) {
        if (nome.isEmpty()) {
            txtNomeMacaco.setError("Por favor Digite um nome");
            txtNomeMacaco.requestFocus();
            return false;
        }

        if (salario.isEmpty() || Integer.parseInt(salario) <= 0) {
            txtIdadeMacaco.setError("Por favor Digite a idade");
            txtIdadeMacaco.requestFocus();
            return false;
        }
        return true;
    }

    private void adicionarEmpregado() {

        String nomeMaca = txtNomeMacaco.getText().toString().trim();
        String idadeMaca = txtIdadeMacaco.getText().toString().trim();
        String especMaca = spnEspecies.getSelectedItem().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dataEntrada = simpleDateFormat.format(calendar.getTime());

        if (verificarEntrada(nomeMaca, idadeMaca)) {


            String insertSQL = "INSERT INTO macacos (" +
                    "nome, " +
                    "especie, " +
                    "dataEntrada," +
                    "idade)" +
                    "VALUES(?, ?, ?, ?);";


            meuBancoDeDados.execSQL(insertSQL, new String[]{nomeMaca, especMaca, dataEntrada, idadeMaca});

            Toast.makeText(getApplicationContext(), "Macaco adicionado com sucesso!!!", Toast.LENGTH_SHORT).show();

            limparCadastro();

        }

    }

    public void limparCadastro() {

        txtNomeMacaco.setText("");
        txtIdadeMacaco.setText("");
        txtNomeMacaco.requestFocus();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdicionarMacaco:
                adicionarEmpregado();
                break;
            case R.id.lblVisualizaMacaco:
                startActivity(new Intent(getApplicationContext(), Macacos_Activity.class));
                break;
        }

    }



    private void criarTabelaEmpregado() {
        meuBancoDeDados.execSQL(
                "CREATE TABLE IF NOT EXISTS macacos (" +
                        "id integer PRIMARY KEY AUTOINCREMENT," +
                        "nome varchar(200) NOT NULL," +
                        "especie varchar(200) NOT NULL," +
                        "dataEntrada datetime NOT NULL," +
                        "idade int NOT NULL );"
        );

    }

}
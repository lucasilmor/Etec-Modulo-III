package br.com.local.appjsonvolleyparserequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private Button btnParse;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse = findViewById(R.id.btnParse);
        mQueue = Volley.newRequestQueue(this);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        ListView list = (ListView) findViewById(R.id.txtResult);
        ArrayList<String> listItems=new ArrayList<String>();

        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        String url = "https://apimortalkombat.herokuapp.com/Personagens";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listItems.add("Personagens" + "\n\n");
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject personagens = response.getJSONObject(i);

                                int id = personagens.getInt("id");
                                String name = personagens.getString("nome");
                                String faccao = personagens.getString("facção");
                                int rating = personagens.getInt("rating");

                                listItems.add
                                                ("Nome: " + name + "\n\n"
                                                + "Facção: " + faccao + " \n\n "
                                                + "Força: " + String.valueOf(rating)
                                                + " \n\n " +  "\n\n");
                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
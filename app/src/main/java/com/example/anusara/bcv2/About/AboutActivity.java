package com.example.anusara.bcv2.About;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anusara.bcv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AboutActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> listname = new ArrayList<>();
    ArrayList<String> listcontent = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        listView = (ListView) findViewById(R.id.listView3);

        adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, listname);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AboutActivity.this,ShowAboutActivity.class);
                intent.putExtra("name", listname.get(position));
                intent.putExtra("content", listcontent.get(position));
//based on item add info to intent
                startActivity(intent);
            }




        });
        getList();
    }
    private void getList() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                getHttp http = new getHttp();
                String response = null;
                try {
//                    response = http.run("http://192.168.43.180/breast-cancer/postnews.php");
//                    response = http.run("http://192.168.1.2/breast-cancer/postnews.php");
                    response = http.run("http://10.10.11.105/breast-cancer/postnews.php");
//                    response = http.run("http://192.168.1.37/breast-cancer/postnews.php");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                try {
                    listname.clear();

                    JSONArray jsonArray = new JSONArray(string);

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject json_data = jsonArray.getJSONObject(i);
                        listname.add(i, json_data.getString("n_name"));
                        listcontent.add(i, json_data.getString("n_content"));
                        Log.e( "json_data: ", json_data.getString("n_name"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //adapter.notifyDataSetChanged();

                adapter.notifyDataSetChanged();
                Log.e( "onPostExecute: ", string);
            }
        }.execute();
    }



    public class getHttp {
        OkHttpClient client = new OkHttpClient();
        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            //adapter.notifyDataSetChanged();
            getList();

            Log.e("onActivityResult: ", "doo");

        }
    }

}

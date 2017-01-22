package com.example.anusara.bcv2.Member.MPost;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anusara.bcv2.R;
import com.example.anusara.bcv2.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MPostActivity extends AppCompatActivity {

    Button btn1;
    TextView txtResult;
    ListView listView;
    String user;


    ArrayList<String> listname = new ArrayList<>();
    ArrayList<String> listcontent = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("username");

        txtResult = (TextView) findViewById(R.id.txtResult);
        listView = (ListView) findViewById(R.id.listView);
//        btn1 = (FloatingActionButton) findViewById(R.id.postbutton);
//        btn1.setOnClickListener(this);
        adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1, listname);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MPostActivity.this,RegisterActivity.class);
                intent.putExtra("username",user);
                intent.putExtra("name", listname.get(position));
                intent.putExtra("content", listcontent.get(position));
                startActivityForResult(intent, 1);
//based on item add info to intent
//                startActivity(intent);
            }




        });

        getList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MPostActivity.this, RegisterActivity.class);
                intent.putExtra("username",user);
                startActivityForResult(intent, 1);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


    }

    private void getList() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                getHttp http = new getHttp();
                String response = null;
                try {
//                    response = http.run("http://192.168.1.2/breast-cancer/post.php");
//                    response = http.run("http://192.168.43.180/breast-cancer/post.php");
                    response = http.run("http://192.168.1.37/breast-cancer/post.php");
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
                        listname.add(i, json_data.getString("p_name"));
                        listcontent.add(i, json_data.getString("p_content"));
                        Log.e( "json_data: ", json_data.getString("p_name"));


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

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.postbutton:
//                Intent intent = new Intent(postsActivity.this, posts1Activity.class);
//                intent.putExtra("username",user);
//                startActivityForResult(intent, 1);
//                break;
//            default:
//                break;
//        }
//    }

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

package com.example.anusara.bcv2.GPS;

import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.anusara.bcv2.R;
import com.example.anusara.bcv2.RegisterActivity;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GPSActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    ArrayList<HashMap<String, String>> contactList;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        contactList = new ArrayList<>();

//        lv = (ListView) findViewById(R.id.listViewGPS);


//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                GPSActivity.getHttp http = new GPSActivity.getHttp();
                String response = null;
                try {
                    response = http.run("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=13.874013, 100.552032&radius=5000&types=hospital&sensor=true&key=AIzaSyCEqFyzssIB4MqKtue_Kt_cMG6_6rZRoxg&language=th&name=โรงพยาบาล");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);

                Log.e("onPostExecute: ", string + "GG");
                try {
                    JSONObject jsonObj = new JSONObject(string);

                    // Getting JSON Array node
                    JSONArray arrygps = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    for (int i = 0; i < arrygps.length(); i++) {
                        JSONObject c = arrygps.getJSONObject(i);

                        String name = c.getString("name");
                        String vicinity = c.getString("vicinity");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value

                        contact.put("name", name);
                        contact.put("vicinity", vicinity);


                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e("Json parsing error: ", e.getMessage().toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
                ListAdapter adapter = new SimpleAdapter(
                        GPSActivity.this, contactList,
                        R.layout.list_itemhospital, new String[]{"name", "vicinity"
                       }, new int[]{R.id.name,
                        R.id.vicinity});

                lv.setAdapter(adapter);

            }
        }.execute();
    }

    @Override
    public void onLocationChanged(Location location) {

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

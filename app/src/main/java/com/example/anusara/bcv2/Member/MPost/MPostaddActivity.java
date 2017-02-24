package com.example.anusara.bcv2.Member.MPost;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.anusara.bcv2.DataManager.DataAccountManager;
import com.example.anusara.bcv2.R;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MPostaddActivity extends AppCompatActivity {

    EditText edt_name, edt_contents;
    Button btn_insert;
    String user ;

    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpostadd);
        user = DataAccountManager.getInstance().getUsername();
//        Log.e( "Userrrrrrrrrrrrrrrrr: ","_________________________" );
//        Log.e( "Userrrrrrrrrrrrrrrrr: ",user + "" );

        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_contents = (EditText)findViewById(R.id.edt_contents);
        btn_insert = (Button)findViewById(R.id.btn_insert);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MPostaddActivity.this, MPostActivity.class);

                startActivity(intent);
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        getHttp http = new getHttp();
                        String response = null;
                        try {
//                            response = http.run("http://192.168.43.180/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.2/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.37/breast-cancer/insert2.php");
                            response = http.run("http://10.10.11.105/breast-cancer/insert2.php");
                            response = http.run("http://192.168.1.33/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.5/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.43/breast-cancer/insert2.php");
//                            response = http.run("http://172.19.237.81/breast-cancer/insert2.php");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return response;
                    }

                    @Override
                    protected void onPostExecute(String string) {
                        super.onPostExecute(string);

                        Log.e( "onPostExecute: ", string);
                    }
                }.execute();



            }
        });

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };


    public class getHttp {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", user)
                .addFormDataPart("p_name", edt_name.getText().toString())
                .addFormDataPart("p_content", edt_contents.getText().toString())
                .addFormDataPart("p_date",cal.getTime().toString())
                .build();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

}

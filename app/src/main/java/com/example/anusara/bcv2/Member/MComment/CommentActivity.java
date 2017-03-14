package com.example.anusara.bcv2.Member.MComment;

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
import com.example.anusara.bcv2.Member.MPost.MPostShowActivity;
import com.example.anusara.bcv2.Member.Questionnaire.MQuestionActivity;
import com.example.anusara.bcv2.R;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

    EditText comment;
    Button sub,can ;
    String user, id ;

    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        user = DataAccountManager.getInstance().getUsername();
        id = getIntent().getStringExtra("p_id");
        Log.e( "onCreate: ", id);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        comment = (EditText)findViewById(R.id.comment);
        sub = (Button)findViewById(R.id.button8);
        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(CommentActivity.this, MPostShowActivity.class);
                //startActivity(intent);

                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        getHttp http = new getHttp();
                        String response = null;
                        try {
//                            response = http.run("http://192.168.43.180/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.2/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.5/breast-cancer/insertcomment.php");
//                            response = http.run("http://192.168.43.180/breast-cancer/insertcomment.php");
//                            response = http.run("http://192.168.1.33/breast-cancer/insertcomment.php");
                            response = http.run("http://192.168.1.35/breast-cancer/insertcomment.php");
//                            response = http.run("http://192.168.1.37/breast-cancer/insertcomment.php");
//                            response = http.run("http://10.10.11.105/breast-cancer/insertcomment.php");
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
                        finish();

                        Log.e( "onPostExecute: ", string);
                    }
                }.execute();



            }


        }) ;

        can = (Button) findViewById(R.id.canbelb);
        can.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.canbelb) {
                    /*Intent intent = new Intent(getApplicationContext(), MPostShowActivity.class);
                    intent.putExtra("p_id",id);
                    startActivity(intent);*/
                    finish();

                }

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
                .addFormDataPart("p_id", id)
                .addFormDataPart("c_message", comment.getText().toString())
                .addFormDataPart("c_date",cal.getTime().toString())
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
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

}

package com.example.anusara.bcv2.Member.MTest;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.anusara.bcv2.DataManager.DataAccountManager;
import com.example.anusara.bcv2.Member.MPost.MPostaddActivity;
import com.example.anusara.bcv2.Member.MembermainActivity;
import com.example.anusara.bcv2.R;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MTSumActivity extends AppCompatActivity {

    TextView textShow, txtResult;
    int sum1,sum2,sum3,sum4,sum5,sum6; //คำตอบ
    Button savebut, agianbut;
    String para , user;

    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtsum);

        user = DataAccountManager.getInstance().getUsername();

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        sum1 = getIntent().getIntExtra("sum1", 0);
        sum2 = getIntent().getIntExtra("sum2", 0);
        sum3 = getIntent().getIntExtra("sum3", 0);
        sum4 = getIntent().getIntExtra("sum4", 0);
        sum5 = getIntent().getIntExtra("sum5", 0);
        sum6 = getIntent().getIntExtra("sum6", 0);

        textShow = (TextView) findViewById(R.id.textView15);
        Datasum();

        txtResult = (TextView) findViewById(R.id.txtResult);

        savebut = (Button) findViewById(R.id.savebutton);
        savebut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.savebutton) {
                    Intent intent = new Intent(getApplicationContext(), MembermainActivity.class);
                    startActivity(intent);
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            MTSumActivity.getHttp http = new MTSumActivity.getHttp();
                            String response = null;
                            try {
//                            response = http.run("http://192.168.43.180/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.2/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.37/breast-cancer/insert2.php");
                            response = http.run("http://10.10.11.105/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.33/breast-cancer/inserttotal.php");
//                            response = http.run("http://192.168.1.5/breast-cancer/insert2.php");
//                            response = http.run("http://192.168.1.43/breast-cancer/insert2.php");
//                            response = http.run("http://172.19.237.81/breast-cancer/insert2.php");
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Log.e( "GGGGGGGGGGGGG: ", response);
                            return response;
                        }

                        @Override
                        protected void onPostExecute(String string) {
                            super.onPostExecute(string);

                            Log.e( "onPostExecute: ", string);
                        }
                    }.execute();

//                    gethttp();

//                    OkHttpClient client = new OkHttpClient();
//
//                    RequestBody requestBody = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("username", user)
//                            .addFormDataPart("r_id", txtResult.getText().toString())
//                            .addFormDataPart("dateup",cal.getTime().toString())
////                            .addFormDataPart("dateup",cal.getText().toString())
//                            .build();

                }

            }
        });
        agianbut = (Button) findViewById(R.id.agianbutton);
        agianbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.agianbutton) {
                    Intent intent = new Intent(getApplicationContext(), MTestActivity.class);
                    intent.putExtra("sum1", sum1);
                    startActivity(intent);

                }

            }
        });


        getHttp http = new getHttp();
        String response = null;
        try {
//            response = http.run("http://192.168.1.2/breast-cancer/getString.php");
//            response = http.run("http://192.168.43.180/breast-cancer/getString.php");
//            response = http.run("http://192.168.1.5/breast-cancer/getString.php");
//            response = http.run("http://192.168.1.37/breast-cancer/getString.php");
            response = http.run("http://192.168.1.33/breast-cancer/getString.php");
//            response = http.run("http://10.10.11.105/breast-cancer/getString.php");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        txtResult.setText(response);

    }
    public class getHttp {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("r_id", para)
                .addFormDataPart("username", user)
                .addFormDataPart("r_id", txtResult.getText().toString())
                .addFormDataPart("dateup",cal.getTime().toString())
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
    public void Datasum() {
        int a = (sum1 + sum2 + sum3 + sum4 + sum5 + sum6);
        String ANS = new String();

        if (a == 0) {
            ANS = "เป็นปกติ";
            para = "000001";
        }
        if (a > 0) {
            ANS = "คุณควรไปพบแพทย์เพื่อตรวจให้แน่ใจว่าคุณมีความเสี่ยงหรือเปล่า";
            para = "000003";
        }
//        if ((a == 0) || (a == 1) || (a == 2) || (a == 3) || (a == 4)) {
//            ANS = "เป็นปกติ";
//            para = "000001";
//        }
//        if ((a == 5) || (a == 6) || (a == 7) || (a == 8)) {
//            ANS = "เสี่ยงต่อการเป็นมะเร็งเต้านม";
//            para = "000002";
//        }
//        if ((a == 9) || (a == 10) || (a == 11) || (a == 12)) {
//            ANS = "ตรวจพบว่าคุณเป็นมะเร็งเต้านม";
//            para = "000003";
//        }


        Toast.makeText(getApplicationContext(),a + "",Toast.LENGTH_LONG).show();
        textShow.setText(ANS);
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




    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }




    public int getSum1() {
        return sum1;
    }

    public void setSum1(int sum1) {
        this.sum1 = sum1;
    }

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    public int getSum3() {
        return sum3;
    }

    public void setSum3(int sum3) {
        this.sum3 = sum3;
    }

    public int getSum4() {
        return sum4;
    }

    public void setSum4(int sum4) {
        this.sum4 = sum4;
    }

    public int getSum5() {
        return sum5;
    }

    public void setSum5(int sum5) {
        this.sum5 = sum5;
    }

    public int getSum6() {
        return sum6;
    }

    public void setSum6(int sum6) {
        this.sum6 = sum6;
    }

}


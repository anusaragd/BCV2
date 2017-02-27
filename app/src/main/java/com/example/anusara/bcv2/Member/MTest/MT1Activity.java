package com.example.anusara.bcv2.Member.MTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.anusara.bcv2.R;

public class MT1Activity extends AppCompatActivity {

    RadioButton myOption1, myOption2, myOption3; //ปลุกกด
    Button btn1;
    String ans; //เก็บผลลัพธ์
    int sum1 = -1; //คำตอบ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt1);

        myOption1 = (RadioButton) findViewById(R.id.radio1);
        myOption2 = (RadioButton) findViewById(R.id.radio2);
//        myOption3 = (RadioButton) findViewById(R.id.radio3);

//        Condition(); // กำหนดค่าของปุ่ม

        btn1 = (Button) findViewById(R.id.nextbutton);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Condition(); // กำหนดค่าของปุ่ม
//                CheckedRadioButton();
                if (v.getId() == R.id.nextbutton) {
                    if( sum1 != -1) {
                        Intent intent = new Intent(getApplicationContext(), MT2Activity.class);
                        intent.putExtra("sum1", sum1);
                        startActivity(intent);
                    }
                    if(sum1 == -1) {
                        Intent intent = new Intent(getApplicationContext(), MT1Activity.class);
                        startActivity(intent);
                    }
                }

            }
        });


    }
    public void Condition(){
        if(myOption1.isChecked()){
            sum1 = 0;
        }
        if(myOption2.isChecked()){
            sum1 = 2;
        }

        if(sum1 == -1){
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
        }




//        ans = String.valueOf(sum1);
        Toast.makeText(getApplicationContext(),sum1 + "",Toast.LENGTH_LONG).show();

    }

}

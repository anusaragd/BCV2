package com.example.anusara.bcv2.Touch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.anusara.bcv2.Member.MTest.MT2Activity;
import com.example.anusara.bcv2.R;

public class IntroTouchActivity extends AppCompatActivity {

    Button next;
    int sum1,sum2,sum3; //คำตอบ
//    RadioButton myOption1, myOption2, myOption3; //ปลุกกด

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_touch);

//        myOption1 = (RadioButton) findViewById(R.id.radioButton);
//        myOption2 = (RadioButton) findViewById(R.id.radioButton2);
//        myOption3 = (RadioButton) findViewById(R.id.radioButton3);

        next = (Button) findViewById(R.id.button3);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button3) {
                    Intent intent = new Intent(getApplicationContext(), TouchActivity.class);
                    intent.putExtra("sum1", sum1);
                    intent.putExtra("sum2", sum2);
                    intent.putExtra("sum3", sum3);
                    startActivity(intent);

                }

            }
        });
    }
//    public void Condition(){
//        if(myOption1.isChecked()){
//            sum3 = 0;
//        }
//        if(myOption2.isChecked()){
//            sum3 = 2;
//        }
//        if(myOption3.isChecked()){
//            sum3 = 1;
//        }
////        ans = String.valueOf(sum3);
//        Toast.makeText(getApplicationContext(),sum1 + ""+sum2 + ""+sum3+"",Toast.LENGTH_LONG).show();
////        Toast.makeText(getApplicationContext(),ans,Toast.LENGTH_LONG).show();
//
//    }
}

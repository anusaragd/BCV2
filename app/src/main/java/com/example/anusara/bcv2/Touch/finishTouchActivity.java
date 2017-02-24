package com.example.anusara.bcv2.Touch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anusara.bcv2.Member.MTest.MT4Activity;
import com.example.anusara.bcv2.R;

public class finishTouchActivity extends AppCompatActivity {

    Button next;
    int sum1,sum2,sum3; //คำตอบ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_touch);

        next = (Button) findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.button2) {
//                    Intent intent = new Intent(getApplicationContext(), TouchActivity.class);
                    Intent intent = new Intent(getApplicationContext(), MT4Activity.class);
                    intent.putExtra("sum1", sum1);
                    intent.putExtra("sum2", sum2);
                    intent.putExtra("sum3", sum3);
                    startActivity(intent);

                }

            }
        });
    }
}

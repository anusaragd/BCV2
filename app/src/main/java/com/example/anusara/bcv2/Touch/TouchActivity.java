package com.example.anusara.bcv2.Touch;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anusara.bcv2.R;

import java.util.ArrayList;

public class TouchActivity extends AppCompatActivity {

    private GestureLibrary gLib;
    private static final String TAG = "gesture";

    int sum1, sum2, sum3, sum4; //คำตอบ
    Button next;
    TextView correct, incorrect;
    int count = 0;
    int countt = 0;
    boolean check = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            Log.w(TAG, "could not load gesture library");
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(handleGestureListener);

        sum1 = getIntent().getIntExtra("sum1", 0);
        sum2 = getIntent().getIntExtra("sum2", 0);
        sum3 = getIntent().getIntExtra("sum3", 0);

        correct = (TextView) findViewById(R.id.correct);
        incorrect = (TextView) findViewById(R.id.incorrect);


        next = (Button) findViewById(R.id.button6);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                if (v.getId() == R.id.button6) {
                    if (check) {
                        Intent intent = new Intent(getApplicationContext(), finishTouchActivity.class);
                        intent.putExtra("sum1", sum1);
                        intent.putExtra("sum2", sum2);
                        intent.putExtra("sum3", sum3);
                        intent.putExtra("sum4", sum4);
                        startActivity(intent);

                    }
                }


        });


//        next.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (v.getId() == R.id.button6) {
//                    Intent intent = new Intent(getApplicationContext(), finishTouchActivity.class);
//                    intent.putExtra("sum1", sum1);
//                    intent.putExtra("sum2", sum2);
//                    intent.putExtra("sum3", sum3);
//                    intent.putExtra("sum4", sum4);
//                    startActivity(intent);
//
//                }
//
//            }
//        });
    }

    /**
     * our gesture listener
     */
    private GestureOverlayView.OnGesturePerformedListener handleGestureListener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureView,
                                       Gesture gesture) {

            ArrayList<Prediction> predictions = gLib.recognize(gesture);

            // one prediction needed
            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                // checking prediction

                if (prediction.score > 1.0) {


                    Toast.makeText(TouchActivity.this, "ถูกต้องค่ะ",
                            Toast.LENGTH_SHORT).show();
                    count++;
                    correct.setText(" "+count);
                    check = true;

                }else {
                    Toast.makeText(TouchActivity.this, "ลองใหม่อีกครั้งนะคะ",
                            Toast.LENGTH_SHORT).show();
                    countt++;
                    incorrect.setText(" " +countt);
                    check = false;
                }
            }
        }

    };
}



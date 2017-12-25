package com.example.hp.assignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HTAG";
    private Button btnStartService;
    private Button btnStopService;
    private TextView txtShowTime;
    private EditText editTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = (Button) findViewById(R.id.btn_start_service);
        btnStopService = (Button) findViewById(R.id.btn_stop_service);
        editTime = (EditText) findViewById(R.id.edit_service_time);
        txtShowTime = (TextView) findViewById(R.id.txt_show_time);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String time = editTime.getText().toString();
                startService(new Intent(MainActivity.this , MySimpleService.class).putExtra("time", time));
                Log.d(TAG, "onClick: start");
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this , MySimpleService.class));
                Log.d(TAG, "onClick: Stop");
            }
        });


      /*  btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String time = editTime.getText().toString();
                startService(new Intent(MainActivity.this , MyIntentService.class).putExtra("time", time));
                Log.d(TAG, "onClick: start");
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this , MyIntentService.class));
                Log.d(TAG, "onClick: Stop");
            }
        });
*/




    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
       // txtShowTime.setText(event.getI());

        String str = Integer.toString(event.getI());
        txtShowTime.setText(str);

        Log.d(TAG, "onMessageEvent: MyMessage : " + str );
        Log.d(TAG, "onMessageEvent: Actual Integer Value : "+ event.getI());
    };

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {

        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

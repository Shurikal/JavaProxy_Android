package com.example.rob_control_panel;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rob_control_panel.Proxy.*;

public class MainActivity extends AppCompatActivity {

    private Rob_Connection rob1;
    private Connection_Handler robs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        robs = new Connection_Handler();
        new Thread(robs).start();
    }

    public void connect(View view){
        rob1 = new Rob_Connection("192.168.1.150",5551,"Rob1");
        rob1.cmd.writeCmd(22);
    }

    public void clear(View view){

    }

}


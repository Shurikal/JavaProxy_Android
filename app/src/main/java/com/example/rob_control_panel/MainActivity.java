package com.example.rob_control_panel;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

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
        TextView textView = findViewById(R.id.textView4);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText("");
        robs = new Connection_Handler(this);
        new Thread(robs).start();
    }

    public void connect(View view){
        rob1 = new Rob_Connection("169.254.1.1",2000,"Rob1");
    }

    public void clear(View view){

    }

    public void sendStart(View view){
        rob1.cmd.writeCmd(100);

    }

    public void sendWurfbereit(View view){
        rob1.cmd.writeCmd(4);
    }

    public void sendFangbereit(View view){
        rob1.cmd.writeCmd(1);
    }

    public void sendFahren(View view){
        rob1.cmd.writeCmd(2);
    }

    public void addText(String s) {
        TextView textView = findViewById(R.id.textView4);
        textView.append(s+"\r\n");
    }
}


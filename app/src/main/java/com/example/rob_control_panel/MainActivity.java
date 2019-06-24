package com.example.rob_control_panel;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.rob_control_panel.Proxy.*;

public class MainActivity extends AppCompatActivity {

    private Rob_Connection rob1,rob2;
    private Connection_Handler robs;

    private long starttime =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TextView textView = findViewById(R.id.textView4);
        textView.setText("");

        robs = new Connection_Handler(this);
        new Thread(robs).start();
    }

    public void connectRob1(View view){
        rob1 = new Rob_Connection("169.254.1.1",2000,"Rob1");
        //rob1 = new Rob_Connection("192.168.1.150",2000,"Rob1");
        robs.addRob(rob1);
        if(starttime ==0){
            starttime = System.currentTimeMillis();
        }
    }

    public void connectRob2(View view){
        rob2 = new Rob_Connection("169.254.1.2",2000,"Rob2");
        //rob2 = new Rob_Connection("192.168.1.150",2001,"Rob2");
        robs.addRob(rob2);
        if(starttime ==0){
            starttime = System.currentTimeMillis();
        }
    }

    public void disconnectRob1(View view){
        if(rob1 !=null) {
            rob1.disconnect();
            rob1 = null;
        }
    }

    public void disconnectRob2(View view){
        if(rob2 != null) {
            rob2.disconnect();
            rob2 = null;
        }
    }

    public void sendStartRob1(View view){
        sendRob1(100);
    }

    public void sendStartRob2(View view){
        sendRob2(100);
    }

    public void sendWurfbereitRob1(View view){
        sendRob1(4);
    }

    public void sendWurfbereitRob2(View view){
        sendRob2(4);
    }

    public void sendFangbereitRob1(View view){
        sendRob1(1);
    }

    public void sendFangbereitRob2(View view){
        sendRob2(1);
    }

    public void sendFahrenRob1(View view){
        sendRob1(2);
    }

    public void sendFahrenRob2(View view){
        sendRob2(2);
    }

    public void sendCustomRob1(View view){
        TextView textView = findViewById(R.id.editText2);
        int i = Integer.parseInt(""+textView.getText());
        sendRob1(i);
        textView.setText("");
    }

    public void sendCustomRob2(View view){
        TextView textView = findViewById(R.id.editText3);
        int i = Integer.parseInt(""+textView.getText());
        sendRob2(i);
        textView.setText("");
    }

    public void addText(String s) {

        Switch simpleSwitch = (Switch) findViewById(R.id.switch1);

        if(simpleSwitch.isChecked()) {
            ScrollView scrollView1 = (ScrollView) findViewById(R.id.scrollView_Log);
            scrollView1.fullScroll(View.FOCUS_DOWN);
        }
        TextView textView = findViewById(R.id.textView4);
        long currentTime = System.currentTimeMillis()-starttime;
        String time = (currentTime/1000 + "." + (currentTime-((currentTime/1000)*1000)) + ": ");
        textView.append(time + s+"\r\n");
    }

    private void sendRob1(int i){
        if(rob1 !=null){
            rob1.cmd.writeCmd(i);
            addText(i + " -> Rob1");
        }
    }

    private void sendRob2(int i){
        if(rob1 !=null){
            rob2.cmd.writeCmd(i);
            addText(i + " -> Rob2");
        }
    }
}


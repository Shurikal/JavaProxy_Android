package com.example.rob_control_panel.Proxy;
import android.app.Activity;

import com.example.rob_control_panel.MainActivity;

import java.util.ArrayList;

public class Connection_Handler extends Activity implements Runnable
{
    private ArrayList<Rob_Connection> robs;
    private MainActivity main;

    private int i;
    private Rob_Connection rob;


    public Connection_Handler(MainActivity main) {
        robs = new ArrayList<>();
        this.main = main;
    //this.gui = gui;
    }

    public void addRob(Rob_Connection rob){
        robs.add(rob);
    }

    public Rob_Connection getRob(int i){
        return robs.get(i);
    }

    @Override
    public void run() {
        while(true){

            for(Rob_Connection rob : robs){
                if(rob!= null){
                    while(rob.cmd.readCmd() == CmdInt.Type.Cmd){
                        i = rob.cmd.getInt();
                        this.rob = rob;
                        addText();
                        for(Rob_Connection rob1 : robs){
                            if(rob1 != rob){
                                rob1.cmd.writeCmd(i);
                            }
                        }
                    }
                }
            }

            try{
                Thread.sleep(5);
            }catch (Exception e){}
        }
    }


    private void addText(){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                main.addText(i + " <- " +rob.getName());


            }
        });
    }

}

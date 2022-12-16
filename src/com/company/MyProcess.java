package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyProcess   {
    public int arrivalTime;
    public int priority;
    public int burnTime;
    public int processId;


    public MyProcess(int processId,int arrivalTime, int priority, int burnTime){
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burnTime = burnTime;
        this.processId = processId;
    }
    public void run() throws IOException {

            ProcessBuilder pb = new ProcessBuilder("sleep","1000");
            java.lang.Process p = pb.start();
            p.destroy();
            burnTime--;
            if(burnTime >= 1){
                System.out.println("Process" + processId + " 1 saniye çalıştı "+ "Önceligi" +priority );
            }



    }


}

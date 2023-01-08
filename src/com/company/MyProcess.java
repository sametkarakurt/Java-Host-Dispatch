package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyProcess   {
    public int arrivalTime;
    public int priority;
    public int burnTime;
    public int processId;
    public int timeout;
    public int remainingtime;

    Colors color = new Colors();

    public String colorForPrint;




    public MyProcess(int processId,int arrivalTime, int priority, int burnTime){
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burnTime = burnTime;
        this.processId = processId;
        this.timeout=arrivalTime+20;
        this.remainingtime = burnTime;
        this.colorForPrint = (String) color.getAllColors().get(processId);

    }
    public void run(float counter) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("java", "-version");
        Process p = pb.start();
        p.destroy();
        


        if(remainingtime== burnTime){
                System.out.println(colorForPrint +counter+ " sn"+"  Process basladi (id: " + processId + " oncelik: " + priority + " kalan sure: " + burnTime + " sn)" );
        }else if (burnTime>=1){
            System.out.println(colorForPrint +counter+ " sn"+"  Process yurutuluyor (id: " + processId + " oncelik: " + priority + " kalan sure: " + burnTime + " sn)" );
        }

       Thread.sleep(1000);
        burnTime--;



    }


}

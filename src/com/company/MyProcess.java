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
    public String color_reset = color.color_reset;;


    public MyProcess(int processId,int arrivalTime, int priority, int burnTime, String colorCode){
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burnTime = burnTime;
        this.processId = processId;
        this.timeout=arrivalTime+20;
        this.remainingtime = burnTime;
        this.colorForPrint = colorCode;

    }
    public void run(float counter) throws IOException {

            ProcessBuilder pb = new ProcessBuilder("java","-version");
            Process p = pb.start();
            p.destroy();



        if(remainingtime== burnTime){
                System.out.println(colorForPrint +counter+ " sn"+"  Process başladı (id: " + processId + " öncelik: " + priority + " kalan süre: " + burnTime + " sn)" + color_reset);
            }else if (burnTime>=1){
                System.out.println(colorForPrint +counter+ " sn"+"  Process yürütülüyor (id: " + processId + " öncelik: " + priority + " kalan süre: " + burnTime + " sn)" + color_reset);
            }
             burnTime--;



    }


}

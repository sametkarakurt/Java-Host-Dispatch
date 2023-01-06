package com.company;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class FileRead {

    SecureRandom rand = new SecureRandom();
    Colors color = new Colors();
    int random_color;
    int upperbound = 8;


    public ArrayList<MyProcess> readFile (String fileName) throws IOException{
        File file = new File(fileName);
        FileReader fReader = new FileReader(file);
        String line;
        BufferedReader bReader = new BufferedReader(fReader);
        ArrayList<MyProcess> nodeArr = new ArrayList<MyProcess>();
        int counterProcess = 0;
        while ((line=bReader.readLine()) != null){ //Her bir satırın okunup parçalara ayrılma işlemi
            String[] arr = line.split(", ");
            int arrivalTime = Integer.parseInt(arr[0]);
            int priority = Integer.parseInt(arr[1]);
            int burnTime = Integer.parseInt(arr[2]);




            nodeArr.add(new MyProcess(counterProcess++,arrivalTime,priority,burnTime)); //Parçalara ayrılan verilerin ilgili işlemi yapması için uygulamaya gönderilecek diziye gönderilmesi
        }
        return nodeArr; //Veri dizisinin uygulamaya gönderilmesi
    }



}

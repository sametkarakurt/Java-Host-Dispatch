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
    String colorCode;

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
            int random_color = rand.nextInt(upperbound-1);

            if(random_color == 0)
            {
                colorCode = color.black;
            } else if (random_color == 1) {
                colorCode = color.red;
            }else if (random_color == 2) {
                colorCode = color.green;
            }else if (random_color == 3) {
                colorCode = color.blue;
            }else if (random_color == 4) {
                colorCode = color.purple;
            }else if (random_color == 5) {
                colorCode = color.yellow;
            }else if (random_color == 6) {
                colorCode = color.cyan;
            }else if (random_color == 7) {
                colorCode = color.white;
            }

            nodeArr.add(new MyProcess(counterProcess++,arrivalTime,priority,burnTime, colorCode)); //Parçalara ayrılan verilerin ilgili işlemi yapması için uygulamaya gönderilecek diziye gönderilmesi
        }
        return nodeArr; //Veri dizisinin uygulamaya gönderilmesi
    }



}

package com.company;
import java.io.*;
import java.util.ArrayList;

public class FileRead {
    public ArrayList<Process> readFile (String fileName) throws IOException{
        File file = new File(fileName);
        FileReader fReader = new FileReader(file);
        String line;
        BufferedReader bReader = new BufferedReader(fReader);
        ArrayList<Process> nodeArr = new ArrayList<Process>();

        while ((line=bReader.readLine()) != null){
            String[] arr = line.split(", ");
            int arrivalTime = Integer.parseInt(arr[0]);
            int priority = Integer.parseInt(arr[1]);
            int burnTime = Integer.parseInt(arr[2]);
            System.out.println("2");

            nodeArr.add(new Process(arrivalTime,priority,burnTime));
        }
        return nodeArr;
    }



}

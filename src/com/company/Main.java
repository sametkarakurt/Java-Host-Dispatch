package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        FileRead fileRead = new FileRead();
        Queue realTime = new Queue();
        Queue priorty1 = new Queue();
        Queue priorty2 = new Queue();
        Queue priorty3 = new Queue();
       

        ArrayList<Process> nodeArr = fileRead.readFile("C:\\Users\\samet\\IdeaProjects\\Operating-System\\src\\Deneme.txt");
        int totalProcess = nodeArr.size();
        nodeArr.sort((node1, node2) -> node1.arrivalTime - node2.arrivalTime);
        System.out.println(nodeArr);
        int counter = 0;
        while (true){
            for (int i = 0; i < nodeArr.size() && nodeArr.get(i).arrivalTime <= counter  ; i++) {
                if(nodeArr.get(i).arrivalTime == counter){
                    switch (nodeArr.get(i).priority){
                        case 0:
                            realTime.push(nodeArr.get(i));

                            break;
                        case 1:
                            priorty1.push(nodeArr.get(i));
                            break;
                        case 2:
                            priorty2.push(nodeArr.get(i));
                            break;
                        case 3:
                            priorty3.push(nodeArr.get(i));
                            break;
                        default:
                            break;


                    }




                }

            }

            if(realTime.head != null){
                realTime.head.process.burnTime --;
                if(realTime.head.process.burnTime == 0){
                    realTime.pop();
                    totalProcess--;
                }

            } else if(priorty1.head != null){

               Node temp = priorty1.head;


               temp.process.burnTime --;
               if(temp.process.burnTime == 0){

                   totalProcess--;
               } else {

                   priorty1.push(temp.process);
               }
                priorty1.pop();


            } else if(priorty2.head != null){

                Node temp = priorty2.head;
               /* do{
                    temp.process.burnTime --;
                    if(temp.process.burnTime == 0){
                        priorty1.pop();

                    }else {
                        priorty1.push(temp.process);
                    }

                    temp = temp.next;
                }while (temp.next != null);
                */



        }

            if(totalProcess == 0){
                return;
            }
            counter++;


        }

    }
}




















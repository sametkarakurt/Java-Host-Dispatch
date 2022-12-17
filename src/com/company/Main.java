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

        MyProcess askıdakiProcess = new MyProcess(-1,0,0,0);

        ArrayList<MyProcess> nodeArr = fileRead.readFile("C:\\Users\\burak\\OneDrive\\Masaüstü\\odev\\Java-Host-Dispatch\\src\\Deneme.txt");
        int totalProcess = nodeArr.size();
        nodeArr.sort((node1, node2) -> node1.arrivalTime - node2.arrivalTime);

        int counter = 0;
        while (true) {
            for (int i = 0; i < nodeArr.size() && nodeArr.get(i).arrivalTime <= counter; i++) {
                if (nodeArr.get(i).arrivalTime == counter) {
                    switch (nodeArr.get(i).priority) {
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
            System.out.println("ZAMAN "+counter);
            if (realTime.head != null) {
                Node temp = realTime.head;

                if (temp.myProcess.timeout < counter) {
                    System.out.println("Prosess" + temp.myProcess.processId + " Zaman aşımına uğradı  Önceliği:" + temp.myProcess.priority);
                    realTime.pop();
                    totalProcess--;
                } else {
                    if (askıdakiProcess.processId != temp.myProcess.processId && askıdakiProcess.processId != -1) {
                        System.out.println("Process" + askıdakiProcess.processId + " askıya alındı");
                    }

                    temp.myProcess.run();
                    if (temp.myProcess.burnTime == 0) {
                        realTime.pop();
                        totalProcess--;
                        System.out.println("Process" + temp.myProcess.processId + " sonlandı");
                        askıdakiProcess.processId = -1;
                    } else {
                        askıdakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }
                }


            } else if (priorty1.head != null) {

                Node temp = priorty1.head;
                if (temp.myProcess.timeout < counter) {
                    System.out.println("Prosess" + temp.myProcess.processId + " Zaman aşımına uğradı  Önceliği:" + temp.myProcess.priority);
                    priorty1.pop();
                    totalProcess--;
                } else {
                    if (askıdakiProcess.processId != temp.myProcess.processId && askıdakiProcess.processId != -1) {
                        System.out.println("Process" + askıdakiProcess.processId + " askıya alındı");
                    }
                    temp.myProcess.run();


                    if (temp.myProcess.burnTime == 0) {
                        priorty1.pop();
                        totalProcess--;
                        System.out.println("Process" + temp.myProcess.processId + " sonlandı");
                        askıdakiProcess.processId = -1;
                    } else {
                        askıdakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);

                    }
                }

            } else if (priorty2.head != null) {

                Node temp = priorty2.head;
                if (temp.myProcess.timeout < counter) {
                    System.out.println("Prosess" + temp.myProcess.processId + " Zaman aşımına uğradı  Önceliği:" + temp.myProcess.priority);
                    priorty2.pop();
                    totalProcess--;
                } else {
                    if (askıdakiProcess.processId != temp.myProcess.processId && askıdakiProcess.processId != -1) {
                        System.out.println("Process" + askıdakiProcess.processId + " askıya alındı");
                    }
                    temp.myProcess.run();


                    if (temp.myProcess.burnTime == 0) {
                        priorty2.pop();
                        totalProcess--;
                        System.out.println("Process" + temp.myProcess.processId + " sonlandı");
                        askıdakiProcess.processId = -1;
                    } else {
                        askıdakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }
                }
            } else if (priorty3.head != null) {
                Node temp = priorty3.head;
                if (temp.myProcess.timeout < counter) {
                    System.out.println("Prosess" + temp.myProcess.processId + " Zaman aşımına uğradı  Önceliği:" + temp.myProcess.priority);
                    priorty3.pop();
                    totalProcess--;
                } else {
                    if (askıdakiProcess.processId != temp.myProcess.processId && askıdakiProcess.processId != -1) {
                        System.out.println("Process" + askıdakiProcess.processId + " askıya alındı");
                    }

                    temp.myProcess.run();

                    if (temp.myProcess.burnTime == 0) {
                        priorty3.pop();
                        totalProcess--;
                        System.out.println("Process" + temp.myProcess.processId + " sonlandı");
                        askıdakiProcess.processId = -1;
                    } else {
                        askıdakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }
                }
            }

            if(totalProcess == 0){
                return;
            }
            counter++;


        }

    }
}




















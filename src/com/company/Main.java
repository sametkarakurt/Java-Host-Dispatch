package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileRead fileRead = new FileRead();		//File read objesi oluşturuldu
        Queue realTime = new Queue();			//Processleri tutacağımız queuelar oluşturuldu
        Queue priorty1 = new Queue();
        Queue priorty2 = new Queue();
        Queue priorty3 = new Queue();

        int timeOutProcessId = 255;
        MyProcess askidakiProcess = new MyProcess(255,0,0,0);	//1 adet askida process oluşturuldu

        ArrayList<MyProcess> nodeArr = fileRead.readFile(args[0]);	//Dosya okuma işlemi
        int totalProcess = nodeArr.size();
        nodeArr.sort((node1, node2) -> node1.arrivalTime - node2.arrivalTime); //processlerin geliş zamanına göre dizilimi

        float counter = 0; //sayaç
        while (true) {
            for (int i = 0; i < nodeArr.size() && nodeArr.get(i).arrivalTime <= counter; i++) {
                if (nodeArr.get(i).arrivalTime == counter) {
                    switch (nodeArr.get(i).priority) {	//Öncelik durumuna göre queuelara yollanıyor.
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
            //Zaman asimikontrolü (Burayı sor)

            if(realTime.head != null && realTime.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (RealTime)
            	timeOutProcessId = realTime.head.myProcess.processId;
                System.out.println(realTime.head.myProcess.colorForPrint +counter+ " sn"+" Process zaman asimi (id: " + realTime.head.myProcess.processId + " oncelik: " + realTime.head.myProcess.priority + " kalan sure: " + realTime.head.myProcess.burnTime +" sn)" );
                realTime.pop();
              
                totalProcess--;

            }
            if(priorty1.head != null &&  priorty1.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (1st Priority)
            	timeOutProcessId = priorty1.head.myProcess.processId;
                System.out.println(priorty1.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman asimi (id: " + priorty1.head.myProcess.processId + " oncelik: " + priorty1.head.myProcess.priority + " kalan sure: " + priorty1.head.myProcess.burnTime +" sn)" );
                priorty1.pop();
            
                totalProcess--;

            }
            if(priorty2.head != null && priorty2.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (2nd Priority)
               	timeOutProcessId = priorty2.head.myProcess.processId;
                System.out.println(priorty2.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman asimi (id: " + priorty2.head.myProcess.processId + " oncelik: " + priorty2.head.myProcess.priority + " kalan sure: " + priorty2.head.myProcess.burnTime +" sn)" );
                priorty2.pop();
             
                totalProcess--;

            }
            if(priorty3.head != null &&priorty3.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (3rd Priority)
               	timeOutProcessId = priorty3.head.myProcess.processId;
                System.out.println(priorty3.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman asimi (id: " + priorty3.head.myProcess.processId + " oncelik: " + priorty3.head.myProcess.priority + " kalan sure: " + priorty3.head.myProcess.burnTime +" sn)" );
                priorty3.pop();
       
                totalProcess--;

            }
            if (realTime.head != null) { //Process askida kontrol işlemlerinin yapıldığı yer (RealTime)
                Node temp = realTime.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && timeOutProcessId != askidakiProcess.processId  && askidakiProcess.processId != 255) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askida  (id: " + askidakiProcess.processId +" oncelik:" +askidakiProcess.priority +" kalan sure: " + askidakiProcess.burnTime + " sn)");
                    }

                    temp.myProcess.run(counter);
                    if (temp.myProcess.burnTime == 0) { //Process çalışma suresi bittiği zaman kuyruktan çıkarma işlemi

                        realTime.pop();
                        totalProcess--;
                        System.out.println(temp.myProcess.colorForPrint + (counter+1)+" sn " +" Process sonlandi (id: "+ temp.myProcess.processId + " oncelik: " + temp.myProcess.priority + " kalan sure: " + temp.myProcess.burnTime +" sn)" );

                        askidakiProcess.processId = 255;;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }


            } else if (priorty1.head != null) { //Process askida kontrol işlemlerinin yapıldığı yer (1st Priority)
                Node temp = priorty1.head;
                    if (askidakiProcess.processId != temp.myProcess.processId &&  timeOutProcessId != askidakiProcess.processId && askidakiProcess.processId != 255) {

                        System.out.println(askidakiProcess.colorForPrint + counter+ " sn"+" Process askida  (id: " + askidakiProcess.processId +" oncelik: " +askidakiProcess.priority +" kalan sure: " + askidakiProcess.burnTime + " sn)");
                    }
                    temp.myProcess.run(counter);


                    if (temp.myProcess.burnTime == 0) { //Process çalışma suresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty1.pop();
                        totalProcess--;

                        System.out.println(temp.myProcess.colorForPrint +(counter+1)+" sn " +" Process sonlandi (id: "+ temp.myProcess.processId + " oncelik: " + temp.myProcess.priority + " kalan sure: " + temp.myProcess.burnTime + " sn)" );

                        askidakiProcess.processId = 255;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);

                    }

            } else if (priorty2.head != null) { //Process askida kontrol işlemlerinin yapıldığı yer (2nd Priority)

                Node temp = priorty2.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && timeOutProcessId != askidakiProcess.processId && askidakiProcess.processId != 255) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askida  (id: " + askidakiProcess.processId +" oncelik: " +askidakiProcess.priority +" kalan sure: " + askidakiProcess.burnTime + " sn)" );
                    }
                    temp.myProcess.run(counter);


                    if (temp.myProcess.burnTime == 0) { //Process çalışma suresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty2.pop();
                        totalProcess--;

                        System.out.println(temp.myProcess.colorForPrint + (counter+1)+" sn " +" Process sonlandi (id: "+ temp.myProcess.processId + " oncelik: " +temp.myProcess.priority + " kalan sure: " +temp.myProcess.burnTime +" sn)" );

                        askidakiProcess.processId = 255;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }

            } else if (priorty3.head != null) { //Process askida kontrol işlemlerinin yapıldığı yer (3rd Priority)
                Node temp = priorty3.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && timeOutProcessId != askidakiProcess.processId && askidakiProcess.processId != 255) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askida  (id: " + askidakiProcess.processId +" oncelik: " +askidakiProcess.priority +" kalan sure: " + askidakiProcess.burnTime + " sn)" );
                    }

                    temp.myProcess.run(counter);

                    if (temp.myProcess.burnTime == 0) { //Process çalışma suresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty3.pop();
                        totalProcess--;

                        System.out.println(temp.myProcess.colorForPrint +(counter+1)+" sn " +" Process sonlandi id: "+ temp.myProcess.processId + " oncelik: " + temp.myProcess.priority+ " kalan sure: "+ temp.myProcess.burnTime +" sn)" );

                        askidakiProcess.processId = 255;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime);
                    }
                }


            if(totalProcess == 0){ //Tüm processler bittiğinde işlem durur
                return;
            }

            counter++; //Sayaç artımı


        }

    }
}




















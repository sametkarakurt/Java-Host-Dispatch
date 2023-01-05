package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        FileRead fileRead = new FileRead();		//File read objesi oluşturuldu
        Queue realTime = new Queue();			//Processleri tutacağımız queuelar oluşturuldu
        Queue priorty1 = new Queue();
        Queue priorty2 = new Queue();
        Queue priorty3 = new Queue();
        Colors color = new Colors();

        MyProcess askidakiProcess = new MyProcess(-1,0,0,0, "\033[0;37m");	//1 adet askıda process oluşturuldu

        ArrayList<MyProcess> nodeArr = fileRead.readFile("src/Deneme.txt");	//Dosya okuma işlemi
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
            //Zaman aşımı kontrolü (Burayı sor)
            boolean zamanasimi=false;
            if(realTime.head != null && realTime.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (RealTime)
                System.out.println(realTime.head.myProcess.colorForPrint +counter+ " sn"+" Process zaman aşımı  (id: " + realTime.head.myProcess.processId + " öncelik: " + realTime.head.myProcess.priority + " kalan süre: " + realTime.head.myProcess.burnTime +" sn)" + color.color_reset);
                realTime.pop();
                totalProcess--;
                zamanasimi=true;
            }
            if(priorty1.head != null &&  priorty1.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (1st Priority)
                System.out.println(priorty1.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman aşımı  (id: " + priorty1.head.myProcess.processId + " öncelik: " + priorty1.head.myProcess.priority + " kalan süre: " + priorty1.head.myProcess.burnTime +" sn)" + color.color_reset);
                priorty1.pop();
                totalProcess--;
                zamanasimi=true;
            }
            if(priorty2.head != null && priorty2.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (2nd Priority)
                System.out.println(priorty2.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman aşımı  (id: " + priorty2.head.myProcess.processId + " öncelik: " + priorty2.head.myProcess.priority + " kalan süre: " + priorty2.head.myProcess.burnTime +" sn)" + color.color_reset);
                priorty2.pop();
                totalProcess--;
                zamanasimi=true;
            }
            if(priorty3.head != null &&priorty3.head.myProcess.timeout<counter)
            {//Process zamanı counterdan küçük ise process işleme devam eder (3rd Priority)
                System.out.println(priorty3.head.myProcess.colorForPrint  +counter+ " sn"+" Process zaman aşımı  (id: " + priorty3.head.myProcess.processId + " öncelik: " + priorty3.head.myProcess.priority + " kalan süre: " + priorty3.head.myProcess.burnTime +" sn)" + color.color_reset);
                priorty3.pop();
                totalProcess--;
                zamanasimi=true;
            }
            if(zamanasimi==true)    //Zaman aşımına uğradıysa döngü devam eder
                continue;
            if (realTime.head != null) { //Process askıda kontrol işlemlerinin yapıldığı yer (RealTime)
                Node temp = realTime.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && askidakiProcess.processId != -1) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askıda  (id: " + askidakiProcess.processId +" öncelik:" +askidakiProcess.priority +" kalan süre: " + askidakiProcess.burnTime + " sn)"+ color.color_reset);
                    }

                    temp.myProcess.run(counter);
                    if (temp.myProcess.burnTime == 0) { //Process çalışma süresi bittiği zaman kuyruktan çıkarma işlemi
                        realTime.pop();
                        totalProcess--;
                        System.out.println(temp.myProcess.colorForPrint + (counter+1)+" sn " +" Process sonlandı (id: "+ temp.myProcess.processId + " öncelik: " + temp.myProcess.priority + " kalan süre: " + temp.myProcess.burnTime +" sn)" + color.color_reset);
                        askidakiProcess.processId = -1;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime, temp.myProcess.colorForPrint);
                    }


            } else if (priorty1.head != null) { //Process askıda kontrol işlemlerinin yapıldığı yer (1st Priority)
                Node temp = priorty1.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && askidakiProcess.processId != -1) {
                        System.out.println(askidakiProcess.colorForPrint + counter+ " sn"+" Process askıda  (id: " + askidakiProcess.processId +" öncelik: " +askidakiProcess.priority +" kalan süre: " + askidakiProcess.burnTime + " sn)"+ color.color_reset);
                    }
                    temp.myProcess.run(counter);


                    if (temp.myProcess.burnTime == 0) { //Process çalışma süresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty1.pop();
                        totalProcess--;
                        System.out.println(temp.myProcess.colorForPrint +(counter+1)+" sn " +" Process sonlandı (id: "+ temp.myProcess.processId + " öncelik: " + temp.myProcess.priority + " kalan süre: " + temp.myProcess.burnTime + " sn)" + color.color_reset);
                        askidakiProcess.processId = -1;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime, temp.myProcess.colorForPrint);

                    }

            } else if (priorty2.head != null) { //Process askıda kontrol işlemlerinin yapıldığı yer (2nd Priority)

                Node temp = priorty2.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && askidakiProcess.processId != -1) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askıda  (id: " + askidakiProcess.processId +" öncelik: " +askidakiProcess.priority +" kalan süre: " + askidakiProcess.burnTime + " sn)" + color.color_reset);
                    }
                    temp.myProcess.run(counter);


                    if (temp.myProcess.burnTime == 0) { //Process çalışma süresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty2.pop();
                        totalProcess--;
                        System.out.println(temp.myProcess.colorForPrint + (counter+1)+" sn " +" Process sonlandı (id: "+ temp.myProcess.processId + " öncelik: " +temp.myProcess.priority + " kalan süre: " +temp.myProcess.burnTime +" sn)" + color.color_reset);
                        askidakiProcess.processId = -1;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime, temp.myProcess.colorForPrint);
                    }

            } else if (priorty3.head != null) { //Process askıda kontrol işlemlerinin yapıldığı yer (3rd Priority)
                Node temp = priorty3.head;
                    if (askidakiProcess.processId != temp.myProcess.processId && askidakiProcess.processId != -1) {
                        System.out.println(askidakiProcess.colorForPrint +counter+ " sn"+" Process askıda  (id: " + askidakiProcess.processId +" öncelik: " +askidakiProcess.priority +" kalan süre: " + askidakiProcess.burnTime + " sn)" + color.color_reset);
                    }

                    temp.myProcess.run(counter);

                    if (temp.myProcess.burnTime == 0) { //Process çalışma süresi bittiği zaman kuyruktan çıkarma işlemi
                        priorty3.pop();
                        totalProcess--;
                        System.out.println(temp.myProcess.colorForPrint +(counter+1)+" sn " +" Process sonlandı id: "+ temp.myProcess.processId + " öncelik: " + temp.myProcess.priority+ " kalan süre: "+ temp.myProcess.burnTime +" sn)" + color.color_reset);
                        askidakiProcess.processId = -1;
                    } else {
                        askidakiProcess = new MyProcess(temp.myProcess.processId, temp.myProcess.arrivalTime, temp.myProcess.priority, temp.myProcess.burnTime, temp.myProcess.colorForPrint);
                    }
                }


            if(totalProcess == 0){ //Tüm processler bittiğinde işlem durur
                return;
            }
            counter++; //Sayaç artımı


        }

    }
}




















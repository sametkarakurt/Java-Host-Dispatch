package com.company;

public class Process {
    public int arrivalTime;
    public int priority;

    public int burnTime;

    public Process(int arrivalTime,int priority,int burnTime){
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burnTime = burnTime;
    }

}

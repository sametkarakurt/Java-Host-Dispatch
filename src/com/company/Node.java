package com.company;

public class Node {

    public Process process;
    public  Node next;


    public Node(Process process) {
        this.process = process;
        this.next = null;
    }
}

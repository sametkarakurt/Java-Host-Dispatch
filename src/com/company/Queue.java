package com.company;

public class Queue {
   public Node head;

   public Queue(){
      this.head = null;
   }

   public void push(Process prcs){
      Node node = new Node(prcs);
      if(head == null){
         head = node;
         return;
      }

      Node temp = head;
      while (temp.next != null){
         temp = temp.next;
      }

      temp.next = node;


   }

   public Process pop(){
      Node popTemp = head;
      head = head.next;
      return popTemp.process;
   }

}

/* This program implements a stack using a LinkedList
 * author: Catherine chu
 * UNI: cjc2243
 * Due Date: March 1st, 2018
*/ 


import java.util.LinkedList;
import java.util.*;

//public class MyStack<AnyType> implements Iterable<AnyType>{
public class MyStack<AnyType>{
    int size;
    boolean isEmpty;
    LinkedList<AnyType> stack = new LinkedList<>();


    public MyStack(){
        size = 0;
    }
    
    // add a new node
    public void push(AnyType x){
        stack.addFirst(x);
        size++;
       
    }
    
    // remove first node
    public AnyType pop(){
        AnyType data = stack.removeFirst();
        size--;
        return data;
    }
    
    // return identiy of first node
    public AnyType peek(){
        AnyType data = stack.getFirst();
        return data;
    }
    
    public boolean isEmpty(){
        if (size == 0){
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
        return isEmpty; 
        
    }
    
    public int size(){        
        return size;
                
    }
    
}
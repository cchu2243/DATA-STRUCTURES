/* This program implements a queue using two stacks 
 * that are implemented using the MyStack (which
 * is implemented through a linkedlist)
 * author: Catherine Chu
 * UNI: cjc2243
 * Due Date: March 1st, 2018
*/ 

import java.util.*; 

public class TwoStackQueue<AnyType> implements MyQueue<AnyType>{    
    private MyStack<AnyType> stack1 = new MyStack<AnyType>(); // queue
    private MyStack<AnyType> stack2 = new MyStack<AnyType>(); // dequeue
    
    // casting aynthing to make sure AnyType is the same AnyType?
    public void enqueue(AnyType x){
        
        stack1.push(x);
       
    }
    
    public AnyType dequeue(){
        
        // when S2 empty
        if (stack2.isEmpty()){
            
            // if S1 is empty, the queue is empty and this should 
            // return a null
            if (stack1.isEmpty()){
                return null;
            }
            
            // if S1 has values in them, all those values should be
            // pushed to S2 until S1 is empty
            else{
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());                    
                }
                
                // once all values are pushed from S1 to
                // S2, pop S2 and get the proper value
                return stack2.pop();
            }
        }
        // if S2 has values in it, they should be popped first
        else{      
            return stack2.pop();            
        }
       
    }
    
    public boolean isEmpty(){
        return (stack1.isEmpty() && stack2.isEmpty());
    }
    
    public int size(){
        return (stack1.size() + stack2.size()); 
                
    }
}
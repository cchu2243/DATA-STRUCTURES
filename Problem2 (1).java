/* Following the specification in the README.md file, provide your 
 * Problem2 class.
 */

public class Problem2{
    
    public static void main(String[] args){
        
        // expected output
        // Original size is 0
        // Null
        // 1
        // 2
        // 3
        // 100
        // Null 
        
        TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();
        System.out.println("Original size is " + queue.size());
        
        System.out.println(queue.dequeue());
        
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(100);
        
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue()); 
        System.out.println(queue.dequeue()); 
        System.out.println(queue.dequeue()); 
        System.out.println(queue.dequeue()); 
        
    }
}
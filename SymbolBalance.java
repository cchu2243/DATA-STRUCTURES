// This program tests if textfiles have balanced symbols
// author: Catherine Chu
// UNI:cjc2243
// Due Date: March 1st, 2018

import java.io.*;
import java.util.Collections;

public class SymbolBalance{
    
    public static void main(String[] args){
        boolean inComment = false;
        boolean inQuote = false;
        boolean unbalanced = false;
        
        if (args.length>0){
            
            try{
                FileReader reader = new FileReader(args[0]);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                MyStack<Character> stack = new MyStack<Character>();
                
                // make sure that there is a next line to read
                // make sure an unbalanced symbol hasn't already been found
                while (line !=null && unbalanced !=true){
                    
                    for (int i = 0; i<line.length(); i++){
                        char c = line.charAt(i);
                        
                        
                        // adds any opening symbols if not in a comment
                        if (((c=='{') || (c == '(') || (c == '[')) && ((inComment ==false) && (inQuote == false))){
                            
                            stack.push(c);
                        }
                        
                        // check quotes
                        else if (c=='"'){
                            
                            // if already in a comment, will want to check if it's balanced
                            if ((inQuote == true) && (inComment == false)){
                                if (!stack.isEmpty()){
                                    
                                    
                                    char quoteBalance = stack.peek();
                                    if (quoteBalance == c){
                                        stack.pop();
                                        inQuote = false;
                                        
                                    }
                                }
                                else{
                                    System.out.println("There's an unbalanced " + c);
                                    unbalanced = true;
                                }
                            }
                            // if not in a comment, will want to push the comment
                            else{
                                if ((inQuote == false) && (inComment == false)){
                                    stack.push(c);
                                    inQuote = true;
                                }                             
                            }
                        }
                        // check multi-line comment
                        else if (c == '*'){
                            
                            if (inComment == true && i!= (line.length()-1)){
                                char commentBalance1 = line.charAt(i+1);
                                if (commentBalance1 == '/' && !stack.isEmpty()){
                                    stack.pop();
                                    inComment = false;
                                }
                                
                                
                            }
                            else if(inComment == false && (i!=0)){
                                char commentBalance2 = line.charAt(i-1);
                                if (commentBalance2 == '/'){
                                    stack.push(c);
                                    inComment = true;
                                }
                                
                                
                            }
                              
                        }
                        else{
                            if ((( c == '}') || ( c == ')') || (c == ']')) && (inComment == false) && (inQuote == false)){
                                if (!stack.isEmpty()){
                                    char symbolCheck = stack.peek();
                                    
                                    if (c == '}'){
                                        if (symbolCheck == '{'){
                                            stack.pop();
                                        }
                                        else{
                                            System.out.println("There's an unbalanced " + c);
                                            unbalanced = true;
                                            break;
                                        }
                                    }
                                    else if ( c == ')'){
                                        if (symbolCheck == '('){
                                            stack.pop();
                                            
                                        }
                                        else{
                                            System.out.println("There's an unbalanced " + c);
                                            unbalanced = true;
                                            break;
                                        }                                       
                                    }
                                    else if (c == ']'){
                                        if (symbolCheck == '['){
                                                stack.pop();
                                            }
                                            else{
                                                System.out.println("There's an unbalanced " + c);
                                                unbalanced = true;
                                                break;
                                            }
                                    }
                                }
                                else{
                                    System.out.println("The program has an unbalanced " + c );
                                    unbalanced =true;
                                    break;
                                }
                            }
                        }
                    }
                    // is this correct not to get stuck in here
                    if (line != null){
                        line = bufferedReader.readLine();
                    }
                } 
   
                if (unbalanced == false){
                    if (stack.isEmpty() == true){
                        System.out.println("The program is balanced");
                        line = null; // may remove
                    }
                    else{                       
                        char firstLeftOver = stack.pop();
                        System.out.println("The program has an unbalanced " + firstLeftOver);
                        
                        line = null;
                    }
                }
                
            }
            catch(IOException a){
                System.out.println("There's something wrong with the file specifed");
            }
            catch(Exception b){
                System.out.println("Something is wrong with the file");
            }
        }
        else{
            System.out.println("Please enter a valid file name");
        }
    }
    
}
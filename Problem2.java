// This program reads through a file and inserts each word and line
// it appears on into a node in an AVL tree
// ignores punctuation and conjunctions 
// all lowercase
// author: Catherine Chu
// UNI:cjc2243
// due date: 04/04/2018

import java.io.*;
import java.util.Collections;

public class Problem2{
    
    public static void main(String[] args){
        
        if (args.length >0){
            AvlTree wordTree = new AvlTree();
            int lineNumber = 0;
            
            try{
                FileReader reader = new FileReader(args[0]);        
                BufferedReader bufferedReader = new BufferedReader(reader);         
                String line = bufferedReader.readLine();
                
                while (line != null){
                    
                    // remove punctuation
                    line = line.replaceAll("\\p{Punct}", ""); 
                    line = line.toLowerCase(); // makes it all the same case 
                    
                    // increment line number                                        
                    lineNumber = lineNumber+1;
             
                    // start extracting words 
                    // stack overflow: 
                    // "How to read a single word (or line) from a textfile in Java"                     
                    String [] words = line.split("\\s+");                    
                                               
                    // iterate through an the array to add to the AvlTree                        
                    for (int i = 0 ; i <= words.length-1 ; i++){
                        
                        // stack overflow
                        // "keep only number, letter and remove space"
                        words[i] = words[i].replaceAll("[^A-Za-z0-9\\s]", "");
                        wordTree.indexWord(words[i], lineNumber);   
                    }
                    
                    line = bufferedReader.readLine();
                                     
                
                }
                wordTree.printIndex();                              
                bufferedReader.close();                            
                reader.close();
  
            }

            catch(IOException a){            
                System.out.println("There is something wrong with the file specifed");                    
            }                    
            catch(Exception b){                            
                System.out.println("There is something wrong with the file specified.");                            
            }        
        }           
        else{
            System.out.println("Please input a valid file name");
        }
       
    }
}
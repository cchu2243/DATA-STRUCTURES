// This program creates an ExpressionTree
// for postfix evaluation 
// author: Catherine Chu
// UNI: cjc2243
// due date: 04/04/2018

import java.util.LinkedList;

public class ExpressionTree{
    String postfix;
    String [] post;
    MyStack<ExpressionNode> stack;
    ExpressionNode root;
    StringBuilder postfixExp;
    StringBuilder prefixExp;
    StringBuilder infixExp;
    
    public ExpressionTree(String p){                
        stack = new MyStack<ExpressionNode>();
        postfix = p;
        post = postfix.split("\\s+");
        if(post.length == 0){
            System.out.println("Empty postfix expression");
            System.exit(-1);
        }
        
        // iterate through the array
        for (String elem : post){
            
            // checks operators
            if((elem.equals("+")) || (elem.equals("-")) || (elem.equals("*")) ||
                (elem.equals("/"))){
                
                String operator = elem;
                
                if (stack.peek() != null){
                    ExpressionNode firstNode = stack.pop();
                    
                    if (stack.peek() != null){
                        ExpressionNode secondNode = stack.pop();
                        ExpressionNode subTree = new ExpressionNode(operator, secondNode, firstNode);
                        stack.push(subTree);
                    }
                    else{
                        System.out.println("Error in the postfix expression!");
                        System.exit(-1);
                    }
                }
                else{
                    System.out.println("Error in the postfix expression");
                    System.exit(-1);
                }
            }
            // push numbers
            else if(isDigit(elem)){
                ExpressionNode operand = new ExpressionNode(elem, null, null);
                stack.push(operand);
            }
            else{
                System.out.println("Invalid characters in the expression");
                System.exit(-1);
            }
        }
        if(!stack.isEmpty()){            
            root = stack.pop();
            if(!stack.isEmpty()){
                System.out.println("Error in the postfix expression");
                System.exit(-1);
            }
        }
    }
    
    public int eval(){
        return eval(root);
    }
    
    private int eval(ExpressionNode r){
        if (r == null){
            System.out.println("The tree is now empty");
        }
        else{ 
            if(r.isOperator()){
                if (r.getData().equals("+")){
                    return eval(r.left) + eval(r.right);
                }
                else if(r.getData().equals("-")){
                    return eval(r.left)- eval(r.right);
                }
                else if (r.getData().equals("*")){
                    return eval(r.left) * eval(r.right);
                }
                else{                    
                    if (r.right.getData().equals("0")){
                        System.out.println("Divide by zero, error!");
                        System.exit(-1);
                    }
                    else{
                        return eval(r.left) / eval(r.right);
                    }                    
                }
            }
            else{
                return r.getDigit();
            }                            
        }                    
        return r.getDigit();  // redudancy ?
    }
    
    public String postfix(){
        postfixExp = new StringBuilder(postfix.length());
        return postfix(root, postfixExp);
    }
    
    private String postfix(ExpressionNode r, StringBuilder sb){
        if (r.left != null){
            postfix(r.left, postfixExp);
        }
        if (r.right != null){
            postfix(r.right, postfixExp);
        }
        postfixExp.append(" " + r.data);
        return postfixExp.toString();
    }
    
    public String prefix(){
        prefixExp = new StringBuilder(postfix.length());
        return prefix(root, prefixExp);
    }
    
    private String prefix(ExpressionNode r, StringBuilder sb){         
        prefixExp.append(" " + r.data);
        
        if (r.left != null){
            prefix(r.left, prefixExp);
        }
        if (r.right != null){
            prefix(r.right, prefixExp);
        }
        return prefixExp.toString();
    }
    
    // stack over-flow
    // "How to print an infix from a binary expression tree with necessary
    // parentheses" 
    public String infix(){
        infixExp = new StringBuilder(postfix.length());                 
        return infix(root, infixExp);
    }
    
    private String infix(ExpressionNode r, StringBuilder sb){
        
        if( r != null){
            if (r.left != null && r.right != null){
                infixExp.append(" ( ");
            }
            infix(r.left, infixExp);
            infixExp.append(" " + r.data);
            infix(r.right, infixExp);
            if (r.left != null && r.right != null){
                infixExp.append(" ) ");
            }
        }                    
        return infixExp.toString();
    }    
    
    // stack overflow:
    // "How to check if a string is numeric? [duplicate]"
    public boolean isDigit(String s){    
        for (int i = 0; i< s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    private class ExpressionNode{
        private String data;
        public ExpressionNode left;
        public ExpressionNode right;
        public ExpressionNode operator;
        
        public ExpressionNode(String d, ExpressionNode l, ExpressionNode r){
            data = d;
            left = l;
            right = r;
        }
        
        public ExpressionNode(ExpressionNode op, ExpressionNode l, ExpressionNode r){
            operator = op;
            left = l;
            right = r;
        }
                
        public String getData(){
            return data;
        }
        
        public boolean isOperator(){
            return (data.equals("+") || 
                     data.equals("-") || 
                     data.equals("*") || 
                     data.equals("/"));
        }
        
        public int getDigit(){
            return Integer.parseInt(data);
        }                
    }    
}
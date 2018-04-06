// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

import java.util.LinkedList;
import java.util.List;

public class AvlTree
{
 
//     private static LinkedList<Integer> lines;
    
    public AvlTree( )
    {
        root = null;
    }

    public void insert( String x )
    {
        root = insert( x, root );
    }
        
    private AvlNode insert( String x, AvlNode t )
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if( t == null )
            return new AvlNode( x, list, null, null );

        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }
    
    public boolean contains( String x )
    {
        return contains( x, root );
    }
    
    private boolean contains( String x, AvlNode t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }
    
    // finds Node based-off word search
    public AvlNode findNode(String x){
        return findNode(x, root);
    }
    
    // returns Node based off word
    private AvlNode findNode(String x, AvlNode t){
        if (t != null){
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                return findNode( x , t.left);
            else if( compareResult > 0 )                   
                return findNode( x, t.right);
            else
                return t;    // Match
            
        }
        else{                   
            System.out.println("That word isn't in the file, sorry!");
        }                   
        return t;
    }
    
    // adds an occurence of the word
    public void indexWord(String word, int line){
        // if word does not exist in the tree
        if (!contains(word)){
            insert(word);
            findNode(word).add(line); // finds the Node in the Avl tree to add line
        }
        
        // if word IS in the there
        else{
            // check if the line placement is already there, using linked-list ADT contains
            LinkedList<Integer> temp = findNode(word).getLines();
            if(!temp.contains(line)){
                findNode(word).add(line);                
            }
            // line and word already there, do nothing
        }   
    }
            
    // looks up a word and returns the lines it appears in 
    public List<Integer> getLinesFromWord(String word){
        if (!contains(word)){
            System.out.println("That word does not exist in the file, sorry!");
            return null; // this was changed
        }
        else{
            return findNode(word).getLines();
            
        }                   
    }
    
    public void printIndex(){
        if (!isEmpty()){
            printIndex(root);
        }
        else{                    
            System.out.println("Tree is now empty");
        }
    }
    
    private void printIndex(AvlNode t){          
        if( t != null )        
        {            
            printIndex( t.left );            
            System.out.println("word : " + t.element + " || line(s) : " + getLinesFromWord(t.element));
            printIndex( t.right );
        }
    }

    // don't touch
    public void remove( String x )
    {
        root = remove( x, root );
    }

    // don't touch
    private AvlNode remove( String x, AvlNode t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }
    
    // don't touch
    public String findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    // don't touch
    public String findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    // don't touch
    public void makeEmpty( )
    {
        root = null;
    }

    // don't touch
    public boolean isEmpty( )
    {
        return root == null;
    }
    
    // don't touch
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    // don't touch
    // Assume t is either balanced or within one of being balanced
    private AvlNode balance( AvlNode t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    // don't touch
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    // don't touch
    private int checkBalance( AvlNode t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    // don't touch
    private AvlNode findMin( AvlNode t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    // don't touch
    private AvlNode findMax( AvlNode t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    // don't touch
    private void printTree( AvlNode t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    // don't touch
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

    private AvlNode rotateWithLeftChild( AvlNode k2 )
    {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    // don't touch
    private AvlNode rotateWithRightChild( AvlNode k1 )
    {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    // don't touch
    private AvlNode doubleWithLeftChild( AvlNode k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    // don't touch
    private AvlNode doubleWithRightChild( AvlNode k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode
    {
        // Constructors
        AvlNode( String theElement, LinkedList<Integer> l )
        {
            this( theElement, l, null, null );
        }

        AvlNode( String theElement, LinkedList<Integer> l, AvlNode lt, AvlNode rt )
        {
            element  = theElement;
            lines = l;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        String element; // The data in the node
        AvlNode  left; // Left child
        LinkedList<Integer> lines = new LinkedList<Integer>();
        AvlNode  right; // Right child
        int height; // Height
        
        public void add(int line){
            lines.add(line); // adds the line at which the word occurs to the linked-list
        }
        
        public LinkedList<Integer> getLines(){
            return lines;
        }

    }

      /** The tree root. */
    private AvlNode root;

}
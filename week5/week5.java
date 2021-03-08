// Written by Cornell Washington

// https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
// https://stackoverflow.com/questions/9259411/what-is-the-best-way-to-iterate-over-the-lines-of-a-java-string
// https://stackoverflow.com/questions/15146052/what-does-the-arrow-operator-do-in-java

/* OLD ASSIGNMENT DESCRIPTION
Your job in this assignment is to create a generic linked list.  The linked list should have the ability to perform the following actions:

check if the list is empty
check the sz of the list
add data to the list
add data to a specific (valid) location in the list
remove an item from the list based on position
remove an item from the list based on data value
removes all elements from the list
gets an item from the list based on the position
outputs the contents of the list

Use the dat file attached to demonstrate effectiveness of the linked list you created.

Note: You should use the Java Collection Framework to assist you in solving this problem
*/

/* NEW ASSIGNMET DESCRIPTION
Your job in this assignment is to create a generic linked list.  The linked list should have the ability to perform the following actions:

check if the list is empty
check the size of the list
add data to the list
add data to a specific (valid) location in the list
remove an item from the list based on data value
outputs the contents of the list

Demonstrate your functions using Doubles, Integers, and Strings.

Sample Output:
The size of the list is: 3
The members of my list: 3.3
4.5
5.34
The size of the list is: 3
The members of my list: 3
4
54
The size of the list is: 3
The members of my list: Al
Carol
Bob

Note: You should use the functions created during the class video.
*/

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.lang.*;
import java.io.*;
import java.nio.file.*;


class Week5{
    /**
    * Perferms varous operations on a linked list
    * i.e., it simply tests the list's functions.
    * Note that the tests are not exhaustive.
    *
    * @param list The linked list to be operated on
    * @param toRemove Items that are intended to be removed from the list
    * @param toAdd Items that are intended to be added to the list
    * @return void
    */
    private static <T extends Comparable<T>> void operateOnList(
    MyLinkedList<T> list,
    ArrayList<T> toRemove,
    ArrayList<T> toAdd){

        try{
            System.out.printf("Contents of list: %s\n\n", list.toString(true));
            // the wording here just happened to come out nicely
            for(T target : toRemove){
                int size = list.size();
                System.out.printf("Repeatedly removing target item \"%s\" from list...\n", target);
                while(size == list.size()){
                    if(list.remove(target)){ System.out.printf("Success.\n"); }
                    else{ System.out.printf("Failed (not found).\n"); }
                    size--;
                }
                System.out.printf("Contents of list: %s\n\n", list.toString(true));
            }

            // ditto
            for(T newItem : toAdd){
                System.out.println("Adding \"" + newItem + "\" to list...");
                list.add(newItem);
                System.out.printf("Contents of list: %s\n\n", list.toString(true));
            }

            int index = 1;
            System.out.printf("Removing index %d...\n", index);
            list.remove(index);
            System.out.printf("Contents of list: %s\n\n", list.toString(true));

            System.out.println("Repeatedly removing last element...");
            while(list.size() > 0){
                list.remove(list.size()-1);
                System.out.println("Contents of list: " + list.toString(true));
            }
        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws java.lang.Exception{

        Path fileName = Path.of("data.dat");
        String data = Files.readString(fileName);

        MyLinkedList<String> strList = new MyLinkedList<String>();
        MyLinkedList<Integer> intList = new MyLinkedList<Integer>();
        MyLinkedList<Double> dblList = new MyLinkedList<Double>();

        Pattern strPattern = Pattern.compile("^[A-Za-z]+$");
        Pattern intPattern = Pattern.compile("^\\d+$");
        Pattern dblPattern = Pattern.compile("^\\d+\\.\\d+$");

        Stream<String> lines = data.lines();
        lines.forEach(line -> {
            Matcher matcher = strPattern.matcher(line);
            if (matcher.matches()){
                strList.add(line);
                return;
            }

            matcher = intPattern.matcher(line);
            if(matcher.matches()){
                intList.add(Integer.parseInt(line));
                return;
            }

            matcher = dblPattern.matcher(line);
            if(matcher.matches()){
                dblList.add(Double.parseDouble(line));
                return;
            }
        });

        // yikes
        ArrayList<String> stringsToAdd = new ArrayList<String>();
        stringsToAdd.add("Pi");
        stringsToAdd.add("Time");
        stringsToAdd.add("Elephants");
        stringsToAdd.add("Dream");

        ArrayList<Integer> integersToAdd = new ArrayList<Integer>();
        integersToAdd.add(255);
        integersToAdd.add(2010);

        ArrayList<Double> doublesToAdd = new ArrayList<Double>();
        doublesToAdd.add(3.14);

        ArrayList<String> stringsToRemove = new ArrayList<String>();
        stringsToRemove.add("Tom");
        stringsToRemove.add("Apple");

        ArrayList<Integer> integersToRemove = new ArrayList<Integer>();
        integersToRemove.add(133);
        integersToRemove.add(2);
        integersToRemove.add(16);
        integersToRemove.add(0xFF);

        ArrayList<Double> doublesToRemove = new ArrayList<Double>();
        doublesToRemove.add(3.14);
        doublesToRemove.add(3.7);
        doublesToRemove.add(7.11);
        doublesToRemove.add(5.0);

        System.out.println("\n\nOperating on list...");
        operateOnList(strList, stringsToRemove, stringsToAdd);

        System.out.println("\n\nOperating on list...");
        operateOnList(intList, integersToRemove, integersToAdd);

        System.out.println("\n\nOperating on list...");
        operateOnList(dblList, doublesToRemove, doublesToAdd);

    }
}

class Node<T extends Comparable<T>>{
    public Node<T> next;
    public T element;

    public Node(){
        next = null;
        element = null;
    }
    public Node(T elem, Node<T> following){
        next = following;
        element = elem;
    }
}

class MyLinkedList<T extends Comparable<T>> extends AbstractCollection{
    private Node<T> head;
    private Node<T> tail; //tail is redundant on singly-linked list
    private int sz;

    public MyLinkedList(){
        head = new Node<T>();
        tail = new Node<T>();
        head.next = tail;
        tail.next = null;
        sz = 0;
    }

    // --------------- PRIVATE METHODS ----------------
    /**
    * Get the node at the zero-indexed position given
    *
    * @param pos Postion of node
    * @return Node<T> at position
    * @throws java.lang.IndexOutOfBoundsException Throws IndexOutOfBoundsException
    * when pos < 0 or pos >= size
    */
    // O(1) at head and tail; O(n) otherwise
    private Node<T> getNodeAtPos(int pos) throws java.lang.IndexOutOfBoundsException{
        if (pos >= sz || pos < 0){
            throw new IndexOutOfBoundsException(pos);
        }
        if (pos == sz-1 && sz > 1){
            return tail;
        }
        Node<T> current = head;
        for (int i = 0; i < pos; i++){
            current = current.next;
        }
        return current;
    }

    //O(1)
    /**
    * Insert new node after given node
    *
    * @param elem Element to be inserted
    * @param pos Node to insert after
    * @return true (always)
    */
    private boolean insertAfter(T elem, Node<T> pos){
        if (sz == 0){
            head.element = elem;
        }
        else if (sz == 1){
            tail.element = elem;
        }
        else{
            Node<T> newNode = new Node<T>(elem, pos.next);
            pos.next = newNode;
            if (pos == tail){ tail = newNode; }
        }
        sz++;
        return true;
    }

    // TODO: currently does not work on nodes that are not head!
    /**
    * Insert new node before given node
    *
    * @param elem Element to be inserted
    * @param pos Node to insert before
    * @return true (always)
    */
    //O(1)
    private boolean insertBefore(T elem, Node<T> pos){
        Node<T> newNode = new Node<T>(elem, pos);
        if (pos == head){
            head = newNode;
        }
        else{
            // implement this!
            return false;
        }
        sz++;
        return true;
    }

    /**
    * Remove new node after given node
    *
    * @param pos Node before node to be removed
    * @return true (always)
    */
    // O(1)
    private boolean removeAfter(Node<T> pos){
        if(pos.next == tail){ tail = pos; }
        pos.next = pos.next.next;
        sz--;
        return true;
    }

    // --------------- PUBLIC METHODS ----------------
    /**
    * Add new node to end of list
    *
    * @param elem Element to be added
    * @return true (always)
    */
    // O(1)
    public boolean add(T elem){
        return insertAfter(elem, tail);
    }

    /**
    * Insert new node after given position (zero-indexed)
    *
    * @param elem Element to be inserted
    * @param pos position to insert after
    * @return true (always)
    */
    // O(n)
    public boolean insertAfter(T elem, int pos){
        return insertAfter(elem, getNodeAtPos(pos));
    }

    /**
    * Insert new node before given position
    *
    * @param elem Element to be inserted
    * @param pos position to insert before
    * @return true (always)
    */
    // O(n)
    public boolean insertBefore(T elem, int pos){
        // workaround (kludge) for limitation of private insertBefore
        if (insertBefore(elem, getNodeAtPos(pos))){ // if pos == 0 (head)
            return true;
        }
        return insertAfter(elem, getNodeAtPos(pos-1)); // otherwise...
    }

    /**
    * Remove first instance of element
    *
    * @param elem Element to be removed
    * @return true if element exists, false otherwise
    */
    // O(n); only removes first instance
    public boolean remove(T elem){
        Node<T> current = head;
        for (int i = 0; i < sz; i++){
            if (current.element.equals(elem)){
                return remove(i);
            }
            current = current.next;
        }
        return false;
    }

    /**
    * Remove node at position
    *
    * @param pos position of node to be removed
    * @return true (always)
    * @throws java.lang.IndexOutOfBoundsException Throws IndexOutOfBoundsException when
    * pos < 0 or pos >= size
    */
    // O(n)
    public boolean remove(int pos) throws java.lang.IndexOutOfBoundsException{
        if (pos >= sz || pos < 0){
            throw new IndexOutOfBoundsException(pos);
        }

        if (pos == 0){
            if (sz > 2){
                head = head.next;
                --sz;
                return true;
            }

            if (sz == 2){
                head.element = tail.element;
                tail.element = null;
                --sz;
                return true;
            }

            if (sz == 1){
                clear();
                return true;
            }
        }

        if (pos == sz-1 && sz == 2){
            tail.element = null;
            sz--;
            return true;
        }
        return removeAfter(getNodeAtPos(pos-1));
    }

    /**
    * Removes all elements/nodes from list
    *
    * @return void
    */
    // O(1) if we are not including the work done by gc
    public void clear(){
        head.next = tail;
        head.element = null;
        tail.element = null;
        sz = 0;
    }

    /**
    * Check if list is empty
    *
    * @return true if list is empty, false otherwise
    */
    // O(1)
    public boolean isEmpty(){ return sz == 0; }

    /**
    * Get size of list (number of elements)
    *
    * @return size of list
    */
    // O(1)
    public int size(){ return sz; }

    /**
    * Get element at given position
    *
    * @return element at given position
    */
    // O(n)
    public T getElement(int pos){ return at(pos); }

    /**
    * Get element at given position
    *
    * @return element at given position
    */
    // O(n)
    public T at(int pos){ return getNodeAtPos(pos).element; }

    /**
    * Does nothing other than statisfy requirements to extend AbstractCollection
    *
    * @return null
    */
    public Iterator<T> iterator(){
        return null;
    }

    /**
    * Convert (copy) list to string
    *
    * @param humanLanguage if true, print "List is empty", otherwise print empty list
    * @return stringified list in Python/JavaScript style
    */
    //O(n)
    public String toString(boolean humanLanguage){
        if (isEmpty()){
            if (humanLanguage){ return "List is empty"; }
                return "[ ]";
        }
        String str = "[ ";
        Node<T> current = head;
        for (int i=0; i < sz; i++){
            if (i == sz-1){
                str += current.element;
                break;
            }
            str += current.element + ", ";
            current = current.next;
        }
        str += " ]";
        return str;
    }

}

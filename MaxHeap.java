import java.util.Arrays;
import java.io.*;

public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {
    private T[] heap;   //Array for heap entries
    private int lastIndex;  //Index of last entry
    private boolean initialized = false; 
    private int swapCount;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    } //end default constructor

    public MaxHeap(int initialCapacity){
        initialized=false;
        //check if capacity is too small
        if(initialCapacity < DEFAULT_CAPACITY){
            initialCapacity=DEFAULT_CAPACITY;
        }
        else{
            checkCapacity(initialCapacity);
        }

        //casting
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized=true;
    } //end parameterized constructor

    public void add(T newEntry){
        checkIntegrity();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex/2;
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex/2;
        } //end while loop

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } //end add

    public T removeMax(){

    } //end removeMax

    public T getMax(){
        
    }

    public boolean isEmpty(){
        return lastIndex==0;
    } //end isEmpty

    public int getSize(){
        return lastIndex;
    } //end getSize

    public void clear(){

    } //end clear

    public void checkCapacity(int capacity){
        if (capacity > MAX_CAPACITY){
            throw new IllegalStateException("Your capacity exceeded the max capacity, please try again.");
        } 
    } //end checkCapacity

    public int getSwapCount(){
        return swapCount;
    } //end getSwapCount

    public void resetSwapCount(){
        swapCount=0;
    } //end resetSwapCount

    public void checkIntegrity(){
        if (initialized==false){
            throw new IllegalStateException("The heap has not been initialized please try again");
        }
    } //end check

    public void ensureCapacity(){
        
    }
}

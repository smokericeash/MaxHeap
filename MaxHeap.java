import java.util.*;
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

     public MaxHeap(T[] entries){
        initialized=false;

        //casting
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[entries.length + 1];
        heap = tempHeap;
        lastIndex = entries.length;
        initialized=true;

        //copying elemnents into heap
        for (int i = 0; i < entries.length; i ++){
            heap[i+1] = entries[i];
        }

        //floyd's algo, skips first half since they're leaves, and then runs through rest of indexes
        for(int rootIndex = lastIndex / 2 ; rootIndex > 0 ; rootIndex--){
            reheap(rootIndex);
        }

    } //end parameterized constructor

    public void add(T newEntry){
        checkIntegrity();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex/2;
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            swapCount++;
            newIndex = parentIndex;
            parentIndex = newIndex/2;
        } //end while loop

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } //end add

    public T removeMax(){
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T root = heap[1];
        heap[1] = heap[lastIndex];
        heap[lastIndex] = null;
        lastIndex--;

        if(lastIndex > 1){
            reheap(1);
        }
        return root;
    } //end removeMax

    private void reheap(int index){
        checkIntegrity();
        int rootIndex = index;
        int leftChildIndex = rootIndex*2;

        while(leftChildIndex <= lastIndex){ //checking if leftChildIndex is a leaf node or not
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if(rightChildIndex <= lastIndex && heap[rightChildIndex].compareTo(heap[leftChildIndex]) > 0){
                largerChildIndex = rightChildIndex;
            } //switch if the rightChild is bigger than left and if it exists
            if(heap[rootIndex].compareTo(heap[largerChildIndex]) < 0){
                swap(rootIndex, largerChildIndex);
                rootIndex = largerChildIndex;
                leftChildIndex = rootIndex*2;
            }
            else{
                break;
            }
        }
    } //end reheap method

    public T getMax(){
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            return heap[1];
        }
    }

    public boolean isEmpty(){
        return lastIndex==0;
    } //end isEmpty

    public int getSize(){
        return lastIndex;
    } //end getSize

    public void clear(){
        checkIntegrity();
        while(!isEmpty()){
            heap[lastIndex] = null;
            lastIndex--;
        }
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
        checkIntegrity();
        if(lastIndex >= heap.length-1){ // -1 because we don't use index 0
            int newCapacity = (heap.length-1) * 2;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity + 1); 
        } 
    } //end ensureCapacity

    private void swap(int index1, int index2){
        T temp = heap[index1]; //placeholder for index1's data since we're overwriting it after
        heap[index1] = heap[index2];
        heap[index2] = temp;

        swapCount++; //increment swap every time we use this method
    } //end swap
}

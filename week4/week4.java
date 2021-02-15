// Written by Cornell Washington
// Based off of https://www.geeksforgeeks.org/iterative-merge-sort/

import java.util.*;
import java.lang.*;
import java.io.*;

// Bubble sort is out.
// We could do selection sort or quicksort
// but we shall do an iterative mergesort instead

// we use arrayLists here as java complains about generic T[] arrays

class Week4{
    /**
    * The copy method copies elements from source array to destination array.
    * It is used here to populate temporary storage arrays.
    * @param sourceArray The source array we are retrieving elements from
    * @param destArray The destination array we are storing elements in
    * @param index The starting index of subarray of sourceArray
    * @param size The size of the subarray
    * @return void
    */
    public static <T extends Comparable<T>> void copy(ArrayList<T> sourceArray, ArrayList<T> destArray, int index, int size){
        for (int i = 0; i < size; i++){
            try{
                destArray.add(sourceArray.get(index+i));
            }
            catch (IndexOutOfBoundsException e){
                System.out.printf("\n%s\nSubarray size: %d\nSubarray index: %d\nSource array index: %d\n", e.getMessage(), size, i, index);
            }
        }
    }

    /**
    * The merge method is used to merge the sub arrays in a bottom-up fashion.
    * It also handles sorting the array.
    * @param array The array to be sorted
    * @param begin The beginning of the (sub)array
    * @param mid The middle of the (sub)array
    * @param end The end of the (sub)array
    * @return void
    */
    public static <T extends Comparable<T>> void merge(ArrayList<T> array, int begin, int mid, int end){
        // temporary arrays
        int leftSize = mid-begin+1;
        int rightSize = end-mid;
        ArrayList<T> leftArray = new ArrayList<T>(leftSize);
        ArrayList<T> rightArray = new ArrayList<T>(rightSize);

        // populate subarrays
        copy(array, leftArray, begin, leftSize);
        copy(array, rightArray, mid+1, rightSize);

        int i = 0; // left array index
        int j = 0; // right array index
        int k = begin; // main array index

        // sort and merge subarrays back into main array
        while (i < leftArray.size() && j < rightArray.size()){
            if ((leftArray.get(i)).compareTo(rightArray.get(j)) < 0){
                array.set(k, leftArray.get(i));
                i++;
            }
            else{
                array.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // populate remaining elements from left subarray
        while(i < leftArray.size()){
            array.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // populate remaining elements from right subarray
        while(j <  rightArray.size()){
            array.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    /**
    * The mergeSort method implements the mergesort algorithm iteratively
    * @param array The array to be sorted
    * @param size The size of the array
    * @return void
    */
    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> array, int size){
        for (int currentSize = 1; currentSize <= size-1; currentSize = 2*currentSize){
            for (int leftBegin = 0; leftBegin < size-1; leftBegin += 2*currentSize){
                // left array ends at mid (mid could also be named leftEnd)
                int mid = Math.min(leftBegin + currentSize-1, size-1);
                // right array starts at mid+1
                int rightEnd = Math.min(leftBegin + 2*currentSize-1, size-1);
                merge(array, leftBegin, mid, rightEnd);
            }
        }
    }

    public static void main (String[] args) throws java.lang.Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers (separated by space, enter a character followed by the Enter key to finish): ");

        ArrayList<Integer> intArray = new ArrayList<Integer>();
        ArrayList<Double> doubleArray = new ArrayList<Double>();
        ArrayList<String> stringArray = new ArrayList<String>();

        while(sc.hasNextInt()){
            try{
                Integer num = sc.nextInt();
                intArray.add(num);
            }
            // user inputs ^Z or ^D without inputting a string
            catch(Exception e){
                break;
            }
        }
        sc.nextLine();

        System.out.println("Enter floating-point numbers (separated by space, enter a character followed by the Enter key to finish): ");
        while(sc.hasNextDouble()){
            try{
                Double num = sc.nextDouble();
                doubleArray.add(num);
            }
            // user inputs ^Z or ^D without inputting a string
            catch(Exception e){
                break;
            }
        }
        sc.nextLine();

        System.out.println("Enter values (separated by newline, press CTRL+Z followed by the Enter key to finish): ");
        while(sc.hasNextLine()){
            try{
                String str = sc.nextLine();
                stringArray.add(str);
            }
            // user inputs ^Z or ^D without inputting a string
            catch(Exception e){
                break;
            }
        }
        sc.close();

        System.out.printf("\nUnsorted arrays:\nInteger: %s\nDouble: %s\nString: %s\n",
            Arrays.toString(intArray.toArray()),
            Arrays.toString(doubleArray.toArray()),
            Arrays.toString(stringArray.toArray()));

        mergeSort(intArray, intArray.size());
        mergeSort(doubleArray, doubleArray.size());
        mergeSort(stringArray, stringArray.size());

        System.out.printf("\nSorted arrays:\nInteger: %s\nDouble: %s\nString: %s\n",
            Arrays.toString(intArray.toArray()),
            Arrays.toString(doubleArray.toArray()),
            Arrays.toString(stringArray.toArray()));

        System.out.println("Done.");
    }
}

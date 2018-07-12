/*
 * Purpose: Data Structure and Algorithms Lab 10
 * Status: Complete and thoroughly tested
 * Last update: 11/16/16
 * Submitted:  11/16/16
 * Comment: test suite and sample run attached
 * @author: Brooke Brown
 * @version: 2016.11.16
 */

import java.io.*;
import java.util.*;

public class Driver
{
    static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
    static int comp = 0;

    public static void main(String args[]) throws IOException
    {
        int[] array;
        String askPrompt =  "   1. Iterative MergeSort\n   2. Recursive QuickSort\n   3. Exit program. \nMake your menu selection now: ";
        System.out.print(askPrompt);
        int answer = Integer.parseInt(stdin.readLine().trim());
        System.out.println(answer);
        while(answer != 3) {
            switch (answer) {
            case 1: //mergeSort
                System.out.print("Enter number of integers: ");
                int numInts = Integer.parseInt(stdin.readLine().trim());
                System.out.println(numInts);
                array = new int[numInts];
                for(int i = 0; i < numInts; i++) {
                    System.out.print("Enter integer number " + (i + 1) + ": ");
                    int num = Integer.parseInt(stdin.readLine().trim());
                    System.out.println(num);
                    array[i] = num;
                }
//start MS
                mergeSort(array);
//end MS
                System.out.println("\nNumber of comparisons: " + comp);
                System.out.print(askPrompt);
                answer = Integer.parseInt(stdin.readLine().trim());
                System.out.println(answer);
                break;
            case 2: //quickSort
                System.out.print("Enter number of integers: ");
                numInts = Integer.parseInt(stdin.readLine().trim());
                System.out.println(numInts);
                array = new int[numInts];
                for(int i = 0; i < numInts; i++) {
                    System.out.print("Enter integer number " + (i + 1) + ": ");
                    int num = Integer.parseInt(stdin.readLine().trim());
                    System.out.println(num);
                    array[i] = num;
                }
                //start QS
                System.out.println("The array after quickSort: ");
                int[] newArray = quickSort(0, array.length - 1, array);
                for(int i = 0; i < newArray.length; i++) {
                    System.out.println(newArray[i]);
                }
                //end QS
                System.out.println("\nNumber of comparisons: " + comp);
                System.out.print(askPrompt);
                answer = Integer.parseInt(stdin.readLine().trim());
                System.out.println(answer);
                break;
            default:
                System.out.println("You entered an incorrect number. ");
                System.out.print(askPrompt);
                answer = Integer.parseInt(stdin.readLine().trim());
                System.out.println(answer);
                break;
            }
        }
        System.out.println("Good bye! :)");
    }

    public static int[] quickSort(int beg, int end, int[] array) //parameters are the indexes of the beginning and end of the part of the array we are working on and the array that is worked on
    {
        // GETTING WRONG STUFF
        if(end > beg) {
            //step 1: choose pivot
            int pivotI = array.length % 3;
            int pivot = array[pivotI];
            //step 2: swap pivot with first item
            int temp = array[beg];
            array[beg] = pivot;
            array[pivotI] = temp;
            //step 3: partition array into < pivot and > pivot
            int l = beg;
            int g = beg;
 
            for(int i = beg + 1; i <=end; i++) {
                if(array[i] > pivot) {
                    g++;
                }
                else {
                    if((l != beg && g != beg) || (l == beg && g !=beg)) {
                        //swap won't bring desired results?
                        temp = array[g];
                        array[g] = array[i];
                        array[i] = temp;
                    }
                    l++;
                    g++;
                }
                //figure out how to incorporate comp...
                comp++;
            }
            //step 4: swap pivot inbetween <p and >p
            temp = array[l];
            array[l] = array[beg];
            array[beg] = temp;
            //step 5: quickSort for <p and >p
            quickSort(beg + 1, l, array);
            quickSort(l + 1, g, array);
        }
        return array;
    }

    public static int[] mergeSort(int[] array) {
//step 1: divide sub problems into lower half and upper half (if size > 1)
        if(array.length > 1) {
            int mid = array.length / 2;
            int[] lower = new int[mid + 1];
            for(int i = 0; i <= mid; i++) {
                lower[i] = array[i];
            }
            int[] upper = new int[array.length - mid];
            for(int j = mid + 1; j < array.length; j++) {
                upper[j] = array[j];
            }
            mergeSort(lower);
            mergeSort(upper);
        }
        return array;
    }
}

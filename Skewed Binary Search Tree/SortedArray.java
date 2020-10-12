/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Dependencies: In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/11model/tinyW.txt
 *                https://algs4.cs.princeton.edu/11model/tinyT.txt
 *                https://algs4.cs.princeton.edu/11model/largeW.txt
 *                https://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [367,966 total values]
 *
 ******************************************************************************/

//package edu.princeton.cs.algs4;
import java.util.*;
import java.util.Arrays;

/**
 *  The {@code BinarySearch} class provides a static method for binary
 *  searching for an integer in a sorted array of integers.
 *  <p>
 *  The <em>indexOf</em> operations takes logarithmic time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SortedArray {

    // degree of the skew:
    int[] array;
    double alpha;
    /**
     * This class should not be instantiated.
     */
    public SortedArray(int[] array, double alpha) {
        this.array = array;
        this.alpha = alpha;
    }

    public void runPred(int[] queries){
      for(int i = 0; i<queries.length; i++){

        int x = pred(queries[i]);
      //  if(x != -1) System.out.print(x + " ");
      //  else        System.out.print("None ");
      }

    }
    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  a the array of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public int pred(int key) {
        int lo = 0;
        int hi = array.length - 1;
        int skewMid = 0;
        while (lo <= hi) {
            skewMid = (int) (lo + (alpha * (hi - lo)));
            if      (key < array[skewMid]) hi = skewMid - 1;
            else if (key > array[skewMid]) lo = skewMid + 1;
            else return array[skewMid];
        }
        if(array[skewMid] > key) return - 1;
        else return array[skewMid];
    }

/*    public void pred_print(int key) {
        int lo = 0;
        int hi = array.length - 1;
        double skewMidD = 0.0;
        int skewMid = 0;
        while (lo <= hi) {
            skewMidD = lo + (alpha * (hi - lo));
            skewMid =  (int) skewMidD;
            if      (key < array[skewMid]) hi = skewMid - 1;
            else if (key > array[skewMid]) lo = skewMid + 1;
            else {
                System.out.print(array[skewMid] + " ");
                return;
            }
        }
        if(array[skewMid] > key) {
            System.out.print("None ");
            return;
        }
        else {
            System.out.print(array[skewMid] + " ");
            return;
        }
    }*/
}



/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *            The types of values that are sorted.
 *
 * @author Samantha Schmidt
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *                   The order in which elements in the array should be ordered
   *                   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Chooses a pivot.
   *
   * @param array
   *              An array with the same length as the final array.
   * @return median
   *         the int that is the middle most point of 3 random numbers
   *         between 0 and the length of the array.
   */
  public int choosePivot(T[] array) {
    int median;
    Random rand = new Random();
    int a = rand.nextInt(1, array.length - 1);
    int b = rand.nextInt(1, array.length - 1);
    int c = rand.nextInt(1, array.length - 1);

    if (((a >= b) && (a <= c)) || ((a >= c) && (a <= b))) {
      median = a;
    } else if (((b >= a) && (b <= c)) || ((b >= c) && (b <= a))) {
      median = b;
    } else {
      median = c;
    } // endif

    return median;
  } // choosePivot(T[])

  /**
   * Partitions the array for quicksort.
   *
   * @param values
   *               The array to sort
   */
  @SuppressWarnings("unchecked")
  public void partition(T[] values) {
    int pivot = choosePivot(values);
    T pivotVal = values[pivot];

    values[pivot] = values[0];
    values[0] = pivotVal;

    int small = 1;
    int large = values.length - 1;
    String lastMoved = null;

    while (small != large) {
      String aString = (String) values[small];
      if (order.compare(pivotVal, values[small]) > 0) {
        small++;
        lastMoved = "small";
      } else {
        T largeVal = values[large];
        values[large] = values[small];
        values[small] = largeVal;
        large--;
        lastMoved = "large";
      } // endif
    }

    if (lastMoved.equals("large")) {
      small = small - 1;
    } // endif

    values[0] = values[small];
    values[small] = pivotVal;

    T[] firstHalf = (T[]) new Object[small];
    for (int i = 0; i < firstHalf.length; i++) {
      firstHalf[i] = values[i];
    } // endfor
    if (firstHalf.length > 2) {
      partition(firstHalf);
    } // endif

    int lengthLast = 0;
    if (lastMoved.equals("large")) {
       lengthLast = values.length - large;
    } else {
      lengthLast = values.length - small + 1;
    }
    T[] lastHalf = (T[]) new Object[lengthLast];
    for (int i = 0; i < lastHalf.length; i++) {
      lastHalf[i] = values[small + 1 + i];
    } // endfor
    if (lastHalf.length > 2) {
      partition(lastHalf);
    } // endif
  } // partition(T[])

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *               an array to sort.
   *
   * @post
   *       The array has been sorted according to some order (often
   *       one given to the constructor).
   * @post
   *       For all i, 0 &lt; i &lt; values.length,
   *       order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 1) {
      return;
    } // endif
    partition(values);
  } // sort(T[])
} // class Quicksorter

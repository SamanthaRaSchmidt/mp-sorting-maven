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
  public int choosePivot(T[] array, int start, int end) {
    int median;
    Random rand = new Random();
    int a = rand.nextInt(start, end);
    int b = rand.nextInt(start, end);
    int c = rand.nextInt(start, end);

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
  public void partition(T[] values, int begin, int end) {
    int pivot = choosePivot(values, begin, end);
    T pivotVal = values[pivot];

    values[pivot] = values[begin];
    values[begin] = pivotVal;

    int small = begin + 1;
    int large = end;

    while (small != large) {
      if (order.compare(pivotVal, values[small]) > 0) {
        small++;
      } else {
        T largeVal = values[large];
        values[large] = values[small];
        values[small] = largeVal;
        large--;
      } // endif
    }

    if (order.compare(pivotVal, values[small]) < 0) {
      small = small - 1;
    } //endif

    values[begin] = values[small];
    values[small] = pivotVal;

    int lengthFirst = small - begin;
    int lengthLast = (end + 1)- (small + 1);
    if (lengthFirst > 1) {
      partition(values, begin, small - 1);
    } //endif

    if (lengthLast > 1) {
      partition(values, small + 1, end);
    } //endif
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
    partition(values, 0, values.length - 1);
  } // sort(T[])
} // class Quicksorter

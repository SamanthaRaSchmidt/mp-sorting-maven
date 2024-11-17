package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
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
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Chooses a pivot.
   * @param array
   *  An array with the same length as the final array.
   * @return median
   *    the int that is the middle most point of 3 random numbers
   *     between 0 and the length of the array.
   */
  public int choosePivot(T[] array) {
    int median;
    Random rand = new Random();
    int a = rand.nextInt(array.length);
    int b = rand.nextInt(array.length);
    int c = rand.nextInt(array.length);

    if (((a >= b) && (a <= c)) || ((a >= c) && (a <= b))) {
      median = a;
    } else if (((b >= a) && (b <= c)) || ((b >= c) && (b <= a))) {
      median = b;
    } else {
      median = c;
    } //endif

    return median;
  } // choosePivot(T[])

  @SuppressWarnings("unchecked")
  public void partition(T[] values) {
    int pivot = choosePivot(values);

    values[pivot] = values[0];
    values[0] = values[pivot];

    int small = 1;
    int large = values.length - 1;

    for (int i = 1; i < values.length; i++) {
      if (order.compare(values[pivot], values[i]) < 0) {
        T smallVal = values[small];
        values[i] = smallVal;
        values[small] = values[i];
        small++;
      } else {
        T largeVal = values[large];
        values[i] = largeVal;
        values[large] = values[i];
        large--;
      } //endif
    } //endfor

    values[pivot] = values[0];

    T[] firstHalf = (T[]) new Object[pivot];
    for (int i = 0; i < firstHalf.length; i++) {
      firstHalf[i] = values[i];
    } //endfor
    if (firstHalf.length > 2) {
      partition(firstHalf);
    } //endif
    T[] lastHalf = (T[]) new Object[values.length - pivot];
    for (int i = 0; i < lastHalf.length; i++) {
      lastHalf[i] = values[i];
    } //endfor
    if(lastHalf.length > 2) {
      partition(lastHalf);
    } //endif
  } // partition(T[])

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 1) {
      return;
    } //endif
    partition(values);
  } // sort(T[])
} // class Quicksorter

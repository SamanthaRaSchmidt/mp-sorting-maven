package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samantha Schmidt
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Inserts the values into the appropriate place in the array.
   *
   * @param values
   *    The array to sort into
   * @param val
   *    The value to be inserted
   * @param i
   *    The index to start swapping at
   */
  public void insert(T[] values, T val, int i) {
    int valIndex = i;
    for (int j = i; j >= 0; j--) {
      if(order.compare(val, values[j]) < 0) {
        values[valIndex] = values[j];
        values[j] = val;
        valIndex = j;
      } //endif
    } //for
  } // insert(T[], int)

  /**
   * Sort an array in place using insertion sort.
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
    if (values.length == 0) {
    } else {
      for (int i = 1; i < values.length; i++) {
        if(order.compare(values[i], values[i - 1]) < 0) {
          insert(values, values[i], i);
        } // endif
      } // for
    } //endif
  } // sort(T[])
} // class InsertionSorter

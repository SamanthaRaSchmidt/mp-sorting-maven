package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * My own sorting algorithm.
 *
 * @param <T>
 *            The types of values that are sorted.
 *
 * @author Samantha Schmidt
 */
public class SchmidtSamanthaSort<T> implements Sorter<T> {
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
  public SchmidtSamanthaSort(Comparator<? super T> comparator) {
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
      if (order.compare(val, values[j]) < 0) {
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
  public void insertSort(T[] values) {
    if (values.length != 0) {
      for (int i = 1; i < values.length; i++) {
        if (order.compare(values[i], values[i - 1]) < 0) {
          insert(values, values[i], i);
        } // endif
      } // for
    } //endif
  } // insertSort(T[])


   /**
   * Merges the two sorted arrays.
   *
   * @param values
   *  The final array
   * @param left
   *  The left sorted array
   * @param right
   *  The right sorted array
   */
  @SuppressWarnings("unchecked")
  public void mergeHelper(T[] values, T[] left, T[] right) {
    T[] empty = (T[]) new Object[left.length + right.length];
    int rightIndex = 0;
    int leftIndex = 0;

    for (int i = 0; i < empty.length; i++) {
      if (rightIndex < right.length && leftIndex < left.length) {
        if (order.compare(right[rightIndex], left[leftIndex]) < 0) {
          empty[i] = right[rightIndex];
          rightIndex++;
        } else {
          empty[i] = left[leftIndex];
          leftIndex++;
        } // endif
      } else if (rightIndex < right.length) {
        empty[i] = right[rightIndex];
        rightIndex++;
      } else {
        empty[i] = left[leftIndex];
        leftIndex++;
      } //endif
    } // for

    for (int i = 0; i < empty.length; i++) {
      values[i] = empty[i];
    } // for
  } // mergeHelper(T[], T[])

  /**
   * Recursively sorts the two sides of the array.
   *
   * @param values
   *  The unsorted array
   */
  @SuppressWarnings("unchecked")
  public void merge(T[] values) {
    if(values.length < 20) {
      insertSort(values);
    } else if (values.length < 2) {
      return;
    } else {
      int mid = values.length / 2;

      T[] left = (T[]) new Object[mid];
      for (int i = 0; i < left.length; i++) {
        left[i] = values[i];
      } // endfor

      T[] right = (T[]) new Object[values.length - mid];
      for (int i = 0; i < right.length; i++) {
        right[i] = values[mid + i];
      } // endfor
      merge(left);
      merge(right);
      mergeHelper(values, left, right);
    } // endif
  } // merge(T[])

  /**
   * Sort an array in place using merge sort.
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
    merge(values);
  } // sort(T[])
} // classSchmidtSamanthaSort

package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samantha Schmidt
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @SuppressWarnings("unchecked")
  public void mergeHelper(T[] values, T[] left, T[] right) {
    T[] empty = (T[]) new Object[values.length];

    for (int i = 0; i < empty.length; i++) {
      int rightIndex = 0;
      int leftIndex = 0;

      if (order.compare(right[rightIndex], left[leftIndex]) < 0) {
        empty[i] = right[rightIndex];
        rightIndex++;
      } else {
        empty[i] = left[leftIndex];
        leftIndex++;
      } //endif
    } //endfor

    for (int i = 0; i < values.length; i++) {
      values[i] = empty[i];
    } //for
  } //mergeHelper(T[], T[])

  @SuppressWarnings("unchecked")
  public void merge(T[] values) {

    if (values.length < 2) {
      return;
    } else {
      int mid = values.length / 2;

      T[] left = (T[]) new Object[mid];
      for (int i = 0; i < left.length; i++) {
        left[i] = values[i];
      } //endfor

      T[] right = (T[]) new Object[values.length - mid];
      for (int i = 0; i < right.length; i++) {
        right[i] = values[mid + i];
      } //endfor
         merge(left);
         merge(right);
      mergeHelper(values, left, right);
    } //endif
  } //merge(T[])

  /**
   * Sort an array in place using merge sort.
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
    merge(values);
  } // sort(T[])
} // class MergeSorter

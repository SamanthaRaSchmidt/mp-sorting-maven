package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Samantha Schmidt
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *               The type of values in the array.
   * @param sorted
   *               The sorted array.
   * @param perm
   *               The permuted sorted array.
   * @param sorter
   *               The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
        () -> String.format("sort(%s) yields %s rather than %s",
            Arrays.toString(tmp),
            Arrays.toString(perm),
            Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test
  public void permutedIntegersTest() {
    int SIZE = 100;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure that an array that is null returns null.
   */
  @Test
  public void nullTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {};
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // nullTest

  /**
   * Ensure that an array of one element returns just that element.
   */
  @Test
  public void oneElementTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // oneElementTest

  /**
   * Ensure that an array where the first half is sorted and the back half
   * is backwards gets sorted.
   */
  @Test
  public void halfSortedHalfBackTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "giant", "foxtrot", "delta" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot", "giant" };
    assertSorts(expected, original, stringSorter);
  } // halfSortedHalfBackTest

  /**
   * Ensure that an array where the first half is backwards and the back half
   * is forwards gets sorted.
   */
  @Test
  public void halfBackHalfSortedTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "giant", "foxtrot", "delta", "alpha", "bravo", "charlie" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot", "giant" };
    assertSorts(expected, original, stringSorter);
  } // halfBackHalfSortedTest

  /**
   * Ensure that an array where every other element is either in order or
   * backwards
   * gets sorted.
   */
  @Test
  public void everyOtherTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "giant", "bravo", "foxtrot", "charlie", "delta" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot", "giant" };
    assertSorts(expected, original, stringSorter);
  } // halfSortedHaldBackTest
} // class TestSorter

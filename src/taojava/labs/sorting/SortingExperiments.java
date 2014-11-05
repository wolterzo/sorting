package taojava.labs.sorting;

import java.util.Arrays;

public class SortingExperiments
{
  public static void main(String[] args)
  {
    /*
    Integer[] test = reverseIntArrBuilder.build(20);
    System.out.println(Arrays.toString(test));
    */

    Integer[] a1 = { 8, 9, 10, 13 };
    Integer[] a2 = { -2 };
    Integer[] results = new Integer[5];
    /*
    Utils.merge(StandardIntegerComparator.COMPARATOR, a1, 0, 4, a2, 0, 1,
                results, 0, 5);
    System.out.println(Arrays.toString(results));
    */
    //Integer[] sortme = { 10, 9, 8, 13, -2 };
    /*
    NewQuicksorter<Integer> sorter = new NewQuicksorter<Integer>();
    sorter.qsort(sortme, StandardIntegerComparator.COMPARATOR, 0, 6);
    
    InsertionSorterInlineSwap<Integer> sorter = new InsertionSorterInlineSwap<Integer>();
    sorter.sorti(sortme, standardIntComparator);
    */
    /*
    InsertionSorterShift<Integer> sorter = new InsertionSorterShift<Integer>();
    sorter.sorti(sortme, standardIntComparator);
    */
    /*
    MergeSorterB<Integer> sorter = new MergeSorterB<Integer>();
    */
    /*
    IterativeMergeSorter<Integer> sorter = new IterativeMergeSorter<Integer>();
    sorter.sorti(sortme, SorterAnalyzer.standardIntComparator);
    */
    int[] sortme = { 3, 7, 8, 12, 0, -45, -5, 7, 10, 13 };
    IntegerQuicksort.qsort(sortme, 0, sortme.length);
    System.out.println(Arrays.toString(sortme));

  }// main (String[])
}// class SortingExperiment

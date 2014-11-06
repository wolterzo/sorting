package taojava.labs.sorting;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * A main class for experimenting with new methods we write.
 *
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class SortingExperiments
{
  /**
   * Main method for tests
   */
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    
    //Test reverse array builder
    Integer[] test = SorterAnalyzer.reverseIntArrBuilder.build(20);
    pen.println("reverse array");
    pen.println(Arrays.toString(test));
    
    // test merge method
    Integer[] a1 = { 8, 9, 10, 13 };
    Integer[] a2 = { -2 };
    Integer[] results = new Integer[5];
    
    Utils.merge(StandardIntegerComparator.COMPARATOR, a1, 0, 4, a2, 0, 1,
                results, 0, 5);
    pen.println("merged array:");
    pen.println(Arrays.toString(results));
    
    // test quick sorter
    Integer[] sortme = { 10, 9, 8, 13, -2 };
    NewQuicksorter<Integer> sorter = new NewQuicksorter<Integer>();
    sorter.qsort(sortme, StandardIntegerComparator.COMPARATOR, 0, 5);
    pen.println("sorted by NewQuicksorter");
    pen.println(Arrays.toString(sortme));
    
    // test in line swap insertion sorter
    Integer[] sortmea = { 10, 9, 8, 13, -2 };
    InsertionSorterInlineSwap<Integer> sortera = new InsertionSorterInlineSwap<Integer>();
    sortera.sorti(sortmea, SorterAnalyzer.standardIntComparator);
    pen.println("sorted by InsertionSorterInlineSwap");
    pen.println(Arrays.toString(sortmea));
    
    // test shift insertion sorter
    Integer[] sortmeb = { 10, 9, 8, 13, -2 };
    InsertionSorterShift<Integer> sorterb = new InsertionSorterShift<Integer>();
    sorterb.sorti(sortmeb, SorterAnalyzer.standardIntComparator);
    pen.println("sorted by InsertionSorterShift");
    pen.println(Arrays.toString(sortmeb));
    
    // test mergesorterB (scratch array)
    Integer[] sortmec = { 10, 9, 8, 13, -2 };
    MergeSorterB<Integer> sorterc = new MergeSorterB<Integer>();
    sorterc.sorti(sortmec, SorterAnalyzer.standardIntComparator);
    pen.println("sorted by MergeSorterB");
    pen.println(Arrays.toString(sortmec));
    
    //test iterativemergesorter
    Integer[] sortmed = { 10, 9, 8, 13, -2 };
    IterativeMergeSorter<Integer> sorterd = new IterativeMergeSorter<Integer>();
    sorterd.sorti(sortmed, SorterAnalyzer.standardIntComparator);
    pen.println("sorted by IterativeMergeSorter");
    pen.println(Arrays.toString(sortmed));
    
    // test integerQuicksort
    int[] sortmee = { 3, 7, 8, 12, 0, -45, -5, 7, 10, 13 };
    IntegerQuicksort.qsort(sortmee, 0, sortmee.length);
    pen.println("sorted by IntegerQuicksort");
    pen.println(Arrays.toString(sortmee));

  }// main (String[])
}// class SortingExperiment

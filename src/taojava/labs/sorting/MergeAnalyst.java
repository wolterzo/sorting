package taojava.labs.sorting;

import java.io.PrintWriter;

/**
 * 
 * Main class for analysing various implementations of merge sort
 * @author Samuel Rebelsky
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class MergeAnalyst
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new MergeSorter<Integer>(),
                                          new MergeSorterB<Integer>(),
                                          new IterativeMergeSorter<Integer>() };
    String[] sorterNames = { "MergeSorter", "MergeSorterB", "IterativeMerge" };

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.reverseIntArrBuilder,
                                                      SorterAnalyzer.mostlyOrderedIntArrBuilder };
    String[] builderNames =
        { "Random", "Increasing", "Reverse", "Mostly Ordered" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);

  } // main(String[])
  //Explanation: 
  // The first two implementations of merge sort (recursive) take virtually the
  // same amount of time. Iterative merge sort take slightly less time than the
  // other methods. Sorting random arrays takes longer than any other type. 
} // MergeAnalyst

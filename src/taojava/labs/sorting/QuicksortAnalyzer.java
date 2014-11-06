package taojava.labs.sorting;

import java.io.PrintWriter;

/**
 * Main class for analyzing various implementations of quick sort.
 * @author Albert Owusu-Asare
 * @author Samuel Rebelsky
 * @author Zhi Chen
 * @author Zoe Wolter
 */
public class QuicksortAnalyzer
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new NewQuicksorter<Integer>(),
                                          new NewerQuicksorter<Integer>(),
                                          new NewRandomQuicksorter<Integer>(),
                                          new NewMedianQuicksorter<Integer>() };
    String[] sorterNames = { "First", "Middle", "Random", "Median" };

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
} // QuickSortAnalyzer

//Explanation:

// For arrays that are in increasing, mostly ordered, or reversed order, 
// picking the middle element as the pivot gives the best run times. For 
// randomly generated arrays, how we pick the pivot doesn't seem to make 
// a big difference in run time. 

// When picking the first element as the pivot, it works fine for random
// arrays. For increasing arrays, it is very inefficient, as the partition 
// performs a lot of swaps which then makes the array less ordered.
// Reversed arrays are even more inefficient. The results are better for 
// mostly ordered arrays, but still about as twice as slow as other pivots.

// We find it interesting that picking the median of three random elements
// is slightly less efficient than picking one random element. Maybe 
// because finding the median takes more time? We're not sure.
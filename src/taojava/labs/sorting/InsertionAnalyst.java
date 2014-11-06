package taojava.labs.sorting;

import java.io.PrintWriter;

/**
 * 
 * Main class for analyzing various implementations of insertion sort
 * @author Samuel Rebelsky
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class InsertionAnalyst
{

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] {
                                          new InsertionSorter<Integer>(),
                                          new InsertionSorterInlineSwap<Integer>(),
                                          new InsertionSorterShift<Integer>() };
    String[] sorterNames = { "InsertionA", "InsertionInline", "InsertionShift" };

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
  } // main (String[])
  // Shifting elements was obviously faster than the other implementations 
  // (average 1715 for 40,000 arrays)
  // Swapping Inline vs. a function call to swap didn't make much of a 
  // difference, but for some reason the inline version of swap took a little 
  // longer in the tests we ran. (2565 vs. 2364)
} // InsertionAnalyst

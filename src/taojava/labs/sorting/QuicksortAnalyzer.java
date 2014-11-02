package taojava.labs.sorting;

import java.io.PrintWriter;

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
    String[] sorterNames = { "New", "Newer", "Random", "Median" };

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

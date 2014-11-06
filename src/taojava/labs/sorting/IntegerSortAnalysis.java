package taojava.labs.sorting;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Random;

/**
 * Methods used to analyze the runtime of IntegerQuicksort
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class IntegerSortAnalysis
{
  //+---------------+---------------------------------------------------
  // | Configuration |
  // +---------------+

  /**
   * The number of repetitions we do in gathering statistics.
   */
  static final int REPETITIONS = 16;

  /**
   * The smallest array size we use.
   */
  static final int SMALLEST = 10000;

  /**
   * The largest array size we use.
   */
  static final int LARGEST = 160000;

  /**
   * The amount we scale the array size between tests.
   */
  static final int SCALE = 2;

  //+-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Build arrays of random integer values.
   */
  public static final IntegerArrayBuilder randomIntArrBuilder = (length) ->
    {
      int[] vals = new int[length];
      Random random = new Random();
      for (int i = 0; i < length; i++)
        vals[i] = random.nextInt(length);
      return vals;
    }; // randomIntArrayBuilder

  /** 
   * Build arrays of integer values in increasing order.
   */
  public static final IntegerArrayBuilder increasingIntArrBuilder = (length) ->
    {
      int[] vals = new int[length];
      for (int i = 0; i < length; i++)
        vals[i] = i;
      return vals;
    }; // increasingIntArrBuilder

  /**
   * Build arrays that are mostly in increasing order.
   */
  public static final IntegerArrayBuilder mostlyOrderedIntArrBuilder =
      (length) ->
        {
          int[] vals = new int[length];
          int tenPercent = length / 10;
          if (tenPercent == 0)
            tenPercent = 1;
          for (int i = 0; i < length; i++)
            vals[i] = i;
          for (int i = 0; i < tenPercent; i++)
            Utils.swap(vals, Utils.generator.nextInt(length),
                       Utils.generator.nextInt(length));
          return vals;
        };// mostlyOrderedIntArrBuilder

  /**
   * Build arrays that are in reverse order.
   */
  public static final IntegerArrayBuilder reverseIntArrBuilder = (length) ->
    {
      int[] vals = new int[length];
      for (int i = 0; i < length; i++)
        vals[i] = length - i;
      return vals;
    };// reverseIntArrBuilder

  //+---------------+---------------------------------------------------
  // | Class Methods |
  // +---------------+
  /**
   * Determine the amount of time sorter takes to sort an array of
   * the given size created by builder.
   *
   * @param sorter
   *   The sorting routine we are testing.
   * @param builder
   *   The constructor for the array we will sort.
   * @param order
   *   The comparator we use in sorting.
   * @param size
   *   The size of the array that we sort.
   */
  public static long
    basicIntegerAnalysis(IntegerArrayBuilder builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();

    // Build the array.
    int[] values = builder.build(size);

    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();

    // Start the timer.  (Duh.)
    timer.start();

    // Do the real work.
    try
      {
        IntegerQuicksort.qsort(values, 0, values.length);
      } // try
    catch (Throwable error)
      {
        // Sorting failed with some error. Return -1 to
        // indicate failure.
        return -1;
      } // catch

    // Stop the timer.
    timer.pause();

    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
   * Repeatedly perform basic analysis and gather statistics
   * (e.g., minimum time, maximum time, average time.
   */
  public static long[] compoundIntegerAnalysis(IntegerArrayBuilder builder,
                                               int size, int repetitions)
  {

    long[] times = new long[repetitions];
    long min = 0;
    long max = 0;
    long avg = 0;
    long remainder = 0;
    long temp;

    for (int i = 0; i < repetitions; i++)
      {
        temp = basicIntegerAnalysis(builder, size);
        times[i] = temp;
        if (i == 0)
          {
            min = temp;
          }
        else if (temp < min)
          {
            min = temp;
          } // if smaller than min
        if (temp > max)
          {
            max = temp;
          } // if larger than max

        avg += (temp / repetitions);
        remainder += (temp % repetitions);
      } // for repetitions
    avg += remainder / repetitions;
    remainder = remainder % repetitions;

    if (Utils.sign(avg) == -(Utils.sign(remainder)))
      avg += Utils.sign(remainder);

    return new long[] { min, max, avg };
  } // compoundAnalysis(Sorter<T>, ArrayBuilder<T>, int, int)

  /**
   * Given a variety of sorters and builders, does some analysis
   * of each sorter/builder pair using a variety of array sizes
   * and prints out the results.
   *
   * @param pen
   *   Where to send the output
   * @param sorters
   *   The objects that do the sorting
   * @param sorterNames
   *   The names of those sorters
   * @param builders
   *   The objects to build the arrays
   * @param builderNames
   *    The names of those builders
   */
  public static <T> void
    combinedIntegerAnalysis(PrintWriter pen, Comparator<T> order,
                            ArrayBuilder<T> builders[],
                            IntegerArrayBuilder intbuilders[],
                            String[] builderNames)
  {
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "Sorter", "Builder",
               "Input Size", "Minimum Time", "Maximum Time", "Average Time");
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "------", "-------",
               "------------", "------------", "------------", "------------");

    for (int b = 0; b < builders.length; b++)
      {
        for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
          {
            long[] stats =
                SorterAnalyzer.compoundAnalysis(new NewRandomQuicksorter(),
                                                order, builders[b], size,
                                                REPETITIONS);
            pen.printf("%-16s%-16s%12d    %12d    %12d    %12d\n",
                       "Generalized", builderNames[b], size, stats[0],
                       stats[1], stats[2]);
          } // for size
      } // for builder : builders

    for (int b = 0; b < builders.length; b++)
      {
        for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
          {
            long[] stats =
                compoundIntegerAnalysis(intbuilders[b], size, REPETITIONS);
            pen.printf("%-16s%-16s%12d    %12d    %12d    %12d\n", "int",
                       builderNames[b], size, stats[0], stats[1], stats[2]);
          } // for size
      } // for builder : builders
  } // combinedAnalysis(PrintWRiter, Sorter<T>, String[], ...)

  /**
   * Force garbage collection to the best of our ability.
   */
  public static void gc()
  {
    // Right now, we use the quick and dirty "suggest garbage
    // collection".  Over the long term, we will probably try
    // something like "get the pid and execute 'jcmd <pid> GC.run'"
    // The pid *might* be in the environment.
    System.gc();
  } // gc()

  /**
   * main method performing tests.
   */
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.reverseIntArrBuilder,
                                                      SorterAnalyzer.mostlyOrderedIntArrBuilder };
    IntegerArrayBuilder[] intbuilders =
        new IntegerArrayBuilder[] { randomIntArrBuilder,
                                   increasingIntArrBuilder,
                                   reverseIntArrBuilder,
                                   mostlyOrderedIntArrBuilder };
    String[] builderNames =
        { "Random", "Increasing", "Reverse", "Mostly Ordered" };
    combinedIntegerAnalysis(pen, SorterAnalyzer.standardIntComparator,
                            builders, intbuilders, builderNames);
  } // main(String[])

} // IntegetSortAnalysis

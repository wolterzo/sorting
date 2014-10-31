package taojava.labs.sorting;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Tools for analyzing sorters.
 *
 * @author Samuel A. Rebelsky
 * @author Zoe Wolter
 * @author Zhi Chen
 * @author Albert Owusu-Asare
 */
public class SorterAnalyzer
{
  // +---------------+---------------------------------------------------
  // | Configuration |
  // +---------------+

  /**
   * The number of repetitions we do in gathering statistics.
   */
  static final int REPETITIONS = 12;

  /**
   * The smallest array size we use.
   */
  static final int SMALLEST = 40;

  /**
   * The largest array size we use.
   */
  static final int LARGEST = 40000;

  /**
   * The amount we scale the array size between tests.
   */
  static final int SCALE = 10;

  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A comparator for integers.
   */
  public static final Comparator<Integer> standardIntComparator =
      (left, right) -> left.compareTo(right);

  /**
   * Build arrays of random integer values.
   */
  public static final ArrayBuilder<Integer> randomIntArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      Random random = new Random();
      for (int i = 0; i < length; i++)
        vals[i] = random.nextInt(length);
      return vals;
    }; // randomIArrayBuilder

  /** 
   * Build arrays of integer values in increasing order.
   */
  public static final ArrayBuilder<Integer> increasingIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          for (int i = 0; i < length; i++)
            vals[i] = i;
          return vals;
        };

  /**
   * Build arrays that are mostly in increasing order.
   */
  public static final ArrayBuilder<Integer> mostlyOrderedIntArrBuilder =
      (length) ->
        {
          Integer[] vals = new Integer[length];
          int tenPercent = length / 10;
          if (tenPercent == 0)
            tenPercent = 1;
          for (int i = 0; i < length; i++)
            vals[i] = i;
          for (int i = 0; i < tenPercent; i++)
            Utils.swap(vals, Utils.generator.nextInt(length),
                       Utils.generator.nextInt(length));
          return vals;
        };

  /**
   * Build arrays that are in reverse order.
   */
  public static final ArrayBuilder<Integer> reverseIntArrBuilder = (length) ->
    {
      Integer[] vals = new Integer[length];
      for (int i = 0; i < length; i++)
        vals[i] = length - i;
      return vals;
    };

  // +--------------+----------------------------------------------------
  // | Class Fields |
  // +--------------+

  // +---------------+---------------------------------------------------
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
  public static <T> long basicAnalysis(Sorter<T> sorter, Comparator<T> order,
                                       ArrayBuilder<T> builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();

    // Build the array.
    T[] values = builder.build(size);

    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();

    // Start the timer.  (Duh.)
    timer.start();

    // Do the real work.
    sorter.sort(values, order);

    // Stop the timer.
    timer.pause();

    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
   * Repeatedly perform basic analysis and gather statistics
   * (e.g., minimum time, maximum time, average time.
   */
  public static <T> long[] compoundAnalysis(Sorter<T> sorter,
                                            Comparator<T> order,
                                            ArrayBuilder<T> builder, int size,
                                            int repetitions)
  {

    long[] times = new long[repetitions];
    long min = 0;
    long max = 0;
    long avg = 0;
    long remainder = 0;
    long temp;

    for (int i = 0; i < repetitions; i++)
      {
        temp = basicAnalysis(sorter, order, builder, size);
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
  public static <T> void combinedAnalysis(PrintWriter pen, Sorter<T>[] sorters,
                                          String[] sorterNames,
                                          Comparator<T> order,
                                          ArrayBuilder<T> builders[],
                                          String[] builderNames)
  {
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "Sorter", "Builder",
               "Input Size", "Minimum Time", "Maximum Time", "Average Time");
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "------", "-------",
               "------------", "------------", "------------", "------------");
    for (int a = 0; a < sorters.length; a++)
      {
        for (int b = 0; b < builders.length; b++)
          {
            for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
              {
                long[] stats =
                    compoundAnalysis(sorters[a], order, builders[b], size,
                                     REPETITIONS);
                pen.printf("%-16s%-16s%12d    %12d    %12d    %12d\n",
                           sorterNames[a], builderNames[b], size, stats[0],
                           stats[1], stats[2]);
              } // for size
          } // for builder : builders
      } // for sorter : sorters
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

  public static void main(String[] args)
  {
    /*
    Integer[] test = reverseIntArrBuilder.build(20);
    System.out.println(Arrays.toString(test));
    */
    Integer[] a1 = { 0, 3, 7, 16 };
    Integer[] a2 = { 4, 5, 6, 8, 9 };
    Integer[] results = new Integer[9];

    Utils.merge(StandardIntegerComparator.COMPARATOR, a1, 0, 4, a2, 0, 5,
                results, 0, 9);
    System.out.println(Arrays.toString(results));
  }// main

} // class SorterAnalyzer

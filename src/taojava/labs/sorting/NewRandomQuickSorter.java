package taojava.labs.sorting;

import java.util.Comparator;

/**
 * Sort using a slightly different version of Quicksort.
 *
 * @author Zoe Wolter   
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class NewRandomQuickSorter<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select a random element of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    return vals[lb + Utils.generator.nextInt(ub-lb)];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewRandomQuickSorter


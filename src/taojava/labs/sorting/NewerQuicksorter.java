package taojava.labs.sorting;

import java.util.Comparator;

/**
 * Sort using a slightly different version of Quicksort.
 *
 * @author Zoe Wolter   
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class NewerQuicksorter<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select the middle element of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    return vals[(lb + ub) / 2];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewerQuickSorter

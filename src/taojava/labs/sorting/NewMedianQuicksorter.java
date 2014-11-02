package taojava.labs.sorting;
import java.util.Comparator;

/**
 * Sort using a slightly different version of Quicksort.
 *
 * @author Zoe Wolter   
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class NewMedianQuicksorter<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select the median of three random elements of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    @SuppressWarnings("unchecked")
    T[] valsAtPivots = (T[]) new Object[3];
    // generate three random values from the array
    for (int i = 0; i < valsAtPivots.length; i++)
      {
        valsAtPivots[i] =  vals[Utils.generator.nextInt(ub-lb) + lb];
      } // for
    // Sort array of values using insertion sort.
    InsertionSorter<T> sorter = new InsertionSorter<T>();
    sorter.sorti(valsAtPivots, order);
    return valsAtPivots[1];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewMedianQuickSorter
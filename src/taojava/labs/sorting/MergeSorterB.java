package taojava.labs.sorting;

import java.util.Comparator;

/**
 * 
 * Sort using insertion sort, swapping elements in-line
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public class MergeSorterB<T>
    extends SorterBridge<T>
{
  /**
   * Sort elements of val using recursive merge sort that only uses one extra
   * scratch array.
   */
  @Override
  public T[] sort(T[] vals, Comparator<T> order)
  {
    T[] scratch = (T[]) new Object[vals.length];
    return mergeSort(vals, order, 0, vals.length, scratch);
  }//sort((T[], Comparator<T>)

  /**
   * Sort values using recursive merge sort with one extra array. 
   * @param values the array to be sorted
   * @param order the comparator
   * @param lb lower bound index of values
   * @param ub upper bound index of values
   * @param scratch a dummy array to store elements while sorting
   * @return a sorted array
   */
  public T[] mergeSort(T[] values, Comparator<T> order, int lb, int ub,
                       T[] scratch)
  {
    int mid;
    int length = ub-lb;
    if (length <= 1)
      {
        return values;
      } // if array is one element
    else
      {
        mid =  lb + length / 2;
        mergeSort(values, order, lb, mid, scratch);
        mergeSort(values, order, mid, ub, scratch);
        // copy sorted halves into scratch
        for (int i = lb; i < ub; i++)
          {
            scratch[i] = values[i];
          } // for
        // merge back into values
        return Utils.merge(order, scratch, lb, mid, scratch, mid, ub, values, lb, ub);
      } // else
  } // mergeSort
} // MergeSorterB<T> 

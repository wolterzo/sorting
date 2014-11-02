package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Your Name Here.
 */
public class IterativeMergeSorter<T>
    extends SorterBridge<T>
{
  /**
   * Sort vals using iterative merge sort. See the Sorter<T> interface for
   * additional details.
   */
  @Override
  public T[] sorti(T[] vals, Comparator<T> order)
  {
    // STUB
    int size = 1;
    int length = vals.length;
    @SuppressWarnings("unchecked")
    T[] scratch = (T[]) new Object[length];
    while (size < length)
      {
        scratch = vals.clone();
        // Merge neighboring subarrays of size size
        int i = 0;
        while (i + 2 * size <= length)
          {
            Utils.merge(order, scratch, i, i + size, scratch, i + size, i + 2
                                                                        * size,
                        vals, i, i + 2 * size);
            i += 2 * size;
          } // while
        if (2 * size > length)
          {
            Utils.merge(order, scratch, 0, size, scratch, size, length, vals,
                        0, length);
          }//if
        // The merged subarrays are now twice as large
        size *= 2;
      } // while

    return vals;
  } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>

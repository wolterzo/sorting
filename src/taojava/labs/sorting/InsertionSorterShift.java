package taojava.labs.sorting;

import java.util.Comparator;

public class InsertionSorterShift<T>
    extends SorterBridge<T>
{
  /**
   * Sort vals using insertion sort. See the Sorter<T> interface for additional
   * details.
   */
  @Override
  public T[] sorti(T[] vals, Comparator<T> order)
  {
    for (int i = 1; i < vals.length; i++)
      {
        insert(vals, order, i);
      } // for
    return vals;
  } // sorti(T[], Comparator<T>)

  /**
   * Insert the value in position i into the sorted subarray in positions
   * 0..(n-1).
   * 
   * @param values
   *   the array into which we are inserting values.
   * @param order
   *   the comparator used to determine order.
   * @param n
   *   the bound on the end of the subarray.
   * 
   * @pre 0 <= n < values.length
   * @pre Utils.sorted(values, order, 0, n).
   * @pre order can be compared to any pair of values in values.
   * @post Utils.sorted(values, order, 0, n-1)
   * @post No elements have been added or removed.
   */
  void insert(T[] vals, Comparator<T> order, int n)
  {
    // Invariants:
    //   I1(i): Utils.sorted(values,0,i).
    //   I2(i): Utils.sorted(values,i+1,n).
    //   I3(i): For all l and r, 0 <= l <= i, i < r <= n,
    //           order.compare(vals[l],vals[r]) <= 0
    // Analysis:
    //   I1(n) holds at the because it's a precondition.
    //   I2(n) holds at the beginning because that subarray is empty
    //   I3(n) holds at the beginning because the second subarray is empty
    int i = n;
    T tmp = vals[n];
    while ((i > 0) && (order.compare(tmp, vals[i-1]) < 0))
      {
        vals[i] = vals[i-1];
        i--;
      } // while
    vals[i] = tmp;
  } // insert(T[], Comparator<T>, int)
} // InsertionSorterShift<T>

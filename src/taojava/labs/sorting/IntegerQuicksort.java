package taojava.labs.sorting;
/**
 * Non-generic quicksort method for ints.
 * @author Zhi Chen
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 */

public class IntegerQuicksort
{
  /**
   * Sort the elements in positions [lb..ub) using Quicksort.
   */
  public static void qsort(int[] vals, int lb, int ub)
  {
    // One element arrays are sorted.
    if (lb >= ub - 1)
      {
        return;
      }
    else
      {
        int pivot = selectPivot(vals, lb, ub);
        int[] bounds = partition(pivot, vals, lb, ub);
        qsort(vals, lb, bounds[0]);
        qsort(vals, bounds[1], ub);
      } // More than one element
  } // qsort(T[], Comparator<T>, int, int)

  /**
   * Select a random pivot from within positions [lb..ub) of vals.
   */
  public static int selectPivot(int[] vals, int lb, int ub)
  {
    return vals[lb + Utils.generator.nextInt(ub-lb)];
  } // selectPivot(T[], Comparator<T>, int, int)

  /**
   * Reorganize the elements in positions [lb..ub) of vals such that
   * elements smaller than the pivot appear to the left, elements
   * bigger than the pivot appear to the right of the pivot, and
   * copies of the pivot are in the middle.  Return a two-element
   * array that gives the lower bound (inclusive) and upper bound
   * (exclusive) of the section of the array that contains the equal
   * values.
   *
   * @param
   *    pivot, a value
   * @param
   *    values, an array.
   * @param
   *    order, a comparator.
   * @param
   *    lb, an integer.
   * @param
   *    ub, an integer.
   * @return
   *    mid, the index of the pivot.
   *
   * @pre
   *    order can be applied to any pair of elements in vals.
   * @pre
   *    0 <= lb < ub < values.length.
   * @post
   *    lb <= mid < ub
   * @post
   *    values[mid] == pivot, for some value pivot
   * @post
   *    For all i, lb <= i < mid, values[i] <= pivot
   *    For all i, mid < i < ub, values[i] > pivot
   */
  static int[] partition(int pivot, int[] vals, int lb, int ub)
  {
    // Looked at notes on Exam 1
    int mid = lb;
    while (mid < ub)
      {
        int curr = vals[mid];
        if (curr < pivot)
          {
            Utils.swap(vals, lb++, mid++);
          } // if smaller
        else if (curr == pivot)
          {
            ++mid;
          } // else if equal to pivot
        else
          {
            Utils.swap(vals, mid, --ub);
          } // else larger
      } // while
    return new int[] { lb, ub };
  } // partition
} // class IntegerQuicksort

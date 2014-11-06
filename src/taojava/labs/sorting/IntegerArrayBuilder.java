package taojava.labs.sorting;

/**
 * An interface for building arrays of ints
 *
 * @author Zoe Wolter
 * @author Albert Owusu-Asare
 * @author Zhi Chen
 */
public interface IntegerArrayBuilder
{
  /**
   * Build an int array of length len.
   *
   * @pre
   *   len >= 0
   */
  public int[] build(int len);
} // IntegerArrayBuilder

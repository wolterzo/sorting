package taojava.labs.sorting;

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

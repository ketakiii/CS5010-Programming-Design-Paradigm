package permutations;

import java.util.NoSuchElementException;

/**
 * This class implements the interface BackAndForthIterator and its methods.
 */
public class Permutations implements BackAndForthIterator<String> {

  private String base;
  private int currentLength;
  private int currentChar;
  private int[] pointer;


  /**
   * Constructor of a Permutation object that will allow iteration through all possible
   * permutations of an input string.
   * @param base the input string defining the characters to permute
   * @throws IllegalArgumentException illegal argument exception
   */
  public Permutations(String base) throws IllegalArgumentException {
    this(base, 1);
  }

  /**
   * Constructor of a Permutation object that will allow iteration through all possible
   * permutations of an input string. We start iterating at sequences at a given length.
   * @param base the input string defining the characters to permute
   * @param startLength the length at which to start the iterator
   * @throws IllegalArgumentException when the start length < base length or less than 1
   */
  public Permutations(String base, int startLength) throws IllegalArgumentException {
    String alphabet = "[abcdefghijklmnopqrstuvwxyz]*";
    if (!base.matches(alphabet)) {
      throw new IllegalArgumentException("Input string should only contain alphabetic characters.");
    }
    if (startLength > base.length() || startLength < 1) {
      throw new IllegalArgumentException("The starting permutation length must be no larger than"
          + " the input string length");
    }
    this.base = base;
    this.currentLength = startLength;
    this.currentChar = 0;
    this.pointer = new int[this.base.length()];
    for (int i = 0; i < this.pointer.length; i++) {
      this.pointer[i] = i;
    }
  }

  private Permutations(String base, int startLength, int currentChar) {
    this.base = base;
    this.currentLength = startLength;
    this.currentChar = currentChar;
  }

  @Override public String previous() throws NoSuchElementException {
    if (!this.hasPrevious()) {
      throw new NoSuchElementException("There is no previous!");
    }
    String result = "";
    for (int i = 0; i < this.currentLength; i++) {
      result += this.base.charAt(this.pointer[i]);
    }
    this.changePrev();
    return result;
  }

  private void changePrev() {
    // is out pointer[index] bigger than 0
    // pointer[index] was 0
    // index was in the first position
    // move to smaller length if possible
    // else reset pointer[index] to its max value
    // decrement pointer [index -1]
    int index = this.base.length() - 1;
    if (this.pointer[index] > 0) {
      this.pointer[index]--;
    } else {
      boolean doneWithLength = true;
      int minValue = 0;
      for (int i = this.currentLength - 1; i >= 0; i--) {
        if (this.pointer[i] != minValue) {
          doneWithLength = false;
        }
        minValue++;
      }
      if (doneWithLength) {
        this.currentLength++;
        if (this.currentLength <= this.base.length()) {
          for (int i = 0; i < this.currentLength; i++) {
            this.pointer[i] = i;
          }
        }
      } else {
        int currInd = index;
        int endValue = this.base.length() - 1;
        while (currInd > 0) {
          if (this.pointer[currInd] == endValue) {
            currInd--;
            endValue--;
          } else {
            break;
          }
          this.pointer[currInd]++;
          for (int i = currInd + 1; i <= index; i++) {
            this.pointer[i] = this.pointer[i - 1] + 1;
          }
        }
      }
    }
  }

  @Override public boolean hasPrevious() {
    return (this.currentLength > 1) || (this.currentLength == 1 && this.currentChar > 0);
  }

  @Override public boolean hasNext() {
    return this.currentLength <= this.base.length();
  }

  @Override public String next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("There is no next!");
    }
    String result = "";
    for (int i = 0; i < this.currentLength; i++) {
      result += this.base.charAt(this.pointer[i]);
    }
    this.changeNext();
    return result;
  }

  /**
   * Updates the class to point to the next item in the iteration set.
   */
  private void changeNext() {
    int index = this.currentLength - 1;
    if (this.pointer[index] < this.base.length() - 1) {
      this.pointer[index]++;
    } else {
      boolean doneWithLength = true;
      int maxValue = this.base.length() - 1;
      for (int i = this.currentLength - 1; i >= 0; i--) {
        if (this.pointer[i] != maxValue) {
          doneWithLength = false;
        }
        maxValue--;
      }
      if (doneWithLength) {
        this.currentLength++;
        if (this.currentLength <= this.base.length()) {
          for (int i = 0; i < this.currentLength; i++) {
            this.pointer[i] = i;
          }
        }
      } else {
        int currInd = index;
        int endValue = this.base.length() - 1;
        while (currInd > 0) {
          if (this.pointer[currInd] == endValue) {
            currInd--;
            endValue--;
          } else {
            break;
          }
          this.pointer[currInd]++;
          for (int i = currInd + 1; i <= index; i++) {
            this.pointer[i] = this.pointer[i - 1] + 1;
          }
        }
      }
    }
  }

  @Override public String toString() {
    return this.base + ", " + this.currentLength;
  }
}

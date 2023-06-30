package assignments;

import java.util.List;

/**
 * This method tells us the scheduling strategy which gives us a method to
 * schedule events or tasks given an implementation of the scheduler.
 */
public interface SchedulingStrategy {

  /**
   * Method responsible for scheduling the assignments according to a given strategy.
   * @param assignments assignments
   * @return string
   */
  public String schedule(List<Assignment> assignments);



}

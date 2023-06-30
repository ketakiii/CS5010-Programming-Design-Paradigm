package assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of task that need to be completed.
 */
public class AssignmentList {

  private final List<Assignment> tasks;
  private String ordering;

  /** Default constructor. */
  public AssignmentList() {
    tasks = new ArrayList<>();
    ordering = "assigned";
  }

  /**
   * Add a task to the task list.
   * 
   * @param t the task
   */
  public void add(Assignment t) {
    tasks.add(t);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Ordered by ");
    sb.append(ordering);
    sb.append("\n");
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(i + 1);
      sb.append(" -- ");
      sb.append(tasks.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * Reorder the list of assignments based on the given SchedulingStrategy.
   * @param strat the strategy to use to schedule assignments
   * @throws IllegalArgumentException if the input strategy is null
   */
  public void scheduleAssignments(SchedulingStrategy strat) throws IllegalArgumentException {
    if (strat == null) {
      throw new IllegalArgumentException("Input must be a valid SchedulingStrategy");
    }
    if (this.tasks.isEmpty()) {
      throw new IllegalArgumentException("No assignments to sort");
    }
    this.ordering = strat.schedule(this.tasks);
  }
}

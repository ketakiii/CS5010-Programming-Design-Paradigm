package assignments;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;


/** Class that tests the tasks. */
public class AssignmentTest {

  private SchedulingStrategy strategy;
  private AssignmentList list;
  private Assignment cs5001;
  private Assignment cs5002;
  private Assignment cs5004;
  private Assignment cs5008;
  private Assignment cs5010;

  /**
   * Creating the setup for assignments.
   */
  @Before
  public void setUp() {
    list = new AssignmentList();
    cs5001 = new Assignment("cs5001 hw1");
    cs5002 = new Assignment("cs5002 hw3");
    cs5004 = new Assignment("cs5004 lab2");
    cs5008 = new Assignment("cs5008 hw3");
    cs5010 = new Assignment("cs5010 lab4");

    cs5001.setStart(1, 10, 2023);
    cs5001.setDeadline(1, 31, 2023);
    cs5002.setStart(2, 15, 2023);
    cs5002.setDeadline(3, 6, 2023);
    cs5004.setStart(1, 20, 2023);
    cs5004.setDeadline(2, 2, 2023);
    cs5008.setStart(2, 15, 2023);
    cs5008.setDeadline(3, 5, 2023);
    cs5010.setStart(2, 7, 2023);
    cs5010.setDeadline(3, 6, 2023);

    list.add(cs5001);
    list.add(cs5002);
    list.add(cs5004);
    list.add(cs5008);
    list.add(cs5010);
  }

  /** Testing constructor and toString(). */
  @Test
  public void testConstructor() {
    LocalDate now = LocalDate.now();
    System.out.println(now);
    Assignment t1 = new Assignment("task 1");
    assertEquals("task 1, starting " + now + ", ending " + now, t1.toString());
    Assignment t2 = new Assignment("task 2");
    t2.setDeadline(3, 4, 2025);
    assertEquals("task 2, starting " + now + ", ending 2025-03-04", t2.toString());
  }

  /**
   * Test to ensure we can properly order assignments in the order they were assigned.
   */
  @Test public void testAssignedScheduling() {
    strategy = new AssignedSchedulingStrategy();
    list.scheduleAssignments(new DifficultySchedulingStrategy());
    list.scheduleAssignments(strategy);
    String expected = "Ordered by assigned\n"
        + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
        + "2 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-06\n"
        + "3 -- cs5004 lab2, starting 2023-01-20, ending 2023-02-02\n"
        + "4 -- cs5008 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "5 -- cs5010 lab4, starting 2023-02-07, ending 2023-03-06\n";
    assertEquals(expected, list.toString());
  }

  /**
   * Test to ensure we can properly order assignments in the alphabetical order they were assigned.
   */
  @Test public void testAlphabeticalScheduling() {
    strategy = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(new DifficultySchedulingStrategy());
    list.scheduleAssignments(strategy);
    String expected = "Ordered by alphabetical\n"
        + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
        + "2 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-06\n"
        + "3 -- cs5004 lab2, starting 2023-01-20, ending 2023-02-02\n"
        + "4 -- cs5008 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "5 -- cs5010 lab4, starting 2023-02-07, ending 2023-03-06\n";
    assertEquals(expected, list.toString());
  }

  /**
   * Test to ensure we can properly order assignments in the order they are due.
   */
  @Test public void testDeadlineScheduling() {
    strategy = new DeadlineSchedulingStrategy();
    list.scheduleAssignments(new AssignedSchedulingStrategy());
    list.scheduleAssignments(strategy);
    String expected = "Ordered by deadline\n"
        + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
        + "2 -- cs5004 lab2, starting 2023-01-20, ending 2023-02-02\n"
        + "3 -- cs5008 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "4 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-06\n"
        + "5 -- cs5010 lab4, starting 2023-02-07, ending 2023-03-06\n";
    assertEquals(expected, list.toString());
  }

  /**
   * Test to ensure we can properly order assignments in the difficulty level.
   */
  @Test public void testDifficultyScheduling() {
    strategy = new DifficultySchedulingStrategy();
    list.scheduleAssignments(new DeadlineSchedulingStrategy());
    list.scheduleAssignments(strategy);
    String expected = "Ordered by difficulty\n"
        + "1 -- cs5010 lab4, starting 2023-02-07, ending 2023-03-06\n" //30
        + "2 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n" //21
        + "3 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-06\n" //
        + "4 -- cs5008 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "5 -- cs5004 lab2, starting 2023-01-20, ending 2023-02-02\n";
    assertEquals(expected, list.toString());

    Assignment cs5008b = new Assignment("cs5008 lab3");
    cs5008b.setStart(3, 1, 2023);
    cs5008b.setDeadline(3, 8, 2023);
    list.add(cs5008b);
    expected += "6 -- cs5008 lab3, starting 2023-03-01, ending 2023-03-08\n";
    assertEquals(expected, list.toString());

    list.scheduleAssignments(strategy);
    expected = "Ordered by difficulty\n"
        + "1 -- cs5010 lab4, starting 2023-02-07, ending 2023-03-06\n"
        + "2 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
        + "3 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-06\n"
        + "4 -- cs5008 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "5 -- cs5004 lab2, starting 2023-01-20, ending 2023-02-02\n"
        + "6 -- cs5008 lab3, starting 2023-03-01, ending 2023-03-08\n";
    assertEquals(expected, list.toString());
  }

  /**
   * Test to ensure that if we try to schedule with a null scheduler we get the
   * proper exception.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testScheduleAssignmentsWithNullScheduler() {
    list.scheduleAssignments(null);
  }

  /**
   * Test to ensure that if we try to schedule when no assignments have been
   * added to the scheduler.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testScheduleAssignmentsWithNoAssignments() {
    list = new AssignmentList();
    strategy = new AlphabeticalSchedulingStrategy();
    list.scheduleAssignments(strategy);
  }
}

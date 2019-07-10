package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.TimeRegistration;

public class MethodFourTest {
    @Test(expected = OperationNotAllowedException.class)
    public void testNoProjects()
            throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
        PlanningApp planningApp = new PlanningApp();

        // Check input

        // Assert the project list is empty
        assertTrue(planningApp.getProjects().isEmpty());

        // Primary action test

        // Register time
        planningApp.registerTime(0, null, null);

        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the
        // exception not is given.
    }

    @Test(expected = OperationNotAllowedException.class)
    public void testProjectnumberOfNonexistingProject()
            throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
        PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Check input

        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(), projectNumber);

        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(), 1);

        // Assert that the project number isn't 190
        assertThat(projectNumber, is(not(190)));

        // Primary action test

        // Register time
        planningApp.registerTime(190, null, null);

        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the
        // exception not is given.
    }

    @Test(expected = ActivityNotFoundException.class)
    public void testActivityNameOfNonExisitingActivity()
            throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
        PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Check input

        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(), projectNumber);

        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(), 1);

        // Assert that the project number isn't 190
        assertThat(projectNumber, is(not(190)));

        // Primary action test

        // Register time
        planningApp.registerTime(projectNumber, "Activity", null);

        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the
        // exception not is given.
    }

    @Test(expected = OperationNotAllowedException.class)
    public void testTimeRegistrationForNonexisitingEmployee()
            throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
        PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null, "PL"));

        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        // Check input

        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(), projectNumber);

        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(), 1);

        // Assert that the project number isn't 190
        assertThat(projectNumber, is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(), "PL");

        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(), "PL");

        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(), 1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(), "Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(), 1);

        // Primary action test

        // Timeregistration with nonexisiting employee, previous assertions checks that only "PL", the project leader is
        // a employee in the planning app. So "JD" cannot be there.
        TimeRegistration timeRegistration = new TimeRegistration(new Employee(null, "JD"), null, (float) 0.0);

        // Register time
        planningApp.registerTime(projectNumber, "Activity", timeRegistration);

        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the
        // exception not is given.
    }

    @Test
    public void testRegisterTimeSuccessfully()
            throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
        PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null, "PL"));

        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        // Check input

        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(), projectNumber);

        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(), 1);

        // Assert that the project number isn't 190
        assertThat(projectNumber, is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(), "PL");

        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(), "PL");

        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(), 1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(), "Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(), 1);

        // Primary action test

        // Timeregistration with nonexisiting employee, previous assertions checks that only "PL", the project leader is
        // a employee in the planning app. So "JD" cannot be there.
        TimeRegistration timeRegistration = new TimeRegistration(new Employee(null, "PL"), null, (float) 0.0);

        // Register time
        planningApp.registerTime(projectNumber, "Activity", timeRegistration);

        // Assert timeregistration exists
        assertTrue(planningApp.searchForActivity(projectNumber, "Activity").getTimeRegistrations()
                .contains(timeRegistration));
    }
}

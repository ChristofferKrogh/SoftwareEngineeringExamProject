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

public class MethodThreeTest {
	@Test (expected = OperationNotAllowedException.class)
    public void testNoProjects() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        
        // Check input
        
        // Assert the project list is empty
        assertTrue(planningApp.getProjects().isEmpty());
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 3.0, 0, null, null);
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
		
	@Test (expected = OperationNotAllowedException.class)
    public void testProjectnumberOfNonexistingProject() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 3.0, 190, null, null);
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = ActivityNotFoundException.class)
	public void testActivityNameOfNonExisitingActivity() throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 3.0, projectNumber, null, null);
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}
	
	@Test (expected = NotProjectLeaderException.class)
	public void testInitialsOfNonProjectLeader() throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"PL"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(),"Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(),1);
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 3.0, projectNumber, "Activity", "JD");
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}

	@Test (expected = OperationNotAllowedException.class)
	public void testNegativeAmountOfHours() throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"PL"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(),"Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(),1);
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) -4.0, projectNumber, "Activity", "PL");
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}

	@Test (expected = OperationNotAllowedException.class)
	public void testTooManyHours() throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"PL"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(),"Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(),1);
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 24.1, projectNumber, "Activity", "PL");
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}

	@Test
	public void testCorrectedAmountOfHoursSuccessfully() throws OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"PL"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");

        // Create activity
        planningApp.addActivity(projectNumber, "Activity", null, null, 0, "PL");

        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),1);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(),"Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(),1);
        
        
        // Primary action test
        
        // Edit hours
        planningApp.editExpectedAmountOfHoursForActivity((float) 3, projectNumber, "Activity", "PL");
        
        // Check that it did happen
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getExpectedAmountOfHours(),3.0, 0);
	}
}

package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;

import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;

public class MethodOneTest {

	@Test (expected = OperationNotAllowedException.class)
    public void testNoEmployees() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        PlanningApp planningApp = new PlanningApp();
        
        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        // Assert that the project is created
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Activity name
        String activityName = "Activity";
        
        // Add project leader by initials
        String projectLeaderInitials = "PL";
        planningApp.addEmployee(new Employee(null,projectLeaderInitials));
        
        // Assert the project leader is added
        assertEquals(planningApp.searchForEmployee(projectLeaderInitials).getInitials(),projectLeaderInitials);
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, projectLeaderInitials);
        
        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),projectLeaderInitials);
        
        // Create activity
        planningApp.addActivity(projectNumber, activityName, new GregorianCalendar(), new GregorianCalendar(), 0, projectLeaderInitials);

        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, activityName).getName(),activityName);
        
        // Assign employee
        planningApp.assignEmployee(projectNumber, activityName, projectLeaderInitials, "JD");
        
        // Assert hat exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test
    public void testInitialsOfNonexistingEmployee() {
        
    }
	
	@Test
    public void testOnlyOneEmployee() {
        
    }
	
	@Test
    public void testInitialsOfNonExistingEmployee() {
        
    }
	
	@Test
    public void testNoProjects() {
        
    }
	
	@Test
    public void testProjectnumberOfNonexistingProject() {
        
    }
	
	@Test
    public void testNoProjectLeaderForProject() {
        
    }
	
	@Test
    public void testActivitynameOfNonexistingActivity() {
        
    }
	
	@Test
    public void testEvertyhingWorks() {
        
    }
}

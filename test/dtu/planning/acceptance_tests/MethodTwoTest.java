package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Test;

import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;

public class MethodTwoTest {
	@Test (expected = OperationNotAllowedException.class)
    public void testNoProjects() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        
        // Check input
        
        // Assert the project list is empty
        assertTrue(planningApp.getProjects().isEmpty());
        
        
        // Primary action test
        
        // Add activity
        planningApp.addActivity(0, null, new GregorianCalendar(), new GregorianCalendar(), 0, null);
        
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
        
        // Add activity
        planningApp.addActivity(190, null, new GregorianCalendar(), new GregorianCalendar(), 0, null);
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = NotProjectLeaderException.class)
	public void testIntialsOfNonProjectleader() throws OperationNotAllowedException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"PL"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");
        
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
        
        
        // Primary action test
        
        // Add activity
        planningApp.addActivity(projectNumber, null, new GregorianCalendar(), new GregorianCalendar(), 0, "JD");
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}
	
	@Test
	public void testActivityIsAddedToProjectSuccessfully() throws OperationNotAllowedException, NotProjectLeaderException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "JD");
        
        
        // Check input
        
        // Assert the project list contains the project
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);

        // Assert that the project number isn't 190
        assertThat(projectNumber,is(not(190)));

        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"JD");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),1);
        
        
        // Primary action test
        
        // Add activity
        planningApp.addActivity(projectNumber, null, new GregorianCalendar(), new GregorianCalendar(), 0, "JD");
                
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
	}
}

package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        // Setup
		PlanningApp planningApp = new PlanningApp();        
             
        
        // Check input
        
        // Assert employee list is empty
        assertTrue(planningApp.getEmployees().isEmpty());
        
        // Assign employee
        planningApp.assignEmployee(0, null, null, "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = OperationNotAllowedException.class)
    public void testInitialsOfNonexistingEmployee() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employee by initials
        planningApp.addEmployee(new Employee(null,"LL"));

        
        // Check input
        
        // Assert the employee is added
        assertEquals(planningApp.searchForEmployee("LL").getInitials(),"LL");
        
        // Assert the employee is the only one
        assertEquals(planningApp.getEmployees().size(),1);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(0, null, null, "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = OperationNotAllowedException.class)
    public void testOnlyOneEmployee() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employee by initials
        planningApp.addEmployee(new Employee(null,"JD"));

        
        // Check input
        
        
        // Assert the employee is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        
        // Assert the employee is the only one
        assertEquals(planningApp.getEmployees().size(),1);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(0, null, null, "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = OperationNotAllowedException.class)
    public void testInitialsOfNonExistingEmployee() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"NE"));

        
        // Check input
        
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("NE").getInitials(),"NE");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(0, null, "PL", "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = OperationNotAllowedException.class)
    public void testNoProjects() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"PL"));

        
        // Check input
        
        // Assert the project list is empty
        assertTrue(planningApp.getProjects().isEmpty());
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(0, null, "PL", "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = OperationNotAllowedException.class)
    public void testProjectnumberOfNonexistingProject() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"PL"));

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        // Check input
        
        // Assert the project list is empty
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(190, null, "PL", "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = NotProjectLeaderException.class)
    public void testNoProjectLeaderForProject() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"PL"));

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        
        // Check input
        
        // Assert the project list is empty
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(projectNumber, null, "PL", "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test (expected = ActivityNotFoundException.class)
    public void testActivitynameOfNonexistingActivity() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"PL"));

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");
        
        
        // Check input
        
        // Assert the project list is empty
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);
        
        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(projectNumber, "Activity", "PL", "JD");
        
        // Assert that exception is given on the assign is in the @Test annotation. So this test will fail if the exception not is given.
    }
	
	@Test
    public void testEmployeeAssignedToActivitySuccessfully() throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
        // Setup
		PlanningApp planningApp = new PlanningApp();

        // Add employees by initials
        planningApp.addEmployee(new Employee(null,"JD"));
        planningApp.addEmployee(new Employee(null,"PL"));

        // Create project and get its ID
        int projectNumber = planningApp.createProject(null, true).getProjectNumber();
        
        // Set project leader
        planningApp.setProjectLeader(projectNumber, "PL");
        
        // Create activity
        planningApp.addActivity(projectNumber, "Activity", new GregorianCalendar(), new GregorianCalendar(), 0, "PL");

        
        // Check input
        
        // Assert the project list is empty
        assertEquals(planningApp.searchForProject(projectNumber).getProjectNumber(),projectNumber);
        
        // Assert that it is the only project
        assertEquals(planningApp.getProjects().size(),1);
        
        // Assert that the project leader has been set
        assertEquals(planningApp.searchForProject(projectNumber).getProjectLeader().getInitials(),"PL");
        
        // Assert that the activity is created
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getName(),"Activity");

        // Assert that it is the only activity
        assertEquals(planningApp.searchForProject(projectNumber).getActivities().size(),1);
        
        // Assert the employees is added
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");
        assertEquals(planningApp.searchForEmployee("PL").getInitials(),"PL");
        
        // Assert the employee list only contain the two employees
        assertEquals(planningApp.getEmployees().size(),2);
        
        
        // Primary action test
        
        // Assign employee
        planningApp.assignEmployee(projectNumber, "Activity", "PL", "JD");
        
        // Assert that the employee is assigned to the activity
        assertEquals(planningApp.searchForEmployee("JD").getInitials(),"JD");

        // Assert that the employee is the only assigned employee
        assertEquals(planningApp.searchForActivity(projectNumber, "Activity").getAssignedEmployees().size(),1);
    }
}

package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.Project;

public class GetAllRelevantActivitiesForEmployee {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ActivityHolder activityHolder;
	private TimeRegistrationHolder timeRegistrationHolder;

	// Private variables for this steps file
	private SimpleEntry<List<Activity>, List<Integer>> relevantActivities;

	public GetAllRelevantActivitiesForEmployee(PlanningAppHolder planningAppHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, TimeRegistrationHolder timeRegistrationHolder) {
		this.planningAppHolder = planningAppHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.activityHolder = activityHolder;
		this.timeRegistrationHolder = timeRegistrationHolder;
	}
	
	@When("I get relevant activites for the employee")
	public void iGetRelevantActivitesForTheEmployee() {
		relevantActivities = planningAppHolder.getPlanningApp().getAllRelevantActivitiesForEmployee(employeeHolder.getEmployee().getInitials());
	}
	
	@Then("I do not get any relevant activities")
	public void iDoNotGetAnyRelevantActivities() {
		assertTrue(relevantActivities.getKey().isEmpty());
		assertTrue(relevantActivities.getValue().isEmpty());
	}
	
	@Then("I get the relevant project number and its activity")
	public void iGetTheRelevantProjectNumberAndItsActivity() {
		// Check that something is there
		assertFalse(relevantActivities.getKey().isEmpty());
		assertFalse(relevantActivities.getValue().isEmpty());
		
		// Check the wanted activity is actually there
	    assertEquals(relevantActivities.getKey().get(0),activityHolder.getActivity());
	    
	    // Check the project number is right
	    // projectHolder.getProject().getProjectNumber()
	    assertEquals(String.valueOf(relevantActivities.getValue().get(0)),String.valueOf(projectHolder.getProject().getProjectNumber()));
	}

}

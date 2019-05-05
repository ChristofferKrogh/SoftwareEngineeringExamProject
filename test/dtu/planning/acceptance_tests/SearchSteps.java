package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.OperationNotAllowedException;

public class SearchSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActivityHolder activityHolder;

	public SearchSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, ActivityHolder activityHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.activityHolder = activityHolder;
	}
	
	@Given("the activity with name {string} does not exists for project")
	public void theActivityWithNameDoesNotExistsForProject(String activityName) {
		Activity activity = null;
		try {
			activity = planningAppHolder.getPlanningApp().searchForProject(projectHolder.getProject().getProjectNumber()).getActivityByName(activityName);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
		// The 2nd and 3rd arguments are set to 0 as they are not important here.
		activityHolder.setActivity(new Activity(activityName,null,null,0));
		
		// Null is expected. If any activity is found the activity given with the name would exist and the given would not be fulfilled.
		assertTrue(activity==null);
	}
	
	@When("I search an activity with name {string}")
	public void iSearchAnActivityWithName(String activityName) {
	    try {
			activityHolder.setActivity(planningAppHolder.getPlanningApp().searchForActivity(projectHolder.getProject().getProjectNumber(), activityName));
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("I get an activity with name {string}")
	public void iGetAnActivityWithName(String activityName) {
		assertEquals(activityHolder.getActivity().getName(),activityName);
	}
}

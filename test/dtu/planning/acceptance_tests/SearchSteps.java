package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.Report;

public class SearchSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActorHolder actorHolder;
	private ActivityHolder activityHolder;
	private Report report;

	public SearchSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder, ActivityHolder activityHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
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
		assertTrue(activityHolder.getActivity().getName().equals(activityName));
	}
}

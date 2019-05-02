package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.Report;
import dtu.planning.app.TimeRegistration;

public class GenerateReportSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActorHolder actorHolder;
	private ActivityHolder activityHolder;
	private Report report;

	// Private variables, will give problems when otheres need to use them. Create holder then?
	private TimeRegistration timeRegistration;

	public GenerateReportSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder, ActivityHolder activityHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
	}

	@Given("the activity is estimated to last {int} hours")
	public void theActivityIsEstimatedToLastHours(Integer hours) throws ActivityNotFoundException, OperationNotAllowedException {
		Project project = planningAppHolder.getPlanningApp().searchForProject(projectHolder.getProject().getProjectNumber());
		Activity activity = project.getActivityByName(activityHolder.getActivity().getName());
		activity.setExpectedAmountOfHours(hours);
	}
	
	@When("The actor generates a report for the project")
	public void theActorGeneratesAReportForTheProject() {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			report = planningApp.generateReport(projectHolder.getProject().getProjectNumber(), actorHolder.getActor());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("A report over the project is generated")
	public void aReportOverTheProjectIsGenerated() {
		// Check that some report is generated
	    assertFalse(report==null);
	}
	
	@Then("A report over the project is generated with {int} hours reported on activity with name {string}")
	public void aReportOverTheProjectIsGeneratedWithHoursReportedOnActivityWithName(int hours, String activityName) throws ActivityNotFoundException {
		assertEquals(report.getReportedTimeForActivity(activityName),hours);
	}
	
	@Then("A report over the project is generated with {int} hours estimated on activity with name {string}")
	public void aReportOverTheProjectIsGeneratedWithHoursEstimatedOnActivityWithName(Integer hours, String activityName) throws ActivityNotFoundException {
		assertEquals(report.getEstimatedTimeForActivity(activityName),hours);
	}
}

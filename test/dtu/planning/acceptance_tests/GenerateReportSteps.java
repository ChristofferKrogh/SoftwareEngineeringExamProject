package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Report;
import dtu.planning.app.TimeRegistration;

public class GenerateReportSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActorHolder actorHolder;
	private Report report;

	// Private variables, will give problems when otheres need to use them. Create holder then?
	private TimeRegistration timeRegistration;

	public GenerateReportSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
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
		// Check that some report is genereated
	    assertFalse(report==null);
	}
	
	@Then("A report over the project is generated with {int} hours reported on activity with name {string}")
	public void aReportOverTheProjectIsGeneratedWithHoursReportedOnActivityWithName(int hours, String activityName) throws ActivityNotFoundException {
		assertEquals(report.getReportedTimeForActivity(activityName),hours);
	}
}

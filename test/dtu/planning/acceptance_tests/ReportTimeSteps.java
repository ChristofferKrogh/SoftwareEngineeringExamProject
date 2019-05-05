package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.TimeRegistration;

public class ReportTimeSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	
	// Private variables, will give problems when others need to use them. Create holder then?
	private TimeRegistration timeRegistration;

	public ReportTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
	}
	
	@When("the employee report {int} hour on {string} on {int}\\/{int}\\/{int}")
	public void iReportHourOnOn(Integer amountOfTime, String activityName, Integer day, Integer month, Integer year) throws OperationNotAllowedException, ActivityNotFoundException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		
		// Parse date
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		
		try {
			// Create new time registration object
			timeRegistration = new TimeRegistration(employeeHolder.getEmployee(), date, amountOfTime);
			
			// Register the time
			planningApp.registerTime(projectHolder.getProject().getProjectNumber(),activityName,timeRegistration);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}
	
	@Then("the time report is saved to activity with name {string}")
	public void theTimeReportIsSavedToActivity(String activityName) throws ActivityNotFoundException {
		// Get activity on project
		Activity activity = projectHolder.getProject().getActivityByName(activityName);
		
		// Check that the time registration is in the list of time registration for the activity by that name. Contains object check
		assertTrue(activity.getTimeRegistrations().contains(timeRegistration));
	}

}

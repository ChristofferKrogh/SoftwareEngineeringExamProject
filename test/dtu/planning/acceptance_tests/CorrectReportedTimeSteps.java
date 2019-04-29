package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.TimeRegistrationNotFoundException;

public class CorrectReportedTimeSteps {

	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
//	private ActorHolder actorHolder;
	private ActivityHolder activityHolder;

	private Employee employee;
	private Project project;
	private Activity activity;

	private TimeRegistration time;
	private TimeRegistration timereg;
	private int timeregOld;

	private String activityName;
	private GregorianCalendar date;

	// Private variables, will give problems when otheres need to use them. Create holder then?
	private TimeRegistration timeRegistration;

	public CorrectReportedTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder) {//ActorHolder actorHolder
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
//		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
	}


	@Given("the employee with initials {string} has reported {int} hours for the activity with name {string} on the date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsHasReportedTimeForTheActivityWithNameOnTheDate(String initials, int hours, String nameActivity, Integer day, Integer month, Integer year) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity;
		timereg = new TimeRegistration(employee, date, hours, TimeRegistration.timeUnits.HOURS);
		timeregOld = timereg.getAmountOfTime();
		TimeRegistration timer = null;
		try {
			project = planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
			activity = projectHolder.getProject().getActivityByName(activityName);
			activity.registerTime(timereg);
			timer = activity.getTimeRegistrationForEmployeeOnDate(employee, date);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

		System.out.println("Timer: "+timer);
		System.out.println("Activity: "+activity);
		assertTrue(activity.getTimeRegistrations().contains(timer));

	}

	@When("I update time used to {int} hours")
	public void iUpdatedeTimeUsedByAddingHours(Integer amountOfTime) throws Exception{
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
//			int amountOfTime = activity.getTimeRegistrationForEmployeeOnDate(employee, date).getAmountOfTime() + numHours;
			activity.getTimeRegistrationForEmployeeOnDate(employee, date).correctTime(amountOfTime);
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		// Register the time
		try {
			planningApp.registerTime(projectHolder.getProject().getProjectNumber(),activityName,activity.getTimeRegistrationForEmployeeOnDate(employee, date));
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the updated time report is saved to activity with name {string}")
	public void theUpdatedTimeReportIsSavedToActivityWithName(String activityName){

		// Check that the time registration is in the list of time registration for the activity by that name. Contains object check
		try {
		assertTrue(timeregOld != activity.getTimeRegistrationForEmployeeOnDate(employee, date).getAmountOfTime());
		assertTrue(activity.getTimeRegistrations().contains(activity.getTimeRegistrationForEmployeeOnDate(employee, date)));
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("the employee with initials {string} does not have reported time for the activity with name {string} on the date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsDoesNotHaveReportedTimeForTheActivityWithNameOnTheDate(String initials, String nameActivity, Integer day, Integer month, Integer year){
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity;
		try {
			activity = projectHolder.getProject().getActivityByName(activityName);
			activity.getTimeRegistrationForEmployeeOnDate(employee, date);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

	}



}

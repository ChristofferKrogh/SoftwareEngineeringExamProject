package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.TimeRegistrationNotFoundException;

public class CorrectReportedTimeSteps {

	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActivityHolder activityHolder;

	private Employee employee;

	private int timeregOld;

	private String activityName;
	private GregorianCalendar date;

	// Private variables, will give problems when others need to use them. Create holder then?
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
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity;
		try {
			TimeRegistration timereg = new TimeRegistration(employee, date, hours);
			timeregOld = timereg.getAmountOfTime();
			activityHolder.setActivity(projectHolder.getProject().getActivityByName(activityName));
			Activity activity = activityHolder.getActivity();
			activity.registerTime(timereg);
			TimeRegistration timer = projectHolder.getProject().getActivityByName(nameActivity).getTimeRegistrationForEmployeeOnDate(employee, date);
			assertTrue(activityHolder.getActivity().getTimeRegistrations().contains(timer));
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("I update time used to {int} hours")
	public void iUpdatedeTimeUsedToHours(Integer amountOfTime) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			projectHolder.getProject().getActivityByName(activityName).getTimeRegistrationForEmployeeOnDate(employee, date).correctTime(amountOfTime);
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		// Register the time
		try {
			planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
			planningApp.registerTime(projectHolder.getProject().getProjectNumber(),activityName,projectHolder.getProject().getActivityByName(activityName).getTimeRegistrationForEmployeeOnDate(employee, date));
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
		assertTrue(timeregOld != activityHolder.getActivity().getTimeRegistrationForEmployeeOnDate(employee, date).getAmountOfTime());
		assertTrue(projectHolder.getProject().getActivityByName(activityName).getTimeRegistrations().contains(activityHolder.getActivity().getTimeRegistrationForEmployeeOnDate(employee, date)));
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("the employee with initials {string} does not have reported time for the activity with name {string} on the date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsDoesNotHaveReportedTimeForTheActivityWithNameOnTheDate(String initials, String nameActivity, Integer day, Integer month, Integer year){
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity;
		try {
			activityHolder.setActivity(projectHolder.getProject().getActivityByName(activityName));
			projectHolder.getProject().getActivityByName(nameActivity).getTimeRegistrationForEmployeeOnDate(employee, date);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

	}



}

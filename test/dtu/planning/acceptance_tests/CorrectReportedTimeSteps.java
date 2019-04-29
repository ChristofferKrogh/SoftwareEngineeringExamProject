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
import dtu.planning.app.TimeRegistrationNotFundException;

public class CorrectReportedTimeSteps {
	
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessage;
//	private ActorHolder actorHolder;
	
	private Employee employee;
	private Activity activity;
	
	private TimeRegistration time;
	private TimeRegistration timereg;
	private int timeregOld;

	private String activityName;
	private GregorianCalendar date;
	
	// Private variables, will give problems when otheres need to use them. Create holder then?
	private TimeRegistration timeRegistration;

	public CorrectReportedTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessage = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
//		this.actorHolder = actorHolder;
	}
	

	@Given("the employee with initials {string} has reported {int} hours for the activity with name {string} on the date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsHasReportedTimeForTheActivityWithNameOnTheDate(String initials, int hours, String nameActivity, Integer day, Integer month, Integer year)  throws Exception{
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity; 
		timereg = new TimeRegistration(employee, date, hours, TimeRegistration.timeUnits.HOURS);
		timeregOld = timereg.getAmountOfTime();
		try {
			activity = projectHolder.getProject().getActivityByName(activityName);
			activity.registerTime(timereg);
		} catch (ActivityNotFoundException e) {
			errorMessage.setErrorMessage(e.getMessage());
//		} catch (OperationNotAllowedException e) {
//			errorMessage.setErrorMessage(e.getMessage());
		}
		
		List<TimeRegistration> timeregistrations = activity.getTimeRegistrations();
		
		for (TimeRegistration t : timeregistrations) {
			if (t.getEmployee()==employee && t.getDate()==date) {
				time = t;
			}
		}
		
		assertTrue(activity.getTimeRegistrations().contains(time));
	}

	@When("I update time used by adding {int} hours")
	public void iUpdatedeTimeUsedByAddingHours(Integer numHours) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		int amountOfTime = time.getAmountOfTime() + numHours;
		
		// Create new time registration object 
		// should not make new, but should correct already existing 
//		timeRegistration = new TimeRegistration(employeeHolder.getEmployee(), date, amountOfTime, TimeRegistration.timeUnits.HOURS);
		
		time.correctTime(amountOfTime);
		
		// Register the time
		try {
			planningApp.registerTime(projectHolder.getProject().getProjectNumber(),activityName,time);
		} catch (ActivityNotFoundException e) {
			errorMessage.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the updated time report is saved to activity with name {string}")
	public void theUpdatedTimeReportIsSavedToActivityWithName(String activityName) throws ActivityNotFoundException{
		
		// Check that the time registration is in the list of time registration for the activity by that name. Contains object check
		
		assertTrue(timeregOld != time.getAmountOfTime());
		assertTrue(activity.getTimeRegistrations().contains(time));
	}

	@Given("the employee with initials {string} does not have reported time for the activity with name {string} on the date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsDoesNotHaveReportedTimeForTheActivityWithNameOnTheDate(String initials, String nameActivity, Integer day, Integer month, Integer year) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
		activityName = nameActivity; 
		
		timereg = new TimeRegistration(employee, date, 0, TimeRegistration.timeUnits.HOURS);
		timeregOld = timereg.getAmountOfTime();
		
		try {
			activity = projectHolder.getProject().getActivityByName(activityName);
			activity.registerTime(timereg);
		} catch (ActivityNotFoundException e) {
			errorMessage.setErrorMessage(e.getMessage());
//		} catch (TimeRegistrationNotFundException e) {
//			errorMessage.setErrorMessage(e.getMessage());
		}
		
		List<TimeRegistration> timeregistrations = activity.getTimeRegistrations();
		
		for (TimeRegistration t : timeregistrations) {
			if (t.getEmployee()==employee && t.getDate()==date) {
				time = t;
			}
		}
//		assertTrue(time.getAmountOfTime()==0);
//		assertTrue(activity.getTimeRegistrations().contains(time));
//	    throw new OperationNotAllowedException("The employee has not registered time for this activity");

	}



}

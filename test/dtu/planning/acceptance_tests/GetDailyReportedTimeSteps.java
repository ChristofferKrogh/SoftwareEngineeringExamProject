package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

public class GetDailyReportedTimeSteps {
	
	// "Global" variable holders so steps can be used across features
		private PlanningAppHolder planningAppHolder;
		private ProjectHolder projectHolder;
		private EmployeeHolder employeeHolder;
		private ErrorMessageHolder errorMessageHolder;
//		private ActorHolder actorHolder;
		private ActivityHolder activityHolder;
		
		private GregorianCalendar date;
		
		private int dailyUsedTime;
		
		private List<TimeRegistration> timeRegistrations = new ArrayList<>();

	
	public GetDailyReportedTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder) {//ActorHolder actorHolder
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
//		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
	}
	
	@Given("time report exists for the date {int}\\/{int}\\/{int}")
	public void timeReportExistsForTheDate(Integer day, Integer month, Integer year) throws TimeRegistrationNotFoundException, OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		date = new GregorianCalendar(year, month, day);
		// assume that a time registration exist for project with id 1 and activity with name "Some activity"
		// the project and activity chosen is only relevant for the purpose of a test 
		TimeRegistration timeReg = new TimeRegistration(employeeHolder.getEmployee(), date, 1, TimeRegistration.timeUnits.HOURS);
		
		// Register the time
		try {
			planningApp.registerTime(1,"Some activity",timeReg);
//			timeRegistrations = planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
//			System.out.println(timeRegistrations);
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		timeRegistrations = planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
		
		System.out.println(timeRegistrations);
		assertTrue(!timeRegistrations.isEmpty());
	}
	
	
	
//	#    And at least 1 hour is reported for the employee on activity "Some activity" for project with id 1
//	@Given("at least {int} hour is reported for the employee on activity {string} for project with id {int}")
//	public void atLeastHourIsReportedForTheEmployeeOnActivityForProjectWithId(Integer amountOfTime, String activityName, Integer id) throws TimeRegistrationNotFoundException {
//		PlanningApp planningApp = planningAppHolder.getPlanningApp();
//		
//		// Create new time registration object
//		TimeRegistration timeReg = new TimeRegistration(employeeHolder.getEmployee(), date, amountOfTime, TimeRegistration.timeUnits.HOURS);
//		
//		// Register the time
//		try {
//			planningApp.registerTime(id,activityName,timeReg);
//			System.out.println(planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date));
//		} catch (ActivityNotFoundException e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
//		} catch (OperationNotAllowedException e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
//		}
//	}

	@When("I ask for my daily used time")
	public void iAskForMyDailyUsedTime() {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			dailyUsedTime = planningApp.getDailyUsedTime(employeeHolder.getEmployee().getInitials(), date);
//			System.out.println(dailyUsedTime);
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

    @Then("I get the time used across all activities across all projects")
    public void iGetTheTimeUsedAcrossAllActivitiesAcrossAllProjects() {
        assertTrue(dailyUsedTime!=0);
    }
    
    @Given("no time report for date {int}\\/{int}\\/{int} exists")
    public void noTimeReportForDateExists(Integer day, Integer month, Integer year) throws OperationNotAllowedException, TimeRegistrationNotFoundException {
    	PlanningApp planningApp = planningAppHolder.getPlanningApp();
		date = new GregorianCalendar(year, month, day);
		List<Project> projects = planningApp.getProjects();
		planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
//		System.out.println(timeRegistrations);
		assertTrue(timeRegistrations.isEmpty());
    }

    @Then("I get zero")
    public void iGetZero() {
        assertTrue(dailyUsedTime == 0);
    }

}

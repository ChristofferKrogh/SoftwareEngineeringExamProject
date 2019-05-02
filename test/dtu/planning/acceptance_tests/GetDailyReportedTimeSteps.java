package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
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
		
		private Employee employee;
		private Project project;
		private Activity activity;
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
//		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
//		try {
//		List<Project> projects = planningApp.getProjects();
//		} catch (OperationNotAllowedException e) {
//			// jaaa... 
//		}
//		for(Project p : projects) {
//			List<Activity> activities = p.getAktivities();
//			for(Activity a : activities) {
//				timeRegistrations.add(a.getTimeRegistrationForEmployeeOnDate(employee, date));
//			}
//		}
		timeRegistrations = planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
		System.out.println(timeRegistrations);
		assertTrue(!timeRegistrations.isEmpty());
	}

	@When("I ask for my daily used time")
	public void iAskForMyDailyUsedTime() {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new cucumber.api.PendingException();
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			dailyUsedTime = planningApp.getDailyUsedTime(employeeHolder.getEmployee(), date);
		} catch (TimeRegistrationNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
//		} catch (OperationNotAllowedException e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

    @Then("I get the time used across all activities across all projects")
    public void iGetTheTimeUsedAcrossAllActivitiesAcrossAllProjects() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
    
    @Given("no time report for date {int}\\/{int}\\/{int} exists")
    public void noTimeReportForDateExists(Integer day, Integer month, Integer year) throws OperationNotAllowedException, TimeRegistrationNotFoundException {
    	PlanningApp planningApp = planningAppHolder.getPlanningApp();
//		employee = employeeHolder.getEmployee();
		date = new GregorianCalendar(year, month, day);
//		try {
		List<Project> projects = planningApp.getProjects();
//		} catch (OperationNotAllowedException e) {
//			// jaaa... 
//		}
//		for(Project p : projects) {
//			List<Activity> activities = p.getAktivities();
//			for(Activity a : activities) {
//				timeRegistrations.add(a.getTimeRegistrationForEmployeeOnDate(employee, date));
//			}
//		}
		planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
//		System.out.println(timeRegistrations);
		assertTrue(timeRegistrations.isEmpty());
    }

    @Then("I get zero")
    public void iGetZero() {
        assertTrue(dailyUsedTime == 0);
    }

}

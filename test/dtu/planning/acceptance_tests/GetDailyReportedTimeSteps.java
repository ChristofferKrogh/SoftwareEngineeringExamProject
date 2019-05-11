package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.NotProjectLeaderException;
//import dtu.planning.app.Employee;
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
		
		private GregorianCalendar date;
		
		private int dailyUsedTime;
		
		private List<TimeRegistration> timeRegistrations = new ArrayList<>();

	
	public GetDailyReportedTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
	}
	
	@Given("time report exists for the date {int}\\/{int}\\/{int}")
	public void timeReportExistsForTheDate(Integer day, Integer month, Integer year) throws TimeRegistrationNotFoundException, OperationNotAllowedException, ActivityNotFoundException, NotProjectLeaderException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		date = new GregorianCalendar(year, month, day);
		// assume that a time registration exist for project with id 1 and activity with name "Some activity"
		// the project and activity chosen is only relevant for the purpose of a test 
		TimeRegistration timeReg = new TimeRegistration(employeeHolder.getEmployee(), date, 2); //2 hours registered 
		Project project = planningApp.createProject(null,false);
		projectHolder.setProject(project);
		planningApp.setProjectLeader(projectHolder.getProject().getProjectNumber(),employeeHolder.getEmployee().getInitials());
		// Register the time 
		planningApp.addActivity(projectHolder.getProject().getProjectNumber(), "Some activity", null, null, 2, employeeHolder.getEmployee().getInitials()); //2 hours are expected
		planningApp.registerTime(projectHolder.getProject().getProjectNumber(),"Some activity",timeReg);
		timeRegistrations = planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
		assertFalse(timeRegistrations.isEmpty());
	}
    
    @Given("no time report for date {int}\\/{int}\\/{int} exists")
    public void noTimeReportForDateExists(Integer day, Integer month, Integer year) throws OperationNotAllowedException, TimeRegistrationNotFoundException {
    	PlanningApp planningApp = planningAppHolder.getPlanningApp();
		date = new GregorianCalendar(year, month, day);
		planningApp.getAllTimeRegistrationsForEmployeeOnDate(employeeHolder.getEmployee(), date);
		assertTrue(timeRegistrations.isEmpty());
    }

	@When("I ask for my daily used time")
	public void iAskForMyDailyUsedTime() throws TimeRegistrationNotFoundException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			dailyUsedTime = planningApp.getDailyUsedTime(employeeHolder.getEmployee().getInitials(), date);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

    @Then("I get the time used across all activities across all projects")
    public void iGetTheTimeUsedAcrossAllActivitiesAcrossAllProjects() {
        assertThat(dailyUsedTime,not(equalTo(0)));
    }

    @Then("I get zero")
    public void iGetZero() {
        assertEquals(dailyUsedTime,0);
    }
}

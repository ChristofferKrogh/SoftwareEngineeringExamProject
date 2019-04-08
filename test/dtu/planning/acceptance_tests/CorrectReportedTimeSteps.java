package dtu.planning.acceptance_tests;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class CorrectReportedTimeSteps {
	
	private PlanningApp planningApp = new PlanningApp();
	private ErrorMessageHolder errorMessage;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder; 
	private Project project;
	private Activity activity; 
	private int projectNumber;
	private GregorianCalendar date;
	private Employee employee; 
	private Employee projectLeader; 
		
	public CorrectReportedTimeSteps(ErrorMessageHolder errorMessage, PlanningApp planningApp, Project project, ProjectHolder projectHolder ,Employee employee) {
		this.planningApp = planningApp; 
		this.errorMessage = errorMessage; 
		this.project = project; 
		this.projectHolder = projectHolder; 
		this.employee = employee; 
	}
		
	@Given("time report exists")
	public void timeReportExists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I send updated time used")
	public void iSendUpdatedTimeUsed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the time is corrected in the time report")
	public void theTimeIsCorrectedInTheTimeReport() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("time report does not exists")
	public void timeReportDoesNotExists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}

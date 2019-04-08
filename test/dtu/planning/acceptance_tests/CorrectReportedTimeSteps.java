package dtu.planning.acceptance_tests;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;

public class CorrectReportedTimeSteps {
	
	// "Global" variable holders so steps can be used across features
		private PlanningAppHolder planningAppHolder;
		private ProjectHolder projectHolder;
		private EmployeeHolder employeeHolder;
		private ErrorMessageHolder errorMessageHolder;
		private ActorHolder actorHolder;
		
		// Private variables, will give problems when otheres need to use them. Create holder then?
		private TimeRegistration timeRegistration;

		public CorrectReportedTimeSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder) {
			this.planningAppHolder = planningAppHolder;
			this.errorMessageHolder = errorMessageHolder;
			this.projectHolder = projectHolder;
			this.employeeHolder = employeeHolder;
			this.actorHolder = actorHolder;
		}
		

	@When("I updatede time used")
	public void iSendUpdatedTimeUsed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the time is corrected in the time report")
	public void theTimeIsCorrectedInTheTimeReport() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}

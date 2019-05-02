package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Report;

public class ToStringSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActorHolder actorHolder;
	private ActivityHolder activityHolder;
	private Report report;
	
	private String savedString;

	public ToStringSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder, ActivityHolder activityHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
	}
	
	@Given("the employee has the name {string}")
	public void theEmployeeHasTheName(String name) {
		employeeHolder.getEmployee().setName(name);
	}
	
	@When("I get the string representation of the employee")
	public void iGetTheStringRepresentationOfTheEmployee() {
		savedString = employeeHolder.getEmployee().toString();
	}
	
	@Then("I get the string {string}")
	public void iGetTheString(String wantedString) {
		assertEquals(savedString,wantedString);
	}
}

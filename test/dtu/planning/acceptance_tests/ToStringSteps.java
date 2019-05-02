package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.Project;
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
	private boolean isMatched;

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
	
	@Given("the activity starts in week {int} of {int}")
	public void theActivityStartsInWeekOf(Integer startWeek, Integer startYear) {
		GregorianCalendar start = new GregorianCalendar();
	    start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
	    activityHolder.getActivity().setStartWeek(start);
	}
	
	@Given("the activity ends in week {int} of {int}")
	public void theActivityEndsInWeekOf(Integer endWeek, Integer endYear) {
		GregorianCalendar end = new GregorianCalendar();
	    end.setWeekDate(endYear, endWeek, GregorianCalendar.SUNDAY);
	    activityHolder.getActivity().setEndWeek(end);
	}
	
	@Given("employee with initials {string} is assigned to the activity")
	public void employeeWithInitialsIsAssignedToTheActivity(String initials) throws NotProjectLeaderException, ActivityNotFoundException {
		Project project = projectHolder.getProject();
		
		Employee employee = new Employee(null,initials);
		employeeHolder.setEmployee(employee);
		
		Employee projectLeader = new Employee(null, null);
		
		// If this step is used by other features than to-string this might prevent a proper setup since is overrides projectleader that might be set by anohter given
		project.setProjectLeader(projectLeader);
		
	    project.assignEmployee(activityHolder.getActivity().getName(), projectLeader, employee);
	}
	
	@Given("the project has the name name {string}")
	public void theProjectHasTheNameName(String projectName) {
	    projectHolder.getProject().setName(projectName);
	}
	
	@When("I get the string representation of the employee")
	public void iGetTheStringRepresentationOfTheEmployee() {
		savedString = employeeHolder.getEmployee().toString();
	}
	
	@When("I get the string representation of the activity")
	public void iGetTheStringRepresentationOfTheActivity() {
		savedString = activityHolder.getActivity().toString();
	}
	
	@When("I get the string representation the start week of the activity")
	public void iGetTheStringRepresentationTheStartWeekOfTheActivity() {
		savedString = activityHolder.getActivity().getStartWeekString();
	}
	
	@When("I get the string representation the end week of the activity")
	public void iGetTheStringRepresentationTheEndWeekOfTheActivity() {
		savedString = activityHolder.getActivity().getEndWeekString();
	}
	
	@When("I get the string representation of the project")
	public void iGetTheStringRepresentationOfTheProject() {
	    savedString = projectHolder.getProject().toString();
	}
	
	@When("I match the employee with {string}")
	public void iMatchTheEmployeeWith(String matchString) {
	    isMatched = employeeHolder.getEmployee().match(matchString);
	}
	
	@When("I match the activity with {string}")
	public void iMatchTheActivityWith(String matchString) {
	    isMatched = activityHolder.getActivity().match(matchString);
	}
	
	@Then("I get the string {string}")
	public void iGetTheString(String wantedString) {
		assertEquals(savedString,wantedString);
	}
	
	@Then("I get a match")
	public void iGetAMatch() {
	    assertTrue(isMatched);
	}
	
	@Then("I do not get a match")
	public void iDoNotGetAMatch() {
	    assertFalse(isMatched);
	}
}

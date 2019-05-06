package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.Project;

public class ToStringSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ActivityHolder activityHolder;
	private TimeRegistrationHolder timeRegistrationHolder;
	
	private String savedString;
	private boolean isMatched;

	public ToStringSteps(PlanningAppHolder planningAppHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, TimeRegistrationHolder timeRegistrationHolder) {
		this.planningAppHolder = planningAppHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.activityHolder = activityHolder;
		this.timeRegistrationHolder = timeRegistrationHolder;
	}
	
	@Given("the employee has the name {string}")
	public void theEmployeeHasTheName(String name) {
		employeeHolder.getEmployee().setName(name);
	}
	
	@Given("the activity starts in week {int} of {int}")
	public void theActivityStartsInWeekOf(Integer startWeek, Integer startYear) throws OperationNotAllowedException, ActivityNotFoundException {
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
	    Project project = projectHolder.getProject();
	    Activity activity = activityHolder.getActivity();
	    planningAppHolder.getPlanningApp().editStartDateOfActivity(startDate, project.getProjectNumber(), activity.getName());
	}
	
	@Given("the activity ends in week {int} of {int}")
	public void theActivityEndsInWeekOf(Integer endWeek, Integer endYear) throws OperationNotAllowedException, ActivityNotFoundException {
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setWeekDate(endYear, endWeek, GregorianCalendar.SUNDAY);
	    Project project = projectHolder.getProject();
	    Activity activity = activityHolder.getActivity();
	    planningAppHolder.getPlanningApp().editEndDateOfActivity(endDate, project.getProjectNumber(), activity.getName());
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
	
	@Given("the project has the name {string}")
	public void theProjectHasTheName(String projectName) {
	    projectHolder.getProject().setName(projectName);
	}
	
	@Given("the employee is project leader")
	public void theEmployeeIsProjectLeader() {
	    projectHolder.getProject().setProjectLeader(employeeHolder.getEmployee());
	}
	
	@Given("the project start date is {int}\\/{int}\\/{int}")
	public void theProjectStartDateIs(Integer day, Integer month, Integer year) throws OperationNotAllowedException {
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day);
		Project project = projectHolder.getProject();
		project.setStartDate(date);
	}
	
	@Given("the project end date is {int}\\/{int}\\/{int}")
	public void theProjectEndDateIs(Integer day, Integer month, Integer year) throws OperationNotAllowedException {
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day);
		Project project = projectHolder.getProject();
		project.setEndDate(date);
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
	
	@When("I get the string representation the start date of the project")
	public void iGetTheStringRepresentationTheStartDateOfTheProject() {
	    savedString = projectHolder.getProject().getStartDateString();
	}
	
	@When("I get the string representation the end date of the project")
	public void iGetTheStringRepresentationTheEndDateOfTheProject() {
	    savedString = projectHolder.getProject().getEndDateString();
	}
	
	@When("I get the string representation of the project")
	public void iGetTheStringRepresentationOfTheProject() {
	    savedString = projectHolder.getProject().toString();
	}
	
	@When("I get the string representation of the time registration")
	public void iGetTheStringRepresentationOfTheTimeRegistration() {
	    savedString = timeRegistrationHolder.getTimeRegistration().toString();
	}
	
	@When("I match the employee with {string}")
	public void iMatchTheEmployeeWith(String matchString) {
	    isMatched = employeeHolder.getEmployee().match(matchString);
	}
	
	@When("I match the activity with {string}")
	public void iMatchTheActivityWith(String matchString) {
	    isMatched = activityHolder.getActivity().match(matchString);
	}
	
	@When("I match the project with {string}")
	public void iMatchTheProjectWith(String matchString) {
	    isMatched = projectHolder.getProject().match(matchString);
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

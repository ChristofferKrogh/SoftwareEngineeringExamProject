package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;

public class CreateActivitySteps {
		
	private PlanningAppHolder planningAppHolder; 
	private ErrorMessageHolder errorMessageHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ActivityHolder activityHolder;
	private ActorHolder actorHolder;
		
	public CreateActivitySteps(ErrorMessageHolder errorMessageHolder, PlanningAppHolder planningAppHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, ActorHolder actorHolder) {
		this.planningAppHolder = planningAppHolder; 
		this.errorMessageHolder = errorMessageHolder; 
		this.projectHolder = projectHolder; 
		this.employeeHolder = employeeHolder; 
		this.activityHolder = activityHolder; 
		this.actorHolder = actorHolder;
	}


	@Given("project leader has initials {string}")
	public void projectLeaderHasInitials(String initials){
		Employee employee = new Employee("Bob Small",initials);
		employeeHolder.setEmployee(employee);
		projectHolder.getProject().setProjectLeader(employee);	
	}

	@When("the project leader {string} creates an activity {string}")
	public void theProjectLeaderCreatesAnActivity(String initials, String name) throws NotProjectLeaderException, OperationNotAllowedException {
		assertTrue(projectHolder.getProject().getProjectLeader().getInitials().equals(initials));
		Activity activity = new Activity(name, null, null, (float) 0.0);
		projectHolder.getProject().addActivity(activity,initials);
	}

	@Then("the activity {string} is created for the project")
	public void theActivityIsCreatedForTheProject(String name) throws ActivityNotFoundException {
		assertThat(projectHolder.getProject().getActivityByName(name).getName(),is(equalTo(name)));    
	}

	@When("an employee {string} creates an activity {string}")
	public void anEmployeeCreatesAnActivity(String initials, String name) throws OperationNotAllowedException {
	
		Employee employee = new Employee(null,initials);
		try {
			Activity activity = new Activity(name, null, null, 0);
			projectHolder.getProject().addActivity(activity,employee.getInitials());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	

	@When("the project leader edits the start week of the activity to {int}\\/{int}")
	public void theProjectLeaderEditsTheStartWeekOfTheActivityTo(Integer numWeekYear, Integer year){
		GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		try {
			planningAppHolder.getPlanningApp().editStartDateOfActivity(startWeek, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
		} catch (ActivityNotFoundException | OperationNotAllowedException | NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the start week of the project is {int}\\/{int}")
	public void theStartWeekOfTheProjectIs(Integer numWeekYear, Integer year) {
		GregorianCalendar compareWeek = new GregorianCalendar();
        compareWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		assertEquals(compareWeek.get(Calendar.WEEK_OF_YEAR),activityHolder.getActivity().getStartWeek().get(Calendar.WEEK_OF_YEAR)); 
		assertEquals(compareWeek.get(Calendar.YEAR),activityHolder.getActivity().getStartWeek().get(Calendar.YEAR)); 
	}

	@When("the project leader edits the end week of the project to {int}\\/{int}")
	public void theProjectLeaderEditsTheEndWeekOfTheProjectTo(Integer numWeekYear, Integer year) throws ActivityNotFoundException {
		GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		try {
			planningAppHolder.getPlanningApp().editEndDateOfActivity(endWeek, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}
	
	@When("the project leader edits the expected amount of hours to {int}")
	public void theProjectLeaderEditsTheExpectedAmountOfHoursTo(Integer hours) throws ActivityNotFoundException, OperationNotAllowedException, NotProjectLeaderException{
	    float expectedAmountOfHours = new Float(hours); 
	    planningAppHolder.getPlanningApp().editExpectedAmountOfHoursForActivity(expectedAmountOfHours, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
	}

	@Then("the expected amount of hours is {int}")
	public void theExpectedAmountOfHoursIs(Integer hours) {
		float expectedAmountOfHours = new Float(hours); 
		assertEquals(expectedAmountOfHours,activityHolder.getActivity().getExpectedAmountOfHours(),0); 
	}
	
	@Given("the assigned employee is {string}")
	public void theAssignedEmployeeIs(String initials) throws OperationNotAllowedException, NotProjectLeaderException, ActivityNotFoundException {
		Employee employee = new Employee(null, initials); 
		employeeHolder.setEmployee(employee);
		
		// Add this employee to the company
		planningAppHolder.getPlanningApp().addEmployee(employee);
		
		// Assign employee to activity 
		planningAppHolder.getPlanningApp().assignEmployee(projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials(), employee.getInitials());
	}
	
	@When("an actor changes the expected amount of hours to {int}")
	public void anActorChangesTheExpectedAmountOfHoursTo(Integer hours) throws ActivityNotFoundException {
		float expectedAmountOfHours = new Float(hours); 

	    try {
			planningAppHolder.getPlanningApp().editExpectedAmountOfHoursForActivity(expectedAmountOfHours, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("the project has start week {int}\\/{int}")
	public void theProjectHasStartWeek(Integer numWeekYear, Integer year) throws ActivityNotFoundException, OperationNotAllowedException, NotProjectLeaderException {
		GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		planningAppHolder.getPlanningApp().editStartDateOfActivity(startWeek, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
	}

	@Given("the project has end week {int}\\/{int}")
	public void theProjectHasEndWeek(Integer numWeekYear, Integer year) throws ActivityNotFoundException, OperationNotAllowedException, NotProjectLeaderException {
		GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		planningAppHolder.getPlanningApp().editEndDateOfActivity(endWeek, projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials());
		
	}
	
	@When("the actor deletes {string} from the activity")
	public void theActorDeletesFromTheActivity(String employeeInitials) {
	    try {
			planningAppHolder.getPlanningApp().removeEmployeeFromActivity(projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), employeeInitials, actorHolder.getActor().getInitials());
		} catch (OperationNotAllowedException | NotProjectLeaderException | ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}

	@Then("the employee is not part of the activity")
	public void theEmployeeIsNotPartOfTheActivity() {
	    assertFalse(activityHolder.getActivity().getAssignedEmployees().stream().map(e -> e.getInitials()).anyMatch(i -> i.equals(employeeHolder.getEmployee().getInitials()))); 
	}
	
}

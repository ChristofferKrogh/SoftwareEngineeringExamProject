package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
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
import dtu.planning.app.PlanningApp;

public class CreateActivitySteps {
		
	private PlanningApp planningApp;
	private ErrorMessageHolder errorMessageHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ActivityHolder activityHolder;
	private ActorHolder actorHolder;
		
	public CreateActivitySteps(ErrorMessageHolder errorMessageHolder, PlanningApp planningApp, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, ActorHolder actorHolder) {
		this.planningApp = planningApp; 
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
		Activity activity = new Activity(name, null, null, 0.0);
		projectHolder.getProject().addActivity(activity,initials);
	}

	@Then("the activity {string} is created for the project")
	public void theActivityIsCreatedForTheProject(String name) throws ActivityNotFoundException {
		assertThat(projectHolder.getProject().getActivityByName(name).getName(),is(equalTo(name)));    
	}

	@When("an employee {string} creates an activity {string}")
	public void anEmployeeCreatesAnActivity(String initials, String name) {
	
		Employee employee = new Employee(null,initials);
		try {
			Activity activity = new Activity(name, null, null, 0);
			projectHolder.getProject().addActivity(activity,employee.getInitials());
			assertTrue(false);
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	

	@When("the project leader edits the start week of the activity to {int}\\/{int}")
	public void theProjectLeaderEditsTheStartWeekOfTheActivityTo(Integer numWeekYear, Integer year) throws OperationNotAllowedException {
		GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		activityHolder.getActivity().setStartWeek(startWeek); 
	}

	@Then("the start week of the project is {int}\\/{int}")
	public void theStartWeekOfTheProjectIs(Integer numWeekYear, Integer year) {
		GregorianCalendar compareWeek = new GregorianCalendar();
        compareWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		assertEquals(compareWeek.get(Calendar.WEEK_OF_YEAR),activityHolder.getActivity().getStartWeek().get(Calendar.WEEK_OF_YEAR)); 
		assertEquals(compareWeek.get(Calendar.YEAR),activityHolder.getActivity().getStartWeek().get(Calendar.YEAR)); 
	}

	@When("the project leader edits the end week of the project to {int}\\/{int}")
	public void theProjectLeaderEditsTheEndWeekOfTheProjectTo(Integer numWeekYear, Integer year) throws OperationNotAllowedException {
		GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		activityHolder.getActivity().setEndWeek(endWeek); 
	}

	@Then("the end week of the project is {int}\\/{int}")
	public void theEndWeekOfTheProjectIs(Integer numWeekYear, Integer year) {
		GregorianCalendar compareWeek = new GregorianCalendar();
        compareWeek.setWeekDate(year, numWeekYear, GregorianCalendar.SUNDAY);
		
		assertEquals(compareWeek,activityHolder.getActivity().getEndWeek()); 
	}
	
	@When("the project leader edits the expected amount of hours to {int}")
	public void theProjectLeaderEditsTheExpectedAmountOfHoursTo(Integer hours) {
	    double expectedAmountOfHours = new Double(hours); 
		activityHolder.getActivity().setExpectedAmountOfHours(expectedAmountOfHours);
	}

	@Then("the expected amount of hours is {int}")
	public void theExpectedAmountOfHoursIs(Integer hours) {
		double expectedAmountOfHours = new Double(hours); 
		assertTrue(expectedAmountOfHours == activityHolder.getActivity().getExpectedAmountOfHours()); 
	}
	
	@Given("the assigned employee is {string}")
	public void theAssignedEmployeeIs(String initials) {
		Employee employee = new Employee(null, initials); 
		employeeHolder.setEmployee(employee);
		try {
			planningApp.assignEmployee(projectHolder.getProject().getProjectNumber(), activityHolder.getActivity().getName(), actorHolder.getActor().getInitials(), employee.getInitials());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the project leader changes the assigned employee to {string}")
	public void theProjectLeaderChangesTheAssignedEmployeeTo(String initials ) throws OperationNotAllowedException {
	    activityHolder.getActivity().getAssignedEmployees().remove(employeeHolder.getEmployee()); 	    
	    Employee newEmployee = new Employee(null, initials); 
	    activityHolder.getActivity().assignEmployee(newEmployee);
	    
	}

	@Then("the employee for the activity is {string}")
	public void theEmployeeForTheActivityIs(String initials) {
	    assertTrue(activityHolder.getActivity().getAssignedEmployees().stream().map(e -> e.getInitials()).anyMatch(i -> i.equals(initials))); 
	}
	
}

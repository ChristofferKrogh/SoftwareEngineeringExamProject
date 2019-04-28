package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.TimeRegistration;
import dtu.planning.app.OperationNotAllowedException;

public class RegularActivitySteps {
	private PlanningAppHolder planningAppHolder;
	private ErrorMessageHolder errorMessageHolder;
	private EmployeeHolder employeeHolder;
	private Activity activity;
	
	public RegularActivitySteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, EmployeeHolder employeeHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.employeeHolder = employeeHolder;
	}

	@Given("I have the regular activity with name {string} , start: week {int} of year {int} and end: week {int} of year {int}")
	public void iHaveTheRegularActivityWithNameStartWeekOfYearAndEndWeekOfYear(String name, Integer startWeek, Integer startYear, Integer endWeek, Integer endYear) {
		// GregorianCalendar has sunday as the first day of the week and saturday as the last day
		GregorianCalendar start = new GregorianCalendar();
	    start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
	    
	    GregorianCalendar end = new GregorianCalendar();
	    end.setWeekDate(endYear, endWeek, GregorianCalendar.SATURDAY);
		
		activity = new Activity(name, start, end);
	}
	
	@Given("the regular activity is in the system")
	public void theRegularActivityIsInTheSystem() {
		try {
			planningAppHolder.getPlanningApp().addRegularActivity(activity, employeeHolder.getEmployee().getInitials());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("the regular activity is not in the system")
	public void theRegularActivityIsNotInTheSystem() {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		assertThat(planningApp.getRegularActivities(), not(hasItem(activity)));
	}

	@When("I create the regular activity and assign the employee to it")
	public void iCreateTheRegularActivityAndAssignTheEmployeeToIt() {
		try {
		    planningAppHolder.getPlanningApp().addRegularActivity(activity, employeeHolder.getEmployee().getInitials());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I change the start week of the regular activity to week {int} of year {int}")
	public void iChangeTheStartWeekOfTheRegularActivityToWeekOfYear(Integer startWeek, Integer startYear) {
		GregorianCalendar start = new GregorianCalendar();
	    start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
	    
	    try {
			planningAppHolder.getPlanningApp().editStartWeekOfRegular(start, activity.getName());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("I change the end week of the regular activity to week {int} of year {int}")
	public void iChangeTheEndWeekOfTheRegularActivityToWeekOfYear(Integer endWeek, Integer endYear) {
		GregorianCalendar end = new GregorianCalendar();
	    end.setWeekDate(endYear, endWeek, GregorianCalendar.SATURDAY);
	    
	    try {
			planningAppHolder.getPlanningApp().editEndWeekOfRegular(end, activity.getName());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the regular activity is created and the employee is assigned to it")
	public void theRegularActivityIsCreatedAndTheEmployeeIsAssignedToIt() {
	    PlanningApp planningApp;
	    planningApp = planningAppHolder.getPlanningApp();
		assertThat(planningApp.getRegularActivities(), hasItem(activity));
		assertThat(activity.getAssignedEmployees(), hasItem(employeeHolder.getEmployee()));
	}
	
	@Then("the start week of the regular activity is week {int} of year {int}")
	public void thenTheStartWeekOfTheRegularActivityIsWeekOfYear(Integer startWeek, Integer startYear) throws OperationNotAllowedException {
	    PlanningApp planningApp = planningAppHolder.getPlanningApp();
	    GregorianCalendar start = new GregorianCalendar();
	    start.setWeekDate(startYear, startWeek, GregorianCalendar.SUNDAY);
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getStartWeek().get(GregorianCalendar.DATE),start.get(GregorianCalendar.DATE));
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getStartWeek().get(GregorianCalendar.MONTH),start.get(GregorianCalendar.MONTH));
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getStartWeek().get(GregorianCalendar.YEAR),start.get(GregorianCalendar.YEAR));
	}
	
	@Then("the end week of the regular activity is week {int} of year {int}")
	public void thenTheEndWeekOfTheRegularActivityIsWeekOfYear(Integer endWeek, Integer endYear) throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
	    GregorianCalendar end = new GregorianCalendar();
	    end.setWeekDate(endYear, endWeek, GregorianCalendar.SATURDAY);
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getEndWeek().get(GregorianCalendar.DATE),end.get(GregorianCalendar.DATE));
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getEndWeek().get(GregorianCalendar.MONTH),end.get(GregorianCalendar.MONTH));
	    assertEquals(planningApp.searchForRegActivity(activity.getName()).getEndWeek().get(GregorianCalendar.YEAR),end.get(GregorianCalendar.YEAR));
}
}

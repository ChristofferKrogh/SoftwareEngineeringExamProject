package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
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
	
	@Given("I have the regular activity with name {string} start week {int} end week {int}")
	public void iHaveTheRegularActivityWithNameStartWeekEndWeekAndExpectedAmountOfHours(String name, Integer startWeek, Integer endWeek) {
		activity = new Activity(name, startWeek, endWeek);
	}

	@When("I create the regular activity and assign the employee to it")
	public void iCreateTheRegularActivityAndAssignTheEmployeeToIt() {
	    planningAppHolder.getPlanningApp().addRegularActivity(activity);
	    activity.assignEmployee(employeeHolder.getEmployee());
	}

	@Then("the regular activity is created and the employee is assigned to it")
	public void theRegularActivityIsCreatedAndTheEmployeeIsAssignedToIt() {
	    PlanningApp planningApp;
	    planningApp = planningAppHolder.getPlanningApp();
		assertThat(planningApp.getRegularActivities(), hasItem(activity));
		assertThat(activity.getAssignedEmployees(), hasItem(employeeHolder.getEmployee()));
	}

}

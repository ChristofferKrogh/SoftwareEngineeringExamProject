package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;

public class CreateEmployeeSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ActivityHolder activityHolder;
	private TimeRegistrationHolder timeRegistrationHolder;
	private ErrorMessageHolder errorMessageHolder;

	public CreateEmployeeSteps(PlanningAppHolder planningAppHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, TimeRegistrationHolder timeRegistrationHolder, ErrorMessageHolder errorMessageHolder) {
		this.planningAppHolder = planningAppHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.activityHolder = activityHolder;
		this.timeRegistrationHolder = timeRegistrationHolder;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("I check for the employee is created")
	public void iCheckForTheEmployeeIsCreated() {
		assertNotNull(employeeHolder.getEmployee());
	}
	
	@When("I add another employee with the initials {string}")
	public void iAddAnotherEmployeeWithTheInitials(String employeeInitials) {
	    try {
			planningAppHolder.getPlanningApp().addEmployee(new Employee(null,employeeInitials));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("I get the name {string} and the initials {string}")
	public void iGetTheNameAndTheInitials(String employeeName, String employeeInitials) {
	    assertEquals(employeeHolder.getEmployee().getName(),employeeName);
	    assertEquals(employeeHolder.getEmployee().getInitials(),employeeInitials);
	}
}

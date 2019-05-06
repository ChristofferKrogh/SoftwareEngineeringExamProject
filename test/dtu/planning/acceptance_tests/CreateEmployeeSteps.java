package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;

public class CreateEmployeeSteps {
	// "Global" variable holders so steps can be used across features
	private PlanningAppHolder planningAppHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;

	public CreateEmployeeSteps(PlanningAppHolder planningAppHolder, EmployeeHolder employeeHolder, ErrorMessageHolder errorMessageHolder) {
		this.planningAppHolder = planningAppHolder;
		this.employeeHolder = employeeHolder;
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
	
	@Then("the initials {string} is in the initials list")
	public void theInitialsIsInTheInitialsList(String employeeInitials) {
		List<String> initialsList = planningAppHolder.getPlanningApp().getEmployeeInitials();
		assertTrue(initialsList.contains(employeeInitials));
	}
	
	@Then("the employeelist conatins the employee")
	public void theEmployeelistConatinsTheEmployee() {
	    List<Employee> employeeList = planningAppHolder.getPlanningApp().getEmployees();
	    assertTrue(employeeList.contains(employeeHolder.getEmployee()));
	}
}

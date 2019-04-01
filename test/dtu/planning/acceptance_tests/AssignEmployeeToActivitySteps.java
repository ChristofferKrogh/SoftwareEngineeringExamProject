package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;

import dtu.planning.app.NotProjectLeaderException;

public class AssignEmployeeToActivitySteps {
	
	private Project project;
	private String errorMessageHolder;
	private Employee actor;
	private Employee employee;

	@Given("employee with initials {string} exists")
	public void employeeWithInitialsExists(String initials) {
		// Employee name doesn't matter, so it is set to null.
		employee = new Employee(null,initials);
	}

	@Given("the project with id {int} exists")
	public void theProjectWithIDExists(int projectid) {
		// Name does not matter here, so it is set to null. It does not matter if the project is internal or external so it is set to false
		project = new Project(null, false, projectid);
	}

	@Given("the activity with name {string} exists for project")
	public void theActivityWithNameExists(String name) {
		// The values 0, 1, 2, 3 are chosen as an example.
		// Activity does not test that the assigned project id, actually exists or is the id that it is assigned to
		project.addActivity(name, 0, 1, 2, 1);
	}

	@Given("the actor is project leader for the project")
	public void theProjectLeaderIsProjectLeaderForTheProject() {
		actor = new Employee("John Smith", "JS");
		project.setProjectLeader(actor);
	}
	
	@Given("the actor is not project leader for the project")
	public void theActorIsNotProjectLeaderForTheOverlyingProject() {
		actor = new Employee("Jane Doe", "JD");
	}
	
	@When("the actor assign the employee to the activity {string}")
	public void theProjectLeaderAssignTheEmployeeToTheActivity(String activityName) throws Exception {
		try {
			project.assignEmployee(activityName, actor,employee);
		} catch (NotProjectLeaderException e) {
			errorMessageHolder = e.getMessage();
		}
		
	}
	
	@Then("the employee {string} is assigned to the activity {string}")
	public void theEmployeeIsAssignedToTheActivity(String employeeInitials, String activityName) {
		assertThat(employee.getInitials(),is(equalTo(employeeInitials)));
		assertTrue(project.getEmployeesAssignedToActivity(activityName).contains(employee));
	}
	
	@Then("I get the error message {string}")
	public void iGetTheErrorMessage(String error) {
		// Credits: Libary app example error message holder
		assertEquals(error, errorMessageHolder);
	}
}

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
import dtu.planning.app.PlanningApp;

public class AssignEmployeeToActivitySteps {
	
	private Project project;
	private PlanningApp planningApp;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private Employee actor;
	private Employee employee;
	
	public AssignEmployeeToActivitySteps(PlanningApp planningApp, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
		this.planningApp = planningApp;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
	}

	@Given("employee with initials {string} exists")
	public void employeeWithInitialsExists(String initials) {
		// Employee name doesn't matter, so it is set to null.
		employee = new Employee(null,initials);
		employeeHolder.setEmployee(employee);
	}

	@Given("the project with id {int} exists")
	public void theProjectWithIDExists(int projectid) {
		// Name does not matter here, so it is set to null. It does not matter if the project is internal or external so it is set to false
		project = new Project(null, false, projectid);
		projectHolder.setProject(project);
	}

	@Given("the activity with name {string} exists for project")
	public void theActivityWithNameExists(String name) {
		// The values 0, 1, 2, 3 are chosen as an example.
		// Activity does not test that the assigned project id, actually exists or is the id that it is assigned to
		project.addActivity(name, 0, 1, 2, 1);
		projectHolder.setProject(project);
	}

	@Given("the actor is project leader for the project")
	public void theProjectLeaderIsProjectLeaderForTheProject() {
		actor = new Employee("John Smith", "JS");
		project.setProjectLeader(actor);
		projectHolder.setProject(project);
	}
	
	@Given("the actor is not project leader for the project")
	public void theActorIsNotProjectLeaderForTheOverlyingProject() {
		actor = new Employee("Jane Doe", "JD");
	}
	
	@When("the actor assign the employee to the activity {string}")
	public void theProjectLeaderAssignTheEmployeeToTheActivity(String activityName) throws Exception {
		try {
			project.assignEmployee(activityName, actor,employee);
			projectHolder.setProject(project);
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}
	
	@Then("the employee {string} is assigned to the activity {string}")
	public void theEmployeeIsAssignedToTheActivity(String employeeInitials, String activityName) {
		project = projectHolder.getProject();
		employee = employeeHolder.getEmployee();
		assertThat(employee.getInitials(),is(equalTo(employeeInitials)));
		assertTrue(project.getEmployeesAssignedToActivity(activityName).contains(employee));
	}
	
	@Then("I get the error message {string}")
	public void iGetTheErrorMessage(String error) {
		// Credits: Libary app example error message holder
		assertEquals(error, errorMessageHolder.getErrorMessage());
	}
}

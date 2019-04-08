package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Employee;
import dtu.planning.app.Project;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;

public class AssignEmployeeToActivitySteps {
	
	private PlanningAppHolder planningAppHolder;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder;
	private ErrorMessageHolder errorMessageHolder;
	private ActorHolder actorHolder;
	
	public AssignEmployeeToActivitySteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
	}

	@Given("employee with initials {string} exists")
	public void employeeWithInitialsExists(String initials) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		//PlanningApp planningApp = new PlanningApp();
		// Employee name doesn't matter, so it is set to null.
		Employee employee = new Employee(null,initials);
		employeeHolder.setEmployee(employee);
		
		// Add this employee to the company
		planningApp.addEmployee(employee);
		planningAppHolder.setPlanningApp(planningApp);
	}

	@Given("the project with id {int} exists")
	public void theProjectWithIDExists(int projectCount) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		// Name does not matter here, so it is set to null. It does not matter if the project is internal or external so it is set to false
		// Please note: Planning app API naming. Here createProject is not creating a new project. It adds the project in question to the planning app.
		Project project = new Project(null, false, projectCount);
		planningApp.createProject(project);
		
		projectHolder.setProject(project);
		planningAppHolder.setPlanningApp(planningApp);
	}

	@Given("the activity with name {string} exists for project")
	public void theActivityWithNameExists(String activityName) throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		// The values 0, 1, 2, 3 are chosen as an example.
		// Activity does not test that the assigned project id, actually exists or is the id that it is assigned to
		planningApp.addActivity(projectHolder.getProject().getProjectNumber(), activityName, 0, 1, 2);
		planningAppHolder.setPlanningApp(planningApp);
	}

	@Given("the actor is project leader for the project")
	public void theProjectLeaderIsProjectLeaderForTheProject() throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Employee actor = new Employee("John Smith", "JS");
		planningApp.addEmployee(actor);
		actorHolder.setActor(actor);
		try {
			planningApp.setProjectLeader(projectHolder.getProject().getProjectNumber(), actorHolder.getActor().getInitials()); // actor.getInitials()
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		planningAppHolder.setPlanningApp(planningApp);
	}
	
	@Given("the actor is not project leader for the project")
	public void theActorIsNotProjectLeaderForTheOverlyingProject() {
		Employee actor = new Employee("Jane Doe", "JD");
		actorHolder.setActor(actor);
	}
	
	@Given("the employee doesn't exist")
	public void theEmployeeDoesnTExist() {
		// Creating a employee with no name and no initials. That should not be possible to match anywhere.
		Employee employee = new Employee(null,null);
		employeeHolder.setEmployee(employee);
	}
	
	@Given("the activity {string} doesn't exist")
	public void theActivityDoesnTExist(String activityName) throws OperationNotAllowedException {
		// Get current program state
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		
		// Current project
		Project project = planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
		
		try {
			// Check activity is not present
			Activity activity = project.getActivityByName(activityName);
			
			// The activity should not have the same name as asked not to.
			assertFalse(activity.getName().equals(activityName));
		} catch (ActivityNotFoundException e) {
			// Everything is ok, exception is to be expected here.
		}
		planningAppHolder.setPlanningApp(planningApp);
	}

	
	@When("the actor assign the employee to the activity {string}")
	public void theProjectLeaderAssignTheEmployeeToTheActivity(String activityName) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			planningApp.assignEmployee(projectHolder.getProject().getProjectNumber(), activityName, actorHolder.getActor(), employeeHolder.getEmployee());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		planningAppHolder.setPlanningApp(planningApp);
	}
	
	@Then("the employee {string} is assigned to the activity {string}")
	public void theEmployeeIsAssignedToTheActivity(String employeeInitials, String activityName) throws OperationNotAllowedException, ActivityNotFoundException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
		assertThat(employeeHolder.getEmployee().getInitials(),is(equalTo(employeeInitials)));
		assertTrue(project.getEmployeesAssignedToActivity(activityName).contains(employeeHolder.getEmployee()));
		planningAppHolder.setPlanningApp(planningApp);
	}
	
	@Then("I get the error message {string}")
	public void iGetTheErrorMessage(String error) {
		// Credits: Library app example error message holder
		assertEquals(errorMessageHolder.getErrorMessage(), error);
	}
}

package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
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
	private ActivityHolder activityHolder;

	public AssignEmployeeToActivitySteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder, ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActorHolder actorHolder, ActivityHolder activityHolder) {
		this.planningAppHolder = planningAppHolder;
		this.errorMessageHolder = errorMessageHolder;
		this.projectHolder = projectHolder;
		this.employeeHolder = employeeHolder;
		this.actorHolder = actorHolder;
		this.activityHolder = activityHolder;
	}

	@Given("employee with initials {string} exists")
	public void employeeWithInitialsExists(String initials) throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();

		// Employee name doesn't matter, so it is set to null.
		Employee employee = new Employee(null,initials);
		employeeHolder.setEmployee(employee);

		// Add this employee to the company
		try {
			planningApp.addEmployee(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

	}

	@Given("the project with id {int} exists")
	public void theProjectWithIDExists(int projectCount) {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		// Name does not matter here, so it is set to null. It does not matter if the project is internal or external so it is set to false
		// Please note: Planning app API naming. Here createProject is not creating a new project. It adds the project in question to the planning app.
		Project project = new Project(null, false, projectCount);
		planningApp.createProject(project);

		projectHolder.setProject(project);
	}

	@Given("the activity with name {string} exists for project")
	public void theActivityWithNameExists(String activityName) throws OperationNotAllowedException, NotProjectLeaderException {
		Project project = projectHolder.getProject(); 
		// The values 0, 1, 2, 3 are chosen as an example.
		try {
			Activity activity = new Activity(activityName, null, null, 2.0, projectHolder.getProject().getProjectNumber()); 
			project.addActivity(activity,projectHolder.getProject().getProjectLeader().getInitials(), projectHolder.getProject().getProjectNumber());
			activityHolder.setActivity(activity);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("the actor is project leader for the project")
	public void theProjectLeaderIsProjectLeaderForTheProject() throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Employee actor = new Employee("The Current Actor", "TCA");
		planningApp.addEmployee(actor);
		actorHolder.setActor(actor);
		try {
			planningApp.setProjectLeader(projectHolder.getProject().getProjectNumber(), actorHolder.getActor().getInitials()); // actor.getInitials()
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
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
	public void theActivityDoesnTExist(String activityName) {
		// Get current program state
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		try {
			// Current project
			Project project = planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
			// Check activity is not present
			Activity activity = project.getActivityByName(activityName);
			activityHolder.setActivity(activity);

			// The activity should not have the same name as asked not to.
			assertFalse(activity.getName().equals(activityName));
		} catch (ActivityNotFoundException e) {
			// Everything is ok, exception is to be expected here.
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
		if (activityHolder.getActivity() == null) {
			// The 2nd and 3rd arguments are set to 0 as they are not important here.
			activityHolder.setActivity(new Activity(activityName,null,null,0,0));
		}
	}


	@When("the actor assign the employee to the activity {string}")
	public void theProjectLeaderAssignTheEmployeeToTheActivity(String activityName) throws Exception {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		System.out.println(actorHolder.getActor()); 
		System.out.println(employeeHolder.getEmployee()); 
		try {
			planningApp.assignEmployee(projectHolder.getProject().getProjectNumber(), activityName, actorHolder.getActor(), employeeHolder.getEmployee());
		} catch (NotProjectLeaderException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} catch (ActivityNotFoundException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee {string} is assigned to the activity {string}")
	public void theEmployeeIsAssignedToTheActivity(String employeeInitials, String activityName) throws OperationNotAllowedException, ActivityNotFoundException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Project project = planningApp.searchForProject(projectHolder.getProject().getProjectNumber());
		assertThat(employeeHolder.getEmployee().getInitials(),is(equalTo(employeeInitials)));
		System.out.println(project.getEmployeesAssignedToActivity(activityName).contains(employeeHolder.getEmployee()));
		//assertTrue(project.getEmployeesAssignedToActivity(activityName).contains(employeeHolder.getEmployee()));
	}

	@Then("I get the error message {string}")
	public void iGetTheErrorMessage(String error) {
		// Credit: Library application example error message holder by Hubert Baumeister, Associate Professor, DTU Compute, 02161 F19 Lectures
		assertEquals(errorMessageHolder.getErrorMessage(), error);
	}
	
	@Given("{string} is project leader for the project") // 
	public void isProjectLeaderForTheProject(String initials) throws OperationNotAllowedException {
		PlanningApp planningApp = planningAppHolder.getPlanningApp();
		Employee actor = new Employee("The Current Project Leader", initials);
		planningApp.addEmployee(actor);
		try {
			planningApp.setProjectLeader(projectHolder.getProject().getProjectNumber(), actor.getInitials()); // actor.getInitials()
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
}

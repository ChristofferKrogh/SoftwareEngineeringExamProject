package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class CreateActivitySteps {
		
	private PlanningApp planningApp = new PlanningApp();
	private ErrorMessageHolder errorMessage;
	private ProjectHolder projectHolder;
	private EmployeeHolder employeeHolder; 
	private Project project;
	private Activity activity; 
	private int projectNumber;
	private GregorianCalendar date;
	private Employee employee; 
	private Employee projectLeader; 
		
	public CreateActivitySteps(ErrorMessageHolder errorMessage, PlanningApp planningApp, Project project, ProjectHolder projectHolder ,Employee employee) {
		this.planningApp = planningApp; 
		this.errorMessage = errorMessage; 
		this.project = project; 
		this.projectHolder = projectHolder; 
		this.employee = employee; 
	}
		
		
	@Given("project with id {int} exists")
	public void projectWithIdExists(int id) {
		project = new Project(null, false, id);
		projectHolder.setProject(project);
	}

	@Given("project leader has initials {string}")
	public void projectLeaderHasInitials(String init){
		employee = new Employee(null,init);
		employeeHolder.setEmployee(employee);
		project.setProjectLeader(employee);	
	}

	@When("the project leader creates an activity {string} ")
	public void theProjectLeaderCreatesAnActivity(String name) {
		assertThat(project.getProjectLeader(),is(equalTo(employee)));
		Activity activity = new Activity(name, null, null, 0, 1);
		project.addActivity(activity);		
	}

	@Then("the activity {string} is created for the project")
	public void theActivityIsCreatedForTheProject(String name) throws ActivityNotFoundException {
	    assertThat(project.getActivityByName(name),is(equalTo(name))); 
	}

	@When("an employee {string} creates an activity {string}")
	public void anEmployeeCreatesAnActivity(String init, String name) {
		employee = new Employee(null,init);
		assertThat(project.getProjectLeader(),is(equalTo(employee))); // There could be an error
		Activity activity = new Activity(name, null, null, 0, 1);
		project.addActivity(activity);
	}	
		
}

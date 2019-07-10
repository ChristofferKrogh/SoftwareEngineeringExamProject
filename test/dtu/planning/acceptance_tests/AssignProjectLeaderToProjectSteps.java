package dtu.planning.acceptance_tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Employee;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;

public class AssignProjectLeaderToProjectSteps {

    private PlanningApp planningApp = new PlanningApp();
    private PlanningAppHolder planningAppHolder;
    private ProjectHolder projectHolder;
    private EmployeeHolder employeeHolder;
    private Project project;
    private Employee employee;
    private ErrorMessageHolder errorMessage;

    public AssignProjectLeaderToProjectSteps(PlanningAppHolder planningAppHolder, PlanningApp planningApp,
            ErrorMessageHolder errorMessage, ProjectHolder projectHolder, EmployeeHolder employeeHolder) {
        this.planningAppHolder = planningAppHolder;
        this.planningApp = planningApp;
        this.errorMessage = errorMessage;
        this.projectHolder = projectHolder;
        this.employeeHolder = employeeHolder;
    }

    @Given("project with id {int} does not exist")
    public void projectWithProjectNumberDoesNotExist(Integer pNumber) {
        assertThat(planningApp.getProjectNumbers(), not(hasItem(pNumber)));
        project = new Project("Test Project", true, pNumber);
        projectHolder.setProject(project);
    }

    @Given("employee with initials {string} does not exist")
    public void employeeWithInitialsDoesNotExist(String initials) {
        assertThat(planningApp.getEmployeeInitials(), not(hasItem(initials)));
        employee = new Employee(null, initials);
        employeeHolder.setEmployee(employee);
    }

    @When("I assign employee with initials {string} as project leader")
    public void iAssignEmployeeWithInitialsAsProjectLeader(String initials) throws Exception {
        PlanningApp planningApp = planningAppHolder.getPlanningApp();
        try {
            project = projectHolder.getProject();
            employee = employeeHolder.getEmployee();
            planningApp.setProjectLeader(project.getProjectNumber(), employee.getInitials()); // employee.getInitials()
            projectHolder.setProject(project);
            employeeHolder.setEmployee(employee);
        } catch (OperationNotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee with initials {string} is assigned as project leader for the project with id {int}")
    public void theEmployeeWithInitialsIsAssignedAsProjectLeaderForTheProjectWithProjectNumber(String initials,
            Integer pNumber) throws Exception {
        project = projectHolder.getProject();
        employee = employeeHolder.getEmployee();
        assertThat(employee.getInitials(), is(equalTo(initials)));
        assertThat(project.getProjectLeader(), is(equalTo(employee)));
    }
}

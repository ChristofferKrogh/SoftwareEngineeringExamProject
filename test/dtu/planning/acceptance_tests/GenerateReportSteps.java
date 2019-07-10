package dtu.planning.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.planning.app.Activity;
import dtu.planning.app.ActivityNotFoundException;
import dtu.planning.app.Employee;
import dtu.planning.app.NotProjectLeaderException;
import dtu.planning.app.OperationNotAllowedException;
import dtu.planning.app.PlanningApp;
import dtu.planning.app.Project;
import dtu.planning.app.Report;

public class GenerateReportSteps {
    // "Global" variable holders so steps can be used across features
    private PlanningAppHolder planningAppHolder;
    private ProjectHolder projectHolder;
    private ErrorMessageHolder errorMessageHolder;
    private ActorHolder actorHolder;
    private ActivityHolder activityHolder;
    private Report report;

    public GenerateReportSteps(PlanningAppHolder planningAppHolder, ErrorMessageHolder errorMessageHolder,
            ProjectHolder projectHolder, ActorHolder actorHolder, ActivityHolder activityHolder) {
        this.planningAppHolder = planningAppHolder;
        this.errorMessageHolder = errorMessageHolder;
        this.projectHolder = projectHolder;
        this.actorHolder = actorHolder;
        this.activityHolder = activityHolder;
    }

    @Given("the activity is estimated to last {int} hours")
    public void theActivityIsEstimatedToLastHours(Integer hours)
            throws ActivityNotFoundException, OperationNotAllowedException {
        Project project = planningAppHolder.getPlanningApp()
                .searchForProject(projectHolder.getProject().getProjectNumber());
        Activity activity = project.getActivityByName(activityHolder.getActivity().getName());
        activity.setExpectedAmountOfHours((float) hours);
    }

    @Given("the project with id {int} exists with project leader {string}")
    public void theProjectWithIdExistsWithProjectLeader(Integer projectCount, String initials) {
        PlanningApp planningApp = planningAppHolder.getPlanningApp();
        // Name does not matter here, so it is set to null. It does not matter if the project is internal or external so
        // it is set to false
        Project project = planningApp.createProject(null, false);

        Employee projectLeader = new Employee(null, initials);
        project.setProjectLeader(projectLeader);
        projectHolder.setProject(project);
    }

    @When("The actor generates a report for the project")
    public void theActorGeneratesAReportForTheProject() {
        PlanningApp planningApp = planningAppHolder.getPlanningApp();

        try {
            report = planningApp.generateReport(projectHolder.getProject().getProjectNumber(), actorHolder.getActor());
        } catch (NotProjectLeaderException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("A report over the project is generated")
    public void aReportOverTheProjectIsGenerated() {
        // Check that some report is generated
        assertNotEquals(report, null);
    }

    @Then("A report over the project is generated with {int} hours estimated on activity with name {string}")
    public void aReportOverTheProjectIsGeneratedWithHoursEstimatedOnActivityWithName(Integer hours, String activityName)
            throws ActivityNotFoundException {
        float hours1 = new Float(hours);
        // Third argument in assert equals with doubble is delta
        assertEquals(report.getEstimatedTimeForActivity(activityName), hours1, 0);
    }

    @Then("A report over the project is generated with {int} hours reported on activity with name {string}")
    public void aReportOverTheProjectIsGeneratedWithHoursReportedOnActivityWithName(Integer hours, String activityName)
            throws ActivityNotFoundException {
        float hours1 = new Float(hours);
        // Third argument in assert equals with doubble is delta
        assertEquals(report.getReportedTimeForActivity(activityName), hours1, 0);
    }
}

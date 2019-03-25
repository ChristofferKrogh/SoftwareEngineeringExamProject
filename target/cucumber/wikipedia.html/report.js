$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("use_cases/create_project.feature");
formatter.feature({
  "name": "Create project",
  "description": "    Description: the project is created and assigned a project number. The project has to have a name.\n    \t\t\tIt can be created with or without the start and end dates.\n    Actors: Employee",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "An internal project is created with name \"Test project\" successfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "there is an internal project with name \"Test project\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CreateProjectSteps.thereIsAnInternalProjectWithName(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an employee creates the project",
  "keyword": "When "
});
formatter.match({
  "location": "CreateProjectSteps.anEmployeeCreatesTheProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the internal project with name \"Test project\" is created",
  "keyword": "Then "
});
formatter.match({
  "location": "CreateProjectSteps.theInternalProjectWithNameIsCreated(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the project is given a project number",
  "keyword": "And "
});
formatter.match({
  "location": "CreateProjectSteps.theProjectIsGivenAProjectNumber()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "An external project is created with name \"Test project\" successfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "there is an external project with name \"Test project\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CreateProjectSteps.thereIsAnExternalProjectWithName(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "an employee creates the project",
  "keyword": "When "
});
formatter.match({
  "location": "CreateProjectSteps.anEmployeeCreatesTheProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the external project with name \"Test project\" is created",
  "keyword": "Then "
});
formatter.match({
  "location": "CreateProjectSteps.theExternalProjectWithNameIsCreated(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the project is given a project number",
  "keyword": "And "
});
formatter.match({
  "location": "CreateProjectSteps.theProjectIsGivenAProjectNumber()"
});
formatter.result({
  "status": "passed"
});
});
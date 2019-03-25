$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("use_cases/create_project.feature");
formatter.feature({
  "name": "Create project",
  "description": "    Description: the project is created and assigned a project number\n    Actors: Employee",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "A project is created successfully",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "an employee creates a project",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the project is created",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the project is given a project number",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});
Feature: Get all relevant activities for employee
	Description: Used by the GUI for showing everything releated to a employee
	Actors: Employees

Scenario: Get no relevant activity
    Given the project with some id exists
    And the actor is project leader for the project
    And the project has the name "Test project"
    And the activity with name "Some Activity" exists for project
	And employee with initials "JD" exists
    When I get relevant activites for the employee
    Then I do not get any relevant activities
    
Scenario: Get relevant activity
    Given the project with some id exists
    And the actor is project leader for the project
    And the project has the name "Test project"
    And the activity with name "Some Activity" exists for project
    And employee with initials "JD" is assigned to the activity
    When I get relevant activites for the employee
    Then I get the relevant project number and its activity
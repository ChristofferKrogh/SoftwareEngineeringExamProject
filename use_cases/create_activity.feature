Feature: Create activity
    Description: activities for a project are created
    Actors: Project leader
    
Scenario: Create activity successfully 
    Given the project with id 1 exists
 	And project leader has initials "BS"
    When the project leader "BS" creates an activity "Project design"
    Then the activity "Project design" is created for the project

Scenario: Create activity when not project leader
	 Given the project with id 1 exists
	 And project leader has initials "BS"
	 When an employee "AP" creates an activity "Project design"
	 Then I get the error message "You are not the project leader for this project"

Scenario: Create activity with same names 
	Given the project with id 1 exists
	And project leader has initials "BS"
	And the activity with name "Test #1" exists for project 
	When an employee "BS" creates an activity "Test #1"
	Then I get the error message "The activity is already part of the project."
 
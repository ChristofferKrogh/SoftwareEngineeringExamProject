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

#Scenario: Create activity with same names 
#	Given project "Test Project" with id 201795 exists
#	And actor have an activity "Project design"
#	And activity "Project design" is already an activity 
#	When the project leader creates an activity
#	Then get the error message "The activity is already part of the project."
# 
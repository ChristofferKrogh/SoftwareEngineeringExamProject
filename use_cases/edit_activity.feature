Feature: Edit activity
	Description: The start week, end week, hours and assigned employee of an activity is set or changed.
	Actors: Project leader 
	
Scenario: The start week of an activity is changed
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	When the project leader edits the start week of the activity to 1/2019
	Then the start week of the project is 1/2019
	
#Scenario: The end date of an activity is changed
#	Given the project with id 1 exists
#    And the actor is project leader for the project
#    And the activity with name "Some Activity" exists for project
#	When the project leader edits the end week of the project to 2/2019
#	Then the end week of the project is 2/2019
	
Scenario: The expected amount of hours is changed 
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	When the project leader edits the expected amount of hours to 20
	Then the expected amount of hours is 20  

#Scenario: An employee is added to a activity // Maybe made before 
#	Given the project with id 1 exists
#   And the actor is project leader for the project
#   And the activity with name "Some Activity" exists for project
#	And the assigned employee is "JD"
#	When the project leader adds an employee "RA" to the activity
#	Then the employee "RA" is assigned to the activity 

Scenario: The employee for an activity is changed 
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	And the assigned employee is "JD"
	When the project leader changes the assigned employee to "KA"
	Then the employee for the activity is "KA" 

#Scenario: A employee who are not project leader changes an activity
#	Given the project with id 1 exists
#   And the actor is project leader for the project
#   And the activity with name "Some Activity" exists for project
#	When an employee changes the expected amount of hours 
#	Then get the error message "You must be project leader to change a activity"

#Scenario: The start week is set to a week after the end week 
#	Given the project with id 1 exists 
#   And the actor is project leader for the project
#   And the activity with name "Some Activity" exists for project
#	And the project has end week 5/2019
#	When the project leader changes the start week to 6/2019
#	Then get the error message "The start week must be before or the same as the end week" 

#Scenario: The end week is set before the start week 
#	Given the project with id 1 exists 
#   And the actor is project leader for the project
#   And the activity with name "Some Activity" exists for project
#	And the project has start week 20/2019 
#	When the project leader changes the end week to  19/2019
#	Then get the error message "The end week must be after or the same as the start week" 

#Scenario: The amount of expected hours is set to less than 0 
#	Given the project with id 1 exists 
#   And the actor is project leader for the project
#   And the activity with name "Some Activity" exists for project
#	And the expected amount of hours is 20 
#	When the project leader changes the expected amount of hours to -1 
#	Then get the error message "The expected amount of hours must be at least 0"
 
	
	
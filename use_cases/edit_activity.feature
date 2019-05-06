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

Scenario: The employee for an activity is changed 
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	And the assigned employee is "JD"
	And employee with initials "KA" exists
	When the project leader changes the assigned from "JD" employee to "KA"
	Then the employee for the activity is "KA" 

Scenario: Fail The employee for an activity is changed if the employee is not assigned
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	And employee with initials "KA" exists
	When the project leader changes the assigned from "JD" employee to "KA"
	Then I get the error message "The employee is not assigned to the activity"

Scenario: Fail The employee for an activity is changed
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
	And the assigned employee is "JS"
	And employee with initials "KA" exists
	And the actor is not project leader for the project
	# TODO: Rename "the project leader" to "the actor" since its the actor that does this.
	When the project leader changes the assigned from "JS" employee to "KA"
	Then I get the error message "You must be project leader to change an activity"
	
Scenario: A employee who are not project leader changes an activity
	Given the project with id 1 exists
	And "BS" is project leader for the project
	And the activity with name "Some Activity" exists for project
    And the actor is not project leader for the project
    When an actor changes the expected amount of hours to 20
	Then I get the error message "You must be project leader to change a activity"

Scenario: The start week is set to a week after the end week 
	Given the project with id 1 exists 
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
    And the project has start week 1/2019
	And the project has end week 5/2019
	When the project leader edits the start week of the activity to 6/2019
	Then I get the error message "The start week must be before the end week" 

Scenario: Fail "The start week is set to a week after the end week" if not project leader
	Given the project with id 1 exists
	# Setup is done by the project leader
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
    And the project has start week 1/2019
	And the project has end week 5/2019
	And the actor is not project leader for the project
	# TODO: Rename "the project leader" to "the actor" since its the actor that does this.
	When the project leader edits the start week of the activity to 6/2019
	Then I get the error message "You must be project leader to change an activity" 

Scenario: Fail The end week is set before the start week 
	Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
 	And the project has start week 20/2019 
 	And the project has end week 25/2019
	When the project leader edits the end week of the project to 19/2019
	Then I get the error message "The end week must be after the start week" 

Scenario: The end week is set before the start week 
	Given the project with id 1 exists
	# Setup is done by the project leader
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
 	And the project has start week 20/2019 
 	And the project has end week 25/2019
	And the actor is not project leader for the project
	# TODO: Rename "the project leader" to "the actor" since its the actor that does this.
	When the project leader edits the end week of the project to 19/2019
	Then I get the error message "You must be project leader to change an activity" 
	
Scenario: The amount of expected hours is set to less than 0 
	Given the project with id 1 exists 
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
    When an actor changes the expected amount of hours to -1
	Then I get the error message "The expected amount of hours must be at least 0"
 
	
	
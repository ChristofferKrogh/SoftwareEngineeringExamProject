Feature: Assign employee to activity
    Description: the project leader assigns an employee to an activity
    Actor: Project leader
    
Scenario: An employee is assigned to the activity successfully
	Given employee with initials "JS" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the actor is project leader for the project
    When the actor assign the employee to the activity "Some Activity"
    Then the employee "JS" is assigned to the activity "Some Activity"

Scenario: A non-project leader tries to assign an employee to an activity
	Given employee with initials "JS" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the actor is not project leader for the project
    When the actor assign the employee to the activity "Some Activity"
    Then I get the error message "You are not the project leader for this project"

Scenario: An employee that doesn't exist is assigned to the activity
    Given the employee doesn't exist
    And the activity with name "Some Activity" exists for project
    And the actor is project leader for the project
    When the actor assign the employee to the activity "Some Activity"
    Then I get the error message "The employee does not exist"
    
#Scenario: An employee is assigned to an activity that doesn't exist
#    Given the activity doesn't exist
#    When I assign an employee to the activity
#    Then I get the error message "The activity does not exist"
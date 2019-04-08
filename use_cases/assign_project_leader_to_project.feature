Feature: Assign project leader to project
    Description: An employee is assigned as project leader for a project
    Actors: Employee
    
Scenario: A project leader is assigned successfully
    Given employee with initials "ABCD" exists
    And the project with id 0 exists
    When I assign employee with initials "ABCD" as project leader
    Then the employee with initials "ABCD" is assigned as project leader for the project with id 0

Scenario: A project leader is assigned to a project that does not exist
    Given project with id 191234 does not exist
    When I assign employee with initials "ABCD" as project leader
    Then I get the error message "The project does not exist"
    
#Scenario: An employee that does not exist is assigned as project leader
#    Given employee with initials "ABCD" does not exist
#    When I assign employee with initials "ABCD" as project leader
#    Then I get the error message "The employee does not exist"
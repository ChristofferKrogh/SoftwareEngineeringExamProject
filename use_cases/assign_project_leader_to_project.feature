#Feature: Assign project leader to project
#    Description: An employee is assigned as project leader for a project
#    Actors: Employee
#    
#Scenario: A project leader is assigned successfully
#    Given employee exists
#    And project exists
#    And no project leader is assigned to project
#    When I assign employee as project leader
#    Then the employee is assigned as project leader for the project
#
#Scenario: A project leader is assigned to a project that does not exist
#    Given project does not exist
#    When I assign employee as project leader
#    Then I get the error message "Project does not exist"
#    
#Scenario: A project leader is assigned to a project that already has a project leader
#    Given employee exists
#    And project exists
#    And project has a project leader
#    When I assign employee as project leader
#    Then I get the error message "A project leader is already assigned to this project"
#    
#Scenario: An employee that does not exist is assigned as project leader
#    Given employee does not exist
#    When I assign employee as project leader
#    Then I get the error message "The employee does not exist"
Feature: Generate Report
    Description: The project leader generates a report over how much work has been done to the project and how much work still needs to be done.
    Actors: Project leader
    
#Scenario: Project leader generates report successfully
#    Given the project with id 1 exists
#    And the actor is project leader for the project
#    When The actor generate a report
#    Then A report over the project is generated

Scenario: An employee that is not project leader for the project tries to generate a report
    Given the project with id 1 exists
    And the actor is not project leader for the project
    When The actor generates a report for the project
    Then I get the error message "You are not the project leader for this project"

#Scenario: An employee tries to generate a report for a project that does not exist
#    Given the project does not exist
#    When I generate a report
#    Then I get the error message "The project does not exist"
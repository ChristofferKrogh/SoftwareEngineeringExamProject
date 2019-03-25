#Feature: Generate Report
#    Description: The project leader generates a report over how much work has been done to the project and how much work still needs to be done.
#    Actors: Project leader
#    
#Scenario: Project leader generates report successfully
#    Given the project exists
#    And the employee is project leader for the project
#    When I generate a report
#    Then a report over the project is generated
#
#Scenario: An employee that is not project leader for the project tries to generate a report
#    Given the project exists
#    And the employee is not project leader for the report
#    When I generate a report
#    Then I get the error message "You are not project leader for this project"
#
#Scenario: An employee tries to generate a report for a project that does not exist
#    Given the project does not exist
#    When I generate a report
#    Then I get the error message "The project does not exist"
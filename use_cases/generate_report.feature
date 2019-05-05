Feature: Generate Report
    Description: The project leader generates a report over how much work has been done to the project and how much work still needs to be done.
    Actors: Project leader
    
Scenario: Project leader generates report successfully
    Given the project with id 1 exists
    And the actor is project leader for the project
    When The actor generates a report for the project
    Then A report over the project is generated

Scenario: Show estimated time for activity in report
    Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
    And the activity is estimated to last 5 hours
    When The actor generates a report for the project
    Then A report over the project is generated with 5 hours estimated on activity with name "Some Activity" 

Scenario: Show time reported for activity in report
    Given the project with id 1 exists
    And "BS" is project leader for the project
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" has reported 3 hours for the activity with name "Some Activity" on the date 1/1/2019
    And the actor is project leader for the project
    When The actor generates a report for the project
    Then A report over the project is generated with 3 hours reported on activity with name "Some Activity" 

Scenario: Show time reported for activity in report with multiple time registrations
    Given the project with id 1 exists
    And the actor is project leader for the project
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" has reported 3 hours for the activity with name "Some Activity" on the date 1/1/2019
    And the employee with initials "EFGH" has reported 5 hours for the activity with name "Some Activity" on the date 1/1/2019
    When The actor generates a report for the project
    Then A report over the project is generated with 8 hours reported on activity with name "Some Activity" 

Scenario: An employee that is not project leader for the project tries to generate a report
    Given the project with id 1 exists with project leader "BS"
    And the actor is not project leader for the project
    When The actor generates a report for the project
    Then I get the error message "You are not the project leader for this project"

Scenario: An employee tries to generate a report for a project that does not exist
    Given employee with initials "JS" exists
    And project with id 1 does not exist
    When The actor generates a report for the project
    Then I get the error message "The project does not exist"
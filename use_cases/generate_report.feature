Feature: Generate Report
    Description: The project leader generates a report over how much work has been done to the project and how much work still needs to be done.
    Actors: Project leader
    
Scenario: Project leader generates report successfully
    Given the project with id 1 exists
    And the actor is project leader for the project
    When The actor generates a report for the project
    Then A report over the project is generated

#Scenario: Report show time estimated
#    Given the project with id 1 exists
#    And the activity with name "Some Activity" exists for project
#    And the employee with initials "ABCD" has reported 3 hours for the activity with name "Some Activity" on the date 1/1/2019
#    And the actor is project leader for the project
#    When The actor generates a report for the project for week 1 of 2019
#    Then A report over the project is generated with 3 hours reported on activity with name "Some Activity" 

Scenario: Show time reported for activity in report
    Given the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" has reported 3 hours for the activity with name "Some Activity" on the date 1/1/2019
    And the actor is project leader for the project
    When The actor generates a report for the project
    Then A report over the project is generated with 3 hours reported on activity with name "Some Activity" 

#Scenario: Report show time reported with multiple time registrations
#    Given the project with id 1 exists
#    And the activity with name "Some Activity" exists for project
#    And the employee with initials "ABCD" has reported 9 hours for the activity with name "Some Activity" on the date 31/12/2018
#    And the employee with initials "ABCD" has reported 3 hours for the activity with name "Some Activity" on the date 1/1/2019
#    And the employee with initials "EFGH" has reported 5 hours for the activity with name "Some Activity" on the date 2/1/2019
#    And the actor is project leader for the project
#    When The actor generates a report for the project for week 1 of 2019
#    Then A report over the project is generated with 8 hours reported on activity with name "Some Activity" 


# Scenario: Report show only requested week's time estimated (Create projects with estimated time)


# Scenario: Report shows correct time left for project

Scenario: An employee that is not project leader for the project tries to generate a report
    Given the project with id 1 exists
    And the actor is not project leader for the project
    When The actor generates a report for the project
    Then I get the error message "You are not the project leader for this project"

Scenario: An employee tries to generate a report for a project that does not exist
    Given employee with initials "JS" exists
    And project with id 1 does not exist
    When The actor generates a report for the project
    Then I get the error message "The project does not exist"
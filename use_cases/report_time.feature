Feature: Report time
    Description: an employee reports how much time he has or will spend on an activity
    Actors: Employee
    
Scenario: Report used time
    Given employee with initials "JS" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When the employee report 1 hour on "Some Activity" on 1/1/2019
    Then the time report is saved to activity with name "Some Activity"
    
Scenario: Fail report used time for activity that does not exist
    Given employee with initials "JS" exists
    And the project with id 1 exists
    And the activity "Don't look at me" doesn't exist
    When the employee report 1 hour on "Don't look at me" on 1/1/2019
    Then I get the error message "The activity does not exist"
    
Scenario: Fail report used time for project that does not exist
    Given the employee doesn't exist
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When the employee report 1 hour on "Some Activity" on 1/1/2019
    Then I get the error message "The employee does not exist"

Scenario: Fail report used time for employee that does not exist
    Given employee with initials "JS" exists
    And project with id 1 does not exist
    And the activity with name "Some Activity" exists for project
    When the employee report 1 hour on "Some Activity" on 1/1/2019
    Then I get the error message "The project does not exist"
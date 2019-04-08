Feature: Report time
    Description: an employee reports how much time he has or will spend on an activity
    Actors: Employee
    
Scenario: Report used time
    Given employee with initials "JS" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When the employee report 1 hour on "Some Activity" on 1/1/2019
    Then the time report is saved to activity with name "Some Activity"
    
#Scenario: Fail report used time for activity that does not exist
#    Given employee exists
#    And activity does not exist
#    When I reports used time
#    Then I get the error message "The activity does not exist"
#Feature: Report time
#    Description: an employee reports how much time he has or will spend on an activity
#    Actors: Employee
#    
#Scenario: Report used time
#    Given employee exists
#    And activity exists
#    When I reports used time
#    Then time is saved to activity
#    
#Scenario: Fail report used time for activity that does not exist
#    Given employee exists
#    And activity does not exist
#    When I reports used time
#    Then I get the error message "The activity does not exist"
#Feature: Correct reported time
#    Description: employee can correct reported time
#    Actors: Employee
#    
#Scenario: Successfully correct time
#    Given employee with initials "ABCD" exists
#    And the project with id 1 exists
#    And the activity with name "Some Activity" exists for project
#    And the employee with initials "ABCD" has reported time for the activity with name "Some Activity" 
#    When I updatede time used by adding 2 hours
#    Then the updated time report is saved to activity with name "Some Activity"
#
#Scenario: Fail if the time report does not exist
#    Given employee with initials "ABCD" exists
#    And the project with id 1 exists
#    And the activity with name "Some Activity" exists for project
#    And the employee with initials "ABCD" does not have reported time for the activity with name "Some Activity"
#    When I updatede time used by adding 2 hours
#    Then I get the error message "The time report does not exist"
Feature: Correct reported time
    Description: employee can correct reported time
    Actors: Employee
    
Scenario: Successfully correct time
    Given employee with initials "ABCD" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" has reported 0 hours for the activity with name "Some Activity" on the date 1/2/2019
    When I update time used to 2 hours
    Then the updated time report is saved to activity with name "Some Activity"

Scenario: Fail if the time report does not exist
    Given employee with initials "ABCD" exists
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" does not have reported time for the activity with name "Some Activity" on the date 1/2/2019
    When I update time used to 2 hours
    Then I get the error message "The employee has not registered time for this activity"

Scenario: Fail if the employee doesn't exist
    Given employee with initials "ABCD" does not exist
    And the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the employee with initials "ABCD" has reported 0 hours for the activity with name "Some Activity" on the date 1/2/2019
    When I update time used to 2 hours
    Then I get the error message "The employee does not exist"

#Scenario: Fail if the activity doesn't exist
#    Given employee with initials "ABCD" exists
#    And the project with id 1 exists
#    And the activity "Some Activity" doesn't exist
#    And the employee with initials "ABCD" has reported 0 hours for the activity with name "Some Activity" on the date 1/2/2019
#    When I update time used to 2 hours
#    Then I get the error message "The activity does not exist"

#Scenario: Fail if the project doesn't exist
#    Given employee with initials "ABCD" exists
#	 And project with id 191234 does not exist
#	 And the activity "Some Activity" doesn't exist
#    And the employee with initials "ABCD" has reported 0 hours for the activity with name "Some Activity" on the date 1/2/2019
#    When I update time used to 2 hours
#    Then I get the error message "The project does not exist"
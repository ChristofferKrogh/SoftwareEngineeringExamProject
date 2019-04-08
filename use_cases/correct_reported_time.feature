Feature: Correct reported time
    Description: employee can correct reported time
    Actors: Employee
    
Scenario: Successfully correct time
    Given employee with initials "ABCD" exists
    And time report exists
    When I send updated time used
    Then the time is corrected in the time report

Scenario: Fail if the time report does not exist
    Given employee with initials "ABCD" exists
    And time report does not exists
    When I send updated time used
    Then I get the error message "The time report does not exist"
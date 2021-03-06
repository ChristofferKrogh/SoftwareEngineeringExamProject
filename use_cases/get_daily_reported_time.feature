Feature: Get daily used time
    Description: employee can see reported time
    Actors: Employee

Scenario: Get daily used time when an employee has reported time
    Given employee with initials "JD" exists
    And time report exists for the date 1/2/2019
    When I ask for my daily used time
    Then I get the time used across all activities across all projects

Scenario: Get daily used time when no time is reported for an employee
    Given employee with initials "JD" exists
    And no time report for date 1/2/2019 exists
    When I ask for my daily used time
    Then I get zero

Scenario: Fail get time for employee that does not exist
    Given employee with initials "JD" does not exist
    When I ask for my daily used time
    Then I get the error message "The employee does not exist"

Scenario: Get daily used time when no time is reported for an employee on that date
    Given employee with initials "JD" exists
    And time report exists for the date 1/4/2019
    And no time report for date 1/2/2019 exists
    When I ask for my daily used time
    Then I get zero
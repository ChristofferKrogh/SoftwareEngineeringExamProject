#Feature: Get daily used time
#    Description: employee can see reported time
#    Actors: Employee
#    
#Scenario: Get daily used time when time is reported
#    Given employee exists
#    And time report exists
#    When I ask for my daily used time
#    Then I get the time used across all activities across all projects
#
#Scenario: Get daily used time when no time is reported
#    Given employee exists
#    And time no report for that date exists
#    When I ask for my daily used time
#    Then I get zero
#    
#Scenario: Fail get time for employee that does not exist
#    Given employee does not exists
#    When I ask for my daily used time
#    Then I get the error message "The employee does not exist"
#    
#Scenario: Fail get time for activity that does not exist
#    Given employee exists
#    And time report does not exists
#    When I ask for my daily used time
#    Then I get the error message "The activity does not exist"
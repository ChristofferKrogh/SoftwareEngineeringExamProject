Feature: String representation
    Description: Represent projects, employees, timeregistrations, activities and more as strings
    Actors: Employees
    
Scenario: Represent an employee as a string
    Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I get the string representation of the employee
    Then I get the string "John Doe (JD)"

Scenario: Match employee intitials
    Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I match the employee with "jd"
    Then I get a match
    
Scenario: Match employee first name
    Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I match the employee with "john"
    Then I get a match
    
Scenario: Fail match employee
    Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I match the employee with "jane"
    Then I do not get a match
    
Scenario: Represent an activity as a string
    Given the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When I get the string representation of the activity
    Then I get the string "Some Activity"
    
#Scenario: Represent an activity start week as a string
#    Given the project with id 1 exists
#    And the activity with name "Some Activity" exists for project
#    And the activity starts in week 1 of 2019
#    When I get the string representation the start week of the activity
#    Then I get the string "week 1 of 2019"
    
Scenario: Represent an activity end week as a string
    Given the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    And the activity ends in week 3 of 2019
    When I get the string representation the end week of the activity
    Then I get the string "week 3 of 2019"
    
Scenario: Match activity on name
    Given the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When I match the activity with "some"
    Then I get a match
    
Scenario: Fail match activity
    Given the project with id 1 exists
    And the activity with name "Some Activity" exists for project
    When I match the activity with "Captain America"
    Then I do not get a match

# Match activity on employee name in activity

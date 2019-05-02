Feature: String representation
    Description: Represent projects, employees, timeregistrations, activities and more as strings
    Actors: Employees
    
Scenario: Represent an employee as a string
    Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I get the string representation of the employee
    Then I get the string "John Doe (JD)"


Feature: Create employee
    Description: employees for the company is created
    Actors: The company
    
Scenario: Create employee successfully
	Given employee with initials "JD" exists
    And the employee has the name "John Doe"
    When I check for the employee is created
    Then I get the name "John Doe" and the initials "JD"
    And the initials "JD" is in the initials list
    And the employeelist conatins the employee


Scenario: Fail create employees with duplicate initials
	Given employee with initials "JD" exists
	When I add another employee with the initials "JD"
	Then I get the error message "An employee with the same initials is already in the system" 
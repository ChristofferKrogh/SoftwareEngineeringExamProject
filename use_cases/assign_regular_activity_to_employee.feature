Feature: Assign employee to regular activity
    Description: the employee assigns itself to a regular activity
    Actor: Employee
    
Scenario: A regular activity is created and an employee is assigned to it successfully
    Given employee with initials "JD" exists
    And I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
    When I create the regular activity and assign the employee to it
    Then the regular activity is created and the employee is assigned to it
 
Scenario: The start week of a regular activity is changed 
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
	And the regular activity is in the system
    When I change the start week of the regular activity to week 3 of year 2019
    Then the start week of the regular activity is week 3 of year 2019
    
Scenario: The end week of a regular activity is changed 
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
	And the regular activity is in the system
    When I change the end week of the regular activity to week 3 of year 2019
    Then the end week of the regular activity is week 3 of year 2019
    
Scenario: I try to change the start date of a regular activity that does not exist
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
	And the regular activity is not in the system
	When I change the start week of the regular activity to week 3 of year 2019
	Then I get the error message "The regular activity does not exist"
	
Scenario: I try to change the end date of a regular activity that does not exist
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
	And the regular activity is not in the system
	When I change the end week of the regular activity to week 3 of year 2019
	Then I get the error message "The regular activity does not exist"

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
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I get the string representation of the activity
    Then I get the string "Some Activity"
    
#Scenario: Represent an activity start week as a string
#    Given the project with id 1 exists
#	And project leader has initials "BS"
#    And the activity with name "Some Activity" exists for project
#    And the activity starts in week 1 of 2019
#    When I get the string representation the start week of the activity
#    Then I get the string "week 1 of 2019"
    
Scenario: Represent an activity end week as a string
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    And the activity ends in week 3 of 2019
    When I get the string representation the end week of the activity
    Then I get the string "week 3 of 2019"
    
Scenario: Match activity on name
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I match the activity with "some"
    Then I get a match
    
Scenario: Fail match activity
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I match the activity with "Captain America"
    Then I do not get a match

Scenario: Match activity on employee intitials
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    And employee with initials "JD" is assigned to the activity
    When I match the activity with "jd"
    Then I get a match
    
Scenario: Match activity on employee first name
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    And employee with initials "JD" is assigned to the activity
    And the employee has the name "John Doe"
    When I match the activity with "john"
    Then I get a match
    
Scenario: Represent an project as a string
    Given the project with id 190001 exists
    And the project has the name "Test project"
    When I get the string representation of the project
    Then I get the string "Test project - 190001"
    
Scenario: Represent a project start date as a string
    Given the project with id 1 exists
    And the project start date is 2/1/2019
    When I get the string representation the start date of the project
    Then I get the string "2/1/2019"
    
Scenario: Represent a project end date as a string
    Given the project with id 1 exists
    And the project end date is 7/1/2019
    When I get the string representation the end date of the project
    Then I get the string "7/1/2019"
    
Scenario: Match internal project
    Given there is an internal project with name "Test project"
    When I match the project with "internal"
    Then I get a match
    
Scenario: Match external project
    Given there is an external project with name "Test project"
    When I match the project with "external"
    Then I get a match
    
Scenario: Match external project
    Given there is an internal project with name "Test project"
    When I match the project with "external"
    Then I do not get a match
    
Scenario: Match project on name
    Given the project with id 190001 exists
    And the project has the name "Test project"
    When I match the project with "test"
    Then I get a match
    
Scenario: Match project on project number
    Given the project with id 190001 exists
    When I match the project with "190001"
    Then I get a match
    
Scenario: Match project on employee intitials
    Given the project with id 1 exists
	And employee with initials "JD" exists
	And the employee is project leader
    When I match the project with "jd"
    Then I get a match
    
Scenario: Match project on employee first name
    Given the project with id 1 exists
	And employee with initials "JD" exists
    And the employee has the name "John Doe"
	And the employee is project leader
    When I match the project with "john"
    Then I get a match
    
Scenario: Fail match project
    Given the project with id 190001 exists
    When I match the project with "testNoProject"
    Then I do not get a match

Scenario: Represent a time registration as a string
    Given employee with initials "JS" exists
    And the employee has the name "John Smith"
    And the project with id 1 exists
    And the activity "Don't look at me" doesn't exist
    When the employee report 4 hour on "Don't look at me" on 1/1/2019
    When I get the string representation of the time registration
    Then I get the string "4.0 hours on 1/1/2019 for John Smith"
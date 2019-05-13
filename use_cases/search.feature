Feature: Search functions
    Description: Allows users to search for projects, employees, timeregistrations, activities and more!
    Actors: Employees
    
Scenario: Search for activity successfully
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I search for an activity with name "Some Activity"
    Then I get an activity with name "Some Activity"

Scenario: Search for activities successfully
    Given the project with id 1 exists
	And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I search for an activities with name "Some Activity"
    Then I get an activity with name "Some Activity"

Scenario: Search for activity that does not exist
    Given the project with id 1 exists
    When I search for an activity with name "Some Activity"
    Then I get the error message "The activity does not exist"

Scenario: Search for regular activity successfully
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
    And the employee has the name "John Doe"
	And the regular activity is in the system
    When I search for a regular activity with name "Sickness"
    Then I get an activity with name "Sickness - John Doe"
 
Scenario: Search for regular activies successfully
	Given I have the regular activity with name "Sickness" , start: week 2 of year 2019 and end: week 4 of year 2019
	And employee with initials "JD" exists
    And the employee has the name "John Doe"
	And the regular activity is in the system
    When I search for a regular activies with name "Sickness"
    Then I get an activity with name "Sickness - John Doe"   

Scenario: Search for regular activity that does not exist
    Given the project with id 1 exists
    When I search for a regular activity with name "Sickness"
    Then I get the error message "The regular activity does not exist"

Scenario: Search for regular activties that does not exist
    When I search for a regular activies with name "Sickness"
    Then I get the error message "The regular activity does not exist"
    
Scenario: Search for project by id successfully
    Given the project with some id exists
    When I search a project with that id
    Then I get a project that project
    
Scenario: Search for project by name successfully
    Given the project with id 1 exists
    And the project has the name "Test project"
    When I search a project with name "Test project"
    Then I get a project with name "Test project"
    
Scenario: Search for project that does not exist by name
    When I search a project with name "Test project"
    Then I get the error message "The project does not exist"
    
Scenario: Search for project that does not exist by id
    When I search a project with id 1
    Then I get the error message "The project does not exist"
    
Scenario: Search for employee by initials successfully
    And employee with initials "JS" exists
    When I search for an employee with initials "JS"
    Then I get an employee with intitials "JS"

Scenario: Search for employee by name successfully
    And employee with initials "JS" exists
    And the employee has the name "John Doe"
    When I search for an employee with name "John Doe"
    Then I get an employee with name "John Doe"
    
Scenario: Search for employee that does not exist by initials
    When I search for an employee with initials "JS"
    Then I get the error message "The employee does not exist"
    
Scenario: Search for employee that does not exist by name
    When I search for an employee with name "John Doe"
    Then I get the error message "The employee does not exist"

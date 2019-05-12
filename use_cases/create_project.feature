Feature: Create project
    Description: the project is created and assigned a project number. The project has to have a name.
    			It can be created with or without the start and end dates.
    Actors: Employee
    
Scenario: An internal project is created with name "Test project" successfully 
	Given there is an internal project with name "Test project"
    When an employee creates the project 
    Then the internal project with name "Test project" is created
    And the project is given a project number
    
Scenario: An external project is created with name "Test project" successfully 
	Given there is an external project with name "Test project"
    When an employee creates the project 
    Then the external project with name "Test project" is created
    And the project is given a project number

Scenario: Project number list
	Given the project with some id exists
	Then the project number is included in the project number list

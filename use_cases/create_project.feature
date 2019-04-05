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
#   Scenarios below are redundant - should probably be deleted
#Scenario: An internal project is created with name "" and dates successfully 
#	Given I have an internal project with name and dates
#    When an employee creates a project 
#    Then the project is created
#    And the project is given a project number 
#    
#Scenario: An external project is created with name "" and dates successfully 
#	Given I have an external project with name and dates
#    When an employee creates a project 
#    Then the project is created
#    And the project is given a project number
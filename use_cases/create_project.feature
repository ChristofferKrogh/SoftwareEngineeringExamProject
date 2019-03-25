Feature: Create project
    Description: the project is created and assigned a project number
    Actors: Employee
    
Scenario: A project is created successfully 
    When an employee creates a project 
    Then the project is created
    And the project is given a project number
Feature: Search functions
    Description: Allows users to search for projects, employees, timeregistrations, activities and more!
    Actors: Employees
    
Scenario: Search for activity successfully
    Given the project with id 1 exists
	 And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I search for an activity with name "Some Activity"
    Then I get an activity with name "Some Activity"

Scenario: Search for activity that does not exist
    Given the project with id 1 exists
    And the activity with name "Some Activity" does not exists for project
    When I search for an activity with name "Some Activity"
    Then I get the error message "The activity does not exist"

Scenario: Search for project by id successfully
    Given the project with id 190001 exists
    When I search a project with id 190001
    Then I get a project with id 190001
    
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
    

# Search for employee
# - By Initials
# - By name
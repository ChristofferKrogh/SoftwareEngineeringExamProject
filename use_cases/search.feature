Feature: Search functions
    Description: Allows users to search for projects, employees, timeregistrations, activities and more!
    Actors: Employees
    
Scenario: Search for activity successfully
    Given the project with id 1 exists
	 And project leader has initials "BS"
    And the activity with name "Some Activity" exists for project
    When I search an activity with name "Some Activity"
    Then I get an activity with name "Some Activity"

Scenario: Search for activity that does not exist
    Given the project with id 1 exists
    And the activity with name "Some Activity" does not exists for project
    When I search an activity with name "Some Activity"
    Then I get the error message "The activity does not exist"

# Search for project
# - By name
# - By id

# Search for employee
# - By Initials
# - By name
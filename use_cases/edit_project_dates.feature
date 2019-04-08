Feature: Edit project dates
	Description: The start and end date of a project is set or changed.
	Actors: Anyone (employee or project leader i.e. any employee)
	
Scenario: The start date of a project is changed
	Given there is a project with id 0
	When an employee edits the start date of the project to 1/1/2019
	Then the start date of the project with id 0 is 1/1/2019
	
Scenario: The end date of a project is changed
	Given there is a project with id 0
	When an employee edits the end date of the project to 2/1/2019
	Then the end date of the project with id 0 is 2/1/2019
	
Scenario: The start date of an unexisting project is changed
	Given project with id 0 does not exist
	When an employee edits the start date of the project to 1/1/2019
	Then I get the error message "The project does not exist"
	
Scenario: The end date of an unexisting project is changed
	Given project with id 0 does not exist
	When an employee edits the end date of the project to 2/1/2019
	Then I get the error message "The project does not exist"
	
Scenario: the start date of a project is changed to be later than the end date
	Given there is a project with id 0
	When an employee edits the start date of the project to a date after the end date
	Then I get the error message "The start date must be before the end date"
	
Scenario: the end date of a project is changed to be sooner than the start date
	Given there is a project with id 0
	When an employee edits the end date of the project to a date before the start date
	Then I get the error message "The end date must be after the start date"
	
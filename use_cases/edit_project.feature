Feature: Edit project
	Description: The start and end date of a project is set or changed.
	Actors: Anyone (employee or project leader i.e. any employee)
	
Scenario: The start date of a project is changed
	Given the project with id 1 exists
	When an employee edits the start date of the project to 1/1/2019
	Then the start date of the project is 1/1/2019
	
Scenario: The end date of a project is changed
	Given the project with id 1 exists
	When an employee edits the end date of the project to 2/1/2019
	Then the end date of the project is 2/1/2019
	
Scenario: The start date of an unexisting project is changed
	Given project with id 1 does not exist
	When an employee edits the start date of the project to 1/1/2019
	Then I get the error message "The project does not exist"
	
Scenario: The end date of an unexisting project is changed
	Given project with id 1 does not exist
	When an employee edits the end date of the project to 2/1/2019
	Then I get the error message "The project does not exist"
	
Scenario: the start date of a project is changed to be later than the end date
	Given the project with id 1 exists
	When an employee edits the start date of the project to a date after the end date
	Then I get the error message "The start date must be before the end date"
	
Scenario: the end date of a project is changed to be sooner than the start date
	Given the project with id 1 exists
	When an employee edits the end date of the project to a date before the start date
	Then I get the error message "The end date must be after the start date"
	
Scenario: the name of a project is changed
	Given the project with id 1 exists
	When an employee changes the name of the project to "New Project Name"
	Then the name of the project is "New Project Name"
	
Scenario: the name of an unexisting project is changed
	Given project with id 1 does not exist
	When an employee changes the name of the project to "New Project Name"
	Then I get the error message "The project does not exist"
	
Scenario: the internal status of a project is changed
	Given the project with id 1 exists
	When an employee changes the internal status of the project to "true"
	Then the internal status of the project is "true"
	
Scenario: the internal status of an unexisting project is changed
	Given project with id 1 does not exist
	When an employee changes the internal status of the project to "true"
	Then I get the error message "The project does not exist"
	
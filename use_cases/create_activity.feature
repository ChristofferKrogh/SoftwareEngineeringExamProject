#Feature: Create activity
#    Description: activities for a project are created
#    Actors: Project leader
#    
#Scenario: Create activity successfully 
#    Given project "Test Project" with id "201795" exists
# 	 And Actor have an activity "Project design"
#    When the project leader creates an activity
#    Then the activity "Project design" is created for the project
#
#Scenario: Create activity when not project leader
#	 Given project "Test Project" with id "201795" exists
# 	 And Actor have an activity "Project design"
#	 When an employee creates an activity
#	 Then get the error message "It is only the project leader who can add activities."
#
#Scenario: Create activity with same names 
#	Given project "Test Project" with id "201795" exists
#	And actor have an activity "Project design"
#	And activity "Project design" is already an activity 
#	When the project leader creates an activity
#	Then get the error message "The activity is already part of the project."
# 
#Scenario: Create activity with no project attached 
#	Given project "Test Project 2" doesn't exist 
#	And actor have and activity "Project design" 
#	When the project leader creates an activity 
#	Then get the error message "The activity is not part of a project." 
# 
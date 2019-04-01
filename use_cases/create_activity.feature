#Feature: Create activity
#    Description: activities for a project are created
#    Actors: Project leader
#    
#Scenario: Create activity successfully 
#    Given the project exists
# 	 And I have a activity "Project design"
#    When the project leader creates an activity
#    Then the activity "Project design" is created for the project
#
#Scenario: Create activity when not project leader
#	 Given the project exists
# 	 And I have a activity "Project design"
#	 When an employee creates an activity
#	 Then I get the error message "It is only the project leader who can add activities."
#
# Tilføj test der fejler for at lave en ny aktivitet med samme navn som et der allerede findes.
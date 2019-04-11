Feature: Assign employee to regular activity
    Description: the employee assigns itself to a regular activity
    Actor: Employee
    
Scenario: An employee is assigned to a regular activity successfully
    Given employee with initials "JD" exists
    And I have the regular activity with name "Sickness" start week 2 end week 4
    When I create the regular activity and assign the employee to it
    Then the regular activity is created and the employee is assigned to it
 
#Scenario: An employee is assigned to the regular activity and wants to prolong it 
#    Given the employee exists
#    And the employee is assign to the regular activity "Sick"
#    And employee gives new end date 
#    When I prolong the regular activity
#    Then the employee is assigned to the regular activity "Sick" with new end date

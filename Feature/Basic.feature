Feature: Basic Action

Scenario: Successful Select Green Box
	Given User is on tatoc Home Page
	When User selects Basic Course
	And Color of boxes should be same
	And User drag box in drop box
	Then Page displayed with Title Popup Windows
	
 
Scenario: Successful Launch New Window
	Given User is on Launch Window Page
	When User selects Launch Window 
	And User will submit name
	Then User will click on Proceed button 
	
Scenario: Complete Obstacle
	Given User is on Cookie Handling Page
	When User will Generate Token
	Then Basic Task completed
	And Quit Browser

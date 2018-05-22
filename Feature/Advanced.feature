Feature: Advanced Action

Scenario: User Complete Obstacle
	Given User is on Tatoc Home Page Advanced
	When User selects advanced course
	And User will enter db details
	Then Task completed
	And Quit Chrome Browser
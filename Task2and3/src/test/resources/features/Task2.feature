@Task2
Feature: Task 2
        
	Scenario: Google Search for Cars in London
		Given I Open the following URL "https://www.google.com"
		And I search for "Cars in London"
		When the results return "Gumtree"
		Then all gumtree results are valid
## Task 1 Test Case Generation

### Test Case 1 – Basic Functionality (top as these make up the MVP if you like).
*	Button can be clicked.
*	Text can box can take text.
*	Button click produces an event. 
*	A date can be entered.
*	A given date produces a given age.

###Test Case 2 – Processing & Validation
*	User can only enter a dd/mm/yyyy format date.
*	Results are returned in Y, YY, YYY, YYYY format (checking how old a historical figure would be now for example).
*	Invalid date strings are handled gracefully, including dd/mm/yy.
*	Leap years days are rejected gracefully.
*	Invalid dates are rejected gracefully.
*	Ensure the dates in the future are rejected. 
*	Ensure the lowest possible date is correctly handled.
	
###Test Case 3 – Non Functional
*	Results are returned within [T] time after click.
*	Load testing to ensure [X] number of requests can be handled correctly.

###Test Case 4 – Security 
*	Ensure no SQL injection can take place on the text box. 
*	Ensure spamming the calculate button is handled gracefully without issue.
*	Fuzz inputs to the text input.

###Test Case 1 - Spelling and Grammar (bottom as the easiest to fix so lowest risk).
*	All spelling is correct.
*	All grammar is correct.
*	Words correctly describe the purpose of the application.


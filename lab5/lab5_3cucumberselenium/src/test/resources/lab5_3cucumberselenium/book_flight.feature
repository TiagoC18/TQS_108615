Feature: Book Flight on BlazeDemo

  Scenario: Successfully book a flight from a chosen departure city to a chosen destination city
    Given I navigate to "https://blazedemo.com"
    And I choose my departure city "Portland" and my destination city "Cairo"
    And I click Find Flights button
    And Page should say "Flights from Portland to Cairo:"
    And I choose a flight and click the button
    And I fill in my name as "Tiago Cruz"
    And I fill in my city as "Lutontown"
    And I click Purchase Flight button
    Then Page title should be "BlazeDemo Confirmation"

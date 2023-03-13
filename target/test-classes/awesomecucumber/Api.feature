Feature: API

  Scenario: Successfully Get Request
    Given I'm on the Login Page
    When set the UserName and Password
    And click LoginButton
    Then the user is on "Products" Page
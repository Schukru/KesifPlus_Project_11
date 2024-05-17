@UI
  @Test
  @Tuesday_2
Feature: US 023 Verify that the project page should be accessible through google account

  Scenario: User should access project page
    Given User clicks login with google button
    And User account information should be entered
    Then Verify that the project page can be accessible

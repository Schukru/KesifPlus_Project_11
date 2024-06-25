Feature: The project should be created through UI and verify through Api and DB, then should be deleted through DB

  @UITest
  Scenario: The project should be able to created through UI
    Given User login with Email Address
    And Creates a new Project

  @Api_USER2
  Scenario: The project should be able to verified through Api
    When verify that the project was created

  @DB_default
  Scenario: The project should be able to verified through DB
    Given verify that the project was created through DB

  @DB_default
  Scenario: The project should be able to deleted through DB
    Given delete the project through DB
    When verify that the project was deleted through DB
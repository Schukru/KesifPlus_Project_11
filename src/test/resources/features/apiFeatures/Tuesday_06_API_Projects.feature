@Api_USER2
Feature: User should be able to create new project and delete through api

  Scenario: Create new project and change values through api
    Given user create a new project through api
    When verify that the project was created
    And get wall-colon values to check
    And change wall-colon values in the room
    And delete the project
    Then verify that the project was deleted
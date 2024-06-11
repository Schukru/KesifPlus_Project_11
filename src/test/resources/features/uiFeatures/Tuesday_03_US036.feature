@UITest
@Tuesday_3
Feature: US 036-1 User should be able to see price changes on the cost page

# 1) stg.mobilyaplan.app'a E-mail credentials ile login
  Scenario: Verify that price changes should be displayed on the cost page-3
    Given User login with Email Address
    And Creates a new Project
    And Open the project
    And Add "BOYDOLAP" to the room
    Then Verify total cost is changed
    And Add "USTDOLAP" to the room
    Then Verify total cost is changed
    Then Delete current project

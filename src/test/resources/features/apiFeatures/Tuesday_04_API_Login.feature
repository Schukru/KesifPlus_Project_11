Feature: User Login with Email

  Scenario Outline: Successful login with valid credentials

    Given I have the login "<endPoint>"
    When I send a POST request with valid credentials
    Then I should receive a successful response "200"

    Examples:
      | endPoint                                         |
      | https://stg.mobilyaplan.app/webapi/login/sign-in |
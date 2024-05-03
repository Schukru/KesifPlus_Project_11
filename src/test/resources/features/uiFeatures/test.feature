Feature: Different login scenarios for FrameWork

#Background:
#  Given Go to main page

  @UI
  Scenario: login version 1
    Given Go to main page
    When version1 calissin


  @UI
  @firefox
  Scenario: login version 2
    Given Go to main page
    When Configuration properties kullanarak


  @UI
  @chrome
  Scenario: login version 3
    Given Scenariom icinde string olarak "user1"
    Given Scenariom icinde int olarak 3
    Given Scenariom icinde double olarak 4.6
    Given Scenariom icinde boolean olarak "false"
    Given Scenariom icinde genel olarak "user1" , 3 , 4.6 ve "false"


  @UI
  Scenario: login version 4
    Given Data table kullanarak
      | user1 | password1 |
      | user2 | password2 |
      | user3 | password3 |
      | user4 | password4 |


  @UI
  Scenario Outline: login version 5
    Given Scenariom Outline "<urunAdi>" and <rakam> <kusurat> "<dogru>"
    Then Sadece <rakam> değerlerini kullanabiliriz

    Examples: scenario outline icin degerlerimiz
      | urunAdi                                                      | rakam | kusurat | dogru |
      | Skinsheen Bronzer Stick                                      | 1     | 88.50   | true  |
      | Total Moisture Facial Cream                                  | 2     | 76.00   | false |
      | Absolute Anti-Age Spot Replenishing Unifying TreatmentSPF 15 | 5     | 210.00  | true  |
      | Benefit Bella Bamba                                          | 2     | 56.00   | false |


  @user1
  @UI
  Scenario: login version 6
    Given tag kullanarak


  @UI
  Scenario: login version 7
    Given Enum kullanarak

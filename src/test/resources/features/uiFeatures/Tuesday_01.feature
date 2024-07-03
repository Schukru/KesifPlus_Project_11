@UIMobilya
@Tuesday_1
Feature: Verify Destek and other menu buttons are available in Daha Fazla main menu

  Scenario: Verify that all menu choises are available in Daha Fazla menu
    Given User click or hover over the Daha Fazla menu
    Then Verify that all menu options are visible and clickable
      | Hakkımızda     |
      | Destek         |
      | ProgramYukleme |
      | Blog           |
      | İletişim       |

#  Scenario: Verify that Destek button is functional
#    Given User click or hover over the Daha Fazla menu
#    When Click "Destek" option in Daha Fazla menu
#    Then Verify that different support options are available in the Destek page
#      | GÜNCELLEME            |
#      | PROGRAM AÇILIŞI       |
#      | PROGRAM KULLANIMI     |
#      | ÇOKÇA SORULAN SORULAR |
#
#    Then Verify that footer section is available in current page
#      | İletişim      |
#      | Destek        |
#      | Hızlı Linkler |


#  Scenario Outline: Verify that all menu choises are available in Daha Fazla menu
#    Given User click or hover over the Daha Fazla menu
#    Then Verify that "<MenuOptions>" are visible and clickable
#
#    Examples: Kontrol edilecek menu secenekleri
#      | MenuOptions    |
#      | Hakkımızda     |
#      | Destek         |
#      | ProgramYukleme |
#      | Blog           |
#      | İletişim       |
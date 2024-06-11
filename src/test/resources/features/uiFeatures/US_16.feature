@UIMobilya
@US_16

Feature: User should be registered into the system and should do various actions on Profile page

Scenario: Bir kullanıcı olarak Profil bölümüne erişebilmeliyim
      Given user clicks on Giriş Yap
      When user clicks on E-postanızla kaydolun
      Then user verifies Kaydolun header is seen
      And user enters E-posta and Şifre
      And user clicks on Kaydolun button
      And user clicks on Profile button
      Then user should be navigated to Profile page
      And user uploads profile image
      And user should see profile image is loaded
      And user changes profile image
      Then user should see pop-up message Profil Guncellendi
      And user should type in Hakkımızda section
      And user should see the text Testers test
      Then user should see pop-up message Profil güncellendi
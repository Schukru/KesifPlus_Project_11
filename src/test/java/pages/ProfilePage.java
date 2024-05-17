package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class ProfilePage extends CommonPage{

      @FindBy(css = "span.YT_9QV")
    public WebElement girisYapBtn;
      @FindBy(xpath="//div[@data-testid=\"switchToEmailLink\"]")
    public WebElement emailIleGırısYapBtn;

    @FindBy(css="h1.O5RCw_")

    public WebElement header;
    @FindBy(xpath = "//input[@type=\"email\"]")
    public WebElement emailInputBox;

    @FindBy(xpath = "//input[@type=\"password\"]")
    public WebElement sifreInputBox;

     @FindBy(xpath = "//button[@data-testid=\"buttonElement\"]")

    public WebElement kaydolunBtn;

     @FindBy(css="div.xzldRa")
     public WebElement profileIcon;

     @FindBy(css="a.ScMAzD")
     public WebElement profileLinkInDropdown;

     @FindBy (id = "ProfileCard-14cefc05-d163-dbb7-e4ec-cd4f2c4d6ddd-profilePhotoInput")
   public WebElement uploadPhotoBtn;

     @FindBy(xpath = "//div[@data-img-loaded=\"true\"]")
     public WebElement uploadElmValue;

     @FindBy(css=".V7x572:nth-child(6) > iframe.U73P_q")
     public WebElement popUpAlert;
     @FindBy(xpath = "//div[text()='Profil Güncellendi']")
     public WebElement profilGuncellendiMsg;

     @FindBy(xpath = "//button[@data-hook=\"Profile-AboutOverlay\"]")
     public WebElement hakkindaTextArea;

     @FindBy(xpath= "//button[@data-hook=\"InputDialog-primaryButton\"]")
     public WebElement yayinlaBtn;

     @FindBy(xpath= "//span[@class=\"_739QG public-DraftStyleDefault-ltr\"]")
     public WebElement inputTextArea;
     @FindBy(id="foo")
     public WebElement textArea;

     @FindBy(xpath = "//span[@data-hook=\"message\"]")
     public WebElement hakkindaGuncellendiPopUp;

     public void simulateHumanTyping(WebElement webElement, String input) throws InterruptedException {

         Random r = new Random();

         for (int i = 0; i < input.length(); i++) {
             try {
                 Thread.sleep((int) r.nextGaussian() * 10 + 100);
             } catch (InterruptedException e) {
             }
             String s = new StringBuilder().append(input.charAt(i)).toString();
             webElement.sendKeys(s);
         }
     }
    public static void scrollIntoViewJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("arguments[0].scrollIntoView();", element);
    }
}

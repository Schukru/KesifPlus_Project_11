package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

     @FindBy(css = "div.GVjl6y")
    public WebElement profileIcon;


}

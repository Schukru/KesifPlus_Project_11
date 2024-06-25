package pages;

import com.github.javafaker.Faker;
import io.cucumber.java.eo.Do;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.UI.Utilities;

import java.util.List;
import java.util.Random;

import static stepDefinitions.Hooks.driver;
import static stepDefinitions.backendStepDefs.Week6_Api_StepDefs.myProjectName;

public class ProjectsPage extends CommonPage {

    @FindBy(xpath = "//span[.='Proje Olustur']")
    private WebElement projeOlusturBtn;
    @FindBy(css = "#basic_project_name")
    private WebElement projectNameInputBox;
    @FindBy(xpath = "//ul[@class='ant-list-items']//h4")
    private List<WebElement> listProjectNames;
    @FindBy(xpath = "//ul[@class='ant-list-items']//a")
    private List<WebElement> listProjectOpen;
    @FindBy(xpath = "//ul[@class='ant-list-items']//button")
    private List<WebElement> listProjectDelete;
    @FindBy(xpath = "(//ul)[3]//li/div/span")
    private List<WebElement> listFurnitures;
    @FindBy(xpath = "//td[.='toplamMalzeme']/following-sibling::td")
    private WebElement malzemeToplami;
    @FindBy(xpath = "//td[.='toplam Fiyat']/following-sibling::td")
    private WebElement toplamFiyat;
    @FindBy(xpath = "//button[.='Yes']")
    private WebElement deleteYesButton;

    public static String projectName;

    public void createNewProject() {
        projeOlusturBtn.click();

        Faker faker = new Faker();
        projectName = faker.lorem().word();
        System.out.println("listProjectOpen = " + listProjectOpen.size());
        Utilities.sendText(projectNameInputBox, projectName);
        projectNameInputBox.sendKeys(Keys.ENTER);
        System.out.println("projectName = " + projectName);
        myProjectName = projectName;
    }

    public void openProject(String project) {
        Utilities.waitFor(1);
        int indexNo = Utilities.getIndexNoFromListElement(listProjectNames, project);
        System.out.println("listProjectOpen sonra = " + listProjectOpen.size());
        Utilities.waitAndClick(listProjectOpen.get(indexNo), 10);
        System.out.println("indexNo = " + indexNo);
    }

    public void clickSideButton(String title) {
        WebElement element = driver.findElement(By.xpath("//span[.='" + title + "']"));
        Utilities.clickWithJS(element);
    }

    public void addFurniture(String menuTitle) {
        clickSideButton(menuTitle);

        Random random = new Random();
        int furnitureIndex = random.nextInt(listFurnitures.size());
        Utilities.clickWithJS(listFurnitures.get(furnitureIndex));
        System.out.println("furnitureIndex = " + furnitureIndex);
    }

    public void verifyCost() {
        clickSideButton("MALİYET");
        clickCostButton("plaka");

        double malzeme = Double.parseDouble(malzemeToplami.getText());

        clickCostButton("Toplam");
        double toplam = Double.parseDouble(toplamFiyat.getText());
        Assert.assertEquals(malzeme, toplam, 0.1);
        clickSideButton("ODA");
    }

    public void clickCostButton(String title) {
        WebElement element = driver.findElement(By.xpath("//button[.='" + title + "']"));
        Utilities.clickWithJS(element);
    }

    public void deleteProject() {
        clickSideButton("GİRİŞ");
        clickSideButton("Projelerime Git");
        int indexNo = Utilities.getIndexNoFromListElement(listProjectNames, projectName);
        Utilities.clickWithJS(listProjectDelete.get(indexNo));
        Utilities.clickWithJS(deleteYesButton);
    }


}

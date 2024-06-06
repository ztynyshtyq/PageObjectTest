// YopmailPage.java

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YopmailPage extends AbstractPage {

    private String yopmailUrl = "https://yopmail.com";

    @FindBy(xpath = "//a[@href='email-generator']")
    private WebElement RandomEmailButton;

    @FindBy(xpath = "//div[contains(@class,'segen')]")
    private WebElement email;

    @FindBy(xpath = "//span[text()='Проверить почту']")
    private WebElement emailReceived;

    @FindBy(xpath = "//h3")
    private WebElement EmailTotalEstimatedCost;
    private Actions actions;

    public YopmailPage(WebDriver driver) {
        super(driver);
    }

    public YopmailPage openPage() {
        driver.get(yopmailUrl);
        actions = new Actions(driver);
        return this;
    }

    public void generateEmailPage() {
        RandomEmailButton.click();
    }

    public String getEmail() {
        System.out.println("New Email:");
        System.out.println(email.getText() + "@yopmail.com");
        return email.getText() + "@yopmail.com";
    }

//    public String getEmailTotalEstimatedCost() {
//        return EmailTotalEstimatedCost.getText().split(" ")[4];
//    }

    public void openEmailsReceived() {
        actions.moveToElement(emailReceived).click().build().perform();
    }

    public void waitOnPage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
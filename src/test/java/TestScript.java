// TestScript.java

import org.checkerframework.checker.units.qual.A;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testEstimatedCosts() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage(driver);
        pricingCalculatorPage.fillForm();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillSeries();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillMachineTypeOption();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.checkAddGPU();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillGPUModel();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillLocalSSDOption();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillRegion();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillCommitmentOptions();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.addToEstimate();

        pricingCalculatorPage.waitOnPage();

        driver.switchTo().newWindow(WindowType.TAB);

        YopmailPage yopmailPage = new YopmailPage(driver);
        yopmailPage.openPage();
        yopmailPage.generateEmailPage();
        yopmailPage.waitOnPage();
        String email = yopmailPage.getEmail();
        System.out.println(email);

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString() );

        pricingCalculatorPage.emailEstimate();
        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.fillEmailField(email);

        pricingCalculatorPage.waitOnPage();
        pricingCalculatorPage.sendEmail();
        pricingCalculatorPage.waitOnPage();

        String actual = pricingCalculatorPage.getTotalEstimatedCost();
        String expected = "1,081.20";

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        yopmailPage.waitOnPage();
        yopmailPage.openEmailsReceived();

        yopmailPage.waitOnPage();
        Assert.assertEquals(expected, actual);
    }

}
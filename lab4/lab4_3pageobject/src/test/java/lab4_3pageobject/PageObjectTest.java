package lab4_3pageobject;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class PageObjectTest {
    private WebDriver driver;

    private static String PAGE_URL = "https://blazedemo.com/";

    @FindBy(name = "fromPort")
    private WebElement fromPortButton;

    @FindBy(xpath = "//option[. = 'Portland']")
    private WebElement portlandOption;

    @FindBy(name = "toPort")
    private WebElement toPortButton;

    @FindBy(xpath = "//option[. = 'Cairo']")
    private WebElement cairoOption;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement chooseThisFlightButton;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "cardType")
    private WebElement cardTypeDropdown;

    @FindBy(xpath = "//option[. = \"Diner's Club\"]")
    private WebElement dinersClubOption;

    @FindBy(id = "creditCardNumber")
    private WebElement cardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement cardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement cardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseFlightButton;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        PageFactory.initElements(driver, this);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bookFlightTest() {
        driver.get(PAGE_URL);

        fromPortButton.click();
        portlandOption.click();

        toPortButton.click();
        cairoOption.click();

        findFlightsButton.click();
        chooseThisFlightButton.click();

        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Purchase");

        nameInput.sendKeys("Tiago Cruz");
        addressInput.sendKeys("420 Duck Street");
        cityInput.sendKeys("Lutontown");
        stateInput.sendKeys("Rabbit");
        zipCodeInput.sendKeys("420");

        cardTypeDropdown.click();
        dinersClubOption.click();

        cardNumber.sendKeys("1826786921420");
        cardMonth.sendKeys("4");
        cardYear.sendKeys("2100");

        nameOnCard.sendKeys("Tiago Cruz");
        
        purchaseFlightButton.click();

        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}


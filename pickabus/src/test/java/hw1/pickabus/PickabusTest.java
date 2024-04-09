package hw1.pickabus;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class PickabusTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void pickabus() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(550, 692));
    driver.findElement(By.id("origin")).click();
    driver.findElement(By.id("origin")).click();
    {
      WebElement dropdown = driver.findElement(By.id("origin"));
      dropdown.findElement(By.xpath("//option[. = 'Berlin']")).click();
    }
    driver.findElement(By.cssSelector("#origin > option:nth-child(3)")).click();
    driver.findElement(By.id("destination")).click();
    {
      WebElement dropdown = driver.findElement(By.id("destination"));
      dropdown.findElement(By.xpath("//option[. = 'Lisbon']")).click();
    }
    driver.findElement(By.cssSelector("#destination > option:nth-child(5)")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).sendKeys("2024-04-24");
    driver.findElement(By.cssSelector("button")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.id("currency")).click();
    {
      WebElement dropdown = driver.findElement(By.id("currency"));
      dropdown.findElement(By.xpath("//option[. = 'USD']")).click();
    }
    driver.findElement(By.cssSelector("#currency > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("button:nth-child(4)")).click();
    driver.findElement(By.id("passengerName")).click();
    driver.findElement(By.id("passengerName")).sendKeys("Jose");
    driver.findElement(By.id("passengerEmail")).click();
    driver.findElement(By.id("passengerEmail")).sendKeys("jose@gmail.com");
    driver.findElement(By.id("numberOfTickets")).click();
    {
      WebElement dropdown = driver.findElement(By.id("numberOfTickets"));
      dropdown.findElement(By.xpath("//option[. = '3']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("button")).click();
  }
}

package lab4_4docker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.BrowserType;

@ExtendWith(SeleniumJupiter.class)
public class DockerTest {
    @Test
    public void testChromeInDocker(@DockerBrowser(type = BrowserType.CHROME) WebDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.findElement(By.name("fromPort")).click();
        WebElement fromPortDropdown = driver.findElement(By.name("fromPort"));
        fromPortDropdown.findElement(By.xpath("//option[. = 'Portland']")).click();

        driver.findElement(By.name("toPort")).click();
        WebElement toPortDropdown = driver.findElement(By.name("toPort"));
        toPortDropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();

        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
        
        driver.findElement(By.id("inputName")).sendKeys("Tiago Cruz");
        driver.findElement(By.id("address")).sendKeys("420 Duck Street");
        driver.findElement(By.id("city")).sendKeys("Lutontown");
        driver.findElement(By.id("state")).sendKeys("Rabbit");
        driver.findElement(By.id("zipCode")).sendKeys("420");
        
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
}

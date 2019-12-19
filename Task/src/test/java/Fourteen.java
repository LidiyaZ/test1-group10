import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Fourteen {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
//        driver= new ChromeDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions(caps);
        options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait (driver, 10);

    }
    @Test
    public void testGoogleSearch() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/login.php/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List <WebElement> countriesEdit = driver.findElements(By.cssSelector(".row a[title=Edit]"));
        WebElement countryEdit = countriesEdit.get(5);
        countryEdit.click();

        List <WebElement> externalLinks = driver.findElements(By.cssSelector(".fa.fa-external-link"));
       for (int i=0; i < externalLinks.size(); i++) {
           String originalWindow = driver.getWindowHandle();
           Set <String> existingWindows = driver.getWindowHandles();
           WebElement currentLink = externalLinks.get(i);
           currentLink.click();
           String newWindow = wait.until(new ExpectedCondition<String>() {
               public String apply(WebDriver driver) {
                   Set<String> newWindowsSet = driver.getWindowHandles();
                   newWindowsSet.removeAll(existingWindows);
                   return newWindowsSet.size() > 0 ?
                           newWindowsSet.iterator().next() : null;
               }
           });
           driver.switchTo().window(newWindow);
//           Thread.sleep(1000);
           driver.close();
           driver.switchTo().window(originalWindow);
           externalLinks = driver.findElements(By.cssSelector(".fa.fa-external-link"));
       }
       
    }
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}


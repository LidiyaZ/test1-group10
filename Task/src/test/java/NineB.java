import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

public class NineB {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        driver= new ChromeDriver();
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

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> countries = driver.findElements(By.cssSelector(".row a:not([title=Edit])"));
        for (int i = 0; i < countries.size(); i++) {
             WebElement currentCountryName = countries.get(i);

              currentCountryName.click();

              List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones tr>:nth-child(3) [name *= zone_code] [selected=selected]"));
                for (int j= 0; j < zones.size() - 1; j++) {
                     int firstZone = j;
                     int secondZone = j + 1;
                     String firstZoneName = zones.get(firstZone).getAttribute("textContent");
                     String secondZoneName = zones.get(secondZone).getAttribute("textContent");
                     int compareResult = firstZoneName.compareTo(secondZoneName);
                     boolean sortCorrect = compareResult <= 0;
                     if (compareResult > 0) throw new InterruptedException();
                }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            countries = driver.findElements(By.cssSelector(".row a:not([title=Edit])"));
        }

    }

    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}


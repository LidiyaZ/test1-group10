import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

public class Nine {
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

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countries = driver.findElements(By.cssSelector(".row a:not([title=Edit])"));

        for (int i = 0; i < countries.size() - 1; i++) {
            int firstCountry = i;
            int secondCountry = i + 1;
            String firstCountryName = countries.get(firstCountry).getAttribute("textContent");
            String secondCountryName = countries.get(secondCountry).getAttribute("textContent");
            int compareResult = firstCountryName.compareTo(secondCountryName);
            boolean sortCorrect = compareResult <= 0;
            if (compareResult > 0) throw new InterruptedException();
        }

      List <WebElement> rows = driver.findElements (By.cssSelector(".row"));
        for (int k = 0; k < rows.size(); k++)
        {
            WebElement currentRowElement = rows.get(k);
            String zone = currentRowElement.findElement(By.cssSelector(".row>:nth-child(6)")).getText();
  //        String currentCountryName = currentRowElement.findElement(By.cssSelector(".row>:nth-child(5)")).getText(); //получаем название страны
            WebElement countryBox = currentRowElement.findElement(By.cssSelector(".row>:nth-child(5)"));
            WebElement countryLink = countryBox.findElement(By.cssSelector("a"));

            boolean isNolik = zone.equals("0");

            if (isNolik == false) {

                countryLink.click();

                List<WebElement> zonesNames = driver.findElements(By.cssSelector("#table-zones tr>:nth-child(3) [type=hidden]"));

                for (int i = 0; i < zonesNames.size() - 1; i++) {
                    int firstZone = i;
                    int secondZone = i + 1;
                    String firstZoneName = zonesNames.get(firstZone).getAttribute("textContent");
                    String secondZoneName = zonesNames.get(secondZone).getAttribute("textContent");
                    int compareResult = firstZoneName.compareTo(secondZoneName);
                    boolean sortCorrect = compareResult <= 0;
                    if (compareResult > 0) throw new InterruptedException();
                }

                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                rows = driver.findElements (By.cssSelector(".row"));
            }

        }

    }

    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

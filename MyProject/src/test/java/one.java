import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class one {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        driver= new ChromeDriver();
        wait = new WebDriverWait (driver, 10);
    }
    @Test
    public void testGoogleSearch() throws InterruptedException {

        driver.get("http://www.google.com/");
        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("historical dress");
        searchBox.submit();
        Thread.sleep(2000);
        driver.quit();
    }
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

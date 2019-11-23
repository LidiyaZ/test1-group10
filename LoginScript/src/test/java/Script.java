import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class Script {
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
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement login = driver.findElement(By.name("login"));
        login.click();
        Thread.sleep(2000);
    }
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

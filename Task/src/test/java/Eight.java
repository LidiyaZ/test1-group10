import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;



public class Eight {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        driver= new ChromeDriver();
        wait = new WebDriverWait (driver, 10);

    }
    @Test
    public void testGoogleSearch() throws InterruptedException {

        driver.get("http://localhost/litecart/en/");
        //Thread.sleep(2000);

        List<WebElement> productS = driver.findElements(By.cssSelector(".product"));
        for (int i = 0; i < productS.size(); i++) {
                List<WebElement> sticker = productS.get(i).findElements(By.cssSelector(".sticker"));
                if (sticker.size() != 1) throw new InterruptedException();
            }
        }


    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}


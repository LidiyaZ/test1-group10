import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;


public class Seven {
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

        List<WebElement> A = driver.findElements(By.cssSelector("[id ^=app]"));

        for (int i=0; i<A.size();i++)
        {
            A = driver.findElements(By.cssSelector("[id ^=app]"));
            A.get(i).click();
            List<WebElement> tag = driver.findElements(By.tagName("h1"));
            if (tag.size()==0) throw new InterruptedException();
            List<WebElement> B = driver.findElements(By.cssSelector("[id ^=doc]"));
            //Thread.sleep(1000);
            for (int j=0; j<B.size();j++)
            {
                B = driver.findElements(By.cssSelector("[id ^=doc]"));
                B.get(j).click();
                List<WebElement> tagsub = driver.findElements(By.tagName("h1"));
                if (tagsub.size()==0) throw new InterruptedException();
               // Thread.sleep(1000);
           }
        }
    }

    
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

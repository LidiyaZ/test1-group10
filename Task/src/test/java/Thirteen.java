import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Thirteen {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
         driver= new ChromeDriver();
//        DesiredCapabilities caps = new DesiredCapabilities();
//        FirefoxOptions options = new FirefoxOptions(caps);
//        options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//        driver = new FirefoxDriver(options);
        wait = new WebDriverWait (driver, 10);

    }
    @Test
    public void testGoogleSearch() throws InterruptedException {

        driver.get("http://localhost/litecart/en/");

     for (int i=0; i<3; i++) {
         WebElement checkCart1 = driver.findElement(By.cssSelector("#cart .content .quantity"));
         String checkResult1 = checkCart1.getAttribute("textContent");
         List<WebElement> mostPopular = driver.findElements(By.cssSelector("#box-most-popular li"));
         WebElement firstMostPopular = mostPopular.get(0);
         firstMostPopular.click();
         WebElement button = wait.until(presenceOfElementLocated(By.cssSelector("button[name=add_cart_product]")));

         WebElement titleName = driver.findElement(By.cssSelector("#box-product .title"));
         String duckName = titleName.getAttribute("textContent");
         if (duckName.equals("Yellow Duck")) {
             WebElement duckSize = driver.findElement(By.cssSelector(".options select"));
             Select selectSize = new Select(duckSize);
             selectSize.selectByValue("Small");
         }
//         Thread.sleep(3000);
         WebElement buttonAddToCart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
//         Thread.sleep(3000);
         buttonAddToCart.click();
 //        Thread.sleep(3000);
         wait.until(not(elementToBeClickable(buttonAddToCart)));
         wait.until(alertIsPresent()).accept();
         wait.until(elementToBeClickable(buttonAddToCart));
 //       Thread.sleep(3000);

         driver.get("http://localhost/litecart/en/");
         WebElement logotypeElement = wait.until(presenceOfElementLocated(By.id("box-logotypes")));
         WebElement checkCart2 = driver.findElement(By.cssSelector("#cart .content .quantity"));
         String checkResult2 = checkCart2.getAttribute("textContent");
         int compareCartResult = checkResult1.compareTo(checkResult2);
         if (compareCartResult >= 0) throw new InterruptedException();
     }

         WebElement checkout = driver.findElement(By.cssSelector("#cart-wrapper .link"));
         checkout.click();
         WebElement boxCheckoutElement = wait.until(presenceOfElementLocated(By.id("box-checkout-cart")));
         List <WebElement> productList1= driver.findElements(By.cssSelector("#box-checkout-summary td.item"));
         List <WebElement> remove = driver.findElements(By.name("remove_cart_item"));

        for (int i=0; i < productList1.size(); i++) {

            wait.until(visibilityOf(remove.get(0))).click();
            wait.until(ExpectedConditions.stalenessOf(productList1.get(0)));
            productList1 = driver.findElements(By.cssSelector("#box-checkout-summary td.item"));
            remove = driver.findElements(By.name("remove_cart_item"));
        }
        Assert.assertEquals(0, productList1.size());

    }
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}


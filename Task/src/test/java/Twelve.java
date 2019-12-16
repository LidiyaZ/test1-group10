import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class Twelve {
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

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        WebElement buttonAddProduct = driver.findElement(By.cssSelector(".button[href *=edit_product]"));
        buttonAddProduct.click();
        Thread.sleep(1000);

        WebElement productName = driver.findElement(By.cssSelector("#tab-general input[name ^= name]"));
        productName.sendKeys("Luminous duck");
        WebElement productCode = driver.findElement(By.cssSelector("#tab-general input[name = code]"));
        productCode.sendKeys("rd010");
        WebElement categories = driver.findElement(By.cssSelector(".input-wrapper tbody>:nth-child(2) [name ^= categories]"));
        categories.click();
        Thread.sleep(500);
        WebElement productGroups = driver.findElement(By.cssSelector(".input-wrapper tbody>:nth-child(4) [name ^= product_groups]"));
        productGroups.click();
        Thread.sleep(500);
        WebElement quantity = driver.findElement(By.cssSelector("#tab-general [name=quantity]"));
        quantity.sendKeys("55");
        quantity.sendKeys(Keys.DELETE);
        WebElement newImages = driver.findElement(By.cssSelector("#tab-general [name ^=new_images]"));
        new Actions (driver).moveToElement(newImages).moveByOffset(-150,0).perform();
        newImages.sendKeys(new File("./duck.jpg").getAbsolutePath());

        WebElement information = driver.findElement(By.cssSelector("ul.index [href *= information]"));
        information.click();
        Thread.sleep(2000);
        WebElement manufacturer = driver.findElement(By.cssSelector("select[name = manufacturer_id]"));
        Select selectManufacturer = new Select(manufacturer);
        selectManufacturer.selectByValue("1");
        WebElement keywords = driver.findElement(By.cssSelector("#tab-information [name=keywords]"));
        keywords.sendKeys("luminous, duck");
        WebElement shortDescription = driver.findElement(By.cssSelector("#tab-information [name^=short_description]"));
        shortDescription.sendKeys("Luminous rubber duck");
        WebElement description = driver.findElement(By.cssSelector("#tab-information .trumbowyg-editor"));
        description.sendKeys("A rubber duck (also known as a rubber ducky) is a toy shaped like a stylized duck, " +
                "generally yellow with a flat base. It may be made of rubber or rubber-like material such as vinyl plastic." +
                " The yellow rubber duck has achieved an iconic status in Western pop culture and is often symbolically linked to bathing.(c)Wikipedia");

        WebElement pricesLink = driver.findElement(By.cssSelector(".index [href *= prices]"));
        pricesLink.click();
        Thread.sleep(1000);
        WebElement purchasePrices = driver.findElement(By.cssSelector("#tab-prices [name=purchase_price]"));
        purchasePrices.sendKeys("25");
        purchasePrices.sendKeys(Keys.DELETE);
        WebElement price = driver.findElement(By.cssSelector("#tab-prices span [name*=USD]"));
        price.sendKeys("25");

        WebElement buttonSave = driver.findElement(By.cssSelector("button[name=save]"));
        buttonSave.click();
        Thread.sleep(1000);
        
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        WebElement check = driver.findElement(By.cssSelector(".dataTable .row.semi-transparent"));
        check.click();

    }
    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

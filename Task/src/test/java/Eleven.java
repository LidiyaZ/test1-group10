import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class Eleven {
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
        WebElement newCustomerLink = driver.findElement(By.cssSelector("#box-account-login a"));
        newCustomerLink.click();
        Thread.sleep(1000);
        WebElement firstName = driver.findElement(By.cssSelector("#create-account [name=firstname]"));
        firstName.sendKeys("Abra");
        WebElement lastName = driver.findElement(By.cssSelector("#create-account [name=lastname]"));
        lastName.sendKeys("Kodabra");
        WebElement address1 = driver.findElement(By.cssSelector("#create-account [name=address1]"));
        address1.sendKeys("Yellow str., 5-15");
        WebElement postcode = driver.findElement(By.cssSelector("#create-account [name=postcode]"));
        postcode.sendKeys("12345");
        WebElement city = driver.findElement(By.cssSelector("#create-account [name=city]"));
        city.sendKeys("Color");
        WebElement countries = driver.findElement(By.cssSelector("span.select2"));
        countries.click();
        WebElement countrySelected = driver.findElement(By.cssSelector("input.select2-search__field"));
        countrySelected.sendKeys("United States");
        countrySelected.sendKeys(Keys.ENTER);
        WebElement email = driver.findElement(By.cssSelector("#create-account [name=email]"));
        email.sendKeys("abra.kodabra@kodabra.ru");
        WebElement phone = driver.findElement(By.cssSelector("#create-account [name=phone]"));
        phone.sendKeys("+79991112223344");
        WebElement password = driver.findElement(By.cssSelector("#create-account [name=password]"));
        password.sendKeys("password");
        WebElement confirmedPassword = driver.findElement(By.cssSelector("#create-account [name=confirmed_password]"));
        confirmedPassword.sendKeys("password");
        WebElement buttonCreate = driver.findElement(By.cssSelector("#create-account [name=create_account]"));
        buttonCreate.click();
        Thread.sleep(1000);
        WebElement zoneCode = driver.findElement(By.name("zone_code"));
        zoneCode.click();
        Thread.sleep(500);
        WebElement selectedZone = driver.findElement(By.cssSelector("[name=zone_code] option:nth-child(3)"));
        selectedZone.click();
        Thread.sleep(500);
        password = driver.findElement(By.cssSelector("#create-account [name=password]"));
        password.sendKeys("password");
        confirmedPassword = driver.findElement(By.cssSelector("#create-account [name=confirmed_password]"));
        confirmedPassword.sendKeys("password");
        buttonCreate = driver.findElement(By.cssSelector("#create-account [name=create_account]"));
        buttonCreate.click();
        Thread.sleep(1000);

        WebElement logout = driver.findElement(By.cssSelector(".content [href *=logout]"));
        logout.click();
        Thread.sleep(1000);
        WebElement accountEmail = driver.findElement(By.cssSelector("#box-account-login [name=email]"));
        accountEmail.sendKeys("abra.kodabra@kodabra.ru");
        WebElement accountPassword = driver.findElement(By.cssSelector("#box-account-login [name=password]"));
        accountPassword.sendKeys("password");
        WebElement buttonLogin = driver.findElement(By.cssSelector("#box-account-login [name=login]"));
        buttonLogin.click();
        Thread.sleep(1000);

        logout = driver.findElement(By.cssSelector(".content [href *=logout]"));
        logout.click();
        Thread.sleep(1000);
    }

    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}


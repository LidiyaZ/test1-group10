import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenIE {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        DesiredCapabilities ieCaps = new DesiredCapabilities();
        driver = new InternetExplorerDriver(ieCaps);
        wait = new WebDriverWait (driver, 10);

    }
    @Test
    public void testGoogleSearch() throws InterruptedException {

        driver.get("http://localhost/litecart/en/");

        WebElement productNameMainPage = driver.findElement(By.cssSelector("#box-campaigns div.name")); //находим текст заголовка MainPage
        String productNameTextMP = productNameMainPage.getAttribute("textContent");
//находим значения текста,цвета, шрифта (зачеркивание), размера для RegularPrice на MainPage
        WebElement productRegularPriceMP = driver.findElement(By.cssSelector("#box-campaigns s.regular-price"));
        String productRPTextMP = productRegularPriceMP.getAttribute("textContent");
        String productRPColorMP = productRegularPriceMP.getCssValue("color");
        String productRPLineMP = productRegularPriceMP.getCssValue("text-decoration");
        Dimension productRPSizeMP = productRegularPriceMP.getSize();
//вычисляем размер поля цены для RegularPrice на MainPage
        int heightRegularMP = productRPSizeMP.getHeight();
        int widthRegularMP = productRPSizeMP.getWidth();
        int areaRegularMP = heightRegularMP * widthRegularMP;
//вытаскиваем значения RGBa для RegularPrice на MainPage
        Color color1 = Color.fromString(productRPColorMP);
        int regularRedMP = color1.getColor().getRed();
        int regularGreenMP = color1.getColor().getGreen();
        int regularBlueMP = color1.getColor().getBlue();
//находим значения текста,цвета, шрифта (зачеркивание), размера для SalePrice на MainPage
        WebElement productSalePriceMP = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price"));
        String productSPTextMP = productSalePriceMP.getAttribute("textContent");
        String productSPColorMP = productSalePriceMP.getCssValue("color");
        String productSPWeightMP = productSalePriceMP.getCssValue("font-weight");
        Dimension productSPSizeMP = productSalePriceMP.getSize();
//вычисляем размер поля цены для SalePrice на MainPage
        int heightSaleMP = productSPSizeMP.getHeight();
        int widthSaleMP = productSPSizeMP.getWidth();
        int areaSaleMP = heightSaleMP * widthSaleMP;

//проверяем, что  акционная цена крупнее, чем обычная на MainPage
        boolean saleAreaBiggerMP = (areaSaleMP > areaRegularMP);
        if (!saleAreaBiggerMP) throw new InterruptedException();

//вытаскиваем значения RGBa для SalePrice на MainPage
        Color color2 = Color.fromString(productSPColorMP);
        int saleRedMP = color2.getColor().getRed();
        int saleGreenMP = color2.getColor().getGreen();
        int saleBlueMP = color2.getColor().getBlue();
//переходим на страницу найденного товара (NextPage)
        WebElement campaigns = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        campaigns.click();
        Thread.sleep(1000);
//находим текст заголовка NextPage
        WebElement productNameNextPage = driver.findElement(By.cssSelector("div.content h1.title"));
        String productNameTextNP = productNameNextPage.getAttribute("textContent");
//находим значения текста,цвета, шрифта (зачеркивание), размера для RegularPrice на NextPage
        WebElement productRegularPriceNP = driver.findElement(By.cssSelector("div.content s.regular-price"));
        String productRPTextNP = productRegularPriceNP.getAttribute("textContent");
        String productRPColorNP = productRegularPriceNP.getCssValue("color");
        String productRPLineNP = productRegularPriceNP.getCssValue("text-decoration");
        Dimension productRPSizeNP = productRegularPriceNP.getSize();
//вычисляем размер поля цены для RegularPrice на NextPage
        int heightRegularNP = productRPSizeNP.getHeight();
        int widthRegularNP = productRPSizeNP.getWidth();
        int areaRegularNP = heightRegularNP * widthRegularNP;
//вытаскиваем значения RGBa для RegularPrice на NextPage
        Color color3 = Color.fromString(productRPColorNP);
        int regularRedNP = color3.getColor().getRed();
        int regularGreenNP = color3.getColor().getGreen();
        int regularBlueNP = color3.getColor().getBlue();
//находим значения текста,цвета, шрифта (зачеркивание), размера для SalePrice на NextPage
        WebElement productSalePriceNP = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String productSPTextNP = productSalePriceNP.getAttribute("textContent");
        String productSPColorNP = productSalePriceNP.getCssValue("color");
        String productSLWeightNP = productSalePriceNP.getCssValue("font-weight");
        Dimension productSPSizeNP = productSalePriceNP.getSize();
//вычисляем размер поля цены для SalePrice на NextPage
        int heightSaleNP = productSPSizeNP.getHeight();
        int widthSaleNP = productSPSizeNP.getWidth();
        int areaSaleNP = heightSaleNP * widthSaleNP;

//проверяем, что  акционная цена крупнее, чем обычная на NextPage
        boolean saleAreaBiggerNP = (areaSaleNP > areaRegularNP);
        if (!saleAreaBiggerNP) throw new InterruptedException();

//вытаскиваем значения RGBa для SalePrice на NextPage
        Color color4 = Color.fromString(productSPColorNP);
        int saleRedNP = color4.getColor().getRed();
        int saleGreenNP = color4.getColor().getGreen();
        int saleBlueNP = color4.getColor().getBlue();

//сравнение заголовков на двух страницах
        int compareNameText = productNameTextMP.compareTo(productNameTextNP);
        if (compareNameText != 0) throw new InterruptedException();
//сравнение текста регулярной цены на двух страницах
        int compareRPText = productRPTextMP.compareTo(productRPTextNP);
        if (compareRPText !=0) throw new InterruptedException();
// сравнение текста скидочной цены на двух страницах
        int compareSPText = productSPTextMP.compareTo(productSPTextNP);
        if (compareSPText !=0) throw new InterruptedException();
        //обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой,
        // у которого в RGBa представлении одинаковые значения для каналов R, G и B) для MAIN PAGE
        boolean greyRegularPriceMP = (regularRedMP == regularBlueMP) && (regularRedMP == regularGreenMP); //подтверждаем, что цвет серый у рег.цены
        int indexRPLineMP = productRPLineMP.indexOf("line-through"); //подтверждаем, что цена зачеркнута
        boolean isRPLineMP = (indexRPLineMP >= 0);
        boolean correctRegPriceMainP = ( isRPLineMP && greyRegularPriceMP ); // объединяем цвет и стиль
        if (!correctRegPriceMainP) throw new InterruptedException();
//акционная жирная и красная (можно считать, что "красный" цвет это такой,
// у которого в RGBa представлении каналы G и B имеют нулевые значения) для MAIN PAGE
        boolean redSalePriceMP = (saleGreenMP == 0) && (saleBlueMP == 0); //подтверждаем, что цвет красный
        int indexSPWeightMP = productSPWeightMP.indexOf("900"); //подтверждаем, что цена жирная
        boolean isSPWeightMP = (indexSPWeightMP >= 0);
        boolean correctSalePriceMainP = ( isSPWeightMP && redSalePriceMP ); // объединяем цвет и стиль
        if (!correctSalePriceMainP) throw new InterruptedException();
//обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой,
// у которого в RGBa представлении одинаковые значения для каналов R, G и B) для NEXT PAGE
        boolean greyRegularPriceNP = (regularRedNP == regularBlueNP) && (regularRedNP == regularGreenNP); //подтверждаем, что цвет серый
        int indexRPLineNP = productRPLineNP.indexOf("line-through"); //подтверждаем, что цена зачеркнута
        boolean isRPLineNP = (indexRPLineNP >= 0);
        boolean correctRegPriceNextP = ( isRPLineNP && greyRegularPriceNP ); // объединяем цвет и стиль
        if (!correctRegPriceNextP) throw new InterruptedException();
//акционная жирная и красная (можно считать, что "красный" цвет это такой,
// у которого в RGBa представлении каналы G и B имеют нулевые значения) для NEXT PAGE
        boolean redSalePriceNP = (saleGreenNP == 0) && (saleBlueNP == 0); //подтверждаем, что цвет красный
        int indexSPWeightNP = productSLWeightNP.indexOf("700"); //подтверждаем, что цена жирная
        boolean isSPWeightNP = (indexSPWeightNP >= 0);
        boolean correctSalePriceNextP = ( isSPWeightNP && redSalePriceNP ); // объединяем цвет и стиль
        if (!correctSalePriceNextP) throw new InterruptedException();
    }

    @After
    public  void stop() {
        driver.quit();
        driver = null;
    }
}

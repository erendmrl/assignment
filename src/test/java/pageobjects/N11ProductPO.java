package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * In this class, PageFactory and @FindBy annotation is not used, By will be used
 */
public class N11ProductPO {

    WebDriver driver;

    public N11ProductPO(WebDriver driver) throws Exception {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }


    By addToCartButton = By.xpath("//a[@title='Sepete Ekle']");
    By musteriAydinlatmaMetniButton = By.xpath("//span[@class='btn btnBlack']");
    By productNameText = By.className("proName");
    By getProductPriceText = By.tagName("ins");


    public void clickAddToTheCart() {
        driver.findElement(addToCartButton).click();
    }

    public void clickTamam_MisafirMusteriAydinlatmaMetni() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(musteriAydinlatmaMetniButton));
        driver.findElement(musteriAydinlatmaMetniButton).click();
    }

    public String retreiveProductName() {
        return driver.findElement(productNameText).getText();
    }

    public String retreiveProductPrice() {
        return driver.findElement(getProductPriceText).getText();
    }
}

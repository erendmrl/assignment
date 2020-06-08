package pageobjects;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * In this class, PageFactory is used(initialized inside TestBase class) and @FindBy annotation is used
 */
public class N11ShoppingCartPO {

    WebDriver driver;

    public N11ShoppingCartPO(WebDriver driver) throws Exception {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(.,'+')]")
    private WebElement increaseAmount;

    @FindBy(xpath = "(//span[@class='price'])[1]")
    private WebElement productPrice;


    @FindBy(xpath = "//b[contains(.,'Toplam 2 ürün')]")
    private WebElement quantity;


    @FindBy(xpath = "//h2[@class='title'][contains(.,'Sepetiniz Boş')]")
    private WebElement cartEmpty;

    @FindBy(xpath = "//span[contains(.,'Sil')]")
    private WebElement removeItem;


    public String retreiveProductPrice() {
        return productPrice.getText();
    }

    public void increaseQuantity() throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(increaseAmount).click().perform();

        //Quantity increase by typing to text field
      /*  quantity.click();
        quantity.sendKeys(Keys.DELETE);
        quantity.clear();
        quantity.sendKeys("5");
        */

    }

    public String retrieveQuantity() {
        return quantity.getText();
    }

    public void removeItem() {
        Actions act = new Actions(driver);
        act.moveToElement(removeItem).click().perform();
        removeItem.click();
    }

    public String retreiveIsEmtpty() {
        return cartEmpty.getText();
    }

}

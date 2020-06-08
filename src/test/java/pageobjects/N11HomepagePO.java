package pageobjects;

import dataProvider.JsonFileParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * In this class, PageFactory is used(initialized inside TestBase class) and both @FindBy annotation and By is used to locate elements
 */
public class N11HomepagePO  {
    WebDriver driver;
    JsonFileParser jsonFileParser = new JsonFileParser();
    public String homePageTitle = jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "homePageTitle");

    public N11HomepagePO(WebDriver driver) throws Exception {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='https://www.n11.com/giris-yap'][contains(.,'Giriş Yap')]")
    private WebElement loginButton;


    @FindBy(className = "icon iconBasket")
    private WebElement cartIcon;

    @FindBy(className = "btnHolder")
    private WebElement kvkkMessage;

    By searchBar = By.id("searchData");
    By searchIcon = By.xpath("//span[@class='icon iconSearch']");



    public void navigateToHomepage() throws Exception {
        String url = jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com","baseUrl");
//        driver.navigate().to(url);
        driver.get(url);
    }

    public String retrieveTitle() {
        return driver.getTitle();
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickSearchBar(){
        driver.findElement(searchBar).click();
    }

    //There's a KVKK message on the homepage that doesn't allow any interaction without clicking it
    public void clickTamamKVKKMessage(){
        //Wait till KVKK message appears since it appears a little bit late after the page loads
        WebElement msg = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='btn btnBlack'][contains(.,'Tamam')]")));
        msg.click();
    }

    public void typeIntoSearchBar(String searchTerm){
        driver.findElement(searchBar).sendKeys(searchTerm);
    }

    public void clickSearch(){
        driver.findElement(searchIcon).click();
    }

    public void navigateToShoppingCart(){
        driver.get("https://www.n11.com/sepetim");
    }

}

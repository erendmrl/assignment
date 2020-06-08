package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.ThreadLocalRandom;


/**
 * In this class, PageFactory and @FindBy annotation is not used, By is used to locate elements
 */
public class N11SearchResultsPO {
    WebDriver driver;

    public N11SearchResultsPO(WebDriver driver) throws Exception {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    int randomNum = ThreadLocalRandom.current().nextInt(7, 24 + 1);

    By pg2Button = By.xpath("//a[@href='https://www.n11.com/arama?q=bilgisayar&pg=2'][contains(.,'2')]");
    By randomProduct = By.xpath("(//h3[@class='productName bold'])[" + randomNum + "]");
    By secondPageLoadIdentifier = By.xpath("(//span[@class='textImg btnAllShopPro'])[22]");
    By lastElementToLoad = By.xpath("(//img[@class='lazy'])[43]");


    public void clickPageTwo() throws Exception {
        Thread.sleep(500);       //page load, no problem with all threads suspended, no need to explicit wait since n11 is slow
        driver.findElement(pg2Button).click();

    }

    public boolean verifySearchPageTwo(){
        //Explicit wait until page is loaded
        WebElement secondPageLoadVerifier = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(secondPageLoadIdentifier));
        return secondPageLoadVerifier.isDisplayed();
    }

    /* The element I want to click is hidden by some other element span, standard clicking and Actions clicking didn't work therefore I used Java Script */
    public void clickRandomProduct() throws Exception{
        WebElement element = driver.findElement(randomProduct);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void waitUntilLastElementIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(lastElementToLoad));
    }

    public void waitUntilSecondPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(secondPageLoadIdentifier));
    }

}

package pageobjects;


import dataProvider.JsonFileParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testclasses.TestBase;


/**
 * In this class, PageFactory(initialized inside TestBase class) and @FindBy annotation is used
 */
public class N11LoginPO  {

    JsonFileParser jsonFileParser = new JsonFileParser();
    WebDriver driver;

    public N11LoginPO(WebDriver driver) throws Exception {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href='//www.n11.com/hesabim'][contains(.,'Eren Demirel')]")
    private WebElement loggedInName;

    public N11LoginPO() throws Exception {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail() throws Exception {
        emailInputField.sendKeys(jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "loginEmail"));
    }

    public void enterPassword() throws Exception {
        passwordInputField.sendKeys(jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "loginPassword"));
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String retreiveLoggedInName() {
        String loginName = loggedInName.getText();
        return loginName;
    }

}

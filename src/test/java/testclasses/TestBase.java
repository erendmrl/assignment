package testclasses;

import dataProvider.ConfigFileReader;
import dataProvider.JsonFileParser;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;


import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver = null;
    protected static final Logger logger = Logger.getLogger(TestBase.class);
    JsonFileParser jsonFileParser = new JsonFileParser();
    ConfigFileReader configFileReader = new ConfigFileReader();
    N11HomepagePO n11HomepagePO;
    N11LoginPO n11LoginPO;
    N11SearchResultsPO n11SearchResultsPO;
    N11ProductPO n11ProductPO;
    N11ShoppingCartPO n11ShoppingCartPO;


    @BeforeAll
    public void initialize() throws Exception {
        logger.warn("Test started.");
        switch (configFileReader.getBrowser()) {
            case ("firefox"):
                n11HomepagePO = PageFactory.initElements(driver, N11HomepagePO.class);
                System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPathFirefox());
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
                break;
            case ("chrome"):
                n11HomepagePO = PageFactory.initElements(driver, N11HomepagePO.class);
                System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
                break;
        }
    }

    @BeforeEach
    public void initializePageObjectElements() throws Exception {
        n11HomepagePO = PageFactory.initElements(driver, N11HomepagePO.class);
        n11LoginPO = PageFactory.initElements(driver, N11LoginPO.class);
        n11SearchResultsPO = PageFactory.initElements(driver, N11SearchResultsPO.class);
        n11ProductPO = PageFactory.initElements(driver, N11ProductPO.class);
        n11ShoppingCartPO = PageFactory.initElements(driver, N11ShoppingCartPO.class);
    }

    @AfterAll
    public void tearDown() throws Exception {
        logger.warn("Test finished.");
        driver.close();
    }
}

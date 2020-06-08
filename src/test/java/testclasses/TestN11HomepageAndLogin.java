package testclasses;

import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import util.ReturnHTPPResponseCode;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestN11HomepageAndLogin extends TestBase {

    @Test
    @Order(1)
    public void httpStatusCodeShouldBe200() throws Exception {
        logger.info("Checking response http status code..");
        ReturnHTPPResponseCode returnHTPPResponseCode = new ReturnHTPPResponseCode();
        try {
            Assertions.assertEquals(200, returnHTPPResponseCode.checkLink(jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "baseUrl")));
        } catch (AssertionFailedError e) {
            logger.error("Can not reach homepage: ", e);
            throw e;
        }
    }

    @Test
    @Order(2)
    public void titleShouldBe() throws Exception {
        try {
            logger.info("Navigating to n11.com homepage..");
            n11HomepagePO.navigateToHomepage();
            logger.info("n11.com homepage title: ");
            logger.info(driver.getTitle());
            Assertions.assertEquals(n11HomepagePO.homePageTitle, n11HomepagePO.retrieveTitle());    //Also since get() is used instead of navigate() to go to URL, that provides verification too
        } catch (AssertionFailedError e) {
            logger.error("Can not navigate to n11.com:", e);
            throw e;
        }
    }

    @Test
    @Order(3)
    public void shouldClickKvkkMessage() throws Exception {
        try {
            logger.info("Clicking KVKK pop-up..");
            n11HomepagePO.clickTamamKVKKMessage();
            Thread.sleep(500);      //animation is slow, no need for explicit wait since it will cause troubles in this case
        } catch (AssertionFailedError e) {
            logger.error("Can not click KVKK pop-up:", e);
            throw e;
        }
    }

    @Nested
    class N11Login {
        @Test
        @Order(4)
        public void loginN11() throws Exception {
            n11HomepagePO.clickLoginButton();
            Thread.sleep(500);
            n11LoginPO.enterEmail();
            Thread.sleep(50);
            n11LoginPO.enterPassword();
            Thread.sleep(50);
            n11LoginPO.clickLogin();
            Thread.sleep(2000);     //didn't use explicit wait since it may fail before assertion
            try {
                logger.info("Trying log in..");
                Assertions.assertTrue(driver.getTitle() == n11HomepagePO.homePageTitle);
                Assertions.assertEquals(n11LoginPO.retreiveLoggedInName(), jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "fullName"));

            } catch (AssertionFailedError e) {
                logger.error("Login failed:", e);
                throw e;
            }
        }
    }
}

package testclasses;

import dataProvider.JsonFileParser;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import util.WriteToFile;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestN11SearchAndShoppingCart extends TestBase {

    JsonFileParser jsonFileParser = new JsonFileParser();

    @Test
    @Order(1)
    public void shouldSearch() throws Exception {
        logger.info("n11.com: Initiating product search..");
        logger.info("Navigating to the homepage..");
        n11HomepagePO.navigateToHomepage();
        logger.info("Clicking KVKK message..");
        n11HomepagePO.clickTamamKVKKMessage();
        Thread.sleep(1000);
        logger.info("Clicking search bar..");
        n11HomepagePO.clickSearchBar();
        logger.info("Typing into search bar..");
        n11HomepagePO.typeIntoSearchBar("bilgisayar");
        logger.info("Clicking search..");
        n11HomepagePO.clickSearch();
        Thread.sleep(2000);     //Because page is overloaded, put before explicit wait
        n11SearchResultsPO.waitUntilLastElementIsLoaded();
    }

    @Test
    @Order(2)
    public void shouldGoToPageTwoInResultsPage() throws Exception {
        logger.info("Clicking the second page of search results.. ");
        n11SearchResultsPO.clickPageTwo();
        Thread.sleep(2000);
        n11SearchResultsPO.waitUntilSecondPageIsLoaded();
    }

    @Test
    @Order(3)
    public void verifySearchPageTwo() throws Exception {
        logger.info("Verifying the second page of search results.. ");
        try {
            Assertions.assertTrue(n11SearchResultsPO.verifySearchPageTwo());
        } catch (AssertionFailedError e) {
            logger.error("Can not go to the page 2 for the search results:", e);
            throw e;
        }
        //Title check in case page fails
        try {
            Assertions.assertEquals(jsonFileParser.parseJsonFileAndReturnRequestedDAta("n11.com", "searchResultsForbilgisayarSecondPageTitle"),
                    TestBase.driver.getTitle());
            Assertions.assertTrue(n11SearchResultsPO.verifySearchPageTwo());
        } catch (AssertionFailedError e) {
            logger.error("Can not go to the page 2 for the search results:", e);
            throw e;
        }
    }

    @Test
    @Order(4)
    public void shouldClickRandomProductOnThePageTwo() throws Exception {
        logger.info("Clicking on a product..");
        n11SearchResultsPO.clickRandomProduct();
        Thread.sleep(1000);
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class N11ShoppingCart {
        String productPriceOnProductDetailsPage = null;
        String productPriceOnShoppingCartPage = null;

        @Test
        @Order(5)
        public void addProductToTheCart() {
            WriteToFile writeToFile = new WriteToFile();
            logger.info("Adding product to the cart..");
            n11ProductPO.clickAddToTheCart();
            logger.info("Writing product info to the text file..");
            writeToFile.writeStr("[" + "Ürün Adı: " + n11ProductPO.retreiveProductName() + " || " + "Fiyatı: " + n11ProductPO.retreiveProductPrice() + "]");
            productPriceOnProductDetailsPage = n11ProductPO.retreiveProductPrice();
            logger.info("productPriceOnProductDetailsPage:");
            logger.info(productPriceOnProductDetailsPage);
        }

        @Test
        @Order(6)
        public void navigateToShoppingCart() throws InterruptedException {
            logger.info("Navigating to the shopping cart..");
            n11HomepagePO.navigateToShoppingCart();
            Thread.sleep(500);
            logger.info("Confirming misafir musteri aydinlatma metni..");
            n11ProductPO.clickTamam_MisafirMusteriAydinlatmaMetni();
            Thread.sleep(500);
        }

        @Test
        @Order(7)
        public void getProductPriceWhileOnShoppingCart() {
            logger.info("Getting tthe price of product that is on the shopping cart..");
            productPriceOnShoppingCartPage = n11ShoppingCartPO.retreiveProductPrice();
            logger.info("productPriceOnShoppingCartPage:");
            logger.info(productPriceOnShoppingCartPage);
        }

        @Test
        @Order(8)
        public void productPricesShouldBeEqual() {
            logger.info("Comparing price of product that is in product details page with the one in shopping cart..");
            try {
                Assertions.assertEquals(productPriceOnProductDetailsPage, productPriceOnShoppingCartPage);
            } catch (AssertionFailedError e) {
                logger.error("Product prices aren't the same:", e);
                throw e;
            }
        }

        @Test
        @Order(9)
        public void increaseTheQuantity() throws InterruptedException {
            logger.info("Increasing the product quantity..");
            n11ShoppingCartPO.increaseQuantity();
            Thread.sleep(500);
        }

        @Test
        @Order(10)
        public void quantityShouldBe2() {
            logger.info("Getting the quantity of product that's on the shopping cart:");
            String quantity = n11ShoppingCartPO.retrieveQuantity();
            logger.info(quantity);
            try {
                Assertions.assertEquals("Toplam 2 ürün", quantity);
            } catch (AssertionFailedError e) {
                logger.error("Product quantity was not increased:", e);
                throw e;
            }
        }

        @Test
        @Order(11)
        public void removeItemFromTheCart() {
            logger.info("Removing the item from the cart..");
            n11ShoppingCartPO.removeItem();
        }

        @Test
        @Order(12)
        public void shoppingCartShouldBeEmpty() {
            logger.info("Navigating to shopping cart..");
            n11HomepagePO.navigateToShoppingCart();
            logger.info("Verifying whether the shopping is empty or not..");
            try {
                Assertions.assertEquals(n11ShoppingCartPO.retreiveIsEmtpty(), "Sepetiniz Boş");
            } catch (AssertionFailedError e) {
                logger.error("Shopping cart is not empty:", e);
                throw e;
            }
        }
    }
}

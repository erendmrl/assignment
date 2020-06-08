package util;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;

public class ReturnHTPPResponseCode {

    public int httpResponseCodeViaPost(String url) {
        return RestAssured.post(url).statusCode();
    }

    public int checkLink(String url) throws Exception {
        int statusCode = httpResponseCodeViaPost(url);
        return statusCode;
    }
}

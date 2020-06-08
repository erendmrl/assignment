package testclasses;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Data;
import pojo.DummyRestApiExampleDotCom;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;

public class TestAPIRestAssured {

    Logger logger = Logger.getLogger(TestAPIRestAssured.class);

    @Test
    @DisplayName("addUserShouldReturn200AndShouldContainUsername, thedemosite.co.uk")
    public void addUserShouldReturn200AndShouldContainUsername() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/addauser.php").then().contentType(ContentType.HTML).extract()
                .response();
        Assertions.assertEquals(response.getStatusCode(), 200);
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        Assertions.assertEquals("Add a user - FREE PHP code and SQL", htmlPath.getString("html.head.title"));
        Assertions.assertTrue(response.getBody().asString().contains("testuser"));
    }


    @Test
    @DisplayName("ShouldReturnHttp200WhenSuccesfullLogin, thedemosite.co.uk")
    public void ShouldReturnHttp200WhenSuccesfullLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        Assertions.assertEquals(response.getStatusCode(), 200);
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        Assertions.assertEquals("Login example page to test the PHP MySQL online system", htmlPath.getString("html.head.title"));
        Assertions.assertTrue(response.getBody().asString().contains("**Successful Login**"));
    }

    @Test
    @DisplayName("shouldReturnTitleAsExpectedWhenSuccesfullLogin, thedemosite.co.uk")
    public void shouldReturnTitleAsExpectedWhenSuccesfullLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "testuser").param("password", "testpass")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        logger.info(htmlPath);
        Assertions.assertEquals("Login example page to test the PHP MySQL online system", htmlPath.getString("html.head.title"));
    }

    @Test
    @DisplayName("ShouldContainFailedWhenWrongParametersLogin, thedemosite.co.uk")
    public void ShouldContainFailedWhenWrongParametersLogin() {
        RestAssured.baseURI = "http://thedemosite.co.uk";
        Response response = given().param("username", "ag8kbDmlvf5S").param("password", "Fb609hnFkpgk6")
                .when().post("/login.php").then().contentType(ContentType.HTML).extract()
                .response();
        Assertions.assertTrue(response.getBody().asString().contains("**Failed Login**"));
    }


    @Test
    @DisplayName("shouldReturn200AndStatusSuccess, dummy.restapiexample.com")
    public void shouldReturn200AndStatusSuccess() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        Response response = doGetRequest("/api/v1/employees");
        JsonPath jsonpath = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        jsonPath();
        logger.info(jsonpath);
        Assertions.assertEquals(response.getStatusCode(), 200);
        Assertions.assertEquals("success", jsonpath.getString("status"));
    }

    @Test
    @DisplayName("shouldReturn200AndStatusSuccess, dummy.restapiexample.com")
    public void shouldReturn400BadRequest() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        Response response = given()
                .when().get("/api/v1/employee/a").then().contentType(JSON).extract().
                        response();
        logger.info(response);
        Assertions.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @DisplayName("shouldReturn200AndStatusSuccess, dummy.restapiexample.com")
    public void shouldContainAnEmployeeNamedQuinn() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        JsonPath jsonpath = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        jsonPath();
        logger.info(jsonpath);
        Assertions.assertTrue(jsonpath.getString("data.employee_name").contains("Quinn"));
    }

    @Test
    @DisplayName("shouldReturnEmployeeIdAccordingToEmployeeNameWithClassPojo, dummy.restapiexample.com")
    public void shouldReturnEmployeeIdAccordingToEmployeeNameWithClassPojo() {
        int empId = getTheIdOfEmployeeUsingClassPojo("Cedric Kelly");
        int empIDfromJsonPath = 0;
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        JsonPath jsonpath = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        jsonPath();
        List res = jsonpath.get("data.employee_salary");
        for (Object x : res) {
            empIDfromJsonPath = res.indexOf("433060");
        }
        Assertions.assertEquals(empId, empIDfromJsonPath + 1);
    }

    @Test
    @DisplayName("shouldReturnEmployeeIdAccordingToEmployeeNameWithJsonPath, dummy.restapiexample.com")
    public void shouldReturnEmployeeIdAccordingToEmployeeNameWithJsonPath() {
        int empId = getTheIdOfEmployeeUsingJsonPath("Cedric Kelly");
        int empIDfromJsonPath = 0;
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        JsonPath jsonpath = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        jsonPath();
        List res = jsonpath.get("data.employee_salary");
        for (Object x : res) {
            empIDfromJsonPath = res.indexOf("433060");
        }
        Assertions.assertEquals(empId, empIDfromJsonPath + 1);
    }


    public int getTheIdOfEmployeeUsingJsonPath(String employeeName) {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        Response response = doGetRequest("/api/v1/employees");
        JsonPath jsonpath = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        jsonPath();
        logger.info(jsonpath);
        List empName = jsonpath.get("data.employee_name");
        int id = empName.indexOf(employeeName);
        return id + 1;
    }

    public int getTheIdOfEmployeeUsingClassPojo(String employeeName) {
        int index = 0;
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        DummyRestApiExampleDotCom response = given()
                .when().get("/api/v1/employees").then().contentType(JSON).extract().
                        as(DummyRestApiExampleDotCom.class);
        for (Data j : response.getData()) {
            index++;
            if (j.getEmployee_name().equals(employeeName)) {
                break;
            }
        }
        return index;
    }

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }
}


package apitests;

import api.Content;
import api.ResponseMessage;
import api.Root;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseAPITest {

    protected Logger log = LogManager.getRootLogger();
    protected Response response;
    protected Root root;
    protected Content content;
    protected static final String filterName = "Filter" + System.currentTimeMillis();
    protected int id;
    protected String projectName = "myproject";
    protected ResponseMessage message;

    public BaseAPITest() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api/v1";
    }

    @BeforeClass(alwaysRun = true)
    public void createFilter() {
    }

    public Response sendPostRequest(Object body, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .post(endpoint);
    }

    public Response sendGetRequest(String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .get(endpoint);
    }

    public Response sendDeleteRequest(String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .delete(endpoint);
    }

    public Response sendPutRequest(Object body, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .put(endpoint);
    }

    public Response sendGetByNameRequest(String endpoint, Object name) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .queryParam("filter.eq.name", name)
                .queryParam("share", false)
                .when()
                .get(endpoint);
    }
}
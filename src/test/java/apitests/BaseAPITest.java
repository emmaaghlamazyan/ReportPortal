package apitests;

import java.util.ArrayList;

import api.Condition;
import api.Content;
import api.Order;
import api.ResponseMessage;
import api.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static utils.Serialization.buildRequest;

public class BaseAPITest {

    protected static Response response;
    protected static Root root;
    protected static Content content;
    protected static final String filterName = "Filter" + System.currentTimeMillis();
    protected static int id;
    protected static String projectName = "myproject";
    protected ResponseMessage message;

    public BaseAPITest() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api/v1";
    }

    @BeforeAll
    public static void createFilter() throws JsonProcessingException {
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition.ConditionBuilder().setCondition("cnt")
                               .setFilteringField("name")
                               .setValue("value")
                               .build());
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order.OrderBuilder().setAsc(true)
                           .setSortingColumn("startTime")
                           .build());
        root = new Root.RootBuilder().setName(filterName)
                .setDescription("description")
                .setShare(true)
                .setType("launch")
                .setConditions(conditions)
                .setOrders(orders)
                .build();
        response = sendPostRequest(buildRequest(root), "/" + projectName + "/filter");

        Root root = sendGetByNameRequest(projectName + "/filter/names", filterName).as(Root.class);
        id = root.getContent()
                .get(0)
                .getId();
    }

    public static Response sendPostRequest(Object body, String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .post(endpoint);
    }

    public Response sendGetRequest(String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .get(endpoint);
    }

    public static Response sendDeleteRequest(String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .delete(endpoint);
    }

    public static Response sendPutRequest(Object body, String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .put(endpoint);
    }

    public static Response sendGetByNameRequest(String endpoint, Object name) {
        return given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .queryParam("filter.eq.name", name)
                .queryParam("share", false)
                .when()
                .get(endpoint);
    }
}
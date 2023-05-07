package steps;

import java.util.ArrayList;

import api.Condition;
import api.Content;
import api.Order;
import api.ResponseMessage;
import api.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.Serialization.buildRequest;

public class UpdateFilterSteps {

    protected static Response response;
    protected static Root root;
    protected static Content content;
    protected static final String filterName = "Filter" + System.currentTimeMillis();
    protected static int id;
    protected static String projectName = "myproject";
    protected ResponseMessage message;

    @Given("User is logged in Report portal")
    public void userIsLoggedInReportPortalLink() {
        RestAssured.basePath = "/api/v1";
    }

    @Given("Filter is created in Report Portal")
    public void createFilter() throws JsonProcessingException {
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

    @When("^User updates filter")
    public void userUpdatesFilterWithId() {
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition.ConditionBuilder().setCondition("cnt")
                               .setFilteringField("name")
                               .setValue("value")
                               .build());
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order.OrderBuilder().setAsc(false)
                           .setSortingColumn("startTime")
                           .build());
        content = new Content.ContentBuilder().setName(filterName + "123")
                .setDescription("description")
                .setId(id)
                .setShare(true)
                .setType("Launch")
                .setConditions(conditions)
                .setOrders(orders)
                .build();
        response = sendPutRequest(content, "myproject/filter/" + id);
        message = response.as(ResponseMessage.class);
    }

    @Then("Check response message")
    public void checkResponseMessage() {
        assertThat(message.getMessage(), equalTo("User filter with ID = '" + id + "' successfully updated."));
    }

    @And("Check {int} code")
    public void checkStatusCode(int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    public static Response sendPostRequest(Object body, String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .post(endpoint);
    }

    public static Response sendGetByNameRequest(String endpoint, Object name) {
        return given().header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .queryParam("filter.eq.name", name)
                .queryParam("share", false)
                .when()
                .get(endpoint);
    }

    public static Response sendPutRequest(Object body, String endpoint) {
        return given().contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47")
                .when()
                .body(body)
                .put(endpoint);
    }
}
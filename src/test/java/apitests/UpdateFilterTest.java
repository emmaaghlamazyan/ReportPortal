package apitests;

import java.util.ArrayList;

import api.Condition;
import api.Content;
import api.Order;
import api.ResponseMessage;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testng.annotations.DataProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class UpdateFilterTest extends BaseAPITest {

    @BeforeAll
    public static void updateFilter() {
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
    }

    @Test
    public void checkStatusCodePutTest() {
        response.then()
                .statusCode(HttpStatus.SC_OK);
    }



    @Test
    public void checkResponseMessagePutTest() {
        message = response.as(ResponseMessage.class);
        assertThat(message.getMessage(), equalTo("User filter with ID = '" + id + "' successfully updated."));
    }

    @DataProvider
    public Object[][] invalidId() {
        return new Object[][]{
                {RandomUtils.nextInt()},
                {null}};
    }

    @Test(dataProvider = "invalidId")
    public void checkResponseMessagePutNegativeTest(int id) {
        message = response.as(ResponseMessage.class);
        assertThat(message.getMessage(), equalTo("User filter with ID = '" + id + "' successfully updated."));
    }
}
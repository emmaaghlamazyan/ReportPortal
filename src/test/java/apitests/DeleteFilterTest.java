package apitests;

import api.ResponseMessage;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

;

public class DeleteFilterTest extends BaseAPITest {

    @BeforeAll
    public void deleteFilter() {
        response = sendDeleteRequest("myproject/filter/" + id);
    }

    @Test
    public void checkStatusCodeDeleteTest() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkResponseMessageDeleteTest() {
         message = response.as(ResponseMessage.class);
        assertThat(
                message.getMessage(),
                equalTo("User filter with ID = '" + id + "' successfully deleted.")
        );
    }
}
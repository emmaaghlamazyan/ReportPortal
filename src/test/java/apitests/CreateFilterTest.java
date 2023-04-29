package apitests;

import api.ResponseMessage;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateFilterTest extends BaseAPITest {

    @Test
    public void checkStatusCodePostTest() {
        response.then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void checkFilterCreatedTest() {
        message = response.as(ResponseMessage.class);
        assertThat(message.getId(), equalTo(id));
    }
}
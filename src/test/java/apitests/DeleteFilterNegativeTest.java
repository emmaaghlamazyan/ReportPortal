package apitests;

import api.ResponseMessage;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.testng.annotations.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class DeleteFilterNegativeTest extends BaseAPITest {
    private static int randomId = RandomUtils.nextInt();

    @BeforeAll
    public static void deleteNonExistingFilter() {
        response = sendDeleteRequest("myproject/filter/" + randomId);
    }

    @Test
    public void checkStatusCodeDeleteNegativeTest() {
        response.then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void checkErrorMessageDeleteNegativeTest() {
        message = response.as(ResponseMessage.class);
        assertThat(message.getMessage(), equalTo("User filter with ID '" + randomId + "' not found on project '" + projectName + "'. Did you use correct User Filter ID?"));
    }
}
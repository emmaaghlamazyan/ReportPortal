package apitests;

import api.Root;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetFilterTest extends BaseAPITest {

    @ParameterizedTest
    @ValueSource(strings = {"myproject/filter"})
    public void checkStatusCodeGetTest(String endpoint) {
        response = sendGetRequest(endpoint);
        response.then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkGetFilterByNameTest() {
        Root root = sendGetByNameRequest(projectName + "/filter/names", filterName).as(Root.class);
        assertThat(root.getContent()
                           .get(0)
                           .getName(), equalTo(filterName));
    }
}
package apitests.httpclient;

import java.io.IOException;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FilterNegativeTest {

    protected static final String filterName = "Filter" + System.currentTimeMillis();
    protected static String projectName = "myproject";
    protected HttpClient httpClient;
    protected HttpPut requestPut;
    protected HttpDelete requestDelete;
    protected HttpResponse response;
    protected String endpointUrl = "http://localhost:8080/api/v1/" + projectName;
    private static int randomId = RandomUtils.nextInt();

    @Test
    public void deleteFilterTest() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        requestDelete = new HttpDelete(endpointUrl + "/filter/" + randomId);
        requestDelete.setHeader("Content-Type", "application/json");
        requestDelete.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");

        response = httpClient.execute(requestDelete);
        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void updateFilterTest() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        requestPut = new HttpPut(endpointUrl + "/filter/" + randomId);
        requestPut.setHeader("Content-Type", "application/json");
        requestPut.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");
        requestPut.setEntity(new StringEntity(
                "{\"description\":\"description\",\"owner\":null,\"share\":true,\"id\":0,\"name\":\"" + filterName + "111" + "\",\"conditions\":[{\"condition\":\"cnt\",\"filteringField\":\"name\",\"value\":\"value\"}],\"orders\":[{\"sortingColumn\":\"startTime\",\"asc\":false}],\"type\":\"Launch\"}"));
        response = httpClient.execute(requestPut);
        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }
}

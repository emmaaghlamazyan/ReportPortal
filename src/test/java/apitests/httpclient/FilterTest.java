package apitests.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FilterTest {
    protected static final String filterName = "Filter" + System.currentTimeMillis();
    protected static String projectName = "myproject";
    protected HttpClient httpClient;
    protected HttpPost requestPost;
    protected HttpGet requestGet;
    protected HttpDelete requestDelete;
    protected HttpPut requestPut;
    protected HttpResponse response;
    protected String endpointUrl = "http://localhost:8080/api/v1/" + projectName;
    protected int id;

    @BeforeClass
    public void createFilter() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        requestPost = new HttpPost(endpointUrl + "/filter");
        requestPost.setHeader("Content-Type", "application/json");
        requestPost.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");
        requestPost.setEntity(new StringEntity(
                "{\"conditions\":[" + "{\"condition\":\"cnt\",\"filteringField\":\"name\",\"value\":\"value\"}" + "],\"description\":\"description\"," + "\"name\":\"" + filterName + "\"," + "\"orders\":[{\"sortingColumn\":\"startTime\",\"asc\":true}]," + "\"share\":true,\"type\":\"launch\",\"content\":null,\"page\":null}"));
        response = httpClient.execute(requestPost);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent(); // Create an InputStream with the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) // Read line by line
        {
            sb.append(line + "\n");
        }

        id = Integer.parseInt(sb.substring(6, 8));
    }

    @Test
    public void createFilterTest() {
        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_CREATED));
    }

    @Test
    public void getFilterByNameTest() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("filter.eq.name", filterName));
        params.add(new BasicNameValuePair("share", "false"));

        String queryString = "";
        for (NameValuePair param : params) {
            if (!queryString.isEmpty()) {
                queryString += "&";
            }
            queryString += param.getName() + "=" + param.getValue();
        }

        requestGet = new HttpGet(endpointUrl + "/filter/names?" + queryString);
        requestGet.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");

        response = httpClient.execute(requestGet);

        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void deleteFilterTest() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        requestDelete = new HttpDelete(endpointUrl + "/filter/" + id);
        requestDelete.setHeader("Content-Type", "application/json");
        requestDelete.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");

        response = httpClient.execute(requestDelete);
        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void updateFilterTest() throws IOException {
        httpClient = HttpClientBuilder.create()
                .build();
        requestPut = new HttpPut(endpointUrl + "/filter/" + id);
        requestPut.setHeader("Content-Type", "application/json");
        requestPut.setHeader("Authorization", "Bearer ca0b2430-524c-48cd-8cca-765216212f47");
        requestPut.setEntity(new StringEntity(
                "{\"description\":\"description\",\"owner\":null,\"share\":true,\"id\":0,\"name\":\"" + filterName + "111" + "\",\"conditions\":[{\"condition\":\"cnt\",\"filteringField\":\"name\",\"value\":\"value\"}],\"orders\":[{\"sortingColumn\":\"startTime\",\"asc\":false}],\"type\":\"Launch\"}"));
        response = httpClient.execute(requestPut);
        assertThat(response.getStatusLine()
                           .getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
}
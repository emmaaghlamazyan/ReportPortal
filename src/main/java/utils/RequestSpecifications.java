package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpHeaders;

@UtilityClass
public class RequestSpecifications {

    public RequestSpecification requestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return requestSpecBuilder.build();
    }
}
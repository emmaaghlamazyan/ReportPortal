package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class DriverHook {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @After
    public void tearDown() {

    }
}
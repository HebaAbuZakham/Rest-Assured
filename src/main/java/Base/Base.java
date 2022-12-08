package Base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class Base {
    String X_API_KEY ="x-api-key";
    String XApiKey ="GfqP7b2I99sUMkbxGEk5Xk56RscaWRuo";
    public String baseURL(String url) {
        return RestAssured.baseURI = url;
    }

    public RequestSpecification httpRequest() {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest;
    }
}

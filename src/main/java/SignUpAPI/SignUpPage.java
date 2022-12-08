package SignUpAPI;

import Base.Base;
import io.restassured.response.Response;

public class SignUpPage extends Base {
    String url = "https://sit.maf-dev.auth0.com/dbconnections/signup";
    SignUpRequestBody signUpRequestBody = new SignUpRequestBody();

    public Response signUpPost(String userName, String password) {
        baseURL(url);
        Response signUpResponse = httpRequest().when().header("Content-Type", "application/json").body(signUpRequestBody.requestBody(userName, password)).post();
        return signUpResponse;
    }
}

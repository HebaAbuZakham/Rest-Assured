package LoginAPI;

import Base.Base;
import io.restassured.response.Response;

public class LoginPage extends Base {
    String url="https://sit.maf-dev.auth0.com/oauth/token";
    LoginRequestBody  loginRequestBody= new LoginRequestBody();
    public Response loginPost(String userName,String password){
        baseURL(url);
        Response loginResponse = httpRequest().when().header("Content-Type","application/json").body(loginRequestBody.requestBody(userName,password)).post();
        return loginResponse;
    }
}

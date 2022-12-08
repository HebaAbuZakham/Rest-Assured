import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MemberTest extends LoginRequestBody {
    String URL = "https://sit.maf-dev.auth0.com";
    private static String devUrl ="https://maf-holding-dev.apigee.net";
    SignUpRequestBody signupRequestBody = new SignUpRequestBody();
    LoginRequestBody lgoinRequest = new LoginRequestBody();
    String userName = "hiba" + Math.floor(Math.random() * 1000) + "@gmail.com";
    String password = "Test1234";
    String email;
    String FirstName;
    String tokenValue;
    private static String X_API_KEY ="x-api-key";
    String xApiKey ="GfqP7b2I99sUMkbxGEk5Xk56RscaWRuo";
    @Test(description = "check status Code and Save the email")
    public void CheckingSignUpAPI() {
        RestAssured.baseURI = URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header("Content-Type", "application/json").body(signupRequestBody.requestBody(userName, password)).post("/dbconnections/signup");
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = response.jsonPath();
        email = jsonPathEvaluators.get("email").toString();
        System.out.println(email);
        FirstName = jsonPathEvaluators.get("user_metadata.firstName").toString();
        System.out.println(FirstName);
    }

    @Test(description = "check status Code and get access Token")
    public void LoginAPI() {
        RestAssured.baseURI = URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header("Content-Type", "application/json").body(lgoinRequest.requestBody(email, password)).post("/oauth/token");
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = response.jsonPath();
        tokenValue = jsonPathEvaluators.get("token_type").toString() + "  "+jsonPathEvaluators.get("access_token").toString();
        System.out.println(tokenValue);
    }
    @Test(description = "check status Code and get access Token")
    public void checkingGetMemberDetails() {
        RestAssured.baseURI = devUrl;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header(X_API_KEY,xApiKey).header("Authorization",tokenValue).get("/v1/gravity/dk-gravity-memberdetails");
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = response.jsonPath();
        Assert.assertEquals(FirstName,jsonPathEvaluators.get("first_name").toString());
        Assert.assertEquals(email,jsonPathEvaluators.get("email_address").toString());
        Assert.assertEquals("BASE",jsonPathEvaluators.get("tier").toString());
        Assert.assertEquals("false",jsonPathEvaluators.get("extra_data.sw_onboarding").toString());
    }

}


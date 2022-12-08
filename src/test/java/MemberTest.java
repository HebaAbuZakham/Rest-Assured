import Base.Base;
import LoginAPI.LoginPage;
import MemebrAPI.MemberPage;
import SignUpAPI.SignUpPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MemberTest extends Base {

    String userName = "hiba" + Math.floor(Math.random() * 1000) + "@gmail.com";
    String password = "Test1234";
    String email;
    String firstName;
    String tokenValue;
    SignUpPage signUpPage = new SignUpPage(); Response signUpResponse;
    LoginPage loginPage = new LoginPage(); Response loginResponse;
    MemberPage memberPage = new MemberPage(); Response memberDetails;
    @Test(description = "check status Code and Save the email")
    public void checkCreateNewUser() {

       signUpResponse = signUpPage.signUpPost(userName,password);
        Assert.assertEquals(signUpResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = signUpResponse.jsonPath();
        email = jsonPathEvaluators.get("email").toString();
        System.out.println(email);
        firstName = jsonPathEvaluators.get("user_metadata.firstName").toString();
        System.out.println(firstName);
    }

    @Test(description = "check status Code and get access Token")
    public void checkingDLogin() {
        loginResponse =loginPage.loginPost(userName,password);
        loginResponse.prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = loginResponse.jsonPath();
        tokenValue = jsonPathEvaluators.get("token_type").toString() + "  "+jsonPathEvaluators.get("access_token").toString();
        System.out.println(tokenValue);
    }
    @Test(description = "check status Code and get access Token")
    public void checkingGetMemberDetails() {
        memberDetails = memberPage.getMemberDetails(tokenValue);
        memberDetails.prettyPrint();
        Assert.assertEquals(memberDetails.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = memberDetails.jsonPath();
        Assert.assertEquals(firstName,jsonPathEvaluators.get("first_name").toString());
        Assert.assertEquals(email,jsonPathEvaluators.get("email_address").toString());
        Assert.assertEquals("BASE",jsonPathEvaluators.get("tier").toString());
        Assert.assertEquals("false",jsonPathEvaluators.get("extra_data.sw_onboarding").toString());
    }

}
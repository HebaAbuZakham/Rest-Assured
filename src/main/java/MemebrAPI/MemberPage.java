package MemebrAPI;
import Base.Base;
import LoginAPI.LoginRequestBody;
import SignUpAPI.SignUpRequestBody;
import io.restassured.response.Response;
public class MemberPage extends Base {
    String url = "https://maf-holding-dev.apigee.net/v1/gravity/dk-gravity-memberdetails";
    public Response getMemberDetails(String token) {
        baseURL(url);
        Response getMemberDetailsResponse = httpRequest().when().headers(getX_API_KEY(),getXApiKey(),"Authorization",token).get();
        return getMemberDetailsResponse;
    }
}

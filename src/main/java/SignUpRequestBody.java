public class SignUpRequestBody {
    public static String requestBody(String userName,String password){
        String request="{\n" +
                "    \"password\": \""+password+"\",\n" +
                "    \"username\": \"Hiba Abu Zakham\",\n" +
                "    \"user_metadata\": {\n" +
                "        \"firstName\": \"Hiba\",\n" +
                "        \"mobile\": \"+962797531543\",\n" +
                "        \"lastName\": \"AbuZakham\",\n" +
                "        \"dateOfBirth\": \"1997-06-05\",\n" +
                "        \"title\": \"Miss\",\n" +
                "        \"share_tc\": \"Yes\"\n" +
                "    },\n" +
                "    \"email\": \""+userName+"\",\n" +
                "    \"connection\": \"Username-Password-Authentication\",\n" +
                "    \"client_id\": \"2IGVUsTpid0NZhjaMUe77k6wERpZ0pbG\"\n" +
                "}";
        return request;
    }
}

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JiraAPICreateIssue {

    @Test
    public void createIssueAndCheckContent() {
        // Create issue
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "   \"fields\":{\n" +
                                "      \"summary\":\"Test ticket\",\n" +
                                "      \"issuetype\":{\n" +
                                "         \"id\":\"10105\",\n" +
                                "         \"name\":\"test\"\n" +
                                "      },\n" +
                                "      \"project\":{\n" +
                                "         \"id\":\"10508\"\n" +
                                "      },\n" +
                                "   \"reporter\": {\n" +
                                "      \"name\": \"webinar5\"\n" +
                                "    }\n" +
                                "   }\n" +
                                "}")
                        .when().post("https://jira.hillel.it/rest/api/2/issue")
                        .then().contentType(ContentType.JSON).statusCode(201)
                        .extract().response();
        response.print();

        String ticket = response.path("id");
        System.out.println(ticket);

        //Get issue and check
        Response getIssue =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://jira.hillel.it/rest/api/2/issue/" + ticket)
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .extract().response();
        getIssue.print();
        assertEquals(getIssue.path("fields.summary"), "Test ticket");
        assertEquals(getIssue.path("fields.creator.name"), "webinar5");


        //Add comment

//        Response addComment =
//
//                given()
//                        .auth().preemptive().basic("webinar5", "webinar5")
//                        .contentType(ContentType.JSON)
//                        .when()
//                        .post(String.format(APIPathes.comment, ticket))
//                        .get("http://jira.hillel.it/rest/api/2/issue/WEBINAR-13727")
//                        .then().contentType(ContentType.JSON)
//                        .then().statusCode(201)
//                        .extract().response();
//addComment.print();

    }
}

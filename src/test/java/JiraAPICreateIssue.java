import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JiraAPICreateIssue {

    @Test
    public void createIssueAndCheckContent() {

        JSONObject newIssueJSON = new JSONObject();
        JSONObject fields = new JSONObject();
        fields.put("summary", "Test ticket");
        JSONObject issueType = new JSONObject();
        issueType.put("id", "10105");
        JSONObject project = new JSONObject();
        project.put("id", "10508");
        JSONObject reporter = new JSONObject();
        reporter.put("name", "webinar5");

        fields.put("issuetype", issueType);
        fields.put("project", project);
        fields.put("reporter", reporter);

        newIssueJSON.put("fields", fields);
        // Create issue
        Response response = JiraAPISteps.createIssue(newIssueJSON.toJSONString());
        response.print();
        String ticket = response.path("id");
        System.out.println(ticket);


        //Get issue and check
        Response getIssue = JiraAPISteps.getIssue(ticket);

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

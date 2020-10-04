import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JiraAPIGetIssue {
    @Test

    public void getIssue() {

        Response response =

                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://jira.hillel.it/rest/api/2/issue/WEBINAR-13727")
                        .then().contentType(ContentType.JSON)
                        .extract().response();

        assertEquals(response.statusCode(), 200);
        assertEquals("WEBINAR-13727", response.path("key"));
    }
}

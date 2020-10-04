import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;
import utils.APIPathes;
import utils.Credentials;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JiraAPIGetIssue {
    @Test

    public void getIssue() {

        Response response =

                given()
                        .auth().preemptive().basic(Credentials.username, Credentials.password)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(APIPathes.issue2)
                        .then().contentType(ContentType.JSON)
                        .extract().response();

        assertEquals(response.statusCode(), 200);
        assertEquals("WEBINAR-13727", response.path("key"));
    }
}

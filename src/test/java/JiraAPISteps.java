import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class JiraAPISteps {

    public static String newIssue;

    @Step("create issue")
    public static Response createIssue(String newIssue) {
        Response response =

                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .body(newIssue)
                        .when().post("https://jira.hillel.it/rest/api/2/issue")
                        .then().contentType(ContentType.JSON).statusCode(201)
                        .extract().response();
        return response;
    }

    @Step("get issue")
    public static Response getIssue(String ticket) {
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://jira.hillel.it/rest/api/2/issue/" + ticket)
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .extract().response();

        return response;
    }

    @Step("add comment")
    public static Response addComment() {
        Response response =

                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "   \"update\": {\n" +
                                "      \"comment\": [\n" +
                                "         {\n" +
                                "            \"add\": {\n" +
                                "               \"body\": \"My comment\"\n" +
                                "            }\n" +
                                "         }\n" +
                                "      ]\n" +
                                "   }\n" +
                                "}")
                        .when().put(APIPathes.issue2)
                        .then().statusCode(204)
                        .extract().response();

        return response;

    }

    @Step("check created comment")
    public static Response checkIfCommentCreated() {
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(APIPathes.issue2)
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .extract().response();
        return response;
    }

    @Step("delete comment")
    public static Response deleteComment(String comment) {
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(String.format(APIPathes.comment, APIPathes.issue2) + comment)
                        .then()
                        .statusCode(204)
                        .extract().response();
        return response;
    }

    @Step("check deleted comment")
    public static Response checkDeletedComment() {
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(APIPathes.issue2)
                        .then()
                        .and().time(lessThan(1000L)).body(JiraAPIAddAndDeleteComment.comment, equalTo(null))
                        .statusCode(200)
                        .extract().response();

        return response;
    }
}




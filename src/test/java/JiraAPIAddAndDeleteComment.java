import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class JiraAPIAddAndDeleteComment {

    public static String comment;
    public static String ticket;

    @Test
    public void AddDeleteComment() {

        //Add comment
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
        response.print();
    }

    @Test
    public void checkCreatedComment() {
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
        comment = response.path("fields.comment.comments[0].id");
        ticket = response.path("fields.issuetype.id");
        System.out.println(comment);
        System.out.println(ticket);
    }

    @Test
    public void deleteComment() {
        Response response =
                given()
                        .auth().preemptive().basic("webinar5", "webinar5")
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(String.format(APIPathes.comment, APIPathes.issue2) + comment)
                        .then()
                        .statusCode(204)
                        .extract().response();
        response.print();
    }

    @Test
    public void checkDeletedComment() {
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
        response.print();
    }
}
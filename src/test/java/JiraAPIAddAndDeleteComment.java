import io.restassured.response.Response;
import org.testng.annotations.Test;


public class JiraAPIAddAndDeleteComment {

    public static String comment;
    public static String ticket;

    @Test
    public void AddDeleteComment() {

        //Add comment
        Response response = JiraAPISteps.addComment();
        response.print();
    }

    @Test
    public void checkCreatedComment() {
        Response response = JiraAPISteps.checkIfCommentCreated();
        comment = response.path("fields.comment.comments[0].id");
        ticket = response.path("fields.issuetype.id");
        System.out.println(comment);
        System.out.println(ticket);
    }

    @Test
    public void deleteComment() {
        Response response = JiraAPISteps.deleteComment(comment);
        response.print();
    }

    @Test
    public void checkDeletedComment() {
        Response response = JiraAPISteps.checkDeletedComment();
        response.print();
    }
}
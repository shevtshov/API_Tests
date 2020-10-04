package utils;

public interface APIPathes {
    String baseURL = "https://jira.hillel.it/";
    String issue = baseURL + "rest/api/2/issue/";
    String issue2 = baseURL + issue + "WEBINAR-13798";
    String comment = "%s/comment/";
}
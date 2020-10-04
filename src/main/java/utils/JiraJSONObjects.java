package utils;

import org.json.simple.JSONObject;

public class JiraJSONObjects {

    public static String newIssueJSON() {
//public static void main(String[] args) {


        JSONObject newIssueJSON = new JSONObject();
        JSONObject fields = new JSONObject();
        fields.put("summary", "Test ticket");
        JSONObject issueType = new JSONObject();
        issueType.put("id", "10107");
        JSONObject project = new JSONObject();
        project.put("id", "11400");
        JSONObject reporter = new JSONObject();
        reporter.put("name", "webinar5");

        fields.put("issueType", issueType);
        fields.put("project", project);
        fields.put("reporter", reporter);

        newIssueJSON.put("fields", fields);
        return newIssueJSON.toJSONString();
//    System.out.println(newIssueJSON.toJSONString());
    }
}

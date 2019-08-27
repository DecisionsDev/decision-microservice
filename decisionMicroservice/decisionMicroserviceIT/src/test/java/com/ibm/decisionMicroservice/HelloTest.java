package com.ibm.decisionMicroservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloTest {
    private String request = "{\n" +
            "  \"__DecisionID__\": \"letest\",\n" +
            "  \"decisionID_\": \"dddd\"\n" +
            "}";

    private String expectedResponse = "{\n" +
            "  \"__DecisionID__\": \"letest\"\n" +
            "}";

    @Test
    public void helloWorldTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Content content = Request.Post(ServiceAddr.HELLO_PATH)
                .bodyString(request, ContentType.APPLICATION_JSON)
                .addHeader("Accept","application/json")
                .execute()
                .returnContent();

        JsonNode responseNode = mapper.readTree(content.asString());
        JsonNode expectedResponseNode = mapper.readTree(expectedResponse);

        assertEquals(expectedResponseNode,responseNode);
    }
}

package com.ibm.decisionMicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;


public class MiniloanTest {
    private static String host;

    public static void initHost(){
        MiniloanTest.host = System.getProperty(ServiceAddr.ENVHOSTKEY);
    }

  private String request =  "{" +
          "  \"__DecisionID__\": \"string\"," +
          "  \"borrower\": {\n    \"creditScore\": 0," +
          "   \"name\": \"string\"," +
          "    \"yearlyIncome\": 0\n  }," +
          "  \"decisionID_\": \"string\"," +
          "  \"loan\": {\n    \"amount\": 0," +
          "    \"approved\": true,\n    \"duration\": 0," +
          "    \"messages\": [\n      \"string\"\n    ]," +
          "    \"yearlyInterestRate\": 0," +
          "    \"yearlyRepayment\": 0" +
          "  }" +
          "}";

  private String expectedResponse =   "{" +
          "  \"__DecisionID__\": \"string\"," +
          "  \"loan\": {\n    \"amount\": 0," +
          "    \"duration\": 0," +
          "    \"yearlyInterestRate\": 0.0," +
          "    \"yearlyRepayment\": 0," +
          "    \"approved\": false," +
          "    \"messages\": [" +
          "      \"string\"," +
          "      \"Credit score below 200\"" +
          "    ]," +
          "    \"approvalStatus\": \"false [string, Credit score below 200]\"" +
          "  }" +
          "}";


    @Test
    public void miniloanAnswerTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Content content = Request.Post(ServiceAddr.MINILOAN_PATH)
                .bodyString(request, ContentType.APPLICATION_JSON)
                .addHeader("Accept","application/json")
                .execute()
                .returnContent();

        JsonNode responseNode = mapper.readTree(content.asString());
        JsonNode expectedResponseNode = mapper.readTree(expectedResponse);

        assertEquals(expectedResponseNode,responseNode);
    }

    @Test
    public void miniloanErrorTest() throws Exception{
        HttpResponse response = Request.Post(ServiceAddr.MINILOAN_PATH)
                .bodyString("{}", ContentType.APPLICATION_JSON)
                .addHeader("Accept","application/json")
                .execute()
                .returnResponse();

        assertEquals(response.getStatusLine().getStatusCode(),500);
    }
}

package com.ibm.decisionMicroservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipmentTest {
    private String request = "{\n" +
            "  \"Customer\": \"Bronze\",\n" +
            "  \"__DecisionID__\": \"string\",\n" +
            "  \"Shipment\": {\n" +
            "    \"weight\": 10517320,\n" +
            "    \"size\": \"Letter\"\n" +
            "  },\n" +
            "  \"Method\": {\n" +
            "    \"pickup\": \"Pickup\",\n" +
            "    \"transport\": \"Air\",\n" +
            "    \"nextDay\": true\n" +
            "  },\n" +
            "  \"Distance\": 10517320\n" +
            "}";

    private String expectedAnswer = "{\n" +
            "  \"__DecisionID__\": \"string\",\n" +
            "  \"Price\": 4.1\n" +
            "}";

    @Test
    public void shipmentAnswerTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Content content = Request.Post(ServiceAddr.SHIPMENT_PATH)
                .addHeader("Accept","application/json")
                .bodyString(request, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent();

        JsonNode responseNode = mapper.readTree(content.asString());
        JsonNode expectedResponseNode = mapper.readTree(expectedAnswer);

        assertEquals(responseNode,expectedResponseNode);
    }

    @Test
    public void shipmentErrorTest() throws Exception{
        HttpResponse response = Request.Post(ServiceAddr.SHIPMENT_PATH)
                .bodyString("{}", ContentType.APPLICATION_JSON)
                .addHeader("Accept","application/json")
                .execute()
                .returnResponse();

        assertEquals(response.getStatusLine().getStatusCode(),200);
    }
}

package com.ibm.decisionMicroservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class LoanScoreTest {

    private String request = "{\n" +
            "  \"loan\": {\n" +
            "    \"numberOfMonthlyPayments\": 3,\n" +
            "    \"startDate\": \"2006-08-19T17:27:14.000Z\",\n" +
            "    \"amount\": 3,\n" +
            "    \"loanToValue\": 10517320\n" +
            "  },\n" +
            "  \"__DecisionID__\": \"string\",\n" +
            "  \"borrower\": {\n" +
            "    \"firstName\": \"string\",\n" +
            "    \"lastName\": \"string\",\n" +
            "    \"birth\": \"2008-09-29T01:49:45.000Z\",\n" +
            "    \"SSN\": {\n" +
            "      \"areaNumber\": \"string\",\n" +
            "      \"groupCode\": \"string\",\n" +
            "      \"serialNumber\": \"string\"\n" +
            "    },\n" +
            "    \"yearlyIncome\": 3,\n" +
            "    \"zipCode\": \"string\",\n" +
            "    \"creditScore\": 3,\n" +
            "    \"spouse\": {\n" +
            "      \"firstName\": null,\n" +
            "      \"lastName\": null,\n" +
            "      \"birth\": \"2019-04-05T13:31:46.858Z\",\n" +
            "      \"SSN\": {\n" +
            "        \"areaNumber\": \"\",\n" +
            "        \"groupCode\": \"\",\n" +
            "        \"serialNumber\": \"\"\n" +
            "      },\n" +
            "      \"yearlyIncome\": 0,\n" +
            "      \"zipCode\": null,\n" +
            "      \"creditScore\": 0,\n" +
            "      \"spouse\": null,\n" +
            "      \"latestBankruptcy\": {\n" +
            "        \"date\": null,\n" +
            "        \"chapter\": 0,\n" +
            "        \"reason\": null\n" +
            "      }\n" +
            "    },\n" +
            "    \"latestBankruptcy\": {\n" +
            "      \"date\": \"2014-09-18T23:18:33.000Z\",\n" +
            "      \"chapter\": 3,\n" +
            "      \"reason\": \"string\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    private String expectedResponse = "{\"report\":{\"borrower\":{\"firstName\":\"string\",\"lastName\":\"string\",\"birth\":\"2008-09-29T01:49:45.000Z\",\"yearlyIncome\":3,\"zipCode\":\"string\",\"creditScore\":3,\"spouse\":{\"birth\":\"2019-04-05T13:31:46.858Z\",\"yearlyIncome\":0,\"creditScore\":0,\"latestBankruptcy\":{\"chapter\":0},\"ssn\":{\"areaNumber\":\"\",\"groupCode\":\"\",\"serialNumber\":\"\",\"fullNumber\":\"--\",\"digits\":0},\"birthDate\":\"2019-04-05T13:31:46.858Z\",\"latestBankruptcyChapter\":0,\"ssncode\":\"--\"},\"latestBankruptcy\":{\"date\":\"2014-09-18T23:18:33.000Z\",\"chapter\":3,\"reason\":\"string\"},\"ssn\":{\"areaNumber\":\"\",\"groupCode\":\"\",\"serialNumber\":\"\",\"fullNumber\":\"--\",\"digits\":0},\"birthDate\":\"2008-09-29T01:49:45.000Z\",\"latestBankruptcyDate\":\"2014-09-18T23:18:33.000Z\",\"latestBankruptcyReason\":\"string\",\"latestBankruptcyChapter\":3,\"ssncode\":\"--\"},\"loan\":{\"numberOfMonthlyPayments\":3,\"startDate\":\"2006-08-19T17:27:14.000Z\",\"amount\":3,\"loanToValue\":1.051732E7,\"duration\":1},\"validData\":false,\"insuranceRequired\":false,\"insuranceRate\":0.0,\"approved\":false,\"messages\":[\"The borrower's SSN should be formatted with 3-2-4 digits\",\"The borrower's Zip Code should have 5 digits\"],\"yearlyInterestRate\":0.0,\"monthlyRepayment\":0.0,\"message\":\"The borrower's SSN should be formatted with 3-2-4 digits\\nThe borrower's Zip Code should have 5 digits\",\"insurance\":\"none\",\"yearlyRepayment\":0.0},\"__DecisionID__\":\"string\"}";
    @Test
    public void loanScoreTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Content content = Request.Post(ServiceAddr.LOANSCORE_PATH)
                .bodyString(request, ContentType.APPLICATION_JSON)
                .addHeader("Accept","application/json")
                .execute()
                .returnContent();

        JsonNode responseNode = mapper.readTree(content.asString());
        JsonNode expectedResponseNode = mapper.readTree(expectedResponse);

        assertEquals(expectedResponseNode,responseNode);
    }
}

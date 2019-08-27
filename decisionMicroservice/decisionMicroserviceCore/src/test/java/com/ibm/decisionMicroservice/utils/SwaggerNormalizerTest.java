package com.ibm.decisionMicroservice.utils;

import io.swagger.models.ModelImpl;
import io.swagger.models.Swagger;
import io.swagger.models.properties.RefProperty;
import io.swagger.parser.SwaggerParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SwaggerNormalizerTest {
    private static final String MINI_PATH = "/MiniloanruleappMiniloanrulesDecisionService.json";
    private static final String SHIPMENT_PATH = "/Shipment_Pricing_RuleAppShipment_PricingDecisionService.json";

    private Swagger miniloanSwagger;
    private Swagger shipmentSwagger;

    @Before
    public void initSwagger() throws Exception{
        this.miniloanSwagger = this.getMiniloanSwagger();
        this.shipmentSwagger = this.getShipmentSwagger();
    }

    @Test
    public void noChangeTest() throws IOException{
        SwaggerNormalizer normalizer = new SwaggerNormalizer();
        normalizer.normalizeEnumeration(this.miniloanSwagger);
        assertEquals(this.miniloanSwagger,this.getMiniloanSwagger());
    }

    @Test
    public void removeEnumTest() throws IOException{
        SwaggerNormalizer normalizer = new SwaggerNormalizer();
        normalizer.normalizeEnumeration(this.shipmentSwagger);

        assertTrue(this.shipmentSwagger.getDefinitions().get("Customer") instanceof ModelImpl);
        assertTrue(this.shipmentSwagger.getDefinitions().get("Request").getProperties().get("Customer") instanceof RefProperty);
    }

    private Swagger getShipmentSwagger() throws IOException{
        return this.getSwagger(SHIPMENT_PATH);
    }

    private Swagger getMiniloanSwagger() throws IOException {
        return this.getSwagger(MINI_PATH);
    }

    private Swagger getSwagger(String filePath) throws IOException{
        String swagger = new String(
                Files.readAllBytes(Paths.get(
                        this.getClass().getResource(filePath)
                                .getFile())));
        return new SwaggerParser().parse(swagger);
    }
}

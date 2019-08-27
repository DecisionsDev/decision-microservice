package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SwaggerNormalizerProcessorTest {
    private Processor swaggerNormalizer;
    private GeneratorOptions options;
    private Swagger swaggerMiniloan;
    private Swagger swaggerShip;

    @Before
    public void init() throws Exception{
        this.swaggerNormalizer = new SwaggerNormalizerProcessor();
        options = new GeneratorOptions();
        swaggerNormalizer.setOptions(options);

        String swaggerMiniPath = this.getClass().getResource("/MiniloanruleappMiniloanrulesDecisionService.json").getPath();
        swaggerMiniloan = new SwaggerParser().parse(new String(Files.readAllBytes(Paths.get(swaggerMiniPath))));

        String swaggerShipPath = this.getClass().getResource("/Shipment_Pricing_RuleAppShipment_PricingDecisionService.json").getPath();
        swaggerShip = new SwaggerParser().parse(new String(Files.readAllBytes(Paths.get(swaggerShipPath))));

    }

    @Test
    public void normalizerTest() throws Exception{
        this.options.setSwagger(this.swaggerMiniloan);
        swaggerNormalizer.process();

        String swaggerPath = this.getClass().getResource("/MiniloanruleappMiniloanrulesDecisionService.json").getPath();
        Swagger expected = new SwaggerParser().parse(new String(Files.readAllBytes(Paths.get(swaggerPath))));

        assertEquals(expected,this.options.getSwagger());
    }

    @Test
    public void normalizerShipmentTest() throws Exception{
        this.options.setSwagger(this.swaggerShip);
        swaggerNormalizer.process();

        String swaggerPath = this.getClass().getResource("/Shipment_Pricing_RuleAppShipment_PricingDecisionService.json").getPath();
        Swagger expected = new SwaggerParser().parse(new String(Files.readAllBytes(Paths.get(swaggerPath))));

        assertNotEquals(expected,this.options.getSwagger());
    }

    @Test
    public void nullTest(){
        swaggerNormalizer.process();
        assertNull(this.options.getSwagger());
    }

    @Test
    public void emptyOption(){
        this.swaggerNormalizer.setOptions(new GeneratorOptions());
        this.swaggerNormalizer.process();
    }



}
